import AST_Grammar.ASTNode
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.{ErrorNode, TerminalNode}






import scala.collection.mutable.ListBuffer

class MiniJavaVisitor extends miniJavaBaseVisitor[Option[ASTNode]] {
  override def visitGoal(ctx: miniJavaParser.GoalContext): Option[ASTNode] = {
    val ctxMainClass = Option(ctx.mainClass())
    val ASTMainClass = ctxMainClass.flatMap(x => x.accept(this))
    val ctxClasses = ctx.classDeclaration()
    val ASTClasses =  new ListBuffer[Any]()
    ctxClasses.forEach(x => ASTClasses.+=(x.accept(this)))
    val goal = Some(AST_Grammar.goal(ASTMainClass.orNull.asInstanceOf[AST_Grammar.mainClass], ASTClasses.toList.asInstanceOf[List[AST_Grammar.klass]]))
    goal
  }

  override def visitMainClass(ctx: miniJavaParser.MainClassContext): Option[ASTNode] = {
    val ctxClassName = Option(ctx.IDENTIFIER(0))
    val ASTClassName = ctxClassName.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val ctxArgName = Option(ctx.IDENTIFIER(1))
    val ASTArgName = ctxArgName.flatMap(x => Option(AST_Grammar.identifier(x.getSymbol.getText)))
    val ctxStatement = Option(ctx.statement())
    val ASTStatement = ctxStatement.flatMap(x => x.accept(this))
    val mainClass = AST_Grammar.mainClass(ASTClassName.orNull, ASTArgName.orNull, ASTStatement.orNull.asInstanceOf[AST_Grammar.statement])
    Some(mainClass)
  }

  override def visitClassDeclaration(ctx: miniJavaParser.ClassDeclarationContext): Option[ASTNode] = {
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
    val classDec = Some(AST_Grammar.klass(ASTClassName.orNull, ASTArgName, ASTVarDecs.toList.asInstanceOf[List[AST_Grammar.variableDecs]], ASTMethods.toList.asInstanceOf[List[AST_Grammar.method]]))
    classDec
  }

  override def visitVarDeclaration(ctx: miniJavaParser.VarDeclarationContext): Option[ASTNode] = super.visitVarDeclaration(ctx)

  override def visitMethodDeclaration(ctx: miniJavaParser.MethodDeclarationContext): Option[ASTNode] = super.visitMethodDeclaration(ctx)

  override def visitIntegerArrayType(ctx: miniJavaParser.IntegerArrayTypeContext): Option[ASTNode] = super.visitIntegerArrayType(ctx)

  override def visitBooleanType(ctx: miniJavaParser.BooleanTypeContext): Option[ASTNode] = super.visitBooleanType(ctx)

  override def visitIntType(ctx: miniJavaParser.IntTypeContext): Option[ASTNode] = super.visitIntType(ctx)

  override def visitCharType(ctx: miniJavaParser.CharTypeContext): Option[ASTNode] = super.visitCharType(ctx)

  override def visitIdentifierType(ctx: miniJavaParser.IdentifierTypeContext): Option[ASTNode] = super.visitIdentifierType(ctx)

  override def visitArrayIndex(ctx: miniJavaParser.ArrayIndexContext): Option[ASTNode] = super.visitArrayIndex(ctx)

  override def visitBlockStatement(ctx: miniJavaParser.BlockStatementContext): Option[ASTNode] = super.visitBlockStatement(ctx)

  override def visitIfStatement(ctx: miniJavaParser.IfStatementContext): Option[ASTNode] = super.visitIfStatement(ctx)

  override def visitWhileStatement(ctx: miniJavaParser.WhileStatementContext): Option[ASTNode] = super.visitWhileStatement(ctx)

  override def visitPrintStatement(ctx: miniJavaParser.PrintStatementContext): Option[ASTNode] = super.visitPrintStatement(ctx)

  override def visitAssignStatement(ctx: miniJavaParser.AssignStatementContext): Option[ASTNode] = super.visitAssignStatement(ctx)

  override def visitArrayAssignStatement(ctx: miniJavaParser.ArrayAssignStatementContext): Option[ASTNode] = super.visitArrayAssignStatement(ctx)

  override def visitExpression(ctx: miniJavaParser.ExpressionContext): Option[ASTNode] = super.visitExpression(ctx)

  override def visitMethodFuncCall(ctx: miniJavaParser.MethodFuncCallContext): Option[ASTNode] = super.visitMethodFuncCall(ctx)

  override def visitArrayLengthCall(ctx: miniJavaParser.ArrayLengthCallContext): Option[ASTNode] = super.visitArrayLengthCall(ctx)

  override def visitAndExpression(ctx: miniJavaParser.AndExpressionContext): Option[ASTNode] = super.visitAndExpression(ctx)

  override def visitCompareExpression(ctx: miniJavaParser.CompareExpressionContext): Option[ASTNode] = super.visitCompareExpression(ctx)

  override def visitAddExpression(ctx: miniJavaParser.AddExpressionContext): Option[ASTNode] = super.visitAddExpression(ctx)

  override def visitSubtractExpression(ctx: miniJavaParser.SubtractExpressionContext): Option[ASTNode] = super.visitSubtractExpression(ctx)

  override def visitMultiplyExpression(ctx: miniJavaParser.MultiplyExpressionContext): Option[ASTNode] = super.visitMultiplyExpression(ctx)

  override def visitArrayIndexCall(ctx: miniJavaParser.ArrayIndexCallContext): Option[ASTNode] = super.visitArrayIndexCall(ctx)

  override def visitGetArrayLength(ctx: miniJavaParser.GetArrayLengthContext): Option[ASTNode] = super.visitGetArrayLength(ctx)

  override def visitFunctionVallExpression(ctx: miniJavaParser.FunctionVallExpressionContext): Option[ASTNode] = super.visitFunctionVallExpression(ctx)

  override def visitNoExpressionTail(ctx: miniJavaParser.NoExpressionTailContext): Option[ASTNode] = super.visitNoExpressionTail(ctx)

  override def visitThisKeyword(ctx: miniJavaParser.ThisKeywordContext): Option[ASTNode] = super.visitThisKeyword(ctx)

  override def visitBooleanExpression(ctx: miniJavaParser.BooleanExpressionContext): Option[ASTNode] = super.visitBooleanExpression(ctx)

  override def visitIntegerExpression(ctx: miniJavaParser.IntegerExpressionContext): Option[ASTNode] = super.visitIntegerExpression(ctx)

  override def visitCharacterExpression(ctx: miniJavaParser.CharacterExpressionContext): Option[ASTNode] = super.visitCharacterExpression(ctx)

  override def visitIdentifierExpression(ctx: miniJavaParser.IdentifierExpressionContext): Option[ASTNode] = super.visitIdentifierExpression(ctx)

  override def visitNewArrayExpression(ctx: miniJavaParser.NewArrayExpressionContext): Option[ASTNode] = super.visitNewArrayExpression(ctx)

  override def visitNewClassExpression(ctx: miniJavaParser.NewClassExpressionContext): Option[ASTNode] = super.visitNewClassExpression(ctx)

  override def visitNegatedExpression(ctx: miniJavaParser.NegatedExpressionContext): Option[ASTNode] = super.visitNegatedExpression(ctx)

  override def visitParenthesizedExpression(ctx: miniJavaParser.ParenthesizedExpressionContext): Option[ASTNode] = super.visitParenthesizedExpression(ctx)

  override def visitBoolVal(ctx: miniJavaParser.BoolValContext): Option[ASTNode] = super.visitBoolVal(ctx)
}
