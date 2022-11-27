package grammarOutput;// Generated from miniJava.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link miniJavaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface miniJavaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#goal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoal(miniJavaParser.GoalContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#mainClass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainClass(miniJavaParser.MainClassContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(miniJavaParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(miniJavaParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(miniJavaParser.MethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerArrayType}
	 * labeled alternative in {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerArrayType(miniJavaParser.IntegerArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanType}
	 * labeled alternative in {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanType(miniJavaParser.BooleanTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntType(miniJavaParser.IntTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charType}
	 * labeled alternative in {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharType(miniJavaParser.CharTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierType}
	 * labeled alternative in {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierType(miniJavaParser.IdentifierTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#arrayIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayIndex(miniJavaParser.ArrayIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(miniJavaParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(miniJavaParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(miniJavaParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(miniJavaParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStatement(miniJavaParser.AssignStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAssignStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAssignStatement(miniJavaParser.ArrayAssignStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionValue}
	 * labeled alternative in {@link miniJavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionValue(miniJavaParser.ExpressionValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andexpression}
	 * labeled alternative in {@link miniJavaParser#expressionAnd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndexpression(miniJavaParser.AndexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#methodFuncCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodFuncCall(miniJavaParser.MethodFuncCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#arrayLengthCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLengthCall(miniJavaParser.ArrayLengthCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compareExpression}
	 * labeled alternative in {@link miniJavaParser#expressionComp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareExpression(miniJavaParser.CompareExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplyExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTailOps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplyExpression(miniJavaParser.MultiplyExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTailOps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpression(miniJavaParser.AddExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subtractExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTailOps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtractExpression(miniJavaParser.SubtractExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code operatorExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperatorExpression(miniJavaParser.OperatorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayIndexCall}
	 * labeled alternative in {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayIndexCall(miniJavaParser.ArrayIndexCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getArrayLength}
	 * labeled alternative in {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetArrayLength(miniJavaParser.GetArrayLengthContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionVallExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionVallExpression(miniJavaParser.FunctionVallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code noExpressionTail}
	 * labeled alternative in {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoExpressionTail(miniJavaParser.NoExpressionTailContext ctx);
	/**
	 * Visit a parse tree produced by the {@code thisKeyword}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisKeyword(miniJavaParser.ThisKeywordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanExpression(miniJavaParser.BooleanExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerExpression(miniJavaParser.IntegerExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code characterExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacterExpression(miniJavaParser.CharacterExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpression(miniJavaParser.IdentifierExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newArrayExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewArrayExpression(miniJavaParser.NewArrayExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newClassExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewClassExpression(miniJavaParser.NewClassExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negatedExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegatedExpression(miniJavaParser.NegatedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpression(miniJavaParser.ParenthesizedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#boolVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolVal(miniJavaParser.BoolValContext ctx);
}