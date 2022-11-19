package parseTreeFiles

import AST_Grammar.ASTNode
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.{ErrorNode, TerminalNode}
import parseTreeFiles.grammarOutput.{miniJavaBaseVisitor, miniJavaParser}

import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`
import scala.collection.mutable.ListBuffer
import scala.language.{existentials, postfixOps}
class MiniJavaVisitor extends miniJavaBaseVisitor[Option[ASTNode]] {

  var mainMethodError = false

  def getMainMethodError: Boolean = mainMethodError
  override def visitGoal(ctx: miniJavaParser.GoalContext): Option[ASTNode] = {
    val ctxMainClass = Option(ctx.mainClass())
    val ASTMainClass = ctxMainClass.flatMap(x => x.accept(this))
    val ctxClasses = ctx.classDeclaration()
    val ASTClasses =  new ListBuffer[Any]()
    ctxClasses.forEach(x => ASTClasses.+=(x.accept(this)))
    val goal = Some(AST_Grammar.goal(ASTMainClass.orNull.asInstanceOf[AST_Grammar.mainClass], ASTClasses.toList.asInstanceOf[List[Option[AST_Grammar.klass]]]))
    goal
  }

  override def visitMainClass(ctx: miniJavaParser.MainClassContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    if(ctx.IDENTIFIER(1).getSymbol.getText != "main"){
      println("ERROR on line " + lineNum + ": Main method needs to be named 'main'.")
      mainMethodError = true
    }
    val ctxClassName = Option(ctx.IDENTIFIER(0))
    val ASTClassName = ctxClassName.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val ctxArgName = Option(ctx.IDENTIFIER(2))
    val ASTArgName = ctxArgName.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val ctxStatement = Option(ctx.statement())
    val ASTStatement = ctxStatement.flatMap(x => x.accept(this))
    val mainClass = AST_Grammar.mainClass(ASTClassName.orNull, ASTArgName.orNull, ASTStatement.orNull.asInstanceOf[AST_Grammar.statement], lineNum)
    Some(mainClass)
  }

  override def visitClassDeclaration(ctx: miniJavaParser.ClassDeclarationContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val ctxClassName = Option(ctx.IDENTIFIER(0))
    val ASTClassName = ctxClassName.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val ctxArgName = Option(ctx.IDENTIFIER(1))
    val ASTArgName = ctxArgName.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val ctxVarDecs = ctx.varDeclaration()
    val ASTVarDecs = new ListBuffer[Any]()
    ctxVarDecs.forEach(x => ASTVarDecs += x.accept(this))
    val ctxMethods = ctx.methodDeclaration()
    val ASTMethods = new ListBuffer[Any]()
    ctxMethods.forEach(x => ASTMethods += x.accept(this))
    val classDec = Some(AST_Grammar.klass(ASTClassName.orNull, ASTArgName, ASTVarDecs.toList.asInstanceOf[List[Option[AST_Grammar.variableDecs]]], ASTMethods.toList.asInstanceOf[List[Option[AST_Grammar.method]]], lineNum))
    classDec
  }

  override def visitVarDeclaration(ctx: miniJavaParser.VarDeclarationContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val ctxType = Option(ctx.`type`())
    val ASTType = ctxType.flatMap(x => x.accept(this))
    val ctxIdentifier = Option(ctx.IDENTIFIER())
    val ASTIdentifier = ctxIdentifier.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val varDec = Some(AST_Grammar.variableDecs(ASTType.orNull.asInstanceOf[AST_Grammar.dataType], ASTIdentifier.orNull, lineNum))
    varDec
  }

  override def visitMethodDeclaration(ctx: miniJavaParser.MethodDeclarationContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val ctxType = Option(ctx.`type`(0))
    val ASTType = ctxType.flatMap(x => x.accept(this))
    val ctxMethodName = Option(ctx.IDENTIFIER(0))
    val ASTMethodName = ctxMethodName.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val ctxParamTypes = ctx.`type`()
    val ctxParamIDs = ctx.IDENTIFIER()
    ctxParamTypes.remove(0)
    ctxParamIDs.remove(0)
    val ASTParams = (ctxParamTypes zip ctxParamIDs).map{case (typeVal, id) =>{
      val ASTCurType = Option(typeVal).flatMap(x => x.accept(this))
      val ASTCurId = Option(id).flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
      (ASTCurType.orNull.asInstanceOf[AST_Grammar.dataType], ASTCurId.orNull)
    }}
    val ctxVarDecs = ctx.varDeclaration()
    val ASTVarDecs = new ListBuffer[Any]()
    ctxVarDecs.forEach(x => ASTVarDecs += x.accept(this))
    val ctxStatements = ctx.statement()
    val ASTStatements = new ListBuffer[Any]()
    ctxStatements.forEach(x => ASTStatements += x.accept(this))
    val ctxreturnedVal = Option(ctx.expression())
    val ASTReturnedVal = ctxreturnedVal.flatMap(x => x.accept(this))
    val methodDec = Some(AST_Grammar.method(ASTType.orNull.asInstanceOf[AST_Grammar.dataType],ASTMethodName.orNull, ASTParams.toList, ASTVarDecs.toList.asInstanceOf[List[Option[AST_Grammar.variableDecs]]], ASTStatements.toList.asInstanceOf[List[Option[AST_Grammar.statement]]], ASTReturnedVal.orNull.asInstanceOf[AST_Grammar.expression], lineNum))
    methodDec
  }

  override def visitIntegerArrayType(ctx: miniJavaParser.IntegerArrayTypeContext): Option[ASTNode] = {
    Some(AST_Grammar.intArray())
  }

  override def visitBooleanType(ctx: miniJavaParser.BooleanTypeContext): Option[ASTNode] = {
    Some(AST_Grammar.boolean())
  }

  override def visitIntType(ctx: miniJavaParser.IntTypeContext): Option[ASTNode] = {
    Some(AST_Grammar.integer())
  }

  override def visitCharType(ctx: miniJavaParser.CharTypeContext): Option[ASTNode] = {
    Some(AST_Grammar.character())
  }

  override def visitIdentifierType(ctx: miniJavaParser.IdentifierTypeContext): Option[ASTNode] = {
    val ctxID = Option(ctx.IDENTIFIER())
    val ASTID = ctxID.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    Some(AST_Grammar.identifierType(ASTID.orNull))
  }

  override def visitArrayIndex(ctx: miniJavaParser.ArrayIndexContext): Option[ASTNode] = {
    val ctxIndex = Option(ctx.expression())
    val ASTIndex = ctxIndex.flatMap(x => x.accept(this))
    Some(ASTIndex.orNull.asInstanceOf[AST_Grammar.expression])
  }

  override def visitBlockStatement(ctx: miniJavaParser.BlockStatementContext): Option[ASTNode] = {
    val ctxStatements = ctx.statement()
    val ASTStatements = new ListBuffer[Any]
    ctxStatements.forEach(x => ASTStatements += x.accept(this).get)
    Some(AST_Grammar.blockStatement(ASTStatements.toList.asInstanceOf[List[AST_Grammar.statement]]))
  }

  override def visitIfStatement(ctx: miniJavaParser.IfStatementContext): Option[ASTNode] = {
    val ctxCondition = Option(ctx.expression())
    val ASTCondition = ctxCondition.flatMap(x => x.accept(this))
    val ctxThen = Option(ctx.statement(0))
    val ASTThen = ctxThen.flatMap(x => x.accept(this))
    val ctxElse = Option(ctx.statement(1))
    val ASTElse = ctxElse.flatMap(x => x.accept(this))
    val ifStatement = Some(AST_Grammar.ifStatement(ASTCondition.orNull.asInstanceOf[AST_Grammar.expression], ASTThen.orNull.asInstanceOf[AST_Grammar.statement], ASTElse.orNull.asInstanceOf[AST_Grammar.statement]))
    ifStatement
  }

  override def visitWhileStatement(ctx: miniJavaParser.WhileStatementContext): Option[ASTNode] = {
    val ctxCondition = Option(ctx.expression())
    val ASTCondition = ctxCondition.flatMap(x => x.accept(this))
    val ctxStatement = Option(ctx.statement())
    val ASTStatement = ctxStatement.flatMap(x => x.accept(this))
    val whileStatement = Some(AST_Grammar.whileStatement(ASTCondition.orNull.asInstanceOf[AST_Grammar.expression], ASTStatement.orNull.asInstanceOf[AST_Grammar.statement]))
    whileStatement
  }

  override def visitPrintStatement(ctx: miniJavaParser.PrintStatementContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val ctxPrintedVal = Option(ctx.expression())
    val ASTPrintedVal = ctxPrintedVal.flatMap(x => x.accept(this))
    val printStatement = Some(AST_Grammar.printStatement(ASTPrintedVal.orNull.asInstanceOf[AST_Grammar.expression], lineNum))
    printStatement
  }

  override def visitAssignStatement(ctx: miniJavaParser.AssignStatementContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val ctxVar = Option(ctx.IDENTIFIER())
    val ASTVar = ctxVar.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val ctxVal = Option(ctx.expression())
    val ASTVal = ctxVal.flatMap(x => x.accept(this))
    val ASTAssign = Some(AST_Grammar.assignStatement(ASTVar.orNull, ASTVal.orNull.asInstanceOf[AST_Grammar.expression], lineNum))
    ASTAssign
  }

  override def visitArrayAssignStatement(ctx: miniJavaParser.ArrayAssignStatementContext): Option[ASTNode] = {
    val ctxArrayRef = Option(ctx.IDENTIFIER())
    val ASTArrayRef = ctxArrayRef.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val ctxArrayIndex = Option(ctx.arrayIndex())
    val ASTArrayIndex = ctxArrayIndex.flatMap(x => x.accept(this))
    val ctxVal = Option(ctx.expression())
    val ASTVal = ctxVal.flatMap(x => x.accept(this))
    val arrayAssignment = Some(AST_Grammar.arrayAssignStatement(ASTArrayRef.orNull, ASTArrayIndex.orNull.asInstanceOf[AST_Grammar.expression], ASTVal.orNull.asInstanceOf[AST_Grammar.expression]))
    arrayAssignment
  }

  override def visitMethodFuncCall(ctx: miniJavaParser.MethodFuncCallContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val ctxMethodName = Option(ctx.IDENTIFIER())
    val ASTMethodName = ctxMethodName.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val ctxParams = ctx.expression()
    val ASTParams = new ListBuffer[Any]()
    ctxParams.forEach(x => ASTParams += x.accept(this).get)
    val methodFuncCall = Some(AST_Grammar.methodFunctionCallExpression(ASTMethodName.orNull, ASTParams.toList.asInstanceOf[List[AST_Grammar.expression]], lineNum, None))
    methodFuncCall
  }

  override def visitArrayLengthCall(ctx: miniJavaParser.ArrayLengthCallContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val expressionIndex = ctx.getStart.getCharPositionInLine
    Some(AST_Grammar.arrayLengthExpression(lineNum, expressionIndex, None))
  }

  override def visitCompareExpression(ctx: miniJavaParser.CompareExpressionContext): Option[ASTNode] = {
    val ctxLeft = Option(ctx.expressionTerminal())
    val ASTLeft = ctxLeft.flatMap(x => x.accept(this))
    val ctxRight = Option(ctx.expressionTail())
    val ASTRight = ctxRight.flatMap(x => x.accept(this))
    val expVal = AST_Grammar.expressionValue(ASTLeft.orNull.asInstanceOf[AST_Grammar.expressionTerminal], ASTRight.asInstanceOf[Option[AST_Grammar.expressionTail]], ctx.getStart.getLine, ctx.getStart.getLine)
    val CtxOptionalComp = Option(ctx.expressionComp())
    var optionalComp = CtxOptionalComp.flatMap(x => x.accept(this))
    if(optionalComp==null){
      optionalComp = None
    }
    val compExpr = Some(AST_Grammar.compExpression(expVal, optionalComp.asInstanceOf[Option[AST_Grammar.compExpression]], ctx.getStart.getLine, ctx.getStart.getCharPositionInLine))
    compExpr
  }

  override def visitAddExpression(ctx: miniJavaParser.AddExpressionContext): Option[ASTNode] = {
    val ctxLeftExpression = Option(ctx.expressionTerminal())
    val ASTLeft = ctxLeftExpression.flatMap(x => x.accept(this))
    val ctxRightExpression = Option(ctx.expressionTail())
    val ASTRight = ctxRightExpression.flatMap(x => x.accept(this))
    val exprVal = AST_Grammar.expressionValue(ASTLeft.orNull.asInstanceOf[AST_Grammar.expressionTerminal], ASTRight.asInstanceOf[Option[AST_Grammar.expressionTail]], ctx.getStart.getLine, ctx.getStart.getLine)
    val addExpr = Some(AST_Grammar.addExpression(exprVal))
    addExpr
  }

  override def visitSubtractExpression(ctx: miniJavaParser.SubtractExpressionContext): Option[ASTNode] = {
    val ctxLeftExpression = Option(ctx.expressionTerminal())
    val ASTLeft = ctxLeftExpression.flatMap(x => x.accept(this))
    val ctxRightExpression = Option(ctx.expressionTail())
    val ASTRight = ctxRightExpression.flatMap(x => x.accept(this))
    val exprVal = AST_Grammar.expressionValue(ASTLeft.orNull.asInstanceOf[AST_Grammar.expressionTerminal], ASTRight.asInstanceOf[Option[AST_Grammar.expressionTail]], ctx.getStart.getLine, ctx.getStart.getLine)
    val subExpr = Some(AST_Grammar.subtractExpression(exprVal))
    subExpr
  }

  override def visitMultiplyExpression(ctx: miniJavaParser.MultiplyExpressionContext): Option[ASTNode] = {
    val ctxLeftExpression = Option(ctx.expressionTerminal())
    val ASTLeft = ctxLeftExpression.flatMap(x => x.accept(this))
    val ctxRightExpression = Option(ctx.expressionTail())
    val ASTRight = ctxRightExpression.flatMap(x => x.accept(this))
    val exprVal = AST_Grammar.expressionValue(ASTLeft.orNull.asInstanceOf[AST_Grammar.expressionTerminal], ASTRight.asInstanceOf[Option[AST_Grammar.expressionTail]], ctx.getStart.getLine, ctx.getStart.getLine)
    val multExpr = Some(AST_Grammar.multiplyExpression(exprVal))
    multExpr
  }

  override def visitArrayIndexCall(ctx: miniJavaParser.ArrayIndexCallContext): Option[ASTNode] = {
    val ctxArrIndex = Option(ctx.arrayIndex())
    val ASTArrIndex = ctxArrIndex.flatMap(x => x.accept(this))
    val ctxOps = Option(ctx.expressionTailOps())
    val ASTOps = ctxOps.flatMap(x => x.accept(this))
    Some(AST_Grammar.arrayIndexExpression(ASTArrIndex.orNull.asInstanceOf[AST_Grammar.expression], ASTOps.asInstanceOf[Option[AST_Grammar.operation]]))
  }

  override def visitGetArrayLength(ctx: miniJavaParser.GetArrayLengthContext): Option[ASTNode] = {
    val ctxArrLengthCall = Option(ctx.arrayLengthCall())
    val ASTArrLengthCall = ctxArrLengthCall.flatMap(x => x.accept(this))
    val ctxOps = Option(ctx.expressionTailOps())
    val ASTOps = ctxOps.flatMap(x => x.accept(this))
    if(ASTOps.isDefined){
      ASTArrLengthCall.get.asInstanceOf[AST_Grammar.arrayLengthExpression].operation = ASTOps.asInstanceOf[Option[AST_Grammar.operation]]
    }
    ASTArrLengthCall
  }

  override def visitExpressionValue(ctx: miniJavaParser.ExpressionValueContext): Option[ASTNode] = {
    val ctxLeftVal = Option(ctx.expressionComp())
    val ASTLeftVal = ctxLeftVal.flatMap(x => x.accept(this))
    val ctxRightVal = Option(ctx.expressionAnd())
    var ASTRightVal = ctxRightVal.flatMap(x => x.accept(this))
    if (ASTRightVal == null) {
      ASTRightVal = None
    }
    val expression = AST_Grammar.expression(ASTLeftVal.orNull.asInstanceOf[AST_Grammar.compExpression], ASTRightVal.asInstanceOf[Option[AST_Grammar.andExpression]])
    Some(expression)
  }

  override def visitAndexpression(ctx: miniJavaParser.AndexpressionContext): Option[ASTNode] = {
    val ctxRightVal = Option(ctx.expression())
    var ASTRightVal = ctxRightVal.flatMap(x => x.accept(this))
    if (ASTRightVal == null) {
      ASTRightVal = None
    }
    Some(AST_Grammar.andExpression(ASTRightVal.asInstanceOf[Option[AST_Grammar.expression]]))
  }

  override def visitOperatorExpression(ctx: miniJavaParser.OperatorExpressionContext): Option[ASTNode] = super.visitOperatorExpression(ctx)

  override def visitFunctionVallExpression(ctx: miniJavaParser.FunctionVallExpressionContext): Option[ASTNode] = {
    val ctxMethodCall = Option(ctx.methodFuncCall())
    val ASTMethodCall = ctxMethodCall.flatMap(x => x.accept(this))
    val ctxOps = Option(ctx.expressionTail())
    val ASTctxOps = ctxOps.flatMap(x => x.accept(this))
    //println(ASTctxOps)
    if (ASTctxOps.isDefined){
      ASTMethodCall.get.asInstanceOf[AST_Grammar.methodFunctionCallExpression].operation = ASTctxOps.asInstanceOf[Option[AST_Grammar.expressionTail]]
    }

    ASTMethodCall
  }

  override def visitNoExpressionTail(ctx: miniJavaParser.NoExpressionTailContext): Option[ASTNode] = None

  override def visitThisKeyword(ctx: miniJavaParser.ThisKeywordContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val expressionIndex = ctx.getStart.getCharPositionInLine
    Some(AST_Grammar.thisExpression(lineNum, expressionIndex))
  }

  override def visitBooleanExpression(ctx: miniJavaParser.BooleanExpressionContext): Option[ASTNode] = {
    val ctxBoolExpr = Option(ctx.boolVal())
    val ASTBoolExpr = ctxBoolExpr.flatMap(x => x.accept(this))
    ASTBoolExpr
  }

  override def visitIntegerExpression(ctx: miniJavaParser.IntegerExpressionContext): Option[ASTNode] = {
    val ctxInt = Option(ctx.INTEGER_LITERAL())
    val expressionLine = ctx.getStart.getLine
    val expressionIndex = ctx.getStart.getCharPositionInLine
    val ASTInt = ctxInt.flatMap(x => Option(x.getSymbol.getText.toInt))
    Some(AST_Grammar.integerExpression(ASTInt.orNull.asInstanceOf[Int], expressionLine, expressionIndex))
  }

  override def visitCharacterExpression(ctx: miniJavaParser.CharacterExpressionContext): Option[ASTNode] = {
    val expressionLine = ctx.getStart.getLine
    val expressionIndex = ctx.getStart.getCharPositionInLine
    val ctxChar = Option(ctx.CHARACTER_LITERAL())
    val ASTChar = ctxChar.flatMap(x => Option(AST_Grammar.characterExpression(x.getSymbol.getText, expressionLine, expressionIndex)))
    ASTChar
  }

  override def visitIdentifierExpression(ctx: miniJavaParser.IdentifierExpressionContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val expressionIndex = ctx.getStart.getCharPositionInLine
    val ctxID = Option(ctx.IDENTIFIER())
    val ASTID = ctxID.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val IDExpression = Some(AST_Grammar.identifierExpression(ASTID.orNull, lineNum, expressionIndex))
    IDExpression
  }

  override def visitNewArrayExpression(ctx: miniJavaParser.NewArrayExpressionContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val expressionIndex = ctx.getStart.getCharPositionInLine
    val ctxSize = Option(ctx.expression())
    val ASTSize = ctxSize.flatMap(x => x.accept(this))
    val newArray = Some(AST_Grammar.newArrayExpression(ASTSize.orNull.asInstanceOf[AST_Grammar.expression], lineNum, expressionIndex))
    newArray
  }

  override def visitNewClassExpression(ctx: miniJavaParser.NewClassExpressionContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val expressionIndex = ctx.getStart.getCharPositionInLine
    val ctxClassID = Option(ctx.IDENTIFIER())
    val ASTClassID = ctxClassID.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val newClass = Some(AST_Grammar.newClassInstanceExpression(ASTClassID.orNull, lineNum, expressionIndex))
    newClass
  }

  override def visitNegatedExpression(ctx: miniJavaParser.NegatedExpressionContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val expressionIndex = ctx.getStart.getCharPositionInLine
    val ctxExpressionLeft = Option(ctx.expressionTerminal())
    val ASTExpressionLeft = ctxExpressionLeft.flatMap(x => x.accept(this))
    val ctxExpressionRight = Option(ctx.expressionTail())
    val ASTExpressionRight = ctxExpressionRight.flatMap(x => x.accept(this))
    val expVal = AST_Grammar.expressionValue(ASTExpressionLeft.orNull.asInstanceOf[AST_Grammar.expressionTerminal], ASTExpressionRight.asInstanceOf[Option[AST_Grammar.expressionTail]], ctx.getStart.getLine, ctx.getStart.getLine)
    val negatedExpression = Some(AST_Grammar.negatedExpression(expVal,lineNum, expressionIndex))
    negatedExpression
  }

  override def visitParenthesizedExpression(ctx: miniJavaParser.ParenthesizedExpressionContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val expressionIndex = ctx.getStart.getCharPositionInLine
    val ctxInnerVal = Option(ctx.expression())
    val ASTInnerVal = ctxInnerVal.flatMap(x => x.accept(this))
    val parenthesizedExpression = Some(AST_Grammar.parenthesizedExpression(ASTInnerVal.orNull.asInstanceOf[AST_Grammar.expression], lineNum, expressionIndex))
    parenthesizedExpression
  }

  override def visitBoolVal(ctx: miniJavaParser.BoolValContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val expressionIndex = ctx.getStart.getCharPositionInLine
    var curBool: Option[String] = null
    if(ctx.TRUE() != null){
      curBool = Some(ctx.TRUE().getSymbol.getText)
    }else if(ctx.FALSE() != null){
      curBool = Some(ctx.FALSE().getSymbol.getText)
    }else{
      curBool = None
    }
    Some(AST_Grammar.booleanExpression(curBool.orNull.toBoolean, lineNum, expressionIndex))
  }
}
