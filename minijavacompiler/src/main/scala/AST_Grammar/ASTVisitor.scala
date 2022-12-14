package AST_Grammar


//A represents the thing that the visitor is going to build using the visitor. In our case, it is either
//Unit or symbolTable
abstract class ASTVisitor[A, B] {
  def visit(currentNode: ASTNode, a:A): B = {
    currentNode match
      case x: goal => visitGoal(x, a)
      case x: identifier => visitIdentifier(x, a)
      case x: variableDecs => visitVarDec(x, a)
      case x: mainClass => visitMainClass(x, a)
      case x: klass => visitClass(x, a)
      case x: method => visitMethod(x, a)
      //not many data types so we can just do a switch statement at this one
      case x: dataType => visitDataType(x, a)
      //statements
      case x: blockStatement => visitBlockStatement(x, a)
      case x: ifStatement => visitIfStatement(x, a)
      case x: whileStatement => visitWhileStatement(x, a)
      case x: printStatement => visitPrintStatement(x, a)
      case x: assignStatement => visitAssignStatement(x, a)
      case x: arrayAssignStatement => visitArrayAssignStatement(x, a)
      case x: expression => visitExpression(x, a)
  }

  def visitExpression(expressionVal: expression, a:A): B = {
    var curType = visitCompExpression(expressionVal.leftVal, a)
    expressionVal.rightVal match
      case Some(value) =>
        curType = visitAndExpression(curType, value, a)
        curType
      case _ => curType
  }
  
  def visitTerminalExpression(currentNode: expressionTerminal, a:A): B ={
      currentNode match
        case x: thisExpression => visitThisExpression(x, a)
        case x: booleanExpression => visitBooleanExpression(x, a)
        case x: integerExpression => visitIntegerExpression(x, a)
        case x: characterExpression => visitCharacterExpression(x, a)
        case x: identifierExpression => visitIdentiferExpression(x, a)
        case x: newArrayExpression => visitNewArrayExpression(x, a)
        case x: newClassInstanceExpression => visitNewClassInstanceExpression(x, a)
        case x: negatedExpression => visitNegatedExpression(x, a)
        case x: parenthesizedExpression => visitParenthesizedExpression(x, a)
  }

  def visitExpressionTail(currentNode: Option[expressionTail], a: A, expressionTerminalVal: B): B = {
    currentNode match
      case Some(value) =>
        value match
          case x: addExpression => visitAddExpression(x, a, expressionTerminalVal)
          case x: subtractExpression => visitSubtractExpression(x, a, expressionTerminalVal)
          case x: multiplyExpression => visitMultiplyExpression(x, a, expressionTerminalVal)
          case x: arrayLengthExpression => visitArrayLengthExpression(x, a, expressionTerminalVal)
          case x: arrayIndexExpression => visitArrayIndexExpression(x, a, expressionTerminalVal)
          case x: methodFunctionCallExpression => visitMethodFunctionCallExpression(x, a, expressionTerminalVal)
      case _ => visitNoTail(expressionTerminalVal)
  }

  def visitBaseExpression(currentNode: expressionValue, a:A): B = {
    val expressionTerminalVal = {
      currentNode.leftVal match
        case x: thisExpression => visitThisExpression(x, a)
        case x: booleanExpression => visitBooleanExpression(x, a)
        case x: integerExpression => visitIntegerExpression(x, a)
        case x: characterExpression => visitCharacterExpression(x, a)
        case x: identifierExpression => visitIdentiferExpression(x, a)
        case x: newArrayExpression => visitNewArrayExpression(x, a)
        case x: newClassInstanceExpression => visitNewClassInstanceExpression(x, a)
        case x: negatedExpression => visitNegatedExpression(x, a)
        case x: parenthesizedExpression => visitParenthesizedExpression(x, a)
    }

    currentNode.rightVal match
      case Some(value) =>
        value match
          case x: addExpression => visitAddExpression(x, a, expressionTerminalVal)
          case x: subtractExpression => visitSubtractExpression(x, a, expressionTerminalVal)
          case x: multiplyExpression => visitMultiplyExpression(x, a, expressionTerminalVal)
          case x: arrayLengthExpression => visitArrayLengthExpression(x, a, expressionTerminalVal)
          case x: arrayIndexExpression => visitArrayIndexExpression(x, a, expressionTerminalVal)
          case x: methodFunctionCallExpression => visitMethodFunctionCallExpression(x, a, expressionTerminalVal)
      case _ => visitNoTail(expressionTerminalVal)
  }

