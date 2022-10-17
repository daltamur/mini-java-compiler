import AST_Grammar.ASTNode
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.{ErrorNode, TerminalNode}
import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`
import scala.collection.mutable.ListBuffer
import scala.language.postfixOps
class MiniJavaVisitor extends miniJavaBaseVisitor[Option[ASTNode]] {
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
    val ctxClassName = Option(ctx.IDENTIFIER(0))
    val ASTClassName = ctxClassName.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val ctxArgName = Option(ctx.IDENTIFIER(1))
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
    val methodDec = Some(AST_Grammar.method(ASTType.orNull.asInstanceOf[AST_Grammar.dataType],ASTMethodName.orNull, ASTParams.toList, ASTVarDecs.toList.asInstanceOf[List[Option[AST_Grammar.variableDecs]]], ASTStatements.toList.asInstanceOf[List[AST_Grammar.statement]], ASTReturnedVal.orNull.asInstanceOf[AST_Grammar.expression], lineNum))
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
    ctxStatements.forEach(x => ASTStatements += x.accept(this))
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

  override def visitExpression(ctx: miniJavaParser.ExpressionContext): Option[ASTNode] = {
    val expressionLine = ctx.getStart.getLine
    val expressionIndex = ctx.getStart.getCharPositionInLine
    val ctxExpressionTerminal = Option(ctx.expressionTerminal())
    var ASTExpressionTerminal = ctxExpressionTerminal.flatMap(x => x.accept(this))
    if (ASTExpressionTerminal == null){
      ASTExpressionTerminal = None
    }
    val ctxExpressionTail = Option(ctx.expressionTail())
    var ASTExpressionTail = ctxExpressionTail.flatMap(x => x.accept(this))
    if (ASTExpressionTail == null){
      ASTExpressionTail = None
    }
    val expression = Some(AST_Grammar.expression(ASTExpressionTerminal.orNull.asInstanceOf[AST_Grammar.expressionTerminal], ASTExpressionTail.asInstanceOf[Option[AST_Grammar.expressionTail]], expressionLine, expressionIndex))
    expression
  }

  override def visitMethodFuncCall(ctx: miniJavaParser.MethodFuncCallContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val ctxMethodName = Option(ctx.IDENTIFIER())
    val ASTMethodName = ctxMethodName.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val ctxParams = ctx.expression()
    val ASTParams = new ListBuffer[Any]()
    ctxParams.forEach(x => ASTParams += x.accept(this))
    val methodFuncCall = Some(AST_Grammar.methodFunctionCallExpression(ASTMethodName.orNull, ASTParams.toList.asInstanceOf[List[AST_Grammar.expression]], lineNum))
    methodFuncCall
  }

  override def visitArrayLengthCall(ctx: miniJavaParser.ArrayLengthCallContext): Option[ASTNode] = {
    Some(AST_Grammar.arrayLengthExpression())
  }

  override def visitAndExpression(ctx: miniJavaParser.AndExpressionContext): Option[ASTNode] = {
    val ctxExpr = Option(ctx.expression())
    val ASTExpr = ctxExpr.flatMap(x => x.accept(this))
    val andExpr = Some(AST_Grammar.andExpression(ASTExpr.orNull.asInstanceOf[AST_Grammar.expression]))
    andExpr
  }

  override def visitCompareExpression(ctx: miniJavaParser.CompareExpressionContext): Option[ASTNode] = {
    val ctxExpr = Option(ctx.expression())
    val ASTExpr = ctxExpr.flatMap(x => x.accept(this))
    val compExpr = Some(AST_Grammar.compareExpression(ASTExpr.orNull.asInstanceOf[AST_Grammar.expression]))
    compExpr
  }

  override def visitAddExpression(ctx: miniJavaParser.AddExpressionContext): Option[ASTNode] = {
    val ctxExpr = Option(ctx.expression())
    val ASTExpr = ctxExpr.flatMap(x => x.accept(this))
    val addExpr = Some(AST_Grammar.addExpression(ASTExpr.orNull.asInstanceOf[AST_Grammar.expression]))
    addExpr
  }

  override def visitSubtractExpression(ctx: miniJavaParser.SubtractExpressionContext): Option[ASTNode] = {
    val ctxExpr = Option(ctx.expression())
    val ASTExpr = ctxExpr.flatMap(x => x.accept(this))
    val subExpr = Some(AST_Grammar.subtractExpression(ASTExpr.orNull.asInstanceOf[AST_Grammar.expression]))
    subExpr
  }

  override def visitMultiplyExpression(ctx: miniJavaParser.MultiplyExpressionContext): Option[ASTNode] = {
    val ctxExpr = Option(ctx.expression())
    val ASTExpr = ctxExpr.flatMap(x => x.accept(this))
    val multExpr = Some(AST_Grammar.multiplyExpression(ASTExpr.orNull.asInstanceOf[AST_Grammar.expression]))
    multExpr
  }

  override def visitArrayIndexCall(ctx: miniJavaParser.ArrayIndexCallContext): Option[ASTNode] = {
    val ctxArrIndex = Option(ctx.arrayIndex())
    val ASTArrIndex = ctxArrIndex.flatMap(x => x.accept(this))
    Some(AST_Grammar.arrayIndexExpression(ASTArrIndex.orNull.asInstanceOf[AST_Grammar.expression]))
  }

  override def visitGetArrayLength(ctx: miniJavaParser.GetArrayLengthContext): Option[ASTNode] = {
    val ctxArrLengthCall = Option(ctx.arrayLengthCall())
    val ASTArrLengthCall = ctxArrLengthCall.flatMap(x => x.accept(this))
    ASTArrLengthCall
  }

  override def visitFunctionVallExpression(ctx: miniJavaParser.FunctionVallExpressionContext): Option[ASTNode] = {
    val ctxMethodCall = Option(ctx.methodFuncCall())
    val ASTMethodCall = ctxMethodCall.flatMap(x => x.accept(this))
    ASTMethodCall
  }

  override def visitNoExpressionTail(ctx: miniJavaParser.NoExpressionTailContext): Option[ASTNode] = {None}

  override def visitThisKeyword(ctx: miniJavaParser.ThisKeywordContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    Some(AST_Grammar.thisExpression(lineNum))
  }

  override def visitBooleanExpression(ctx: miniJavaParser.BooleanExpressionContext): Option[ASTNode] = {
    val ctxBoolExpr = Option(ctx.boolVal())
    val ASTBoolExpr = ctxBoolExpr.flatMap(x => x.accept(this))
    ASTBoolExpr
  }

  override def visitIntegerExpression(ctx: miniJavaParser.IntegerExpressionContext): Option[ASTNode] = {
    val ctxInt = Option(ctx.INTEGER_LITERAL())
    val ASTInt = ctxInt.flatMap(x => Option(x.getSymbol.getText.toInt))
    Some(AST_Grammar.integerExpression(ASTInt.orNull.asInstanceOf[Int]))
  }

  override def visitCharacterExpression(ctx: miniJavaParser.CharacterExpressionContext): Option[ASTNode] = {
    val ctxChar = Option(ctx.CHARACTER_LITERAL())
    val ASTChar = ctxChar.flatMap(x => Option(AST_Grammar.characterExpression(x.getSymbol.getText)))
    ASTChar
  }

  override def visitIdentifierExpression(ctx: miniJavaParser.IdentifierExpressionContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val ctxID = Option(ctx.IDENTIFIER())
    val ASTID = ctxID.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val IDExpression = Some(AST_Grammar.identifierExpression(ASTID.orNull, lineNum))
    IDExpression
  }

  override def visitNewArrayExpression(ctx: miniJavaParser.NewArrayExpressionContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val ctxSize = Option(ctx.expression())
    val ASTSize = ctxSize.flatMap(x => x.accept(this))
    val newArray = Some(AST_Grammar.newArrayExpression(ASTSize.orNull.asInstanceOf[AST_Grammar.expression], lineNum))
    newArray
  }

  override def visitNewClassExpression(ctx: miniJavaParser.NewClassExpressionContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val ctxClassID = Option(ctx.IDENTIFIER())
    val ASTClassID = ctxClassID.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val newClass = Some(AST_Grammar.newClassInstanceExpression(ASTClassID.orNull, lineNum))
    newClass
  }

  override def visitNegatedExpression(ctx: miniJavaParser.NegatedExpressionContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val ctxExpression = Option(ctx.expression())
    val ASTExpression = ctxExpression.flatMap(x => x.accept(this))
    val negatedExpression = Some(AST_Grammar.negatedExpression(ASTExpression.orNull.asInstanceOf[AST_Grammar.expression], lineNum))
    negatedExpression
  }

  override def visitParenthesizedExpression(ctx: miniJavaParser.ParenthesizedExpressionContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    val ctxInnerVal = Option(ctx.expression())
    val ASTInnerVal = ctxInnerVal.flatMap(x => x.accept(this))
    val parenthesizedExpression = Some(AST_Grammar.parenthesizedExpression(ASTInnerVal.orNull.asInstanceOf[AST_Grammar.expression], lineNum))
    parenthesizedExpression
  }

  override def visitBoolVal(ctx: miniJavaParser.BoolValContext): Option[ASTNode] = {
    val lineNum = ctx.getStart.getLine
    var curBool: Option[String] = null
    if(ctx.TRUE() != null){
      curBool = Some(ctx.TRUE().getSymbol.getText)
    }else if(ctx.FALSE() != null){
      curBool = Some(ctx.FALSE().getSymbol.getText)
    }else{
      curBool = None
    }
    Some(AST_Grammar.booleanExpression(curBool.orNull.toBoolean, lineNum))
  }
}
