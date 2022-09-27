// Generated from /home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/src/main/scala/grammarOutput/miniJava.g4 by ANTLR 4.10.1
package grammarOutput;
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
	 * Visit a parse tree produced by {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(miniJavaParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#arrayIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayIndex(miniJavaParser.ArrayIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(miniJavaParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(miniJavaParser.ExpressionContext ctx);
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
	 * Visit a parse tree produced by {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionTail(miniJavaParser.ExpressionTailContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#andExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(miniJavaParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#shiftExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftExpression(miniJavaParser.ShiftExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#addExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpression(miniJavaParser.AddExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#subtractExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtractExpression(miniJavaParser.SubtractExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#multiplyExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplyExpression(miniJavaParser.MultiplyExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionTerminal(miniJavaParser.ExpressionTerminalContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#boolVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolVal(miniJavaParser.BoolValContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(miniJavaParser.IntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#character}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacter(miniJavaParser.CharacterContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(miniJavaParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#arrayInstantiation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInstantiation(miniJavaParser.ArrayInstantiationContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#classInstantiation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassInstantiation(miniJavaParser.ClassInstantiationContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#negatedExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegatedExpression(miniJavaParser.NegatedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniJavaParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpression(miniJavaParser.ParenthesizedExpressionContext ctx);
}