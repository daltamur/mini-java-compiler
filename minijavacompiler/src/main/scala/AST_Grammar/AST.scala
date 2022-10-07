package AST_Grammar



case class goal()

case class mainClass()

case class klass()

case class method()

sealed trait binOp

case object andOp extends binOp
case object subOp extends binOp
case object multOp extends binOp
case object compOp extends binOp

sealed trait expressionTerminal
case class thisTerminal() extends expressionTerminal

case class booleanExpression() extends expressionTerminal

case class integerExpression() extends expressionTerminal

case class charExpression() extends expressionTerminal

case class identifierExpression() extends expressionTerminal

case class intArrayExpression() extends expressionTerminal

case class newClassExpression() extends expressionTerminal

case class negatedExpression() extends expressionTerminal

case class parenthesizedExpression() extends expressionTerminal

sealed trait expressionTail

case class expression(expression: expressionTerminal, expressionT: expressionTail)

case class identifier(name: String)

