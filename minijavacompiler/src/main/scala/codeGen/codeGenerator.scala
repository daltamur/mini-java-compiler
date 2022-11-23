package codeGen
import AST_Grammar.{addExpression, andExpression, arrayAssignStatement, arrayIndexExpression, arrayLengthExpression, assignStatement, blockStatement, booleanExpression, characterExpression, compExpression, dataType, expression, expressionTail, expressionValue, goal, identifier, identifierExpression, ifStatement, integerExpression, klass, mainClass, method, methodFunctionCallExpression, multiplyExpression, negatedExpression, newArrayExpression, newClassInstanceExpression, operation, parenthesizedExpression, printStatement, subtractExpression, thisExpression, variableDecs, whileStatement}
import org.objectweb.asm
import org.objectweb.asm.{ClassWriter, Label, MethodVisitor, Opcodes}
import java.io.FileOutputStream
import java.nio.file.{Files, Paths}
import scala.collection.mutable.ListBuffer
class codeGenerator extends AST_Grammar.ASTVisitor [MethodVisitor, Unit]{
  private var curMethodParamAmount: Integer = -1
  private var curClassName: String = ""

  override def visitCompExpression(expression: compExpression, a: MethodVisitor): Unit = {
    visitBaseExpression(expression.value, a)
    var curCompTail = expression.optVal
    if(curCompTail.isDefined) {
      val compareFail = new Label()
      val compareEnd = new Label()
      while (curCompTail.isDefined) {
        visitBaseExpression(curCompTail.get.value, a)
        //comparison goes here I think?
        a.visitJumpInsn(Opcodes.IF_ICMPGE, compareFail)
        curCompTail = curCompTail.get.optVal
      }
      a.visitInsn(Opcodes.ICONST_1)
      a.visitJumpInsn(Opcodes.GOTO, compareEnd)
      a.visitLabel(compareFail)
      a.visitInsn(Opcodes.ICONST_0)
      a.visitLabel(compareEnd)
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

//        println(methodSignature)
        val mmw = cw.visitMethod(Opcodes.ACC_PUBLIC, curMethod.get.methodName.name, methodSignature, null, null)
        mmw.visitCode()
        val methodStartLabel = new Label()
        val methodEndLabel = new Label()
        curMethodParamAmount = curMethod.get.params.length
        for ((variable, index) <- curMethod.get.variables.zipWithIndex) {
//          println(convertToASMType(AST_Grammar.getVarType(variable.get.typeval)))
          mmw.visitLocalVariable(variable.get.name.name, convertToASMType(AST_Grammar.getVarType(variable.get.typeval)), null, methodStartLabel, methodEndLabel, index+curMethodParamAmount+1)
        }

        mmw.visitLabel(methodStartLabel)
        //visit statements goes here
        for(statement<-curMethod.get.statements){
          visit(statement.get, mmw)
        }


        //return statement goes here
        visit(curMethod.get.returnVal, mmw)
        mmw.visitInsn(getReturnInsn(AST_Grammar.getVarType(curMethod.get.returnType)))
        mmw.visitLabel(methodEndLabel)
        mmw.visitMaxs(-1, -1)
        mmw.visitEnd()
      }
      cw.visitEnd()
      //optimization step happens somewhere here
      val fos = new FileOutputStream(f"compiledPrograms/${goal.main.className.name}/$className.class")
      fos.write(cw.toByteArray)
      fos.close()
    }
    println(goal.main.className.name + ".java successfully compiled!")
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

  override def visitIfStatement(statement: ifStatement, a: MethodVisitor): Unit = {
    val ifFail = new Label()
    val ifEnd = new Label()
    visit(statement.condition, a)
    a.visitJumpInsn(Opcodes.IFEQ, ifFail)
    visit(statement.thenStatement, a)
    a.visitJumpInsn(Opcodes.GOTO, ifEnd)
    a.visitLabel(ifFail)
    visit(statement.elseStatement, a)
    a.visitLabel(ifEnd)
  }

