package AST_Grammar

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks

class typeCheckingVisitor extends ASTVisitor[symbolTable, typeCheckResult] {

  private var curError: Option[error] = None
  def getCurError: Option[error] = curError
  override def visitGoal(goal: goal, a: symbolTable): typeCheckResult = {
    //assume we have no errors to begin with, only change is the main class or class throw an error
    val loop = Breaks
    var hasError = hasErrorResult(false)
    //first type check the main class statement
    hasError = visit(goal.main, a.getClassVal(goal.main.className.name).get.asInstanceOf[classVal].classScope).asInstanceOf[hasErrorResult]
    loop.breakable {
      for (curClass <- goal.classes) {
        if(hasError.errorVal){
          loop.break()
        }
        //type check each class' statement
        hasError = visit(curClass.get, a.getClassVal(curClass.get.className.name).get.asInstanceOf[classVal].classScope).asInstanceOf[hasErrorResult]
      }
    }
    hasError
  }

  //return what type the identifier is
  //I don't think I need this in hindsight, but we'll keep it here for now
  override def visitIdentifier(identifier: identifier, a: symbolTable): typeCheckResult = super.visitIdentifier(identifier, a)

  //just make sure the statement has no type check errors
  override def visitMainClass(clazz: mainClass, a: symbolTable): typeCheckResult = {
    var hasError = hasErrorResult(false)
    hasError = visit(clazz.body, a.getMethodVal(("main", List{commandLineArgs})).get.asInstanceOf[methodVal].methodScope).asInstanceOf[hasErrorResult]
    hasError
  }

  //type check the statements in each method, it will return true if any errors are found and false otherwise
  override def visitClass(klass: klass, a: symbolTable): typeCheckResult = {
    //assume we have no errors to begin with, only change is the main class or class throw an error
    val loop = Breaks
    var hasError = hasErrorResult(false)
    loop.breakable {
      for (method <- klass.methods) {
        if (hasError.errorVal) {
          loop.break()
        }
        
        //remember the method key is a tuple of the method and the list of params ;)
        val methodParams = new ListBuffer[varType]
        for(param <- method.get.params){
          methodParams += AST_Grammar.getVarType(param._1)
        }

        hasError = visit(method.get, a.getMethodVal((method.get.methodName.name, methodParams.toList)).get.asInstanceOf[methodVal].methodScope).asInstanceOf[hasErrorResult]
      }
    }
    hasError
  }

  //type check the statements, return true if any of them make an error
  override def visitMethod(method: method, a: symbolTable): typeCheckResult = {
    //assume we have no errors to begin with, only change is the main class or class throw an error
    val loop = Breaks
    var hasError = hasErrorResult(false)
    loop.breakable {
      for (statement <- method.statements) {
        if (hasError.errorVal) {
          loop.break()
        }
        hasError = visit(statement.get, a).asInstanceOf[hasErrorResult]
      }
    }
    hasError
  }

  //each statement visit method will return true or false indicating that a type check failed

  //type check each statement in the block
  override def visitBlockStatement(statement: blockStatement, a: symbolTable): typeCheckResult = {
    //assume we have no errors to begin with, only change is the main class or class throw an error
    val loop = Breaks
    var hasError = hasErrorResult(false)
    loop.breakable{
      for(curStatement <- statement.statements){
        if (hasError.errorVal) {
          loop.break()
        }
        hasError = visit(curStatement, a).asInstanceOf[hasErrorResult]
      }
    }

    hasError
  }


  //make sure conditional is a boolean, type check the statements in the bodies
  override def visitIfStatement(statement: ifStatement, a: symbolTable): typeCheckResult = {
    var hasError = hasErrorResult(false)
    val ifCondVal = visit(statement.condition, a)
    ifCondVal match {
      //if it is a variable result, make sure it is a boolean
      case result: varValResult =>
        if (result.varVal != booleanType) {
          hasError.errorVal = true
        }else{
          //now type check the then and else statements
          hasError = visit(statement.thenStatement, a).asInstanceOf[hasErrorResult]
          //only check the else if we didn't already find an error in the then statement
          if(!hasError.errorVal) {
            hasError = visit(statement.elseStatement, a).asInstanceOf[hasErrorResult]
          }
        }

      //this means an error resulted from typechecking the expression
      case _ => hasError = ifCondVal.asInstanceOf[hasErrorResult]
    }

    hasError
  }

  //make sure conditional is a boolean, type check the statement in the body
  override def visitWhileStatement(statement: whileStatement, a: symbolTable): typeCheckResult = {
    var hasError = hasErrorResult(false)
    val whileCondVal = visit(statement.condition, a)
    whileCondVal match
      case result: varValResult =>
        if (result.varVal != booleanType) {
          hasError.errorVal = true
        } else {
          //now type check the then and else statements
          hasError = visit(statement.thenStatement, a).asInstanceOf[hasErrorResult]

        }
      //this means it was false
      case _ => hasError = whileCondVal.asInstanceOf[hasErrorResult]
    hasError
  }

