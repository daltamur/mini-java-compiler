package codeGen
import AST_Grammar.{addExpression, andExpression, arrayAssignStatement, arrayIndexExpression, arrayLengthExpression, assignStatement, blockStatement, booleanExpression, characterExpression, compExpression, dataType, expression, expressionTail, expressionValue, goal, identifier, identifierExpression, ifStatement, integerExpression, klass, mainClass, method, methodFunctionCallExpression, multiplyExpression, negatedExpression, newArrayExpression, newClassInstanceExpression, operation, parenthesizedExpression, printStatement, subtractExpression, thisExpression, variableDecs, whileStatement}
import org.objectweb.asm
import org.objectweb.asm.{ClassWriter, Label, MethodVisitor, Opcodes}

import java.io.FileOutputStream
import java.nio.file.{Files, Paths}
import scala.collection.mutable.ListBuffer
class codeGenerator extends AST_Grammar.ASTVisitor [MethodVisitor, Unit]{
  private val classWriters: ListBuffer[ClassWriter] = ListBuffer()

  override def visitExpression(expressionVal: expression, a: MethodVisitor): Unit = super.visitExpression(expressionVal, a)

  override def visitBaseExpression(currentNode: expressionValue, a: MethodVisitor): Unit = super.visitBaseExpression(currentNode, a)

  override def visitTerminalTail(expressionVal: Option[expressionTail], a: MethodVisitor, expressionTerminalVal: Unit): Unit = super.visitTerminalTail(expressionVal, a, expressionTerminalVal)

  override def visitExpressionOpt(expressionVal: Option[operation], a: MethodVisitor, expressionTerminalVal: Unit): Unit = super.visitExpressionOpt(expressionVal, a, expressionTerminalVal)

  override def visitCompExpression(expression: compExpression, a: MethodVisitor): Unit = super.visitCompExpression(expression, a)

  override def visitAndExpression(curType: Unit, curExpression: andExpression, a: MethodVisitor): Unit = super.visitAndExpression(curType, curExpression, a)

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

    //for now we're just having it do hello world, this will instead be replaced with whatever comes from visiting the statement
    visit(goal.main.body, mmw)

    mmw.visitInsn(Opcodes.RETURN)
    mmw.visitEnd()
    mmw.visitMaxs(-1, -1)
    //clos the class writer
    cw.visitEnd()
    //write class to file
    Files.createDirectories(Paths.get(className))
    val fos = new FileOutputStream(f"$className/$className.class")
    fos.write(cw.toByteArray)
    fos.close()

