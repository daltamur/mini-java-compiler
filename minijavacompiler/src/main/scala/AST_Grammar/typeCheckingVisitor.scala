package AST_Grammar

class typeCheckingVisitor extends ASTVisitor[symbolTable, Unit] {
  private var curError: Option[error] = None

  def getCurError(): Option[error] = curError
  override def visit(currentNode: ASTNode, a: symbolTable): Unit = super.visit(currentNode, a)

  override def visitExpression(expressionVal: expression, a: symbolTable): Unit = super.visitExpression(expressionVal, a)

  override def visitGoal(goal: goal, a: symbolTable): Unit = super.visitGoal(goal, a)

  override def visitIdentifier(identifier: identifier, a: symbolTable): Unit = super.visitIdentifier(identifier, a)

  override def visitVarDec(decs: variableDecs, a: symbolTable): Unit = super.visitVarDec(decs, a)

  override def visitMainClass(clazz: mainClass, a: symbolTable): Unit = super.visitMainClass(clazz, a)

  override def visitClass(klass: klass, a: symbolTable): Unit = super.visitClass(klass, a)

  override def visitMethod(method: method, a: symbolTable): Unit = super.visitMethod(method, a)

  override def visitDataType(dataType: dataType, a: symbolTable): Unit = super.visitDataType(dataType, a)

  override def visitBlockStatement(statement: blockStatement, a: symbolTable): Unit = super.visitBlockStatement(statement, a)

  override def visitIfStatement(statement: ifStatement, a: symbolTable): Unit = super.visitIfStatement(statement, a)

  override def visitWhileStatement(statement: whileStatement, a: symbolTable): Unit = super.visitWhileStatement(statement, a)

  override def visitPrintStatement(statement: printStatement, a: symbolTable): Unit = super.visitPrintStatement(statement, a)

  override def visitAssignStatement(statement: assignStatement, a: symbolTable): Unit = super.visitAssignStatement(statement, a)

  override def visitArrayAssignStatement(statement: arrayAssignStatement, a: symbolTable): Unit = super.visitArrayAssignStatement(statement, a)

  override def visitThisExpression(expression: thisExpression, a: symbolTable): Unit = super.visitThisExpression(expression, a)

  override def visitBooleanExpression(expression: booleanExpression, a: symbolTable): Unit = super.visitBooleanExpression(expression, a)

  override def visitIntegerExpression(expression: integerExpression, a: symbolTable): Unit = super.visitIntegerExpression(expression, a)

  override def visitCharacterExpression(expression: characterExpression, a: symbolTable): Unit = super.visitCharacterExpression(expression, a)

  override def visitIdentiferExpression(expression: identifierExpression, a: symbolTable): Unit = super.visitIdentiferExpression(expression, a)

  override def visitNewArrayExpression(expression: newArrayExpression, a: symbolTable): Unit = super.visitNewArrayExpression(expression, a)

  override def visitNewClassInstanceExpression(expression: newClassInstanceExpression, a: symbolTable): Unit = super.visitNewClassInstanceExpression(expression, a)

  override def visitNegatedExpression(expression: negatedExpression, a: symbolTable): Unit = super.visitNegatedExpression(expression, a)

  override def visitParenthesizedExpression(expression: parenthesizedExpression, a: symbolTable): Unit = super.visitParenthesizedExpression(expression, a)

  override def visitAndExpression(expression: andExpression, a: symbolTable): Unit = super.visitAndExpression(expression, a)

  override def visitAddExpression(expression: addExpression, a: symbolTable): Unit = super.visitAddExpression(expression, a)

  override def visitCompareExpression(expression: compareExpression, a: symbolTable): Unit = super.visitCompareExpression(expression, a)

  override def visitSubtractExpression(expression: subtractExpression, a: symbolTable): Unit = super.visitSubtractExpression(expression, a)

  override def visitMultiplyExpression(expression: multiplyExpression, a: symbolTable): Unit = super.visitMultiplyExpression(expression, a)

  override def visitArrayLengthExpression(expression: arrayLengthExpression, a: symbolTable): Unit = super.visitArrayLengthExpression(expression, a)

  override def visitArrayIndexExpression(expression: arrayIndexExpression, a: symbolTable): Unit = super.visitArrayIndexExpression(expression, a)

  override def visitMethodFunctionCallExpression(expression: methodFunctionCallExpression, a: symbolTable): Unit = super.visitMethodFunctionCallExpression(expression, a)

  override def visitNoTail: Unit = super.visitNoTail
}