  def visitTerminalTail(expressionVal: Option[AST_Grammar.expressionTail], a:A, expressionTerminalVal:B): B = {
    expressionVal match
      case Some(value) =>
        value match
          case x: addExpression => visitAddExpression(x, a, expressionTerminalVal)
          case x: subtractExpression => visitSubtractExpression(x, a, expressionTerminalVal)
          case x: multiplyExpression => visitMultiplyExpression(x, a, expressionTerminalVal)
          case x: arrayLengthExpression => visitArrayLengthExpression(x, a, expressionTerminalVal)
          case x: arrayIndexExpression => visitArrayIndexExpression(x, a, expressionTerminalVal)
          case x: methodFunctionCallExpression => visitMethodFunctionCallExpression(x, a, expressionTerminalVal)
      case _ => visitNoTail(expressionTerminalVal)
  }
  def visitExpressionOpt(expressionVal: Option[operation], a:A, expressionTerminalVal:B): B ={
    expressionVal match
      case Some(value) =>
        value match
          case x: addExpression => visitAddExpression(x, a, expressionTerminalVal)
          case x: subtractExpression => visitSubtractExpression(x, a, expressionTerminalVal)
          case x: multiplyExpression => visitMultiplyExpression(x, a, expressionTerminalVal)
      case _ => visitNoTail(expressionTerminalVal)
  }

  def visitCompExpression(expression: compExpression, a: A): B = ???

  def visitAndExpression(curType: B, curExpression:andExpression, a:A): B = ???

  def visitGoal(goal: goal, a: A): B = ???


  def visitIdentifier(identifier: identifier, a: A): B = ???

  def visitVarDec(decs: variableDecs, a: A): B = ???

  def visitMainClass(clazz: mainClass, a: A): B = ???

  def visitClass(klass: klass, a: A): B = ???

  def visitMethod(method: method, a: A): B = ???

  def visitDataType(dataType: dataType, a: A): B = ???

  def visitBlockStatement(statement: blockStatement, a: A): B = ???

  def visitIfStatement(statement: ifStatement, a: A): B = ???

  def visitWhileStatement(statement: whileStatement, a: A): B = ???

  def visitPrintStatement(statement: printStatement, a: A): B = ???

  def visitAssignStatement(statement: assignStatement, a: A): B = ???

  def visitArrayAssignStatement(statement: arrayAssignStatement, a: A): B = ???

  def visitThisExpression(expression: thisExpression, a: A): B = ???

  def visitBooleanExpression(expression: booleanExpression, a: A): B = ???

  def visitIntegerExpression(expression: integerExpression, a: A): B = ???

  def visitCharacterExpression(expression: characterExpression, a: A): B = ???

  def visitIdentiferExpression(expression: identifierExpression, a: A): B = ???

  def visitNewArrayExpression(expression: newArrayExpression, a: A): B = ???

  def visitNewClassInstanceExpression(expression: newClassInstanceExpression, a: A): B = ???

  def visitNegatedExpression(expression: negatedExpression, a: A): B = ???

  def visitParenthesizedExpression(expression: parenthesizedExpression, a: A): B = ???

  def visitAndExpression(expression: andExpression, a: A, b: B): B = ???

  def visitAddExpression(expression: addExpression, a: A, b: B): B = ???

  def visitSubtractExpression(expression: subtractExpression, a: A, b: B): B = ???

  def visitMultiplyExpression(expression: multiplyExpression, a: A, b: B): B = ???

  def visitArrayLengthExpression(expression: arrayLengthExpression, a: A, b: B): B = ???

  def visitArrayIndexExpression(expression: arrayIndexExpression, a: A, b: B): B = ???

  def visitMethodFunctionCallExpression(expression: methodFunctionCallExpression, a: A, b: B): B = ???

  def visitNoTail(previousExpressionVal: B): B = ???
}