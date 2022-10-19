package AST_Grammar

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks
import scala.util.control.NonLocalReturns.*

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
    if(!hasError.errorVal){
      val returnValue = visit(method.returnVal, a)
      returnValue match
        case result:hasErrorResult => hasError = result
        case result:varValResult =>
          if(!result.varVal.equals(AST_Grammar.getVarType(method.returnType))){
            hasError.errorVal = true
            curError = Some(returnTypeError(AST_Grammar.getVarType(method.returnType), result.varVal, method.line))
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
        if (result.varVal != booleanType()) {
          curError = Some(typeInconformitiyError(result.varVal, booleanType(), statement.condition.line,statement.condition.index))
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
        if (result.varVal != booleanType()) {
          curError = Some(typeInconformitiyError(result.varVal, booleanType(), statement.condition.line, statement.condition.index))
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
  //NOT FINISHED
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
            //need to check the extended classes for an int array
            val idType = checkExtendedClassesForVar(arrayID.name, a.getParentTable.get)
            idType match
              case hasErrorResult =>
                hasError.errorVal = true

              case x:intArrayType => {
                // we found the identifier and it was an array
                //check and make sure the array index is of type int
                val arrIndex = visit(statement.arrayIndex, a)
                arrIndex match
                  case result: hasErrorResult =>
                    hasError = result
                  case result: varValResult =>
                    if (!result.varVal.equals(integerType)) {
                      curError = Some(typeInconformitiyError(result.varVal, integerType(), statement.arrayIndex.line, statement.arrayIndex.index))
                      hasError.errorVal = true
                    }
                //so long as the previous check passed, we'll type check the assigned expression now to make sure it is an integer
                if (!hasError.errorVal) {
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

              case _ =>
                //we found the identifier and it was not an array
                curError = Some(typeInconformitiyError(idType.asInstanceOf[varValResult].varVal, intArrayType(), statement.value.line, statement.value.index))
                hasError.errorVal = true

          }else{//DO NOT FORGET THE ELSE HERE
            //the array exists as a global within the class
          }
        case _ => hasError.errorVal = true
          println("Something went wrong...Ill-defined statement")
          System.exit(1)

    }else{
      //the array exists in the current method if we get here
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

  //just return the var type
  override def visitIdentiferExpression(expression: identifierExpression, a: symbolTable): typeCheckResult = {
    var returnedVal: typeCheckResult = hasErrorResult(false)
    //check from inside out for the scope
    //currentMethod Scope -> Class Scope -> Extended Class Scope
    //return they type of the variable
    val variableID = expression.value.name
    //first checking the current method
    if(a.checkIfVarIDExists(variableID)){
      returnedVal = varValResult(a.getVariableVal(variableID).get.asInstanceOf[variableVal].varValue)
    }else if(a.getParentTable.get.checkIfVarIDExists(variableID)){
      returnedVal =  varValResult(a.getParentTable.get.getVariableVal(variableID).get.asInstanceOf[variableVal].varValue)
    }else{
      //now we will look at the extended classes and see if we can find a variable with the given ID
      //if we don't find it, throw an error saying that the variable does not exist in any inherited scope
      returnedVal
      val curClassname = a.getParentTable.get.getName
      val curClassExtension = a.getParentTable.get.getParentTable.get.getClassVal(curClassname).get.asInstanceOf[classVal].extendedClass
      curClassExtension match
        case Some(value) =>
          //we have an extended class, run our function to look for the identifier in the inherited class
          returnedVal = visitExtendedClassForVariable(value, variableID, a.getParentTable.get.getParentTable.get, expression.line)

        case None =>
          //no extended class, variable does not possibly exist
          curError = Some(noSuchVariableUnknownTypeDefinedError(variableID, expression.line))
          returnedVal.asInstanceOf[hasErrorResult].errorVal = true
    }
    returnedVal
  }

  def visitExtendedClassForVariable(extendedClassID: String, varID: String, a: symbolTable, line:Integer): typeCheckResult = {
    var returnedVal: typeCheckResult = hasErrorResult(false)
    if(a.getClassVal(extendedClassID).get.asInstanceOf[classVal].classScope.checkIfVarIDExists(varID)){
      //variable exists at this point, so set returnedVal to the return type
      returnedVal = varValResult(a.getClassVal(extendedClassID).get.asInstanceOf[classVal].classScope.getVariableVal(varID).get.asInstanceOf[variableVal].varValue)
    }else{
      val curClassname = a.getParentTable.get.getParentTable.get.getName
      val curClassExtension = a.getParentTable.get.getParentTable.get.getClassVal(curClassname).get.asInstanceOf[classVal].extendedClass
      curClassExtension match
        case Some(value) =>
          //we have an extended class, run our function to look for the identifier in the inherited class
          returnedVal = visitExtendedClassForVariable(value, varID, a.getParentTable.get.getParentTable.get, line)

        case None =>
          //no extended class, variable does not possibly exist
          curError = Some(noSuchVariableUnknownTypeDefinedError(varID, line))
          returnedVal.asInstanceOf[hasErrorResult].errorVal = true
    }

    returnedVal
  }

  //just return array type, make sure the size is of type int.
  override def visitNewArrayExpression(expression: newArrayExpression, a: symbolTable): typeCheckResult = {
    var returnedVal: typeCheckResult = hasErrorResult(false)
    //just check the size expression and make sure it is an integer
    returnedVal = visit(expression.size, a)
    returnedVal match
      case result: varValResult =>
        if(!result.varVal.equals(integerType())){
          curError = Some(typeInconformitiyError(result.varVal, integerType(), expression.line, expression.index))
          returnedVal = hasErrorResult(true)
        }

      case _ =>
    if(!returnedVal.isInstanceOf[hasErrorResult]){
      varValResult(intArrayType())
    }else{
      returnedVal
    }
  }

  //just return class var type, make sure the class actually exists
  override def visitNewClassInstanceExpression(expression: newClassInstanceExpression, a: symbolTable): typeCheckResult = {
    var returnedVal: typeCheckResult = hasErrorResult(false)
    //we know if we're in an expression that we're in a method, so just get the parent of the current parent to see if the class exists
    val programTable = a.getParentTable.get.getParentTable.get
    if(programTable.checkIfClassIDExists(expression.classType.name)){
      returnedVal = varValResult(classType(expression.classType.name))
    }else{
      //no such class
      curError = Some(noSuchClassError(expression.classType.name, expression.line, expression.index))
    }

    returnedVal
  }

  //just make sure the expression is of type boolean
  override def visitNegatedExpression(expression: negatedExpression, a: symbolTable): typeCheckResult = {
    var expressionType:typeCheckResult = visit(expression.value, a)
    if(!expressionType.isInstanceOf[hasErrorResult]){
      if(!expressionType.asInstanceOf[varValResult].varVal.equals(booleanType())){
        curError = Some(typeInconformitiyError(expressionType.asInstanceOf[varValResult].varVal, booleanType(), expression.line, expression.index))
        expressionType = hasErrorResult(true)
      }
    }

    expressionType
  }

  //get returned type of parenthesized expression
  override def visitParenthesizedExpression(expression: parenthesizedExpression, a: symbolTable): typeCheckResult = {
    visit(expression.value, a)
  }

  //just return what the left side had
  override def visitNoTail(previousExpressionVal: typeCheckResult): typeCheckResult = previousExpressionVal

  //make sure all expressions are booleans
  override def visitAndExpression(expression: andExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = {
    if(b.isInstanceOf[hasErrorResult]){
      b
    }else{
      //now just make sure b is a boolean
      if(b.asInstanceOf[varValResult].varVal.equals(booleanType())){
        val rightExpressionType = visit(expression.value, a)
        rightExpressionType match
          case result:varValResult =>
            if(!result.varVal.equals(booleanType())){
              curError = Some(typeInconformitiyError(result.asInstanceOf[varValResult].varVal, booleanType(), expression.value.line, expression.value.index))
              hasErrorResult(true)
            }else{
              result
            }
          case _ => rightExpressionType
      }else{
        curError=Some(typeInconformitiyError(b.asInstanceOf[varValResult].varVal, booleanType(), expression.value.line, expression.value.index-1))
        hasErrorResult(true)
      }
    }
  }

  //make sure all expressions are ints
  override def visitAddExpression(expression: addExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = {
    if (b.isInstanceOf[hasErrorResult]) {
      b
    } else {
      //now just make sure b is a boolean
      if (b.asInstanceOf[varValResult].varVal.equals(integerType())) {
        val rightExpressionType = visit(expression.value, a)
        rightExpressionType match
          case result: varValResult =>
            if (!result.varVal.equals(integerType())) {
              curError = Some(typeInconformitiyError(result.varVal, integerType(), expression.value.line, expression.value.index))
              hasErrorResult(true)
            } else {
              result
            }
          case _ => rightExpressionType
      } else {
        curError = Some(typeInconformitiyError(b.asInstanceOf[varValResult].varVal, integerType(), expression.value.line, expression.value.index - 1))
        hasErrorResult(true)
      }
    }
  }

  //make sure all expressions are ints
  override def visitCompareExpression(expression: compareExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = {
    if (b.isInstanceOf[hasErrorResult]) {
      b
    } else {
      //now just make sure b is a boolean
      if (b.asInstanceOf[varValResult].varVal.equals(integerType())) {
        val rightExpressionType = visit(expression.value, a)
        rightExpressionType match
          case result: varValResult =>
            if (!result.varVal.equals(integerType())) {
              curError = Some(typeInconformitiyError(result.asInstanceOf[varValResult].varVal, integerType(), expression.value.line, expression.value.index))
              hasErrorResult(true)
            } else {
              varValResult(booleanType())
            }
          case _ => varValResult(booleanType())
      } else {
        curError = Some(typeInconformitiyError(b.asInstanceOf[varValResult].varVal, integerType(), expression.value.line, expression.value.index - 1))
        hasErrorResult(true)
      }
    }
  }

  //make sure all expressions are ints
  override def visitSubtractExpression(expression: subtractExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = {
    if (b.isInstanceOf[hasErrorResult]) {
      b
    } else {
      //now just make sure b is a boolean
      if (b.asInstanceOf[varValResult].varVal.equals(integerType())) {
        val rightExpressionType = visit(expression.value, a)
        rightExpressionType match
          case result: varValResult =>
            if (!result.varVal.equals(integerType())) {
              curError = Some(typeInconformitiyError(result.varVal, integerType(), expression.value.line, expression.value.index))
              hasErrorResult(true)
            } else {
              result
            }
          case _ => rightExpressionType
      } else {
        curError = Some(typeInconformitiyError(b.asInstanceOf[varValResult].varVal, integerType(), expression.value.line, expression.value.index - 1))
        hasErrorResult(true)
      }
    }

  }

  //make sure all expressions are ints
  override def visitMultiplyExpression(expression: multiplyExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = {
    if (b.isInstanceOf[hasErrorResult]) {
      b
    } else {
      //now just make sure b is a boolean
      if (b.asInstanceOf[varValResult].varVal.equals(integerType())) {
        val rightExpressionType = visit(expression.value, a)
        rightExpressionType match
          case result: varValResult =>
            if (!result.varVal.equals(integerType())) {
              curError = Some(typeInconformitiyError(result.varVal, integerType(), expression.value.line, expression.value.index))
              hasErrorResult(true)
            } else {
              result
            }
          case _ => rightExpressionType
      } else {
        curError = Some(typeInconformitiyError(b.asInstanceOf[varValResult].varVal, integerType(), expression.value.line, expression.value.index - 1))
        hasErrorResult(true)
      }
    }
  }

  //make sure b is an int array type. If it is, return an integer type
  override def visitArrayLengthExpression(expression: arrayLengthExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = {
    var returnVal: typeCheckResult = hasErrorResult(false)
    if (b.isInstanceOf[hasErrorResult]) {
      returnVal = b
    }else if(b.asInstanceOf[varValResult].varVal.equals(intArrayType())){
      returnVal = varValResult(integerType())
    }else{
      curError = Some(typeInconformitiyError(b.asInstanceOf[varValResult].varVal, integerType(), expression.line, expression.index))
      returnVal = hasErrorResult(true)
    }

    returnVal
  }

  //make sure b is an int array type. If it is, make sure the index is an integer. If these checks pass, return an integer type
  override def visitArrayIndexExpression(expression: arrayIndexExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = {
    var returnVal: typeCheckResult = hasErrorResult(false)
    if(b.isInstanceOf[hasErrorResult]){
      returnVal = b
    }else{
      val indexVal = visit(expression.value, a)
      indexVal match
        case result: hasErrorResult => returnVal = result
        case result: varValResult =>
          if(!result.varVal.equals(integerType())){
            curError = Some(typeInconformitiyError(result.varVal, integerType(), expression.value.line, expression.value.index))
            returnVal = hasErrorResult(true)
          }else{
            returnVal = result
          }
    }
    returnVal
  }

  //check if b is actually of a class type. If it is, gather a list of the operands and the method name. Check and make sure
  //the method exists either in the class type of the variable calling the method or in one of the variable's inherited classes.
  //if it does exist, return the return type of the visited method call
  override def visitMethodFunctionCallExpression(expression: methodFunctionCallExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = {
    var returnedVal: typeCheckResult = hasErrorResult(false)
    val loop = Breaks
    val methodName = expression.funcName.name
    val methodParams = expression.params
    val paramVarTypes = new ListBuffer[varType]
    var className: String = null
    if(b.isInstanceOf[hasErrorResult]){
      return b
    }else{
      b.asInstanceOf[varValResult].varVal match
        //we're ok so long as the var type is a class
        case result:classType => className = result.clazz
        case _ =>
          curError = Some(callMethodOnPrimitve(b.asInstanceOf[varValResult].varVal, expression.line))
          returnedVal = hasErrorResult(true)
          return returnedVal
    }

    loop.breakable {
      for (curParam <- methodParams) {
        val returnedParamVal = visit(curParam, a)
        if (returnedParamVal.isInstanceOf[hasErrorResult]) {
          returnedVal = returnedParamVal
          loop.break()
        } else {
          paramVarTypes += returnedParamVal.asInstanceOf[varValResult].varVal
        }
      }
    }
    //if there was some error going through the expressions, we're not going to bother looking for the method
    if(!returnedVal.asInstanceOf[hasErrorResult].errorVal) {
      val methodKey = (methodName, paramVarTypes.toList)
      //method exists in th current scope
      //get method keys for current class
      if (a.getParentTable.get.getParentTable.get.getClassVal(className).get.asInstanceOf[classVal].classScope.checkIfMethodIDExists(methodKey)) {
        returnedVal = varValResult(a.getParentTable.get.getParentTable.get.getClassVal(className).get.asInstanceOf[classVal].classScope.getMethodVal(methodKey).get.asInstanceOf[methodVal].returnType)
        returnedVal
      } else {
        //possible that the method requires parent types, making the method signature valid. check this and  return as
        //necessary
        val methodKeys = a.getParentTable.get.getParentTable.get.getClassVal(className).get.asInstanceOf[classVal].classScope.getMethodKeys
        for (possibleKey <- methodKeys) {
          if (possibleKey.asInstanceOf[(String, List[varType])]._1.equals(methodKey._1)) {
            val varsMatch = checkForParentTypes(methodKey._2, possibleKey.asInstanceOf[(String, List[varType])]._2, a.getParentTable.get.getParentTable.get)
            if (varsMatch) {
              returnedVal = varValResult(a.getParentTable.get.getParentTable.get.getClassVal(className).get.asInstanceOf[classVal].classScope.getMethodVal(possibleKey).get.asInstanceOf[methodVal].returnType)
            }
          }
        }

        returnedVal match {
          case result: hasErrorResult =>
            //look at the extended class if one exists and look for the method there
            val extendedClass = a.getParentTable.get.getParentTable.get.getClassVal(className).get.asInstanceOf[classVal].extendedClass
            extendedClass match
              //there is an extended class, so try and find the method in the scope of the extended class
              case Some(extended) =>
                returnedVal = checkForMethod(methodKey, extended, a.getParentTable.get.getParentTable.get)
                if (returnedVal.isInstanceOf[hasErrorResult]) {
                  curError = Some(noSuchMethodError(expression.funcName.name, paramVarTypes.toList, expression.line))
                }
              case None =>
                curError = Some(noSuchMethodError(expression.funcName.name, paramVarTypes.toList, expression.line))
                result.errorVal = true
          case _ =>
        }
        returnedVal
      }
    }else{
      returnedVal
    }
  }
  
  def isChild(givenParam: varType, neededParam: varType, table: symbolTable): Boolean = {
    val givenParamExtendedVal = table.getClassVal(givenParam.asInstanceOf[classType].clazz).get.asInstanceOf[classVal].extendedClass
    givenParamExtendedVal match
      case Some(value) =>
        matchParams(classType(value), neededParam, table)
      case _ => false
  }
  
  def matchParams(givenParam: varType, neededParam: varType, table: symbolTable): Boolean = {
    if(givenParam.equals(neededParam)){
      true
    }else{
      //check if givenParam is just a child of neededParam
      if(neededParam.isInstanceOf[classType] && givenParam.isInstanceOf[classType]){
        isChild(givenParam, neededParam, table)
      }else{
        false
      }
    }
  }
  def checkForParentTypes(givenParamList: List[varType], neededParamList: List[varType], a: symbolTable):Boolean = {
    //assume the given table is the outermost table
    if(givenParamList.length == neededParamList.length) {
      val pairs = givenParamList.zip(neededParamList)
      val paramMatch = for ((givenParam, neededParam) <- pairs)
        yield matchParams(givenParam, neededParam, a)
      !paramMatch.contains(false)
    }else{
      false
    }
  }
  
  

  def checkForMethod(key: (String, List[varType]), curClassName: String, programTable: symbolTable): typeCheckResult ={
    var returnedVal: typeCheckResult = hasErrorResult(false)
    val classVal = programTable.getClassVal(curClassName).get.asInstanceOf[AST_Grammar.classVal]
    if(classVal.classScope.checkIfMethodIDExists(key)){
      returnedVal = varValResult(classVal.classScope.getMethodVal(key).get.asInstanceOf[methodVal].returnType)
    }else if(!classVal.classScope.checkIfMethodIDExists(key)){
      //possible that the method requires parent types, making the method signature valid. check this and  return as
      //necessary
      val methodKeys = classVal.classScope.getMethodKeys
      for (possibleKey <- methodKeys) {
        if (possibleKey.asInstanceOf[(String, List[varType])]._1.equals(key._1)) {
          val varsMatch = checkForParentTypes(key._2, possibleKey.asInstanceOf[(String, List[varType])]._2, programTable)
          if (varsMatch) {
            returnedVal = varValResult(classVal.classScope.getMethodVal(possibleKey).get.asInstanceOf[methodVal].returnType)
          }
        }
      }
    }else if(classVal.extendedClass.isDefined){
      returnedVal = checkForMethod(key, classVal.extendedClass.get, programTable)
    }else{
      returnedVal = hasErrorResult(true)
    }
    returnedVal
  }
}
