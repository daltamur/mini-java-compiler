package AST_Grammar

import scala.util.control.Breaks

class typeCheckingVisitor extends ASTVisitor[symbolTable, typeCheckResult] {

  private var curError: Option[error] = None
  def getCurError: Option[error] = curError

  //get the expression terminal var type. Only certain expression tails can follow specific expression terminals
  //(for example, if we have a 'this' expression terminal, the only possible tails could be a method function call
  //or no tail). If we don't get the proper pattern, throw the proper error
  override def visitExpression(expressionVal: expression, a: symbolTable): typeCheckResult = super.visitExpression(expressionVal, a)

  //check all the statements in the methods in each class (make sure the print statement is an integer)
  //returns true if there is an error, otherwise returns false
  override def visitGoal(goal: goal, a: symbolTable): typeCheckResult = {
    //assume we have no errors to begin with, only change is the main class or class throw an error
    val loop = Breaks
    var hasError = hasErrorResult(false)
    //first type check the main class statement
    hasError = visit(goal.main, a).asInstanceOf[hasErrorResult]
    loop.breakable {
      for (curClass <- goal.classes) {
        if(hasError.errorVal){
          loop.break()
        }
        //type check each class' statement
        hasError = visit(curClass.get, a).asInstanceOf[hasErrorResult]
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
    hasError = visit(clazz.body, a).asInstanceOf[hasErrorResult]
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
        hasError = visit(method.get, a).asInstanceOf[hasErrorResult]
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
        hasError = visit(statement, a).asInstanceOf[hasErrorResult]
      }
    }
    hasError
  }
  
  //each statement visit method will return true or false indicating that a type check failed

  //type check each statement in the block
  override def visitBlockStatement(statement: blockStatement, a: symbolTable): typeCheckResult = super.visitBlockStatement(statement, a)


  //make sure conditional is a boolean, type check the statements in the bodies
  override def visitIfStatement(statement: ifStatement, a: symbolTable): typeCheckResult = super.visitIfStatement(statement, a)

  //make sure conditional is a boolean, type check the statement in the body
  override def visitWhileStatement(statement: whileStatement, a: symbolTable): typeCheckResult = super.visitWhileStatement(statement, a)

  //make sure expresison in print statement is an integer
  override def visitPrintStatement(statement: printStatement, a: symbolTable): typeCheckResult = super.visitPrintStatement(statement, a)

  //get type of left side of assign statement, make sure right side is of the same type
  override def visitAssignStatement(statement: assignStatement, a: symbolTable): typeCheckResult = super.visitAssignStatement(statement, a)

  //make sure the expression in the brackets is an int, make sure the assigned value is an int
  override def visitArrayAssignStatement(statement: arrayAssignStatement, a: symbolTable): typeCheckResult = super.visitArrayAssignStatement(statement, a)
  
  
  //
  //return whatever the name of the current class is
  override def visitThisExpression(expression: thisExpression, a: symbolTable): typeCheckResult = super.visitThisExpression(expression, a)

  //just return boolean var type
  override def visitBooleanExpression(expression: booleanExpression, a: symbolTable): typeCheckResult = super.visitBooleanExpression(expression, a)

  //just return integer var type
  override def visitIntegerExpression(expression: integerExpression, a: symbolTable): typeCheckResult = super.visitIntegerExpression(expression, a)

  //just return character var type
  override def visitCharacterExpression(expression: characterExpression, a: symbolTable): typeCheckResult = super.visitCharacterExpression(expression, a)

  //just return a class var type
  override def visitIdentiferExpression(expression: identifierExpression, a: symbolTable): typeCheckResult = super.visitIdentiferExpression(expression, a)

  //just return array type
  override def visitNewArrayExpression(expression: newArrayExpression, a: symbolTable): typeCheckResult = super.visitNewArrayExpression(expression, a)

  //just return class var type
  override def visitNewClassInstanceExpression(expression: newClassInstanceExpression, a: symbolTable): typeCheckResult = super.visitNewClassInstanceExpression(expression, a)

  //just make sure the expression is of type boolean
  override def visitNegatedExpression(expression: negatedExpression, a: symbolTable): typeCheckResult = super.visitNegatedExpression(expression, a)

  //get type of expression
  override def visitParenthesizedExpression(expression: parenthesizedExpression, a: symbolTable): typeCheckResult = super.visitParenthesizedExpression(expression, a)

  // make sure expression is of type boolean, if it is, return var type bool
  override def visitAndExpression(expression: andExpression, a: symbolTable): typeCheckResult = super.visitAndExpression(expression, a)

  //make sure the expression is of type int. If it is, return var type int
  override def visitAddExpression(expression: addExpression, a: symbolTable): typeCheckResult = super.visitAddExpression(expression, a)

  //make sure the expression is of type book, If it is, return var type bool
  override def visitCompareExpression(expression: compareExpression, a: symbolTable): typeCheckResult = super.visitCompareExpression(expression, a)

  //make sure the expression is of type int. If it is, return var type int
  override def visitSubtractExpression(expression: subtractExpression, a: symbolTable): typeCheckResult = super.visitSubtractExpression(expression, a)

 ////make sure the expression is of type int. If it is, return var type int
  override def visitMultiplyExpression(expression: multiplyExpression, a: symbolTable): typeCheckResult = super.visitMultiplyExpression(expression, a)

  //return type int
  override def visitArrayLengthExpression(expression: arrayLengthExpression, a: symbolTable): typeCheckResult = super.visitArrayLengthExpression(expression, a)

  //make sure the expression, is of type int. If it is, return true, else return false
  override def visitArrayIndexExpression(expression: arrayIndexExpression, a: symbolTable): typeCheckResult = super.visitArrayIndexExpression(expression, a)

  //make sure the method exists either in the current class or some class in its hierarchy, if it does, return whatever the method's return type is
  override def visitMethodFunctionCallExpression(expression: methodFunctionCallExpression, a: symbolTable): typeCheckResult = super.visitMethodFunctionCallExpression(expression, a)

}