  override def visitWhileStatement(statement: whileStatement, a: MethodVisitor): Unit = {
    val whileStart = new Label()
    val whileLeave = new Label()
    a.visitLabel(whileStart)
    visit(statement.condition, a)
    a.visitJumpInsn(Opcodes.IFEQ, whileLeave)
    visit(statement.thenStatement, a)
    a.visitJumpInsn(Opcodes.GOTO, whileStart)
    a.visitLabel(whileLeave)
  }

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
    if(statement.idVal.isLocal && statement.idVal.isParameter){
      visit(statement.value, a)
//      println(statement.idVal.name)
//      println(statement.idVal.variableType)
      a.visitIntInsn(getStoreInsn(statement.idVal.variableType), statement.idVal.paramIndex.get)
    }else if (statement.idVal.isLocal){
      visit(statement.value, a)
//      println(statement.idVal.name)
//      println(statement.idVal.variableType)
      a.visitIntInsn(getStoreInsn(statement.idVal.variableType),statement.idVal.paramIndex.get+curMethodParamAmount)
    }else{
      a.visitIntInsn(Opcodes.ALOAD, 0)
      visit(statement.value, a)
//      println(statement.idVal.name)
//      println(statement.idVal.variableType)
      a.visitFieldInsn(Opcodes.PUTFIELD, statement.idVal.parentName, statement.idVal.name, convertToASMType(statement.idVal.variableType))
    }

  }

  override def visitArrayAssignStatement(statement: arrayAssignStatement, a: MethodVisitor): Unit = {
    if (statement.idVal.isLocal && statement.idVal.isParameter) {
      a.visitIntInsn(getLoadInsn(statement.idVal.variableType), statement.idVal.paramIndex.get)
      visit(statement.arrayIndex, a)
      visit(statement.value, a)
      a.visitInsn(Opcodes.IASTORE)
    } else if (statement.idVal.isLocal) {
      a.visitIntInsn(getLoadInsn(statement.idVal.variableType), statement.idVal.paramIndex.get + curMethodParamAmount)
      visit(statement.arrayIndex, a)
      visit(statement.value, a)
      a.visitInsn(Opcodes.IASTORE)
    } else {
      a.visitIntInsn(Opcodes.ALOAD, 0)
      a.visitFieldInsn(Opcodes.GETFIELD, statement.idVal.parentName, statement.idVal.name, convertToASMType(AST_Grammar.intArrayType()))
      visit(statement.arrayIndex, a)
      visit(statement.value, a)
      a.visitInsn(Opcodes.IASTORE)
    }
  }

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
    if(expression.value.length == 3) {
      a.visitLdcInsn(expression.value.charAt(1))
    }else if (expression.value.length == 4){
      //this means we have one of the new line characters
      expression.value.substring(1, expression.value.length-1) match
        case ("\\n") => a.visitLdcInsn('\n')
        case "\\t"=> a.visitLdcInsn('\t')
        case "\\b"=> a.visitLdcInsn('\b')
        case "\\f"=> a.visitLdcInsn('\f')
        case "\\r"=> a.visitLdcInsn('\r')
        case "\\\\"=> a.visitLdcInsn('\\')
        case "\\'"=> a.visitLdcInsn('\'')
    }
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
      //now get the instance variable
      a.visitFieldInsn(Opcodes.GETFIELD, curClassName, expression.value.name, convertToASMType(expression.variableType))
    }
    //do a null check here



  }

  override def visitNewArrayExpression(expression: newArrayExpression, a: MethodVisitor): Unit = {
    //first visit the given size
    visit(expression.size, a)

    //now throw a new int array on the stack
    a.visitIntInsn(Opcodes.NEWARRAY, 10)
  }

  override def visitNewClassInstanceExpression(expression: newClassInstanceExpression, a: MethodVisitor): Unit = {
    a.visitTypeInsn(Opcodes.NEW, expression.classType.name)
    a.visitInsn(Opcodes.DUP)
    a.visitMethodInsn(Opcodes.INVOKESPECIAL, expression.classType.name, "<init>", "()V", false)
  }

  override def visitNegatedExpression(expression: negatedExpression, a: MethodVisitor): Unit = {
    val isNotZero = new Label()
    val negationEnd = new Label()
    visitBaseExpression(expression.value, a)
    a.visitJumpInsn(Opcodes.IFNE, isNotZero)
    a.visitInsn(Opcodes.ICONST_1)
    a.visitJumpInsn(Opcodes.GOTO, negationEnd)
    a.visitLabel(isNotZero)
    a.visitInsn(Opcodes.ICONST_0)
    a.visitLabel(negationEnd)
  }

  override def visitParenthesizedExpression(expression: parenthesizedExpression, a: MethodVisitor): Unit = {
    visit(expression.value, a)
  }

  //the following three functions are for arithmetic operations. It is important to consider any multiplication operations as a single value
  //to preseve order of operations. For instance, 5-8+5*5*6-7 needs to be considered 5-8+(5*5*6)-7,
  //so when we run visitAdd and visitSubtract, before we go on to just linearly keep performing arithmetic operations,
  //we need to look at the next value's operation and if it is a multiplication operation, we need to perform all of those operations
  //first and then either subtract or add

  def iterateThroughArrayAndFunctionCalls(expression: Option[expressionTail], a: MethodVisitor, b: Unit): Option[expressionTail] = {
    if(expression.isDefined && (expression.get.isInstanceOf[arrayLengthExpression] || expression.get.isInstanceOf[arrayIndexExpression] || expression.get.isInstanceOf[methodFunctionCallExpression])){
      expression.get match
        case x: arrayLengthExpression =>
          visitArrayLengthExpressionNoVisitTail(x, a, b)
          iterateThroughArrayAndFunctionCalls(x.operation, a, b)
        case x: arrayIndexExpression =>
          visitArrayIndexExpressionNoTail(x, a, b)
          iterateThroughArrayAndFunctionCalls(x.operation, a, b)
        case x: methodFunctionCallExpression =>
          //method
          visitMethodFunctionCallExpressionNoTailVisit(x, a, b)
          var curMethodTail = x.operation
          while (curMethodTail.isDefined && curMethodTail.get.isInstanceOf[methodFunctionCallExpression]) {
            visitMethodFunctionCallExpressionNoTailVisit(curMethodTail.get.asInstanceOf[methodFunctionCallExpression], a, b)
            curMethodTail = curMethodTail.get.asInstanceOf[methodFunctionCallExpression].operation
          }
          //all method calls have now been chained together, now just make sure it is not followed by an array index or length call
          curMethodTail match
            case Some(value) =>
              value match
                case x: arrayLengthExpression =>
                  visitArrayLengthExpressionNoVisitTail(x, a, b)
                  iterateThroughArrayAndFunctionCalls(x.operation, a, b)
                case x: arrayIndexExpression =>
                  visitArrayIndexExpressionNoTail(x, a, b)
                  iterateThroughArrayAndFunctionCalls(x.operation, a, b)
                case _ =>
                  iterateThroughArrayAndFunctionCalls(x.operation, a, b)


            case None => iterateThroughArrayAndFunctionCalls(x.operation, a, b)
    }else{
      expression
    }
  }

  def iterateThroughMultiplicationChain(expression: Option[expressionTail], a: MethodVisitor, b: Unit): Option[expressionTail] = {
    if(expression.isDefined && expression.get.isInstanceOf[multiplyExpression]){
      //we have a multiplication chain, iterate through it so that it gets performed before any other operation
      val currentOperation = expression.get.asInstanceOf[multiplyExpression]
      visitTerminalExpression(currentOperation.value.leftVal, a)
      //iterate past method and array functionality here
      val nextOp = iterateThroughArrayAndFunctionCalls(currentOperation.value.rightVal, a, b)
      a.visitInsn(Opcodes.IMUL)
      return iterateThroughMultiplicationChain(nextOp, a, b)
    }
    expression
  }

  override def visitAddExpression(expression: addExpression, a: MethodVisitor, b: Unit): Unit = {
    visitTerminalExpression(expression.value.leftVal, a)
    //immediately visit the expression tail if it is any of the following expressions, it means the value currently on the stack is not the one getting the operation
    //done on it
    //make sure to perform any multiplication chains before calling the IADD function
    if (expression.value.rightVal.isDefined && (expression.value.rightVal.get.isInstanceOf[arrayLengthExpression] || expression.value.rightVal.get.isInstanceOf[arrayIndexExpression] || expression.value.rightVal.get.isInstanceOf[methodFunctionCallExpression])) {
      expression.value.rightVal.get match
        case x: arrayLengthExpression =>
          visitArrayLengthExpressionNoVisitTail(x, a, b)
          //check for multiplication chain here
          val nextOp =iterateThroughMultiplicationChain(x.operation, a, b)
          a.visitInsn(Opcodes.IADD)
          //now visit the operation that MAY be done on the tail after this
          visitExpressionTail(nextOp, a, b)
        case x: arrayIndexExpression =>
          visitArrayIndexExpressionNoTail(x, a, b)
          //check for multiplication chain here
          val nextOp = iterateThroughMultiplicationChain(x.operation, a, b)
          a.visitInsn(Opcodes.IADD)
          //now visit the operation that MAY be done on the tail after this
          visitExpressionTail(nextOp, a, b)
        case x: methodFunctionCallExpression =>
          //method
          visitMethodFunctionCallExpressionNoTailVisit(x, a, b)
          var curMethodTail = x.operation
          while (curMethodTail.isDefined && curMethodTail.get.isInstanceOf[methodFunctionCallExpression]) {
            visitMethodFunctionCallExpressionNoTailVisit(curMethodTail.get.asInstanceOf[methodFunctionCallExpression], a, b)
            curMethodTail = curMethodTail.get.asInstanceOf[methodFunctionCallExpression].operation
          }
          //all method calls have now been chained together, now just make sure it is not followed by an array index or length call
          curMethodTail match
            case Some(value) =>
              value match
                case x: arrayLengthExpression =>
                  visitArrayLengthExpressionNoVisitTail(x, a, b)
                  //check for multiplication chain here
                  val nextOp = iterateThroughMultiplicationChain(x.operation, a, b)
                  a.visitInsn(Opcodes.IADD)
                  //now visit the operation that MAY be done on the tail after this
                  visitExpressionTail(nextOp, a, b)
                case x: arrayIndexExpression =>
                  visitArrayIndexExpressionNoTail(x, a, b)
                  //check for multiplication chain here
                  val nextOp = iterateThroughMultiplicationChain(x.operation, a, b)
                  a.visitInsn(Opcodes.IADD)
                  //now visit the operation that MAY be done on the tail after this
                  visitExpressionTail(nextOp, a, b)
                case _ => //for all other cases, just call the visitExpressionTailFunction
                  //check for multiplication chain here
                  val nextOp = iterateThroughMultiplicationChain(curMethodTail, a, b)
                  a.visitInsn(Opcodes.IADD)
                  visitExpressionTail(nextOp, a, b)


            case None => //do nothing if there is no other tails
    } else {
      //check for multiplication chain here
      val nextOp = iterateThroughMultiplicationChain(expression.value.rightVal, a, b)
      a.visitInsn(Opcodes.IADD)
      visitExpressionTail(nextOp, a, b)
    }
  }

  override def visitSubtractExpression(expression: subtractExpression, a: MethodVisitor, b: Unit): Unit = {
    visitTerminalExpression(expression.value.leftVal, a)
    //immediately visit the expression tail if it is any of the following expressions, it means the value currently on the stack is not the one getting the operation
    //done on it
    //make sure to perform any multiplication chains before calling the ISUB function
    if(expression.value.rightVal.isDefined && (expression.value.rightVal.get.isInstanceOf[arrayLengthExpression] || expression.value.rightVal.get.isInstanceOf[arrayIndexExpression] || expression.value.rightVal.get.isInstanceOf[methodFunctionCallExpression])){
      expression.value.rightVal.get match
        case x: arrayLengthExpression =>
          visitArrayLengthExpressionNoVisitTail(x, a, b)
          //check for multiplication chain here
          val nextOp = iterateThroughMultiplicationChain(x.operation, a, b)
          a.visitInsn(Opcodes.ISUB)
          //now visit the operation that MAY be done on the tail after this
          visitExpressionTail(nextOp, a, b)
        case x: arrayIndexExpression =>
          visitArrayIndexExpressionNoTail(x, a, b)
          //check for multiplication chain here
          val nextOp = iterateThroughMultiplicationChain(x.operation, a, b)
          a.visitInsn(Opcodes.ISUB)
          //now visit the operation that MAY be done on the tail after this
          visitExpressionTail(nextOp, a, b)
        case x: methodFunctionCallExpression =>
          //method
          visitMethodFunctionCallExpressionNoTailVisit(x, a, b)
          var curMethodTail = x.operation
          while (curMethodTail.isDefined && curMethodTail.get.isInstanceOf[methodFunctionCallExpression]) {
            visitMethodFunctionCallExpressionNoTailVisit(curMethodTail.get.asInstanceOf[methodFunctionCallExpression], a, b)
            curMethodTail = curMethodTail.get.asInstanceOf[methodFunctionCallExpression].operation
          }
          //all method calls have now been chained together, now just make sure it is not followed by an array index or length call
          curMethodTail match
            case Some(value) =>
              value match
                case x: arrayLengthExpression =>
                  visitArrayLengthExpressionNoVisitTail(x, a, b)
                  //check for multiplication chain here
                  val nextOp = iterateThroughMultiplicationChain(x.operation, a, b)
                  a.visitInsn(Opcodes.ISUB)
                  //now visit the operation that MAY be done on the tail after this
                  visitExpressionTail(nextOp, a, b)
                case x: arrayIndexExpression =>
                  visitArrayIndexExpressionNoTail(x, a, b)
                  //check for multiplication chain here
                  val nextOp = iterateThroughMultiplicationChain(x.operation, a, b)
                  a.visitInsn(Opcodes.ISUB)
                  //now visit the operation that MAY be done on the tail after this
                  visitExpressionTail(nextOp, a, b)
                case _ => //for all other cases, just call the visitExpressionTailFunction
                  //check for multiplication chain here
                  val nextOp = iterateThroughMultiplicationChain(curMethodTail, a, b)
                  a.visitInsn(Opcodes.ISUB)
                  visitExpressionTail(nextOp, a, b)


            case None => //do nothing if there is no other tails
    }else{
      //check for multiplication chain here
      val nextOp = iterateThroughMultiplicationChain(expression.value.rightVal, a, b)
      a.visitInsn(Opcodes.ISUB)
      visitExpressionTail(nextOp, a, b)
    }
  }

  override def visitMultiplyExpression(expression: multiplyExpression, a: MethodVisitor, b: Unit): Unit = {
    visitTerminalExpression(expression.value.leftVal, a)
    //immediately visit the expression tail if it is any of the following expressions, it means the value currently on the stack is not the one getting the operation
    //done on it
    if (expression.value.rightVal.isDefined && (expression.value.rightVal.get.isInstanceOf[arrayLengthExpression] || expression.value.rightVal.get.isInstanceOf[arrayIndexExpression] || expression.value.rightVal.get.isInstanceOf[methodFunctionCallExpression])) {
      expression.value.rightVal.get match
        case x: arrayLengthExpression =>
          visitArrayLengthExpressionNoVisitTail(x, a, b)
          a.visitInsn(Opcodes.IMUL)
          //now visit the operation that MAY be done on the tail after this
          visitExpressionOpt(x.operation, a, b)
        case x: arrayIndexExpression =>
          visitArrayIndexExpressionNoTail(x, a, b)
          a.visitInsn(Opcodes.IMUL)
          //now visit the operation that MAY be done on the tail after this
          visitExpressionOpt(x.operation, a, b)
        case x: methodFunctionCallExpression =>
          //method
          visitMethodFunctionCallExpressionNoTailVisit(x, a, b)
          var curMethodTail = x.operation
          while(curMethodTail.isDefined && curMethodTail.get.isInstanceOf[methodFunctionCallExpression]){
            visitMethodFunctionCallExpressionNoTailVisit(curMethodTail.get.asInstanceOf[methodFunctionCallExpression], a, b)
            curMethodTail = curMethodTail.get.asInstanceOf[methodFunctionCallExpression].operation
          }
          //all method calls have now been chained together, now just make sure it is not followed by an array index or length call
          curMethodTail match
            case Some(value) =>
              value match
                case x: arrayLengthExpression =>
                  visitArrayLengthExpressionNoVisitTail(x, a, b)
                  a.visitInsn(Opcodes.IMUL)
                  //now visit the operation that MAY be done on the tail after this
                  visitExpressionOpt(x.operation, a, b)
                case x: arrayIndexExpression =>
                  visitArrayIndexExpressionNoTail(x, a, b)
                  a.visitInsn(Opcodes.IMUL)
                  //now visit the operation that MAY be done on the tail after this
                  visitExpressionOpt(x.operation, a, b)
                case _ => //for all other cases, just call the visitExpressionTailFunction
                  a.visitInsn(Opcodes.IMUL)
                  visitExpressionTail(curMethodTail, a, b)


            case None => //do nothing if there is no other tails
    } else {
      a.visitInsn(Opcodes.IMUL)
      visitExpressionTail(expression.value.rightVal, a, b)
    }
  }

  def visitArrayLengthExpressionNoVisitTail(expression: arrayLengthExpression, a: MethodVisitor, b: Unit): Unit = {
    // Push the array length
    a.visitInsn(Opcodes.ARRAYLENGTH)
  }

  override def visitArrayLengthExpression(expression: arrayLengthExpression, a: MethodVisitor, b: Unit): Unit = {
    // Push the array length
    a.visitInsn(Opcodes.ARRAYLENGTH)
    visitExpressionOpt(expression.operation, a, b)
  }

  def visitArrayIndexExpressionNoTail(expression: arrayIndexExpression, a: MethodVisitor, b: Unit): Unit = {
    visit(expression.value, a)
    a.visitInsn(Opcodes.IALOAD)
  }

  override def visitArrayIndexExpression(expression: arrayIndexExpression, a: MethodVisitor, b: Unit): Unit = {
    visit(expression.value, a)
    a.visitInsn(Opcodes.IALOAD)
    visitExpressionOpt(expression.operation, a, b)
  }

  def visitMethodFunctionCallExpressionNoTailVisit(expression: methodFunctionCallExpression, a: MethodVisitor, b: Unit): Unit = {
    //put all the parameters of the method call on the stack
    for (param <- expression.params) {
      visit(param, a)
    }
    //make the signature
    var methodSignature: String = "("
    for (param <- expression.paramTypes)
      methodSignature = methodSignature.concat(convertToASMType(param))

    methodSignature = methodSignature.concat(")")


    methodSignature = methodSignature.concat(convertToASMType(expression.returnType))

    if (methodSignature.equals("()")) {
      methodSignature = null
    }

    a.visitMethodInsn(Opcodes.INVOKEVIRTUAL, AST_Grammar.varTypeToString(expression.classType), expression.funcName.name, methodSignature, false)
  }

  override def visitMethodFunctionCallExpression(expression: methodFunctionCallExpression, a: MethodVisitor, b: Unit): Unit = {
    //put all the parameters of the method call on the stack
    for(param <- expression.params){
      visit(param, a)
    }
    //make the signature
    var methodSignature: String = "("
    for (param <- expression.paramTypes)
      methodSignature = methodSignature.concat(convertToASMType(param))

    methodSignature = methodSignature.concat(")")


    methodSignature = methodSignature.concat(convertToASMType(expression.returnType))

    if (methodSignature.equals("()")) {
      methodSignature = null
    }

    a.visitMethodInsn(Opcodes.INVOKEVIRTUAL, AST_Grammar.varTypeToString(expression.classType), expression.funcName.name, methodSignature, false)

    visitExpressionTail(expression.operation, a , b)


  }

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