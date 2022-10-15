package AST_Grammar


sealed trait ASTNode

//root node
case class goal (main: mainClass, classes: List[Option[klass]]) extends ASTNode

//identifier case
case class identifier(name: String) extends ASTNode

//variable declaration case
case class variableDecs(typeval: dataType, name: identifier) extends ASTNode

//class cases
case class mainClass(className: identifier, commandLineArgs: identifier, body: statement) extends ASTNode
case class klass(className: identifier, extendedClassName: Option[identifier], variables: List[Option[variableDecs]], methods: List[Option[method]]) extends ASTNode

//method case
case class method(returnType: dataType, methodName: identifier, params: List[(dataType, identifier)], variables: List[variableDecs], statements: List[statement], returnVal: expression) extends ASTNode

//type cases
sealed trait dataType extends ASTNode
case class intArray() extends dataType
case class boolean() extends dataType
case class integer() extends dataType
case class character() extends dataType
case class identifierType(name: identifier) extends dataType

//statement cases
sealed trait statement() extends ASTNode

case class blockStatement(statements: List[statement]) extends statement

case class ifStatement(condition: expression, thenStatement: statement, elseStatement: statement) extends statement

case class whileStatement(condition: expression, thenStatement: statement) extends statement

case class printStatement(value: expression) extends statement

case class assignStatement(idVal: identifier, value: expression) extends statement

case class arrayAssignStatement(idVal: identifier, arrayIndex: expression, value: expression) extends statement

//expression cases
sealed trait expressionTerminal() extends ASTNode
case class thisExpression() extends expressionTerminal
case class booleanExpression(value: Boolean) extends expressionTerminal
case class integerExpression(value: Integer) extends expressionTerminal
case class characterExpression(value: String) extends expressionTerminal
case class identifierExpression(value: identifier) extends expressionTerminal
case class newArrayExpression(size: expression) extends expressionTerminal
case class newClassInstanceExpression(classType: identifier) extends expressionTerminal
case class negatedExpression(value: expression) extends expressionTerminal
case class parenthesizedExpression(value: expression) extends expressionTerminal

sealed trait expressionTail() extends ASTNode

case class andExpression(value: expression) extends expressionTail
case class addExpression(value: expression) extends expressionTail
case class compareExpression(value: expression) extends expressionTail
case class subtractExpression(value: expression) extends expressionTail
case class multiplyExpression(value: expression) extends expressionTail
case class arrayLengthExpression() extends expressionTail

case class arrayIndexExpression(value: expression) extends expressionTail
case class methodFunctionCallExpression(funcName: identifier, params: List[expression]) extends expressionTail
case class expression(expressionTerm: expressionTerminal, expressionOpt: Option[expressionTail]) extends ASTNode









//node declarations for the symbol table
sealed trait symbolTableVal

def getVarType(dType: dataType): varType = {
  dType match
    case x: identifierType => classType(x.name.name)
    case x: intArray => intArrayType()
    case x: boolean => booleanType()
    case x: integer => intArrayType()
    case x: character => characterType()
}
sealed trait varType
case class intArrayType() extends varType

case class commandLineArgs() extends varType
case class booleanType() extends varType
case class integerType() extends varType
case class characterType() extends varType
case class classType(clazz: String) extends varType

case class methodVal(methodScope: symbolTable, paramTypes: List[varType], returnType: varType) extends symbolTableVal

case class classVal(classScope: symbolTable, extendedClass: Option[String]) extends symbolTableVal

case class variableVal(varValue: varType) extends symbolTableVal

case class programVal(program: symbolTable) extends symbolTableVal