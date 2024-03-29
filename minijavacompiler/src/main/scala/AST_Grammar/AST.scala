package AST_Grammar

import antlr.ASTNULLType

import scala.collection.mutable.ListBuffer


sealed trait ASTNode

//root node
case class goal (main: mainClass, classes: List[Option[klass]]) extends ASTNode

//identifier case
case class identifier(name: String) extends ASTNode{
  var parentName: String = ""
  var isLocal: Boolean = false
  var isParameter: Boolean = false
  var paramIndex: Option[Integer] = None
  var variableType: varType = null
}

//variable declaration case
case class variableDecs(typeval: dataType, name: identifier, line: Integer) extends ASTNode

//class cases
case class mainClass(className: identifier, commandLineArgs: identifier, body: statement, line: Integer) extends ASTNode
case class klass(className: identifier, extendedClassName: Option[identifier], variables: List[Option[variableDecs]], methods: List[Option[method]], line: Integer) extends ASTNode

//method case
case class method(returnType: dataType, methodName: identifier, params: List[(dataType, identifier)], variables: List[Option[variableDecs]], statements: List[Option[statement]], returnVal: expression,line: Integer) extends ASTNode

//type cases
sealed trait dataType extends ASTNode
case class intArray() extends dataType
case class boolean() extends dataType
case class integer() extends dataType
case class character() extends dataType
case class identifierType(name: identifier) extends dataType

//statement cases
sealed class statement() extends ASTNode

case class blockStatement(statements: List[statement]) extends statement

case class ifStatement(condition: expression, thenStatement: statement, elseStatement: statement) extends statement

case class whileStatement(condition: expression, thenStatement: statement) extends statement

case class printStatement(value: expression, line: Integer) extends statement

case class assignStatement(idVal: identifier, value: expression, line: Integer) extends statement

case class arrayAssignStatement(idVal: identifier, arrayIndex: expression, value: expression) extends statement

//expression cases

case class expressionValue(leftVal: expressionTerminal, rightVal: Option[expressionTail], line: Integer, index: Integer)
case class expression(leftVal: compExpression, rightVal: Option[andExpression]) extends ASTNode{
  var typeValue: Option[varType] = None
}
case class compExpression(value: expressionValue, optVal: Option[compExpression], line:Integer, index:Integer) extends ASTNode
case class andExpression(leftVal:Option[expression]) extends ASTNode
sealed class expressionTerminal() extends ASTNode
case class thisExpression(line: Integer, index: Integer) extends expressionTerminal
case class booleanExpression(value: Boolean, line: Integer, index: Integer) extends expressionTerminal
case class integerExpression(value: Integer, line: Integer, index: Integer) extends expressionTerminal
case class characterExpression(value: String, line: Integer, index: Integer) extends expressionTerminal
case class identifierExpression(value: identifier, line: Integer, index: Integer) extends expressionTerminal{
  var isLocal: Boolean = false
  var isParameter: Boolean = false
  var paramIndex: Option[Integer] = None
  var variableType: varType = null
}
case class newArrayExpression(size: expression, line: Integer, index: Integer) extends expressionTerminal
case class newClassInstanceExpression(classType: identifier, line: Integer, index: Integer) extends expressionTerminal
case class negatedExpression(value: expressionValue, line: Integer, index: Integer) extends expressionTerminal
case class parenthesizedExpression(value: expression, line: Integer, index: Integer) extends expressionTerminal

sealed class expressionTail() extends ASTNode

sealed class operation() extends expressionTail
case class addExpression(value: expressionValue) extends operation
case class subtractExpression(value: expressionValue) extends operation
case class multiplyExpression(value: expressionValue) extends operation
case class arrayLengthExpression(line: Integer, index: Integer,var operation: Option[operation]) extends expressionTail
case class arrayIndexExpression(value: expression, var operation: Option[operation]) extends expressionTail
case class methodFunctionCallExpression(funcName: identifier, params: List[expression], line: Integer, var operation: Option[expressionTail]) extends expressionTail{
  var classType: varType = null
  var returnType: varType = null
  var paramTypes: List[varType] =null
}









//node declarations for the symbol table
sealed trait symbolTableVal

def getVarType(dType: dataType): varType = {
  dType match
    case x: identifierType => classType(x.name.name)
    case _: intArray => intArrayType()
    case _: boolean => booleanType()
    case _: integer => integerType()
    case _: character => characterType()
}

def varTypeToString(vType: varType): String = {
  vType match
    case x: classType => x.clazz
    case _: intArrayType => "int[]"
    case _: booleanType => "boolean"
    case _: integerType => "int"
    case _: characterType => "char"
}
sealed trait varType extends symbolTableVal
case class intArrayType() extends varType