  //make sure expresison in print statement is an integer
  override def visitPrintStatement(statement: printStatement, a: symbolTable): typeCheckResult = {
    var hasError = hasErrorResult(false)
    val printedVal = visit(statement.value, a)
    printedVal match
      case result: varValResult =>
        if(result.varVal != integerType()) {
          curError = Some(typeInconformitiyError(result.varVal, integerType(), statement.value.line, statement.value.index))
          hasError.errorVal = true
        }

      //this means it was false
      case _ => hasError = printedVal.asInstanceOf[hasErrorResult]

    hasError
  }

  //get type of left side of assign statement, make sure right side is of the same type
  override def visitAssignStatement(statement: assignStatement, a: symbolTable): typeCheckResult = {
    var hasError = hasErrorResult(false)
    //first we make sure that the variable getting assigned exists.
    val leftVal = statement.idVal

    //if the variable is not defined in the method scope, check the current class scope. If that fails,
    //check the extended classes for the variable. If THAT fails, then the variable does not exist so change
    //hasError to true
    if(!a.checkIfVarIDExists(leftVal.name)){
      a.getParentTable match
        case Some(parent) =>
          //checking the class that the method is a child of
          if(!parent.checkIfVarIDExists(leftVal.name)){
            //now check any extend class to see if the variable might reside in the extended class
            val variableType = checkExtendedClassesForVar(leftVal.name, parent)
            variableType match
              case result: hasErrorResult =>
                curError = Some(noSuchVariableUnknownTypeDefinedError(leftVal.name, statement.line))
                hasError = result
              case result: varValResult =>
                //we were able to find the variable type
                //result now is of type varType
                //check the right expression and make sure it is not an hasError node or an unequivalent type
                val rightSide = visit(statement.value, a)
                rightSide match
                  case rightVal: hasErrorResult => hasError = rightVal
                  case rightVal: varValResult =>
                    if(!result.varVal.equals(rightVal.varVal)){
                      curError = Some(noSuchVariableDefinedError(leftVal.name, rightVal.varVal, statement.line))
                      hasError.errorVal = true
                    }
          }else{
            //the variable is defined as a global value in the class, get it and perform the rest of the checks
            val variableType = parent.getVariableVal(leftVal.name).get.asInstanceOf[variableVal]
            //check right side
            val rightSide = visit(statement.value, a)
            rightSide match
              case rightVal: hasErrorResult => hasError = rightVal
              case rightVal: varValResult =>
                if (!variableType.varValue.equals(rightVal.varVal)) {
                  curError = Some(noSuchVariableDefinedError(leftVal.name, rightVal.varVal, statement.line))
                  hasError.errorVal = true
                }
          }
        case _ => hasError.errorVal = true
          println("Something went wrong...Ill-defined statement")
          System.exit(1)
    }else{
      val variableType = a.getVariableVal(leftVal.name).get.asInstanceOf[variableVal]
      val rightSide = visit(statement.value, a)
      rightSide match
        case rightVal: hasErrorResult => hasError = rightVal
        case rightVal: varValResult =>
          if (!variableType.varValue.equals(rightVal.varVal)) {
            curError = Some(noSuchVariableDefinedError(leftVal.name, rightVal.varVal, statement.line))
            hasError.errorVal = true
          }
    }

    hasError
  }

  def checkExtendedClassesForVar(variable: String, a: symbolTable): typeCheckResult = {
    var returnedVal:typeCheckResult = hasErrorResult(false)
    //remember that we are in a class, so get the classVal from the parent of the current symbol table
    val extendedClass = a.getParentTable.get.getClassVal(a.getName).get.asInstanceOf[classVal].extendedClass
    extendedClass match
      case Some(clazz) =>
        //get that class's symbol table, if it has the variable letter, return its type, otherwise check for
        //that class's extended class
        val extendedClassSymbolTable = a.getParentTable.get.getClassVal(clazz).get.asInstanceOf[classVal].classScope
        if(extendedClassSymbolTable.checkIfVarIDExists(variable)){
          returnedVal = varValResult(extendedClassSymbolTable.getVariableVal(variable).get.asInstanceOf[variableVal].varValue)
        }else{
          returnedVal = checkExtendedClassesForVar(variable, extendedClassSymbolTable)
        }

      case None => returnedVal.asInstanceOf[hasErrorResult].errorVal = true

    returnedVal
  }

