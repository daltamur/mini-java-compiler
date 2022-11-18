package codeGen
import AST_Grammar.{addExpression, andExpression, arrayAssignStatement, arrayIndexExpression, arrayLengthExpression, assignStatement, blockStatement, booleanExpression, characterExpression, compExpression, dataType, expression, expressionTail, expressionValue, goal, identifier, identifierExpression, ifStatement, integerExpression, klass, mainClass, method, methodFunctionCallExpression, multiplyExpression, negatedExpression, newArrayExpression, newClassInstanceExpression, operation, parenthesizedExpression, printStatement, subtractExpression, thisExpression, variableDecs, whileStatement}
import org.objectweb.asm
import org.objectweb.asm.{ClassWriter, Label, MethodVisitor, Opcodes}

import java.io.FileOutputStream
import java.nio.file.{Files, Paths}
import scala.collection.mutable.ListBuffer

//9 more things to implement, let's pray to god it works
class codeGenerator extends AST_Grammar.ASTVisitor [MethodVisitor, Unit]{
  private var curMethodParamAmount: Integer = -1
  private var curClassName: String = ""

  override def visitCompExpression(expression: compExpression, a: MethodVisitor): Unit = {
    visitBaseExpression(expression.value, a)
    var curCompTail = expression.optVal
    while(curCompTail.isDefined){
      visitBaseExpression(curCompTail.get.value, a)
      //comparison goes here I think?
      curCompTail = curCompTail.get.optVal
    }
  }

  override def visitAndExpression(curType: Unit, curExpression: andExpression, a: MethodVisitor): Unit = {
    //this methodology is pulled almost directly from an observed set of instructions from when I compiled the expression (a&&b&&true):
    //    L5
    //    LINENUMBER 16 L5
    //    GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
    //    ILOAD 3
    //    IFEQ L6
    //    ILOAD 4
    //    IFEQ L6
    //    ILOAD 5
    //    IFEQ L6
    //    ICONST_1
    //    GOTO L7
    //   L6
    //   FRAME FULL [codeGenTestOtherClass I I I I I] [java/io/PrintStream]
    //    ICONST_0
    //   L7
    //   FRAME FULL [codeGenTestOtherClass I I I I I] [java/io/PrintStream I]
    //    INVOKEVIRTUAL java/io/PrintStream.println (Z)V
    //everytime we visit a new value in the AND instruction, we have to do the IFEQ opcode and jump to a failed state
    val andPass = new Label()
    val andFail = new Label()
    var curNextAnd: Option[andExpression] = None
    a.visitJumpInsn(Opcodes.IFEQ, andFail)
    //and operation goes here???
    curNextAnd = Some(curExpression)
    while(curNextAnd.isDefined){
      visitCompExpression(curNextAnd.get.leftVal.get.leftVal, a)
      a.visitJumpInsn(Opcodes.IFEQ, andFail)
      curNextAnd = curNextAnd.get.leftVal.get.rightVal
    }
    a.visitInsn(Opcodes.ICONST_1)
    a.visitJumpInsn(Opcodes.GOTO, andPass)
    a.visitLabel(andFail)
    a.visitInsn(Opcodes.ICONST_0)
    a.visitLabel(andPass)
  }

  def generateConstructor(cw: ClassWriter, parent: String): Unit = {
    val constructorMethodVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null)

