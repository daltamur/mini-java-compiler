package parseTreeFiles.grammarOutput;// Generated from miniJava.g4 by ANTLR 4.10.1
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
	 * Enter a parse tree produced by the {@code integerArrayType}
	 * labeled alternative in {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntegerArrayType(miniJavaParser.IntegerArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerArrayType}
	 * labeled alternative in {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntegerArrayType(miniJavaParser.IntegerArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanType}
	 * labeled alternative in {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBooleanType(miniJavaParser.BooleanTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanType}
	 * labeled alternative in {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBooleanType(miniJavaParser.BooleanTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intType}
	 * labeled alternative in {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntType(miniJavaParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntType(miniJavaParser.IntTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code charType}
	 * labeled alternative in {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterCharType(miniJavaParser.CharTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code charType}
	 * labeled alternative in {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitCharType(miniJavaParser.CharTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierType}
	 * labeled alternative in {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierType(miniJavaParser.IdentifierTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierType}
	 * labeled alternative in {@link miniJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierType(miniJavaParser.IdentifierTypeContext ctx);
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
	 * Enter a parse tree produced by the {@code blockStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(miniJavaParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(miniJavaParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(miniJavaParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(miniJavaParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(miniJavaParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(miniJavaParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(miniJavaParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(miniJavaParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignStatement(miniJavaParser.AssignStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignStatement(miniJavaParser.AssignStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayAssignStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterArrayAssignStatement(miniJavaParser.ArrayAssignStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayAssignStatement}
	 * labeled alternative in {@link miniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitArrayAssignStatement(miniJavaParser.ArrayAssignStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionValue}
	 * labeled alternative in {@link miniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionValue(miniJavaParser.ExpressionValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionValue}
	 * labeled alternative in {@link miniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionValue(miniJavaParser.ExpressionValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andexpression}
	 * labeled alternative in {@link miniJavaParser#expressionAnd}.
	 * @param ctx the parse tree
	 */
	void enterAndexpression(miniJavaParser.AndexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andexpression}
	 * labeled alternative in {@link miniJavaParser#expressionAnd}.
	 * @param ctx the parse tree
	 */
	void exitAndexpression(miniJavaParser.AndexpressionContext ctx);
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
	 * Enter a parse tree produced by the {@code compareExpression}
	 * labeled alternative in {@link miniJavaParser#expressionComp}.
	 * @param ctx the parse tree
	 */
	void enterCompareExpression(miniJavaParser.CompareExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compareExpression}
	 * labeled alternative in {@link miniJavaParser#expressionComp}.
	 * @param ctx the parse tree
	 */
	void exitCompareExpression(miniJavaParser.CompareExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplyExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTailOps}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyExpression(miniJavaParser.MultiplyExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplyExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTailOps}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyExpression(miniJavaParser.MultiplyExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTailOps}.
	 * @param ctx the parse tree
	 */
	void enterAddExpression(miniJavaParser.AddExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTailOps}.
	 * @param ctx the parse tree
	 */
	void exitAddExpression(miniJavaParser.AddExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subtractExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTailOps}.
	 * @param ctx the parse tree
	 */
	void enterSubtractExpression(miniJavaParser.SubtractExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subtractExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTailOps}.
	 * @param ctx the parse tree
	 */
	void exitSubtractExpression(miniJavaParser.SubtractExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operatorExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 */
	void enterOperatorExpression(miniJavaParser.OperatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operatorExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 */
	void exitOperatorExpression(miniJavaParser.OperatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayIndexCall}
	 * labeled alternative in {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 */
	void enterArrayIndexCall(miniJavaParser.ArrayIndexCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayIndexCall}
	 * labeled alternative in {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 */
	void exitArrayIndexCall(miniJavaParser.ArrayIndexCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getArrayLength}
	 * labeled alternative in {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 */
	void enterGetArrayLength(miniJavaParser.GetArrayLengthContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getArrayLength}
	 * labeled alternative in {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 */
	void exitGetArrayLength(miniJavaParser.GetArrayLengthContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionVallExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 */
	void enterFunctionVallExpression(miniJavaParser.FunctionVallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionVallExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 */
	void exitFunctionVallExpression(miniJavaParser.FunctionVallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code noExpressionTail}
	 * labeled alternative in {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 */
	void enterNoExpressionTail(miniJavaParser.NoExpressionTailContext ctx);
	/**
	 * Exit a parse tree produced by the {@code noExpressionTail}
	 * labeled alternative in {@link miniJavaParser#expressionTail}.
	 * @param ctx the parse tree
	 */
	void exitNoExpressionTail(miniJavaParser.NoExpressionTailContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thisKeyword}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void enterThisKeyword(miniJavaParser.ThisKeywordContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thisKeyword}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void exitThisKeyword(miniJavaParser.ThisKeywordContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void enterBooleanExpression(miniJavaParser.BooleanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void exitBooleanExpression(miniJavaParser.BooleanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void enterIntegerExpression(miniJavaParser.IntegerExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void exitIntegerExpression(miniJavaParser.IntegerExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code characterExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void enterCharacterExpression(miniJavaParser.CharacterExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code characterExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void exitCharacterExpression(miniJavaParser.CharacterExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpression(miniJavaParser.IdentifierExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpression(miniJavaParser.IdentifierExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newArrayExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void enterNewArrayExpression(miniJavaParser.NewArrayExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newArrayExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void exitNewArrayExpression(miniJavaParser.NewArrayExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newClassExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void enterNewClassExpression(miniJavaParser.NewClassExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newClassExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void exitNewClassExpression(miniJavaParser.NewClassExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negatedExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void enterNegatedExpression(miniJavaParser.NegatedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negatedExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void exitNegatedExpression(miniJavaParser.NegatedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(miniJavaParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link miniJavaParser#expressionTerminal}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(miniJavaParser.ParenthesizedExpressionContext ctx);
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
}