  //make sure the array identifier is actually of type array
  //make sure the expression in the brackets is an int, make sure the assigned value is an int
  override def visitArrayAssignStatement(statement: arrayAssignStatement, a: symbolTable): typeCheckResult = {
    var hasError = hasErrorResult(false)
    //first get the array identifier
    val arrayID = statement.idVal

    //just like the assignment statement, we have to check and make sure the identifier is defined in either the current
    //class or an extended class. If it is not, we have an error on our hands
    //if it is found, make sure it is of type int array
    if(!a.checkIfVarIDExists(arrayID.name)){
      //if we're here, then the array identifier does not exist in the current scope, check the current class &
      //its parent classes
      a.getParentTable match
        case Some(parent) =>
          //checking the class that the method is a child of
          if (!parent.checkIfVarIDExists(arrayID.name)) {

            //fill this body in later
          }else{//DO NOT FORGET THE ELSE HERE
            //fill this body in later
          }
        case _ => hasError.errorVal = true
          println("Something went wrong...Ill-defined statement")
          System.exit(1)

    }else{
      //the array exists in the current method
      //check and make sure the array index is of type int
      val arrIndex = visit(statement.arrayIndex, a)
      arrIndex match
        case result:hasErrorResult =>
          hasError = result
        case result: varValResult =>
          if(!result.varVal.equals(integerType)){
            curError = Some(typeInconformitiyError(result.varVal, integerType(), statement.arrayIndex.line, statement.arrayIndex.index))
            hasError.errorVal = true
          }
      //so long as the previous check passed, we'll type check the assigned expression now to make sure it is an integer
      if(!hasError.errorVal){
        val assignedExpression = visit(statement.value, a)
        assignedExpression match
          case result: hasErrorResult =>
            hasError = result
          case result: varValResult =>
            if (!result.varVal.equals(integerType)) {
              curError = Some(typeInconformitiyError(result.varVal, integerType(), statement.arrayIndex.line, statement.arrayIndex.index))
              hasError.errorVal = true
            }
      }
    }

    hasError
  }


  //
  //return whatever the name of the current class is
  override def visitThisExpression(expression: thisExpression, a: symbolTable): typeCheckResult = {
    varValResult(classType(a.getParentTable.get.getName))
  }

  //just return boolean var type
  override def visitBooleanExpression(expression: booleanExpression, a: symbolTable): typeCheckResult = {
    varValResult(booleanType())
  }

  //just return integer var type
  override def visitIntegerExpression(expression: integerExpression, a: symbolTable): typeCheckResult = {
    varValResult(integerType())
  }

  //just return character var type
  override def visitCharacterExpression(expression: characterExpression, a: symbolTable): typeCheckResult = {
    varValResult(characterType())
  }

  //just return a class var type
  override def visitIdentiferExpression(expression: identifierExpression, a: symbolTable): typeCheckResult = {
    //have to check and make sure this class actually exists
    var curParent = a.getParentTable
    while(curParent.get.getParentTable.isDefined){
      curParent = curParent.get.getParentTable
    }
    if(curParent.get.checkIfClassIDExists(expression.value.name)){
      varValResult(classType(expression.value.name))
    }else{
      curError = Some(noSuchTypeError(classType(expression.value.name), expression.line, expression.index))
      hasErrorResult(true)
    }
  }

  //just return array type
  override def visitNewArrayExpression(expression: newArrayExpression, a: symbolTable): typeCheckResult = super.visitNewArrayExpression(expression, a)

  //just return class var type
  override def visitNewClassInstanceExpression(expression: newClassInstanceExpression, a: symbolTable): typeCheckResult = super.visitNewClassInstanceExpression(expression, a)

  //just make sure the expression is of type boolean
  override def visitNegatedExpression(expression: negatedExpression, a: symbolTable): typeCheckResult = {
    var expressionType:typeCheckResult = visit(expression, a)
    if(!expressionType.isInstanceOf[hasErrorResult]){
      if(!expressionType.asInstanceOf[varValResult].varVal.equals(booleanType)){
        curError = Some(typeInconformitiyError(expressionType.asInstanceOf[varValResult].varVal, booleanType(), expression.line, expression.index))
        expressionType = hasErrorResult(true)
      }
    }

    expressionType
  }

  //get type of expression
  override def visitParenthesizedExpression(expression: parenthesizedExpression, a: symbolTable): typeCheckResult = super.visitParenthesizedExpression(expression, a)

  override def visitNoTail(previousExpressionVal: typeCheckResult): typeCheckResult = previousExpressionVal

  override def visitAndExpression(expression: andExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = super.visitAndExpression(expression, a, b)

  override def visitAddExpression(expression: addExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = super.visitAddExpression(expression, a, b)

  override def visitCompareExpression(expression: compareExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = super.visitCompareExpression(expression, a, b)

  override def visitSubtractExpression(expression: subtractExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = super.visitSubtractExpression(expression, a, b)

  override def visitMultiplyExpression(expression: multiplyExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = super.visitMultiplyExpression(expression, a, b)

  override def visitArrayLengthExpression(expression: arrayLengthExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = super.visitArrayLengthExpression(expression, a, b)

  override def visitArrayIndexExpression(expression: arrayIndexExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = super.visitArrayIndexExpression(expression, a, b)

  override def visitMethodFunctionCallExpression(expression: methodFunctionCallExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = super.visitMethodFunctionCallExpression(expression, a, b)
}