    constructorMethodVisitor.visitCode()
    constructorMethodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
    constructorMethodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, parent, "<init>", "()V", false)
    constructorMethodVisitor.visitInsn(Opcodes.RETURN)
    constructorMethodVisitor.visitMaxs(-1,-1)
    constructorMethodVisitor.visitEnd()
  }

  def visitGoal(goal: goal, sourceName: String): Unit = {
    //first make the main class
    val cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES)
    val className = goal.main.className.name
    cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className, null, "java/lang/Object", null)
    generateConstructor(cw, "java/lang/Object")
    cw.visitSource(sourceName, null)
    //make the main method
    val mmw = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
    mmw.visitCode()

    visit(goal.main.body, mmw)

    mmw.visitInsn(Opcodes.RETURN)
    mmw.visitEnd()
    mmw.visitMaxs(-1, -1)
    //clos the class writer
    cw.visitEnd()
    //write class to file
    Files.createDirectories(Paths.get("compiledPrograms/" + className))
    val fos = new FileOutputStream(f"compiledPrograms/$className/$className.class")
    fos.write(cw.toByteArray)
    fos.close()

    //then make all the other classes
    for (curClass <- goal.classes){
      curClassName = curClass.get.className.name
      val cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES)
      val className = curClass.get.className.name
      val parentClass = curClass.get.extendedClassName.orNull
      if(parentClass == null){
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className, null, "java/lang/Object", null)
        generateConstructor(cw, "java/lang/Object")
      }else {
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className, null, parentClass.name, null)
        generateConstructor(cw, parentClass.name)
      }
      cw.visitSource(sourceName, null)

      for(variable <- curClass.get.variables){
        cw.visitField(Opcodes.ACC_PUBLIC, variable.get.name.name, convertToASMType(AST_Grammar.getVarType(variable.get.typeval)), null, null)
      }

      for(curMethod <- curClass.get.methods) {
        //make the signature
        var methodSignature: String = "("
        for (param <- curMethod.get.params)
          methodSignature = methodSignature.concat(convertToASMType(AST_Grammar.getVarType(param._1)))

        methodSignature = methodSignature.concat(")")


        methodSignature = methodSignature.concat(convertToASMType(AST_Grammar.getVarType(curMethod.get.returnType)))

        if (methodSignature.equals("()")) {
          methodSignature = null
        }

        println(methodSignature)
        val mmw = cw.visitMethod(Opcodes.ACC_PUBLIC, curMethod.get.methodName.name, methodSignature, null, null)
        mmw.visitCode()
        val methodStartLabel = new Label()
        val methodEndLabel = new Label()
        curMethodParamAmount = curMethod.get.params.length
        for ((variable, index) <- curMethod.get.variables.zipWithIndex) {
          println(convertToASMType(AST_Grammar.getVarType(variable.get.typeval)))
          //println("Var Index: " + index)
          val valIndex= index+curMethodParamAmount+1
          mmw.visitLocalVariable("var"+valIndex, convertToASMType(AST_Grammar.getVarType(variable.get.typeval)), null, methodStartLabel, methodEndLabel, index+curMethodParamAmount+1)
        }
        //^declared, but won't show up in the bytecode until we implement EVERYTHING

        mmw.visitLabel(methodStartLabel)
        //visit statements goes here
        for(statement<-curMethod.get.statements){
          visit(statement.get, mmw)
        }


        //return statement goes here
        visit(curMethod.get.returnVal, mmw)
        mmw.visitInsn(getReturnInsn(AST_Grammar.getVarType(curMethod.get.returnType)))
        mmw.visitLabel(methodEndLabel)
        mmw.visitMaxs(ClassWriter.COMPUTE_MAXS, ClassWriter.COMPUTE_MAXS)
        mmw.visitEnd()
      }
      cw.visitEnd()
      val fos = new FileOutputStream(f"compiledPrograms/${goal.main.className.name}/$className.class")
      fos.write(cw.toByteArray)
      fos.close()
    }
  }

  override def visitIdentifier(identifier: identifier, a: MethodVisitor): Unit = {
    if (identifier.isLocal && identifier.isParameter) {
      //we load the identifier as a method parameter
      a.visitVarInsn(getLoadInsn(identifier.variableType), identifier.paramIndex.get)
    } else if (identifier.isLocal) {
      //we load the identifier as a value declared within the method
      //nearly the same as the previous case, we just need to add the offset of how many params there are in the current method
      a.visitVarInsn(getLoadInsn(identifier.variableType), identifier.paramIndex.get + curMethodParamAmount)
    } else {
      //we load the identifier as a class instance variable

      //first load in the current class variable
      a.visitIntInsn(Opcodes.ALOAD, 0)
      a.visitFieldInsn(Opcodes.GETFIELD, curClassName, identifier.name, convertToASMType(identifier.variableType))
    }
  }

  override def visitBlockStatement(statement: blockStatement, a: MethodVisitor): Unit = {
    for(curStatement <- statement.statements){
      visit(curStatement, a)
    }
  }

  override def visitIfStatement(statement: ifStatement, a: MethodVisitor): Unit = super.visitIfStatement(statement, a)

  override def visitWhileStatement(statement: whileStatement, a: MethodVisitor): Unit = super.visitWhileStatement(statement, a)

  override def visitPrintStatement(statement: printStatement, a: MethodVisitor): Unit = {
    a.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
    visit(statement.value, a)
    //we need to type check the expression, it could be multiple things
    if(statement.value.typeValue.get == AST_Grammar.characterType()){
      a.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(C)V", false)
    }else{
      a.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false)
    }
  }

  override def visitAssignStatement(statement: assignStatement, a: MethodVisitor): Unit = {
    //the order for the assign statement is as such:
    //push the value that is getting assigned to the variable
    //store the value at whatever index the identifier is at
    //this is nearly identical to the visitIdentifierExpression method except we are using the store opcode now
    visit(statement.value, a)
    if(statement.idVal.isLocal && statement.idVal.isParameter){
      a.visitIntInsn(getStoreInsn(statement.idVal.variableType), statement.idVal.paramIndex.get)
    }else if (statement.idVal.isLocal){
      a.visitIntInsn(getStoreInsn(statement.idVal.variableType),statement.idVal.paramIndex.get+curMethodParamAmount)
    }else{
      a.visitIntInsn(Opcodes.ALOAD, 0)
      a.visitFieldInsn(Opcodes.PUTFIELD, AST_Grammar.varTypeToString(statement.idVal.variableType), statement.idVal.name, convertToASMType(statement.idVal.variableType))
    }

  }

  override def visitArrayAssignStatement(statement: arrayAssignStatement, a: MethodVisitor): Unit = super.visitArrayAssignStatement(statement, a)

  override def visitThisExpression(expression: thisExpression, a: MethodVisitor): Unit = {
    //load this
    a.visitIntInsn(Opcodes.ALOAD, 0)
  }

  override def visitBooleanExpression(expression: booleanExpression, a: MethodVisitor): Unit = {
    if(expression.value){
      a.visitInsn(Opcodes.ICONST_1)
    }else{
      a.visitInsn(Opcodes.ICONST_0)
    }
  }

  override def visitIntegerExpression(expression: integerExpression, a: MethodVisitor): Unit = {
    a.visitLdcInsn(expression.value)
  }

  override def visitCharacterExpression(expression: characterExpression, a: MethodVisitor): Unit = {
    a.visitLdcInsn(expression.value.charAt(1))
  }

  override def visitIdentiferExpression(expression: identifierExpression, a: MethodVisitor): Unit = {
    if(expression.isLocal && expression.isParameter){
      //we load the identifier as a method parameter
      a.visitIntInsn(getLoadInsn(expression.variableType),expression.paramIndex.get)
    }else if(expression.isLocal){
      //we load the identifier as a value declared within the method
      //nearly the same as the previous case, we just need to add the offset of how many params there are in the current method
      a.visitIntInsn(getLoadInsn(expression.variableType),expression.paramIndex.get+curMethodParamAmount)
    }else{
      //we load the identifier as a class instance variable

      //first load in the current class variable
      a.visitIntInsn(Opcodes.ALOAD, 0)
      a.visitFieldInsn(Opcodes.GETFIELD, curClassName, expression.value.name, convertToASMType(expression.variableType))
    }
  }

  override def visitNewArrayExpression(expression: newArrayExpression, a: MethodVisitor): Unit = super.visitNewArrayExpression(expression, a)

  override def visitNewClassInstanceExpression(expression: newClassInstanceExpression, a: MethodVisitor): Unit = super.visitNewClassInstanceExpression(expression, a)

  override def visitNegatedExpression(expression: negatedExpression, a: MethodVisitor): Unit = super.visitNegatedExpression(expression, a)

  override def visitParenthesizedExpression(expression: parenthesizedExpression, a: MethodVisitor): Unit = {
    visit(expression.value, a)
  }

  override def visitAndExpression(expression: andExpression, a: MethodVisitor, b: Unit): Unit = super.visitAndExpression(expression, a, b)

  override def visitAddExpression(expression: addExpression, a: MethodVisitor, b: Unit): Unit = {
    visitTerminalExpression(expression.value.leftVal, a)
    a.visitInsn(Opcodes.IADD)
    visitExpressionTail(expression.value.rightVal, a, b)
  }

  override def visitSubtractExpression(expression: subtractExpression, a: MethodVisitor, b: Unit): Unit = {
    visitTerminalExpression(expression.value.leftVal, a)
    a.visitInsn(Opcodes.ISUB)
    visitExpressionTail(expression.value.rightVal, a, b)
  }

  override def visitMultiplyExpression(expression: multiplyExpression, a: MethodVisitor, b: Unit): Unit = {
    visitTerminalExpression(expression.value.leftVal, a)
    a.visitInsn(Opcodes.IMUL)
    visitExpressionTail(expression.value.rightVal, a, b)
  }

  override def visitArrayLengthExpression(expression: arrayLengthExpression, a: MethodVisitor, b: Unit): Unit = {
    // Push the array length
    a.visitInsn(Opcodes.ARRAYLENGTH)
  }

  override def visitArrayIndexExpression(expression: arrayIndexExpression, a: MethodVisitor, b: Unit): Unit = super.visitArrayIndexExpression(expression, a, b)

  override def visitMethodFunctionCallExpression(expression: methodFunctionCallExpression, a: MethodVisitor, b: Unit): Unit = super.visitMethodFunctionCallExpression(expression, a, b)

  override def visitNoTail(previousExpressionVal: Unit): Unit = {
    /*
    do nothing
  */
  }

  def convertToASMType(typeVal: AST_Grammar.varType): String ={
      typeVal match {
        case x: AST_Grammar.classType => f"L${x.clazz};"
        case _: AST_Grammar.intArrayType => "[I"
        case _: AST_Grammar.booleanType => "Z"
        case _: AST_Grammar.integerType => "I"
        case _: AST_Grammar.characterType => "C"
        case _ =>
          println("Okay something wild went wrong if none of these are working")
          System.exit(-1)
          ""
      }
  }

  def getLoadInsn(varType: AST_Grammar.varType): Int = {
    varType match
      case _: AST_Grammar.classType => Opcodes.ALOAD
      case _: AST_Grammar.intArrayType => Opcodes.ALOAD
      case _: AST_Grammar.booleanType => Opcodes.ILOAD
      case _: AST_Grammar.integerType => Opcodes.ILOAD
      //do ILOAD for chars, you just have to specify the type as 'C' when printing
      case _: AST_Grammar.characterType => Opcodes.ILOAD
      case _ =>
        println("Okay something wild went wrong if none of these are working")
        System.exit(-1)
        0
  }

  def getStoreInsn(varType: AST_Grammar.varType): Int = {
    varType match
      case _: AST_Grammar.classType => Opcodes.ASTORE
      case _: AST_Grammar.intArrayType => Opcodes.ASTORE
      case _: AST_Grammar.booleanType => Opcodes.ISTORE
      case _: AST_Grammar.integerType => Opcodes.ISTORE
      //do ISTORE for chars, you just have to specify the type as 'C' when printing
      case _: AST_Grammar.characterType => Opcodes.ISTORE
      case _ =>
        println("Okay something wild went wrong if none of these are working")
        System.exit(-1)
        0
  }

  def getReturnInsn(varType: AST_Grammar.varType): Int = {
    varType match
      case _: AST_Grammar.classType => Opcodes.ARETURN
      case _: AST_Grammar.intArrayType => Opcodes.ARETURN
      case _: AST_Grammar.booleanType => Opcodes.IRETURN
      case _: AST_Grammar.integerType => Opcodes.IRETURN
      //do ISTORE for chars, you just have to specify the type as 'C' when printing
      case _: AST_Grammar.characterType => Opcodes.IRETURN
      case _ =>
        println("Okay something wild went wrong if none of these are working")
        System.exit(-1)
        0
  }

}

object codeGenerator{
  def main(args: Array[String]): Unit ={

    //just a little test
    val cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES)
    val className = "Test"
    cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className, null, "java/lang/Object", null)
    val mmw = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
    mmw.visitCode()
    mmw.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
    mmw.visitLdcInsn('b')
    mmw.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(C)V", false)
    mmw.visitInsn(Opcodes.RETURN)
    mmw.visitEnd()
    mmw.visitMaxs(-1, -1)
    cw.visitEnd()

    val fos = new FileOutputStream(f"$className.class")
    fos.write(cw.toByteArray)
    fos.close()

  }
}