    //then make all the other classes
    for (curClass <- goal.classes){
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

      //let's make all our class variables now!

      for(curMethod <- curClass.get.methods)
        //make the signature
        var methodSignature: String = "("
        for(param<-curMethod.get.params){
          methodSignature = methodSignature.concat(convertToASMType(AST_Grammar.getVarType(param._1)))
        }

        methodSignature = methodSignature.concat(")")


        methodSignature = methodSignature.concat(convertToASMType(AST_Grammar.getVarType(curMethod.get.returnType)))

        if(methodSignature.equals("()")){
          methodSignature = null
        }

        println(methodSignature)
        val mmw = cw.visitMethod(Opcodes.ACC_PUBLIC, curMethod.get.methodName.name,  methodSignature, null, null)
        //INSERT VISITING THE METHOD STATEMENTS HERE
        mmw.visitEnd()
        mmw.visitMaxs(-1, -1)
      cw.visitEnd()
      val fos = new FileOutputStream(f"${goal.main.className.name}/$className.class")
      fos.write(cw.toByteArray)
      fos.close()
    }
  }

  def writeClasses(folderName: String): Unit ={

  }

  override def visitIdentifier(identifier: identifier, a: MethodVisitor): Unit = super.visitIdentifier(identifier, a)

  override def visitVarDec(decs: variableDecs, a: MethodVisitor): Unit = super.visitVarDec(decs, a)

  override def visitMainClass(clazz: mainClass, a: MethodVisitor): Unit = super.visitMainClass(clazz, a)

  override def visitClass(klass: klass, a: MethodVisitor): Unit = super.visitClass(klass, a)

  override def visitMethod(method: method, a: MethodVisitor): Unit = {

  }

  override def visitDataType(dataType: dataType, a: MethodVisitor): Unit = super.visitDataType(dataType, a)

  override def visitBlockStatement(statement: blockStatement, a: MethodVisitor): Unit = super.visitBlockStatement(statement, a)

  override def visitIfStatement(statement: ifStatement, a: MethodVisitor): Unit = super.visitIfStatement(statement, a)

  override def visitWhileStatement(statement: whileStatement, a: MethodVisitor): Unit = super.visitWhileStatement(statement, a)

  override def visitPrintStatement(statement: printStatement, a: MethodVisitor): Unit = {
    a.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
    a.visitLdcInsn('b')
    a.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(C)V", false)
  }

  override def visitAssignStatement(statement: assignStatement, a: MethodVisitor): Unit = super.visitAssignStatement(statement, a)

  override def visitArrayAssignStatement(statement: arrayAssignStatement, a: MethodVisitor): Unit = super.visitArrayAssignStatement(statement, a)

  override def visitThisExpression(expression: thisExpression, a: MethodVisitor): Unit = super.visitThisExpression(expression, a)

  override def visitBooleanExpression(expression: booleanExpression, a: MethodVisitor): Unit = super.visitBooleanExpression(expression, a)

  override def visitIntegerExpression(expression: integerExpression, a: MethodVisitor): Unit = super.visitIntegerExpression(expression, a)

  override def visitCharacterExpression(expression: characterExpression, a: MethodVisitor): Unit = super.visitCharacterExpression(expression, a)

  override def visitIdentiferExpression(expression: identifierExpression, a: MethodVisitor): Unit = super.visitIdentiferExpression(expression, a)

  override def visitNewArrayExpression(expression: newArrayExpression, a: MethodVisitor): Unit = super.visitNewArrayExpression(expression, a)

  override def visitNewClassInstanceExpression(expression: newClassInstanceExpression, a: MethodVisitor): Unit = super.visitNewClassInstanceExpression(expression, a)

  override def visitNegatedExpression(expression: negatedExpression, a: MethodVisitor): Unit = super.visitNegatedExpression(expression, a)

  override def visitParenthesizedExpression(expression: parenthesizedExpression, a: MethodVisitor): Unit = super.visitParenthesizedExpression(expression, a)

  override def visitAndExpression(expression: andExpression, a: MethodVisitor, b: Unit): Unit = super.visitAndExpression(expression, a, b)

  override def visitAddExpression(expression: addExpression, a: MethodVisitor, b: Unit): Unit = super.visitAddExpression(expression, a, b)

  override def visitSubtractExpression(expression: subtractExpression, a: MethodVisitor, b: Unit): Unit = super.visitSubtractExpression(expression, a, b)

  override def visitMultiplyExpression(expression: multiplyExpression, a: MethodVisitor, b: Unit): Unit = super.visitMultiplyExpression(expression, a, b)

  override def visitArrayLengthExpression(expression: arrayLengthExpression, a: MethodVisitor, b: Unit): Unit = super.visitArrayLengthExpression(expression, a, b)

  override def visitArrayIndexExpression(expression: arrayIndexExpression, a: MethodVisitor, b: Unit): Unit = super.visitArrayIndexExpression(expression, a, b)

  override def visitMethodFunctionCallExpression(expression: methodFunctionCallExpression, a: MethodVisitor, b: Unit): Unit = super.visitMethodFunctionCallExpression(expression, a, b)

  override def visitNoTail(previousExpressionVal: Unit): Unit = super.visitNoTail(previousExpressionVal)

  def convertToASMType(typeVal: AST_Grammar.varType): String ={
      typeVal match {
        case x: AST_Grammar.classType => f"L${x.clazz};"
        case x: AST_Grammar.intArrayType => "[I"
        case x: AST_Grammar.booleanType => "Z"
        case x: AST_Grammar.integerType => "I"
        case x: AST_Grammar.characterType => "C"
      }
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
