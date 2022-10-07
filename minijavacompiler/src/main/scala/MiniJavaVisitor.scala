import AST_Grammar.goal
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.{ErrorNode, TerminalNode}

class MiniJavaVisitor extends miniJavaBaseVisitor[Option[goal]] {
  override def visitGoal(ctx: miniJavaParser.GoalContext): Option[goal] = Some(goal())

  override def visitMainClass(ctx: miniJavaParser.MainClassContext): Option[goal] = super.visitMainClass(ctx)

  override def visitClassDeclaration(ctx: miniJavaParser.ClassDeclarationContext): Option[goal] = super.visitClassDeclaration(ctx)

  override def visitVarDeclaration(ctx: miniJavaParser.VarDeclarationContext): Option[goal] = super.visitVarDeclaration(ctx)

  override def visitMethodDeclaration(ctx: miniJavaParser.MethodDeclarationContext): Option[goal] = super.visitMethodDeclaration(ctx)

  override def visitIntegerArrayType(ctx: miniJavaParser.IntegerArrayTypeContext): Option[goal] = super.visitIntegerArrayType(ctx)

  override def visitBooleanType(ctx: miniJavaParser.BooleanTypeContext): Option[goal] = super.visitBooleanType(ctx)

  override def visitIntType(ctx: miniJavaParser.IntTypeContext): Option[goal] = super.visitIntType(ctx)

  override def visitCharType(ctx: miniJavaParser.CharTypeContext): Option[goal] = super.visitCharType(ctx)

  override def visitIdentifierType(ctx: miniJavaParser.IdentifierTypeContext): Option[goal] = super.visitIdentifierType(ctx)

  override def visitArrayIndex(ctx: miniJavaParser.ArrayIndexContext): Option[goal] = super.visitArrayIndex(ctx)

  override def visitBlockStatement(ctx: miniJavaParser.BlockStatementContext): Option[goal] = super.visitBlockStatement(ctx)

  override def visitIfStatement(ctx: miniJavaParser.IfStatementContext): Option[goal] = super.visitIfStatement(ctx)

  override def visitWhileStatement(ctx: miniJavaParser.WhileStatementContext): Option[goal] = super.visitWhileStatement(ctx)

  override def visitPrintStatement(ctx: miniJavaParser.PrintStatementContext): Option[goal] = super.visitPrintStatement(ctx)

  override def visitAssignStatement(ctx: miniJavaParser.AssignStatementContext): Option[goal] = super.visitAssignStatement(ctx)

  override def visitArrayAssignStatement(ctx: miniJavaParser.ArrayAssignStatementContext): Option[goal] = super.visitArrayAssignStatement(ctx)

  override def visitExpression(ctx: miniJavaParser.ExpressionContext): Option[goal] = super.visitExpression(ctx)

  override def visitMethodFuncCall(ctx: miniJavaParser.MethodFuncCallContext): Option[goal] = super.visitMethodFuncCall(ctx)

  override def visitArrayLengthCall(ctx: miniJavaParser.ArrayLengthCallContext): Option[goal] = super.visitArrayLengthCall(ctx)

  override def visitAndExpression(ctx: miniJavaParser.AndExpressionContext): Option[goal] = super.visitAndExpression(ctx)

  override def visitCompareExpression(ctx: miniJavaParser.CompareExpressionContext): Option[goal] = super.visitCompareExpression(ctx)

  override def visitAddExpression(ctx: miniJavaParser.AddExpressionContext): Option[goal] = super.visitAddExpression(ctx)

  override def visitSubtractExpression(ctx: miniJavaParser.SubtractExpressionContext): Option[goal] = super.visitSubtractExpression(ctx)

  override def visitMultiplyExpression(ctx: miniJavaParser.MultiplyExpressionContext): Option[goal] = super.visitMultiplyExpression(ctx)

  override def visitArrayIndexCall(ctx: miniJavaParser.ArrayIndexCallContext): Option[goal] = super.visitArrayIndexCall(ctx)

  override def visitGetArrayLength(ctx: miniJavaParser.GetArrayLengthContext): Option[goal] = super.visitGetArrayLength(ctx)

  override def visitFunctionVallExpression(ctx: miniJavaParser.FunctionVallExpressionContext): Option[goal] = super.visitFunctionVallExpression(ctx)

  override def visitNoExpressionTail(ctx: miniJavaParser.NoExpressionTailContext): Option[goal] = super.visitNoExpressionTail(ctx)

  override def visitThisKeyword(ctx: miniJavaParser.ThisKeywordContext): Option[goal] = super.visitThisKeyword(ctx)

  override def visitBooleanExpression(ctx: miniJavaParser.BooleanExpressionContext): Option[goal] = super.visitBooleanExpression(ctx)

  override def visitIntegerExpression(ctx: miniJavaParser.IntegerExpressionContext): Option[goal] = super.visitIntegerExpression(ctx)

  override def visitCharacterExpression(ctx: miniJavaParser.CharacterExpressionContext): Option[goal] = super.visitCharacterExpression(ctx)

  override def visitIdentifierExpression(ctx: miniJavaParser.IdentifierExpressionContext): Option[goal] = super.visitIdentifierExpression(ctx)

  override def visitNewArrayExpression(ctx: miniJavaParser.NewArrayExpressionContext): Option[goal] = super.visitNewArrayExpression(ctx)

  override def visitNewClassExpression(ctx: miniJavaParser.NewClassExpressionContext): Option[goal] = super.visitNewClassExpression(ctx)

  override def visitNegatedExpression(ctx: miniJavaParser.NegatedExpressionContext): Option[goal] = super.visitNegatedExpression(ctx)

  override def visitParenthesizedExpression(ctx: miniJavaParser.ParenthesizedExpressionContext): Option[goal] = super.visitParenthesizedExpression(ctx)

  override def visitBoolVal(ctx: miniJavaParser.BoolValContext): Option[goal] = super.visitBoolVal(ctx)
}
