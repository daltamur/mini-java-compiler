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

  //just make sure the statement has no type check errors
  override def visitMainClass(clazz: mainClass, a: symbolTable): typeCheckResult = {
    var hasError = hasErrorResult(false)
    hasError = visit(clazz.body, a.getMethodVal(("main", List{commandLineArgs()})).get.asInstanceOf[methodVal].methodScope).asInstanceOf[hasErrorResult]
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
        case result:hasErrorResult =>
          hasError = result
        case result:varValResult =>
          if(result.varVal.isInstanceOf[classType]) {
            if (!result.varVal.equals(AST_Grammar.getVarType(method.returnType))) {
              //if is possible that the return value is just a child of the expected return value, check for that.
              val extendedClass = a.getParentTable.get.getParentTable.get.getClassVal(varTypeToString(result.varVal)).get.asInstanceOf[classVal].extendedClass
              if (extendedClass.isDefined) {
                hasError.errorVal = !matchParams(result.varVal, AST_Grammar.getVarType(method.returnType), a.getParentTable.get.getParentTable.get)
                if (hasError.errorVal) {
                  curError = Some(returnTypeError(AST_Grammar.getVarType(method.returnType), result.varVal, method.line))
                  hasError = hasErrorResult(true)
                  return  hasError
                }
              }else{
                curError = Some(returnTypeError(AST_Grammar.getVarType(method.returnType), result.varVal, method.line))
                hasError = hasErrorResult(true)
                return  hasError
              }
            }
          }else{
            if(!result.varVal.equals(AST_Grammar.getVarType(method.returnType))){
              curError = Some(returnTypeError(AST_Grammar.getVarType(method.returnType), result.varVal, method.line))
              hasError = hasErrorResult(true)
            }
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

  override def visitExpression(expressionVal: expression, a: symbolTable): typeCheckResult =
    var curType = visitCompExpression(expressionVal.leftVal, a)
    expressionVal.rightVal match
      case Some(value) =>
        curType = visitAndExpression(curType, value, a)
        curType
      case _ => curType


  //make sure conditional is a boolean, type check the statements in the bodies
  override def visitIfStatement(statement: ifStatement, a: symbolTable): typeCheckResult = {
    var hasError = hasErrorResult(false)
    val ifCondVal = visit(statement.condition, a)
    ifCondVal match {
      //if it is a variable result, make sure it is a boolean
      case result: varValResult =>
        if (result.varVal != booleanType()) {
          curError = Some(typeInconformitiyError(result.varVal, booleanType(), statement.condition.leftVal.line,statement.condition.leftVal.index))
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
          curError = Some(typeInconformitiyError(result.varVal, booleanType(), statement.condition.leftVal.line, statement.condition.leftVal.index))
          hasError.errorVal = true
        } else {
          //now type check the then and else statements
          hasError = visit(statement.thenStatement, a).asInstanceOf[hasErrorResult]

        }
      //this means it was false
      case _ => hasError = whileCondVal.asInstanceOf[hasErrorResult]
    hasError
  }

  //make sure expression in print statement is an integer
  override def visitPrintStatement(statement: printStatement, a: symbolTable): typeCheckResult = {
    var hasError = hasErrorResult(false)
    val printedVal = visit(statement.value, a)
    printedVal match
      case result: varValResult =>
        statement.value.typeValue = Some(result.varVal)
        if(result.varVal != integerType() && (result.varVal != characterType() && result.varVal != integerType())) {
          curError = Some(printError(statement.value.leftVal.line, statement.value.leftVal.index, result.varVal))
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
    if(a.checkIfVarIDExists(leftVal.name)) {
      //variable exists in current method
      val leftSideType = a.getVariableVal(leftVal.name).get.asInstanceOf[variableVal].varValue
      statement.idVal.isParameter = a.getVariableVal(leftVal.name).get.asInstanceOf[variableVal].isMethodParam
      statement.idVal.isLocal = true
      statement.idVal.paramIndex = Some(a.getVariableVal(leftVal.name).get.asInstanceOf[variableVal].variableIndex)
      statement.idVal.variableType = leftSideType

      //now get the type of the right side
      val rightSideType = visit(statement.value, a)
      //if the two types are not equal, we'll attempt to check if the right type is a child of the left type
      if (!rightSideType.isInstanceOf[hasErrorResult]) {
        checkIfLeftTypeEqualsRightType(leftSideType, rightSideType.asInstanceOf[varValResult].varVal, a, statement)
      }else{
        hasError = rightSideType.asInstanceOf[hasErrorResult]
      }
    } else if (a.getParentTable.get.checkIfVarIDExists(leftVal.name)) {
      //the variable exists as a global within the class
      statement.idVal.parentName = a.getParentTable.get.getName
      val leftSideType = a.getParentTable.get.getVariableVal(leftVal.name).get.asInstanceOf[variableVal].varValue
      statement.idVal.variableType = leftSideType
      val rightSideType = visit(statement.value, a)
      if (!rightSideType.isInstanceOf[hasErrorResult]) {
        checkIfLeftTypeEqualsRightType(leftSideType, rightSideType.asInstanceOf[varValResult].varVal, a, statement)
      } else {
        hasError = rightSideType.asInstanceOf[hasErrorResult]
      }
    } else {
      //check if the variable exists globally. If it does not, throw a variable never made error
      val leftSideType = checkExtendedClassesForVar(leftVal.name, a.getParentTable.get)
      if (!leftSideType.isInstanceOf[hasErrorResult]) {
        //we found the reference in an inherited class, now see if it is equal to the right value
        statement.idVal.variableType = leftSideType.asInstanceOf[varValResult].varVal
        statement.idVal.parentName = a.getParentTable.get.getName
        val rightSideType = visit(statement.value, a)
        if (!rightSideType.isInstanceOf[hasErrorResult]) {
          checkIfLeftTypeEqualsRightType(leftSideType.asInstanceOf[varValResult].varVal, rightSideType.asInstanceOf[varValResult].varVal, a, statement)
        } else {
          hasError = rightSideType.asInstanceOf[hasErrorResult]
        }
      }else{
        curError = Some(noSuchVariableUnknownTypeDefinedError(leftVal.name, statement.line))
        hasError = leftSideType.asInstanceOf[hasErrorResult]
      }
    }
    hasError
  }

  def checkIfLeftTypeEqualsRightType(leftSideType: varType, rightSideType: varType, a: symbolTable, statement: assignStatement): typeCheckResult = {
    val hasError = hasErrorResult(false)
    if(!leftSideType.equals(rightSideType)) {
      if (leftSideType.isInstanceOf[classType] && rightSideType.isInstanceOf[classType]) {
        //match params returns true if the classes are related, so just negate it
        hasError.errorVal = !matchParams(rightSideType, leftSideType, a.getParentTable.get.getParentTable.get)
        if (hasError.errorVal) {
          curError = Some(typeInconformitiyError(rightSideType, leftSideType, statement.line, statement.value.leftVal.index))
        }
      } else {
        hasError.errorVal = true
        curError = Some(typeInconformitiyError(rightSideType, leftSideType, statement.line, statement.value.leftVal.index))
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
    //first we're going to make sure the arrayID is indeed an array
    //local context
    var IDType:varType = null
    if (a.checkIfVarIDExists(arrayID.name)) {
      IDType = a.getVariableVal(arrayID.name).get.asInstanceOf[variableVal].varValue
      //now the class context
    }else if(a.getParentTable.get.checkIfVarIDExists(arrayID.name)){
      statement.idVal.parentName = a.getParentTable.get.getName
      IDType = a.getParentTable.get.getVariableVal(arrayID.name).get.asInstanceOf[variableVal].varValue
    }else{
      //try and get it in the global context
      val globalCheck = checkExtendedClassesForVar(arrayID.name, a)
      globalCheck match {
        case result: varValResult =>
          statement.idVal.parentName = a.getParentTable.get.getName
          IDType = result.varVal
        case _ =>
      }
    }
    if(IDType != null && IDType.equals(intArrayType())){
      //check the index, make sure it is an int
      visit(statement.arrayIndex, a) match
        case result:varValResult =>
          if(result.varVal.equals(integerType())) {
            visit(statement.value, a) match
              case expressionResult: varValResult =>
                if(!expressionResult.varVal.equals(integerType())){
                  hasError.errorVal = true
                  curError = Some(typeInconformitiyError(expressionResult.varVal, intArrayType(), statement.value.leftVal.line, statement.value.leftVal.index))
                }
              case expressionResult:hasErrorResult => hasError = expressionResult
          }else{
            hasError.errorVal = true
            curError = Some(typeInconformitiyError(result.varVal, intArrayType(), statement.arrayIndex.leftVal.line, statement.arrayIndex.leftVal.index))
          }
        case result:hasErrorResult => hasError = result
    }else{
      hasError.errorVal = true
      curError = Some(noSuchVariableDefinedError(arrayID.name, intArrayType(), statement.value.leftVal.line))
    }

    hasError
  }


  //return whatever the name of the current class is
  override def visitThisExpression(expression: thisExpression, a: symbolTable): typeCheckResult = {
    val classVal = classType(a.getParentTable.get.getName)
    classVal.isMainClass = a.getParentTable.get.getParentTable.get.getClassVal(classVal.clazz).get.asInstanceOf[classVal].isMainClass
    varValResult(classVal)
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
      expression.isLocal = true
      if(a.getVariableVal(variableID).get.asInstanceOf[variableVal].isMethodParam){
        expression.isParameter = true
      }
      expression.paramIndex = Some(a.getVariableVal(variableID).get.asInstanceOf[variableVal].variableIndex)
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
    if(returnedVal.isInstanceOf[varValResult]) {
      expression.variableType = returnedVal.asInstanceOf[varValResult].varVal
    }
    returnedVal
  }

  def visitExtendedClassForVariable(extendedClassID: String, varID: String, a: symbolTable, line:Integer): typeCheckResult = {
    //assume the symbol table we have exists on the outermost scope
    var returnedVal: typeCheckResult = hasErrorResult(false)
    if(a.getClassVal(extendedClassID).get.asInstanceOf[classVal].classScope.checkIfVarIDExists(varID)){
      //variable exists at this point, so set returnedVal to the return type
      returnedVal = varValResult(a.getClassVal(extendedClassID).get.asInstanceOf[classVal].classScope.getVariableVal(varID).get.asInstanceOf[variableVal].varValue)
    }else{
      val curClassExtension = a.getClassVal(extendedClassID).get.asInstanceOf[classVal].extendedClass
      curClassExtension match
        case Some(value) =>
          //we have an extended class, run our function to look for the identifier in the inherited class
          returnedVal = visitExtendedClassForVariable(value, varID, a, line)

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
    var expressionType:typeCheckResult = visitBaseExpression(expression.value, a)
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

  //make sure all expressions are ints
  override def visitAddExpression(expression: addExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = {
    checkMathOp(expression.value, a, b, expression.value.line, expression.value.index)
  }
  override def visitExpressionOpt(expressionVal: Option[operation], a: symbolTable, expressionTerminalVal: typeCheckResult): typeCheckResult = super.visitExpressionOpt(expressionVal, a, expressionTerminalVal)

  override def visitCompExpression(expression: compExpression, a: symbolTable): typeCheckResult = {
    expression.optVal match
      case Some(_) =>
        //make sure they are all ints
        val finalType = makeSureCompIsAllInts(expression, a)
        finalType match
          case _:hasErrorResult => finalType
          case _:varValResult => varValResult(booleanType())
      case None => visitBaseExpression(expression.value, a)

  }

  def makeSureCompIsAllInts(expression: compExpression, a: symbolTable): typeCheckResult = {
    expression.optVal match
      case Some(value) =>
        val curInstance = makeSureCompIsAllInts(value, a)
        curInstance match
          case _:hasErrorResult =>
            curInstance
          case _:varValResult =>
            //if we get this far, we know it gave an int because we return an error if we get anything else.
            val leftType = visitBaseExpression(expression.value, a)
            leftType match
              case _:hasErrorResult => leftType
              case result:varValResult =>
                if(!result.varVal.equals(integerType())){
                  curError = Some(typeInconformitiyError(result.varVal, integerType(), expression.line, expression.index))
                  hasErrorResult(true)
                }else{
                  leftType
                }
      case None =>
        val tailType = visitBaseExpression(expression.value, a)
        tailType match
          case _:hasErrorResult => tailType
          case result:varValResult =>
            if(result.varVal!=integerType()){
              curError = Some(typeInconformitiyError(result.varVal, integerType(), expression.line, expression.index))
              hasErrorResult(true)
            }else{
              result
            }

  }

  override def visitAndExpression(curType: typeCheckResult, curExpression: andExpression, a: symbolTable): typeCheckResult = {

    //and value exists
    //first make sure curType is not an errorType
    curType match
      case currentType:varValResult =>
        curExpression.leftVal match
          case Some(andVal) =>
            visit(andVal, a) match
              case andType:varValResult =>
                //if andType and curType are not equal to boolean, we have a type inconformity error
                if(!currentType.varVal.equals(booleanType()) || !andType.varVal.equals(booleanType())){
                  if(!currentType.varVal.equals(booleanType())){
                    curError = Some(typeInconformitiyError(currentType.varVal, booleanType(), andVal.leftVal.line, andVal.leftVal.index))
                  }else{
                    curError = Some(typeInconformitiyError(andType.varVal, booleanType(), andVal.leftVal.line, andVal.leftVal.index))
                  }
                  hasErrorResult(true)
                }else{
                  varValResult(booleanType())
                }

              case andType:hasErrorResult => andType
          case None => curType
      case _ => curType


  }

  def checkMathOp(expression: expressionValue, a: symbolTable, b: typeCheckResult, line: Integer, index: Integer): typeCheckResult = {
    if (b.isInstanceOf[hasErrorResult]) {
      b
    } else {
      b match
        case leftType: varValResult =>
          leftType.varVal match
            case _: integerType =>
              //now make sure the right side returns an int
              val RightType = visitBaseExpression(expression, a)
              RightType match
                case rightSideType:varValResult =>
                  rightSideType.varVal match
                    case _:integerType => varValResult(integerType())
                    case _  =>
                      curError = Some(typeInconformitiyError(rightSideType.varVal, integerType(), line, index))
                      hasErrorResult(true)

                case _ => RightType
            case _ =>
              curError = Some(typeInconformitiyError(b.asInstanceOf[varValResult].varVal, integerType(), line, index))
              hasErrorResult(true)
        //previous type check yielded an error value
        case _ => b
    }

  }


  //make sure all expressions are ints
  override def visitSubtractExpression(expression: subtractExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = {
    checkMathOp(expression.value, a, b, expression.value.line, expression.value.index)
  }

  //make sure all expressions are ints
  override def visitMultiplyExpression(expression: multiplyExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = {
    checkMathOp(expression.value, a, b, expression.value.line, expression.value.index)
  }

  //make sure b is an int array type. If it is, return an integer type
  override def visitArrayLengthExpression(expression: arrayLengthExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = {
    var returnVal: typeCheckResult = hasErrorResult(false)
    if (b.isInstanceOf[hasErrorResult]) {
      returnVal = b
    }else if(b.asInstanceOf[varValResult].varVal.equals(intArrayType())){
      returnVal = varValResult(integerType())
      returnVal = visitExpressionOpt(expression.operation, a, returnVal)
    }else{
      curError = Some(typeInconformitiyError(b.asInstanceOf[varValResult].varVal, intArrayType(), expression.line, expression.index))
      returnVal = hasErrorResult(true)
    }

    returnVal
  }

  //make sure b is an int array type. If it is, make sure the index is an integer. If these checks pass, return an integer type
  override def visitArrayIndexExpression(expression: arrayIndexExpression, a: symbolTable, b: typeCheckResult): typeCheckResult = {
    var returnVal: typeCheckResult = hasErrorResult(false)
    b match {
      case _: hasErrorResult =>
        returnVal = b
      case identifierType:varValResult =>
        identifierType.varVal match
          case _:intArrayType =>
            val indexVal = visit(expression.value, a)
            indexVal match
              case result: hasErrorResult => returnVal = result
              case result: varValResult =>
                if (!result.varVal.equals(integerType())) {
                  curError = Some(typeInconformitiyError(result.varVal, integerType(), expression.value.leftVal.line, expression.value.leftVal.index))
                  returnVal = hasErrorResult(true)
                } else {
                  returnVal = result
                  returnVal = visitExpressionOpt(expression.operation, a, returnVal)
                  returnVal
                }
          case _ =>
            curError = Some(typeInconformitiyError(b.asInstanceOf[AST_Grammar.varValResult].varVal, intArrayType(), expression.value.leftVal.line, expression.value.leftVal.index))
            returnVal = hasErrorResult(true)
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
    if (b.isInstanceOf[hasErrorResult]) {
      return b
    } else {
      b.asInstanceOf[varValResult].varVal match
        //we're ok so long as the var type is a class
        case result: classType =>
          className = result.clazz
          //keep a reference of the class type that the method belongs to
          expression.classType = result
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
    expression.paramTypes = paramVarTypes.toList
    //if there was some error going through the expressions, we're not going to bother looking for the method
    if (!returnedVal.asInstanceOf[hasErrorResult].errorVal) {
      val methodKey = (methodName, paramVarTypes.toList)
      //method exists in th current scope
      //get method keys for current class
      if (a.getParentTable.get.getParentTable.get.getClassVal(className).get.asInstanceOf[classVal].classScope.checkIfMethodIDExists(methodKey)) {
        returnedVal = varValResult(a.getParentTable.get.getParentTable.get.getClassVal(className).get.asInstanceOf[classVal].classScope.getMethodVal(methodKey).get.asInstanceOf[methodVal].returnType)
        //do a check for any possible operation arg
        expression.returnType = returnedVal.asInstanceOf[varValResult].varVal
        returnedVal = visitTerminalTail(expression.operation, a, returnedVal)
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
              expression.returnType = returnedVal.asInstanceOf[varValResult].varVal
              //do a check for any possible operation arg
              expression.paramTypes = possibleKey.asInstanceOf[(String, List[varType])]._2
              returnedVal = visitTerminalTail(expression.operation, a, returnedVal)
              returnedVal
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
                returnedVal = checkForMethod(methodKey, extended, a.getParentTable.get.getParentTable.get, expression)
                if (returnedVal.isInstanceOf[hasErrorResult]) {
                  curError = Some(noSuchMethodError(expression.funcName.name, paramVarTypes.toList, expression.line))
                }else{
                  expression.returnType = returnedVal.asInstanceOf[varValResult].varVal
                  returnedVal = visitTerminalTail(expression.operation, a, returnedVal)
                }
              case None =>
                curError = Some(noSuchMethodError(expression.funcName.name, paramVarTypes.toList, expression.line))
                result.errorVal = true
          case _ =>
            expression.returnType = returnedVal.asInstanceOf[varValResult].varVal
            //do a check for any possible operation arg
            returnedVal = visitTerminalTail(expression.operation, a, returnedVal)
        }
        returnedVal
      }
    } else {
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
  
  

  def checkForMethod(key: (String, List[varType]), curClassName: String, programTable: symbolTable, expression: methodFunctionCallExpression): typeCheckResult ={
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
            expression.paramTypes = possibleKey.asInstanceOf[(String, List[varType])]._2
            returnedVal = varValResult(classVal.classScope.getMethodVal(possibleKey).get.asInstanceOf[methodVal].returnType)
          }
        }
      }
    }

    //if neither of those worked, check for more parents
    if (returnedVal.isInstanceOf[hasErrorResult] && classVal.extendedClass.isDefined) {
      returnedVal = checkForMethod(key, classVal.extendedClass.get, programTable, expression)
    }

    returnedVal
  }
}