case class commandLineArgs() extends varType
case class booleanType() extends varType
case class integerType() extends varType
case class characterType() extends varType
case class classType(clazz: String) extends varType {
  var isMainClass:Boolean = false
}
case class voidType() extends varType
case class methodVal(methodScope: symbolTable, paramTypes: List[varType], returnType: varType, line: Integer) extends symbolTableVal

case class classVal(classScope: symbolTable, extendedClass: Option[String], line: Integer) extends symbolTableVal{
  var isMainClass:Boolean = false
}

case class variableVal(varValue: varType, line: Integer, isMethodParam: Boolean, variableIndex: Integer) extends symbolTableVal

case class programVal(program: symbolTable) extends symbolTableVal

sealed trait typeCheckResult

case class hasErrorResult(var errorVal: Boolean) extends typeCheckResult

case class varValResult(varVal: varType) extends typeCheckResult

//error types
sealed trait error{
  val errorVal: String
}
case class circularInheritanceError(className: String, parentClassName: String, line: Integer) extends error {
  val errorVal: String = "ERROR on line "+line+": Circular Inheritance happening at Class " + className + " extending " + parentClassName
}

case class noSuchParentMethodError(parentClassName: String, line: Integer) extends error {
  val errorVal: String = "ERROR on line "+line+": Extended class "+parentClassName+" does not exist"
}

case class noSuchReturnType(className: String, methodName: String, returnVal: String, line: Integer) extends error{
  val errorVal: String = "ERROR on line "+line+": method "+methodName+" of class "+className+" has an undefined return type of "+returnVal
}

case class methodOverLoadingError(className: String, methodName: String, varType: List[varType], returnType: varType, line: Integer) extends error{
  val errorVal: String = Error
  private def Error: String = {
    var curError = "ERROR on line "+line+": method "+methodName+"("
    for (typeVal <- varType) {
      curError += varTypeToString(typeVal)
      if (varType.last != typeVal) {
        curError += ", "
      }
    }
    curError += ") returning " + varTypeToString(returnType) + " has already been defined in Class " + className
    curError
  }
}

case class classAlreadyDefinedError(className: String, line: Integer) extends error {
  val errorVal: String = "ERROR on line "+line+": Class "+className+" has already been defined"
}

case class variableAlreadyDefinedError(varName: String, line: Integer) extends error {
  val errorVal: String = "ERROR on line " + line + ": Variable "+varName+" has already been defined in the current scope"
}

case class noSuchVariableDefinedError(varName: String, typeIns: varType,line: Integer) extends error {
  val errorVal: String = "ERROR on line " + line + ": No variable named " + varName + " of type " + varTypeToString(typeIns) + " has been declared in neither the current scope or any inherited scope"
}

case class noSuchVariableUnknownTypeDefinedError(varName: String, line: Integer) extends error {
  val errorVal: String = "ERROR on line " + line + ": No variable named " + varName + " has been declared in neither the current scope or any inherited scope"
}

case class typeInconformitiyError(gotType: varType, expectedType: varType, line: Integer, index: Integer) extends error {
  val errorVal: String = "ERROR on line " + line + ":" +index+ " :expected type " + varTypeToString(expectedType) + ", instead got type " + varTypeToString(gotType)
}

case class noSuchTypeError(givenType: varType, line: Integer, index: Integer) extends  error {
  val errorVal: String = "ERROR on line " + line + ":" + index + " :no such data type of " + varTypeToString(givenType)
}

case class noSuchMethodError(methodName: String, types: List[varType], line: Integer) extends error {
  val errorVal: String = Error

  private def Error: String = {
    var curError = "ERROR on line " + line + ": method " + methodName + "("
    for (typeVal <- types) {
      curError += varTypeToString(typeVal)
      if (types.last != typeVal) {
        curError += ", "
      }
    }
    curError += ") does not exist in this scope or any inherited scope"
    curError
  }
}

case class printError(line: Integer, index: Integer, givenType: varType) extends error{
  val errorVal: String = "ERROR on line " + line + ":" + index + " Print Statements require a Character or Integer, " + varTypeToString(givenType) + " types are currently not supported"
}

case class noSuchClassError(className: String, line: Integer, index: Integer) extends  error {
  val errorVal: String = "ERROR on line " + line + ":" + index + " :no such class named " + className
}

case class notInitializedError(varName: String, line: Integer, index: Integer) extends error {
  val errorVal: String = "ERROT on line " + line + ":" + index + " " + varName + " referenced but never initialized"
}

case class callMethodOnPrimitve(primitive: varType, line: Integer) extends  error {
  val errorVal: String = "ERROR on line " + line + ": cannot call functions on primitives"
}

case class returnTypeError(expectedVal: varType, gotVal: varType, line: Integer) extends error{
  val errorVal: String = "ERROR on line " + line + ": bad method return type, expected "+varTypeToString(expectedVal)+ " and got "+varTypeToString(gotVal)
}