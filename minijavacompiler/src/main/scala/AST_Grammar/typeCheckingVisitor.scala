package AST_Grammar

class typeCheckingVisitor extends ASTVisitor[symbolTable, Unit] {

  private var curError: Option[error] = None
  def getCurError: Option[error] = curError

  //get the expression terminal var type. Only certain expression tails can follow specific expression terminals
  //(for example, if we have a 'this' expression terminal, the only possible tails could be a method function call
  //or no tail). If we don't get the proper pattern, throw the proper error
  override def visitExpression(expressionVal: expression, a: symbolTable): Unit = super.visitExpression(expressionVal, a)

  //check all the statements in the methods in each class (make sure the print statement is an integer)
  override def visitGoal(goal: goal, a: symbolTable): Unit = super.visitGoal(goal, a)

  //return what type the identifier is
  override def visitIdentifier(identifier: identifier, a: symbolTable): Unit = super.visitIdentifier(identifier, a)

  //just make sure the statement has no type check errors
  override def visitMainClass(clazz: mainClass, a: symbolTable): Unit = super.visitMainClass(clazz, a)

  //type check the statements in each method, it will return true if any errors are found and false otherwise
  override def visitClass(klass: klass, a: symbolTable): Unit = super.visitClass(klass, a)

  //type check the statements, return true if any of them make an error
  override def visitMethod(method: method, a: symbolTable): Unit = super.visitMethod(method, a)

  //type check each statement in the block
  override def visitBlockStatement(statement: blockStatement, a: symbolTable): Unit = super.visitBlockStatement(statement, a)


  //make sure conditional is a boolean, type check the statements in the bodies
  override def visitIfStatement(statement: ifStatement, a: symbolTable): Unit = super.visitIfStatement(statement, a)

  //make sure conditional is a boolean, type check the statement in the body
  override def visitWhileStatement(statement: whileStatement, a: symbolTable): Unit = super.visitWhileStatement(statement, a)

  //make sure expresison in print statement is an integer
  override def visitPrintStatement(statement: printStatement, a: symbolTable): Unit = super.visitPrintStatement(statement, a)

  //get type of left side of assign statement, make sure right side is of the same type
  override def visitAssignStatement(statement: assignStatement, a: symbolTable): Unit = super.visitAssignStatement(statement, a)

  //make sure the expression in the brackets is an int, make sure the assigned value is an int
  override def visitArrayAssignStatement(statement: arrayAssignStatement, a: symbolTable): Unit = super.visitArrayAssignStatement(statement, a)

  //return whatever the name of the current class is
  override def visitThisExpression(expression: thisExpression, a: symbolTable): Unit = super.visitThisExpression(expression, a)

  //just return boolean var type
  override def visitBooleanExpression(expression: booleanExpression, a: symbolTable): Unit = super.visitBooleanExpression(expression, a)

  //just return integer var type
  override def visitIntegerExpression(expression: integerExpression, a: symbolTable): Unit = super.visitIntegerExpression(expression, a)

  //just return character var type
  override def visitCharacterExpression(expression: characterExpression, a: symbolTable): Unit = super.visitCharacterExpression(expression, a)

  //just return a class var type
  override def visitIdentiferExpression(expression: identifierExpression, a: symbolTable): Unit = super.visitIdentiferExpression(expression, a)

  //just return array type
  override def visitNewArrayExpression(expression: newArrayExpression, a: symbolTable): Unit = super.visitNewArrayExpression(expression, a)

  //just return class var type
  override def visitNewClassInstanceExpression(expression: newClassInstanceExpression, a: symbolTable): Unit = super.visitNewClassInstanceExpression(expression, a)

  //just make sure the expression is of type boolean
  override def visitNegatedExpression(expression: negatedExpression, a: symbolTable): Unit = super.visitNegatedExpression(expression, a)

  //get type of expression
  override def visitParenthesizedExpression(expression: parenthesizedExpression, a: symbolTable): Unit = super.visitParenthesizedExpression(expression, a)

  // make sure expression is of type boolean, if it is, return var type bool
  override def visitAndExpression(expression: andExpression, a: symbolTable): Unit = super.visitAndExpression(expression, a)

  //make sure the expression is of type int. If it is, return var type int
  override def visitAddExpression(expression: addExpression, a: symbolTable): Unit = super.visitAddExpression(expression, a)

  //make sure the expression is of type book, If it is, return var type bool
  override def visitCompareExpression(expression: compareExpression, a: symbolTable): Unit = super.visitCompareExpression(expression, a)

  //make sure the expression is of type int. If it is, return var type int
  override def visitSubtractExpression(expression: subtractExpression, a: symbolTable): Unit = super.visitSubtractExpression(expression, a)

 ////make sure the expression is of type int. If it is, return var type int
  override def visitMultiplyExpression(expression: multiplyExpression, a: symbolTable): Unit = super.visitMultiplyExpression(expression, a)

  //return type int
  override def visitArrayLengthExpression(expression: arrayLengthExpression, a: symbolTable): Unit = super.visitArrayLengthExpression(expression, a)

  //make sure the expression, is of type int. If it is, return true, else return false
  override def visitArrayIndexExpression(expression: arrayIndexExpression, a: symbolTable): Unit = super.visitArrayIndexExpression(expression, a)

  //make sure the method exists either in the current class or some class in its hierarchy, if it does, return whatever the method's return type is
  override def visitMethodFunctionCallExpression(expression: methodFunctionCallExpression, a: symbolTable): Unit = super.visitMethodFunctionCallExpression(expression, a)

}
