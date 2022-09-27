// Generated from miniJava.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link miniJavaParser}.
 */
public interface miniJavaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#goal}.
	 * @param ctx the parse tree
	 */
	void enterGoal(miniJavaParser.GoalContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#goal}.
	 * @param ctx the parse tree
	 */
	void exitGoal(miniJavaParser.GoalContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void enterMainClass(miniJavaParser.MainClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void exitMainClass(miniJavaParser.MainClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(miniJavaParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(miniJavaParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(miniJavaParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(miniJavaParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(miniJavaParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(miniJavaParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(miniJavaParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(miniJavaParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#arrayIndex}.
	 * @param ctx the parse tree
	 */
	void enterArrayIndex(miniJavaParser.ArrayIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#arrayIndex}.
	 * @param ctx the parse tree
	 */
	void exitArrayIndex(miniJavaParser.ArrayIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(miniJavaParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(miniJavaParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(miniJavaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(miniJavaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#methodFuncCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodFuncCall(miniJavaParser.MethodFuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#methodFuncCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodFuncCall(miniJavaParser.MethodFuncCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#arrayLengthCall}.
	 * @param ctx the parse tree
	 */
	void enterArrayLengthCall(miniJavaParser.ArrayLengthCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#arrayLengthCall}.
	 * @param ctx the parse tree
	 */
	void exitArrayLengthCall(miniJavaParser.ArrayLengthCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 */
	void enterExpressionTail(miniJavaParser.ExpressionTailContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 */
	void exitExpressionTail(miniJavaParser.ExpressionTailContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(miniJavaParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(miniJavaParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpression(miniJavaParser.ShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpression(miniJavaParser.ShiftExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#addExpression}.
	 * @param ctx the parse tree
	 */
	void enterAddExpression(miniJavaParser.AddExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#addExpression}.
	 * @param ctx the parse tree
	 */
	void exitAddExpression(miniJavaParser.AddExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#subtractExpression}.
	 * @param ctx the parse tree
	 */
	void enterSubtractExpression(miniJavaParser.SubtractExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#subtractExpression}.
	 * @param ctx the parse tree
	 */
	void exitSubtractExpression(miniJavaParser.SubtractExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#multiplyExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyExpression(miniJavaParser.MultiplyExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#multiplyExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyExpression(miniJavaParser.MultiplyExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void enterExpressionTerminal(miniJavaParser.ExpressionTerminalContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void exitExpressionTerminal(miniJavaParser.ExpressionTerminalContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#boolVal}.
	 * @param ctx the parse tree
	 */
	void enterBoolVal(miniJavaParser.BoolValContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#boolVal}.
	 * @param ctx the parse tree
	 */
	void exitBoolVal(miniJavaParser.BoolValContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(miniJavaParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(miniJavaParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#character}.
	 * @param ctx the parse tree
	 */
	void enterCharacter(miniJavaParser.CharacterContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#character}.
	 * @param ctx the parse tree
	 */
	void exitCharacter(miniJavaParser.CharacterContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(miniJavaParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(miniJavaParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#arrayInstantiation}.
	 * @param ctx the parse tree
	 */
	void enterArrayInstantiation(miniJavaParser.ArrayInstantiationContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#arrayInstantiation}.
	 * @param ctx the parse tree
	 */
	void exitArrayInstantiation(miniJavaParser.ArrayInstantiationContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#classInstantiation}.
	 * @param ctx the parse tree
	 */
	void enterClassInstantiation(miniJavaParser.ClassInstantiationContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#classInstantiation}.
	 * @param ctx the parse tree
	 */
	void exitClassInstantiation(miniJavaParser.ClassInstantiationContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#negatedExpression}.
	 * @param ctx the parse tree
	 */
	void enterNegatedExpression(miniJavaParser.NegatedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#negatedExpression}.
	 * @param ctx the parse tree
	 */
	void exitNegatedExpression(miniJavaParser.NegatedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link miniJavaParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(miniJavaParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link miniJavaParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(miniJavaParser.ParenthesizedExpressionContext ctx);
}