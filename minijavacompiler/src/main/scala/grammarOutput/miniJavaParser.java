// Generated from miniJava.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class miniJavaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, AND=27, EQUALS=28, COMPARE=29, ADD=30, SUBTRACT=31, 
		MULTIPLY=32, TRUE=33, FALSE=34, IDENTIFIER=35, NOT=36, INTEGER_LITERAL=37, 
		WS=38, COMMENT=39, ESCAPED_CHARACTER=40, CHARACTER_LITERAL=41;
	public static final int
		RULE_goal = 0, RULE_mainClass = 1, RULE_classDeclaration = 2, RULE_varDeclaration = 3, 
		RULE_methodDeclaration = 4, RULE_type = 5, RULE_arrayIndex = 6, RULE_statement = 7, 
		RULE_expression = 8, RULE_methodFuncCall = 9, RULE_arrayLengthCall = 10, 
		RULE_expressionTailOps = 11, RULE_expressionTail = 12, RULE_expressionTerminal = 13, 
		RULE_boolVal = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"goal", "mainClass", "classDeclaration", "varDeclaration", "methodDeclaration", 
			"type", "arrayIndex", "statement", "expression", "methodFuncCall", "arrayLengthCall", 
			"expressionTailOps", "expressionTail", "expressionTerminal", "boolVal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'class'", "'{'", "'public'", "'static'", "'void'", "'('", "'String'", 
			"'['", "']'", "')'", "'}'", "'extends'", "';'", "','", "'return'", "'int'", 
			"'boolean'", "'char'", "'if'", "'else'", "'while'", "'System.out.println'", 
			"'length'", "'.'", "'this'", "'new'", "'&&'", "'='", "'<'", "'+'", "'-'", 
			"'*'", "'true'", "'false'", null, "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "AND", "EQUALS", "COMPARE", "ADD", "SUBTRACT", "MULTIPLY", 
			"TRUE", "FALSE", "IDENTIFIER", "NOT", "INTEGER_LITERAL", "WS", "COMMENT", 
			"ESCAPED_CHARACTER", "CHARACTER_LITERAL"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "miniJava.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public miniJavaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class GoalContext extends ParserRuleContext {
		public MainClassContext mainClass() {
			return getRuleContext(MainClassContext.class,0);
		}
		public TerminalNode EOF() { return getToken(miniJavaParser.EOF, 0); }
		public List<ClassDeclarationContext> classDeclaration() {
			return getRuleContexts(ClassDeclarationContext.class);
		}
		public ClassDeclarationContext classDeclaration(int i) {
			return getRuleContext(ClassDeclarationContext.class,i);
		}
		public GoalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitGoal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GoalContext goal() throws RecognitionException {
		GoalContext _localctx = new GoalContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_goal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			mainClass();
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(31);
				classDeclaration();
				}
				}
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(37);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainClassContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(miniJavaParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(miniJavaParser.IDENTIFIER, i);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public MainClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainClass; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitMainClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainClassContext mainClass() throws RecognitionException {
		MainClassContext _localctx = new MainClassContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mainClass);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(T__0);
			setState(40);
			match(IDENTIFIER);
			setState(41);
			match(T__1);
			setState(42);
			match(T__2);
			setState(43);
			match(T__3);
			setState(44);
			match(T__4);
			setState(45);
			match(IDENTIFIER);
			setState(46);
			match(T__5);
			setState(47);
			match(T__6);
			setState(48);
			match(T__7);
			setState(49);
			match(T__8);
			setState(50);
			match(IDENTIFIER);
			setState(51);
			match(T__9);
			setState(52);
			match(T__1);
			setState(53);
			statement();
			setState(54);
			match(T__10);
			setState(55);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(miniJavaParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(miniJavaParser.IDENTIFIER, i);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<MethodDeclarationContext> methodDeclaration() {
			return getRuleContexts(MethodDeclarationContext.class);
		}
		public MethodDeclarationContext methodDeclaration(int i) {
			return getRuleContext(MethodDeclarationContext.class,i);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(T__0);
			setState(58);
			match(IDENTIFIER);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(59);
				match(T__11);
				setState(60);
				match(IDENTIFIER);
				}
			}

			setState(63);
			match(T__1);
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(64);
				varDeclaration();
				}
				}
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(70);
				methodDeclaration();
				}
				}
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(76);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(miniJavaParser.IDENTIFIER, 0); }
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_varDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			type();
			setState(79);
			match(IDENTIFIER);
			setState(80);
			match(T__12);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDeclarationContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(miniJavaParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(miniJavaParser.IDENTIFIER, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitMethodDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_methodDeclaration);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__2);
			setState(83);
			type();
			setState(84);
			match(IDENTIFIER);
			setState(85);
			match(T__5);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(86);
				type();
				setState(87);
				match(IDENTIFIER);
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__13) {
					{
					{
					setState(88);
					match(T__13);
					setState(89);
					type();
					setState(90);
					match(IDENTIFIER);
					}
					}
					setState(96);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(99);
			match(T__9);
			setState(100);
			match(T__1);
			setState(104);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(101);
					varDeclaration();
					}
					} 
				}
				setState(106);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__18) | (1L << T__20) | (1L << T__21) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(107);
				statement();
				}
				}
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(113);
			match(T__14);
			setState(114);
			expression();
			setState(115);
			match(T__12);
			setState(116);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CharTypeContext extends TypeContext {
		public CharTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitCharType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanTypeContext extends TypeContext {
		public BooleanTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitBooleanType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntTypeContext extends TypeContext {
		public IntTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitIntType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentifierTypeContext extends TypeContext {
		public TerminalNode IDENTIFIER() { return getToken(miniJavaParser.IDENTIFIER, 0); }
		public IdentifierTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitIdentifierType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerArrayTypeContext extends TypeContext {
		public IntegerArrayTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitIntegerArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		try {
			setState(125);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new IntegerArrayTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				match(T__15);
				setState(119);
				match(T__7);
				setState(120);
				match(T__8);
				}
				break;
			case 2:
				_localctx = new BooleanTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(121);
				match(T__16);
				}
				break;
			case 3:
				_localctx = new IntTypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(122);
				match(T__15);
				}
				break;
			case 4:
				_localctx = new CharTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(123);
				match(T__17);
				}
				break;
			case 5:
				_localctx = new IdentifierTypeContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(124);
				match(IDENTIFIER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayIndexContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayIndex; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitArrayIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayIndexContext arrayIndex() throws RecognitionException {
		ArrayIndexContext _localctx = new ArrayIndexContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_arrayIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(T__7);
			setState(128);
			expression();
			setState(129);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class WhileStatementContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintStatementContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrintStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitPrintStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockStatementContext extends StatementContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitBlockStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayAssignStatementContext extends StatementContext {
		public TerminalNode IDENTIFIER() { return getToken(miniJavaParser.IDENTIFIER, 0); }
		public ArrayIndexContext arrayIndex() {
			return getRuleContext(ArrayIndexContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(miniJavaParser.EQUALS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayAssignStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitArrayAssignStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignStatementContext extends StatementContext {
		public TerminalNode IDENTIFIER() { return getToken(miniJavaParser.IDENTIFIER, 0); }
		public TerminalNode EQUALS() { return getToken(miniJavaParser.EQUALS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitAssignStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStatementContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public IfStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_statement);
		int _la;
		try {
			setState(170);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new BlockStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				match(T__1);
				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__18) | (1L << T__20) | (1L << T__21) | (1L << IDENTIFIER))) != 0)) {
					{
					{
					setState(132);
					statement();
					}
					}
					setState(137);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(138);
				match(T__10);
				}
				break;
			case 2:
				_localctx = new IfStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				match(T__18);
				setState(140);
				match(T__5);
				setState(141);
				expression();
				setState(142);
				match(T__9);
				setState(143);
				statement();
				setState(144);
				match(T__19);
				setState(145);
				statement();
				}
				break;
			case 3:
				_localctx = new WhileStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(147);
				match(T__20);
				setState(148);
				match(T__5);
				setState(149);
				expression();
				setState(150);
				match(T__9);
				setState(151);
				statement();
				}
				break;
			case 4:
				_localctx = new PrintStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(153);
				match(T__21);
				setState(154);
				match(T__5);
				setState(155);
				expression();
				setState(156);
				match(T__9);
				setState(157);
				match(T__12);
				}
				break;
			case 5:
				_localctx = new AssignStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(159);
				match(IDENTIFIER);
				setState(160);
				match(EQUALS);
				setState(161);
				expression();
				setState(162);
				match(T__12);
				}
				break;
			case 6:
				_localctx = new ArrayAssignStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(164);
				match(IDENTIFIER);
				setState(165);
				arrayIndex();
				setState(166);
				match(EQUALS);
				setState(167);
				expression();
				setState(168);
				match(T__12);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionTerminalContext expressionTerminal() {
			return getRuleContext(ExpressionTerminalContext.class,0);
		}
		public ExpressionTailContext expressionTail() {
			return getRuleContext(ExpressionTailContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			expressionTerminal();
			setState(173);
			expressionTail();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodFuncCallContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(miniJavaParser.IDENTIFIER, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MethodFuncCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodFuncCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitMethodFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodFuncCallContext methodFuncCall() throws RecognitionException {
		MethodFuncCallContext _localctx = new MethodFuncCallContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_methodFuncCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(IDENTIFIER);
			setState(176);
			match(T__5);
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__24) | (1L << T__25) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIER) | (1L << NOT) | (1L << INTEGER_LITERAL) | (1L << CHARACTER_LITERAL))) != 0)) {
				{
				setState(177);
				expression();
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__13) {
					{
					{
					setState(178);
					match(T__13);
					setState(179);
					expression();
					}
					}
					setState(184);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(187);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayLengthCallContext extends ParserRuleContext {
		public ArrayLengthCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLengthCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitArrayLengthCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLengthCallContext arrayLengthCall() throws RecognitionException {
		ArrayLengthCallContext _localctx = new ArrayLengthCallContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_arrayLengthCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(T__22);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionTailOpsContext extends ParserRuleContext {
		public ExpressionTailOpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionTailOps; }
	 
		public ExpressionTailOpsContext() { }
		public void copyFrom(ExpressionTailOpsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CompareExpressionContext extends ExpressionTailOpsContext {
		public TerminalNode COMPARE() { return getToken(miniJavaParser.COMPARE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CompareExpressionContext(ExpressionTailOpsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitCompareExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndExpressionContext extends ExpressionTailOpsContext {
		public TerminalNode AND() { return getToken(miniJavaParser.AND, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AndExpressionContext(ExpressionTailOpsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddExpressionContext extends ExpressionTailOpsContext {
		public TerminalNode ADD() { return getToken(miniJavaParser.ADD, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AddExpressionContext(ExpressionTailOpsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitAddExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubtractExpressionContext extends ExpressionTailOpsContext {
		public TerminalNode SUBTRACT() { return getToken(miniJavaParser.SUBTRACT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SubtractExpressionContext(ExpressionTailOpsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitSubtractExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiplyExpressionContext extends ExpressionTailOpsContext {
		public TerminalNode MULTIPLY() { return getToken(miniJavaParser.MULTIPLY, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MultiplyExpressionContext(ExpressionTailOpsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitMultiplyExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionTailOpsContext expressionTailOps() throws RecognitionException {
		ExpressionTailOpsContext _localctx = new ExpressionTailOpsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expressionTailOps);
		try {
			setState(201);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AND:
				_localctx = new AndExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(191);
				match(AND);
				setState(192);
				expression();
				}
				break;
			case COMPARE:
				_localctx = new CompareExpressionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(193);
				match(COMPARE);
				setState(194);
				expression();
				}
				break;
			case ADD:
				_localctx = new AddExpressionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(195);
				match(ADD);
				setState(196);
				expression();
				}
				break;
			case SUBTRACT:
				_localctx = new SubtractExpressionContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(197);
				match(SUBTRACT);
				setState(198);
				expression();
				}
				break;
			case MULTIPLY:
				_localctx = new MultiplyExpressionContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(199);
				match(MULTIPLY);
				setState(200);
				expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionTailContext extends ParserRuleContext {
		public ExpressionTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionTail; }
	 
		public ExpressionTailContext() { }
		public void copyFrom(ExpressionTailContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayIndexCallContext extends ExpressionTailContext {
		public ArrayIndexContext arrayIndex() {
			return getRuleContext(ArrayIndexContext.class,0);
		}
		public ExpressionTailOpsContext expressionTailOps() {
			return getRuleContext(ExpressionTailOpsContext.class,0);
		}
		public ArrayIndexCallContext(ExpressionTailContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitArrayIndexCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OperatorExpressionContext extends ExpressionTailContext {
		public ExpressionTailOpsContext expressionTailOps() {
			return getRuleContext(ExpressionTailOpsContext.class,0);
		}
		public OperatorExpressionContext(ExpressionTailContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitOperatorExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionVallExpressionContext extends ExpressionTailContext {
		public MethodFuncCallContext methodFuncCall() {
			return getRuleContext(MethodFuncCallContext.class,0);
		}
		public ExpressionTailOpsContext expressionTailOps() {
			return getRuleContext(ExpressionTailOpsContext.class,0);
		}
		public FunctionVallExpressionContext(ExpressionTailContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitFunctionVallExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NoExpressionTailContext extends ExpressionTailContext {
		public NoExpressionTailContext(ExpressionTailContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitNoExpressionTail(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GetArrayLengthContext extends ExpressionTailContext {
		public ArrayLengthCallContext arrayLengthCall() {
			return getRuleContext(ArrayLengthCallContext.class,0);
		}
		public ExpressionTailOpsContext expressionTailOps() {
			return getRuleContext(ExpressionTailOpsContext.class,0);
		}
		public GetArrayLengthContext(ExpressionTailContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitGetArrayLength(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionTailContext expressionTail() throws RecognitionException {
		ExpressionTailContext _localctx = new ExpressionTailContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expressionTail);
		try {
			setState(219);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new OperatorExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(203);
				expressionTailOps();
				}
				break;
			case 2:
				_localctx = new ArrayIndexCallContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(204);
				arrayIndex();
				setState(206);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(205);
					expressionTailOps();
					}
					break;
				}
				}
				break;
			case 3:
				_localctx = new GetArrayLengthContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(208);
				match(T__23);
				setState(209);
				arrayLengthCall();
				setState(211);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(210);
					expressionTailOps();
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new FunctionVallExpressionContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(213);
				match(T__23);
				setState(214);
				methodFuncCall();
				setState(216);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(215);
					expressionTailOps();
					}
					break;
				}
				}
				break;
			case 5:
				_localctx = new NoExpressionTailContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionTerminalContext extends ParserRuleContext {
		public ExpressionTerminalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionTerminal; }
	 
		public ExpressionTerminalContext() { }
		public void copyFrom(ExpressionTerminalContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParenthesizedExpressionContext extends ExpressionTerminalContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenthesizedExpressionContext(ExpressionTerminalContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitParenthesizedExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThisKeywordContext extends ExpressionTerminalContext {
		public ThisKeywordContext(ExpressionTerminalContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitThisKeyword(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentifierExpressionContext extends ExpressionTerminalContext {
		public TerminalNode IDENTIFIER() { return getToken(miniJavaParser.IDENTIFIER, 0); }
		public IdentifierExpressionContext(ExpressionTerminalContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitIdentifierExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegatedExpressionContext extends ExpressionTerminalContext {
		public TerminalNode NOT() { return getToken(miniJavaParser.NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NegatedExpressionContext(ExpressionTerminalContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitNegatedExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewArrayExpressionContext extends ExpressionTerminalContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NewArrayExpressionContext(ExpressionTerminalContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitNewArrayExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewClassExpressionContext extends ExpressionTerminalContext {
		public TerminalNode IDENTIFIER() { return getToken(miniJavaParser.IDENTIFIER, 0); }
		public NewClassExpressionContext(ExpressionTerminalContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitNewClassExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanExpressionContext extends ExpressionTerminalContext {
		public BoolValContext boolVal() {
			return getRuleContext(BoolValContext.class,0);
		}
		public BooleanExpressionContext(ExpressionTerminalContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitBooleanExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CharacterExpressionContext extends ExpressionTerminalContext {
		public TerminalNode CHARACTER_LITERAL() { return getToken(miniJavaParser.CHARACTER_LITERAL, 0); }
		public CharacterExpressionContext(ExpressionTerminalContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitCharacterExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerExpressionContext extends ExpressionTerminalContext {
		public TerminalNode INTEGER_LITERAL() { return getToken(miniJavaParser.INTEGER_LITERAL, 0); }
		public IntegerExpressionContext(ExpressionTerminalContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitIntegerExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionTerminalContext expressionTerminal() throws RecognitionException {
		ExpressionTerminalContext _localctx = new ExpressionTerminalContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expressionTerminal);
		try {
			setState(242);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				_localctx = new ThisKeywordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(221);
				match(T__24);
				}
				break;
			case 2:
				_localctx = new BooleanExpressionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(222);
				boolVal();
				}
				break;
			case 3:
				_localctx = new IntegerExpressionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(223);
				match(INTEGER_LITERAL);
				}
				break;
			case 4:
				_localctx = new CharacterExpressionContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(224);
				match(CHARACTER_LITERAL);
				}
				break;
			case 5:
				_localctx = new IdentifierExpressionContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(225);
				match(IDENTIFIER);
				}
				break;
			case 6:
				_localctx = new NewArrayExpressionContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(226);
				match(T__25);
				setState(227);
				match(T__15);
				setState(228);
				match(T__7);
				setState(229);
				expression();
				setState(230);
				match(T__8);
				}
				break;
			case 7:
				_localctx = new NewClassExpressionContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(232);
				match(T__25);
				setState(233);
				match(IDENTIFIER);
				setState(234);
				match(T__5);
				setState(235);
				match(T__9);
				}
				break;
			case 8:
				_localctx = new NegatedExpressionContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(236);
				match(NOT);
				setState(237);
				expression();
				}
				break;
			case 9:
				_localctx = new ParenthesizedExpressionContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(238);
				match(T__5);
				setState(239);
				expression();
				setState(240);
				match(T__9);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolValContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(miniJavaParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(miniJavaParser.FALSE, 0); }
		public BoolValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolVal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitBoolVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolValContext boolVal() throws RecognitionException {
		BoolValContext _localctx = new BoolValContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_boolVal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001)\u00f7\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0001\u0000"+
		"\u0005\u0000!\b\u0000\n\u0000\f\u0000$\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002>\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0005\u0002B\b\u0002\n\u0002\f\u0002E\t\u0002"+
		"\u0001\u0002\u0005\u0002H\b\u0002\n\u0002\f\u0002K\t\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004]\b\u0004\n\u0004\f\u0004"+
		"`\t\u0004\u0003\u0004b\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005"+
		"\u0004g\b\u0004\n\u0004\f\u0004j\t\u0004\u0001\u0004\u0005\u0004m\b\u0004"+
		"\n\u0004\f\u0004p\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005~\b\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0005\u0007\u0086\b\u0007"+
		"\n\u0007\f\u0007\u0089\t\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00ab"+
		"\b\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0005\t\u00b5\b\t\n\t\f\t\u00b8\t\t\u0003\t\u00ba\b\t\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u00ca\b\u000b\u0001\f\u0001\f\u0001\f\u0003\f\u00cf\b\f\u0001\f"+
		"\u0001\f\u0001\f\u0003\f\u00d4\b\f\u0001\f\u0001\f\u0001\f\u0003\f\u00d9"+
		"\b\f\u0001\f\u0003\f\u00dc\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00f3"+
		"\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0000\u0000\u000f\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u0000"+
		"\u0001\u0001\u0000!\"\u010e\u0000\u001e\u0001\u0000\u0000\u0000\u0002"+
		"\'\u0001\u0000\u0000\u0000\u00049\u0001\u0000\u0000\u0000\u0006N\u0001"+
		"\u0000\u0000\u0000\bR\u0001\u0000\u0000\u0000\n}\u0001\u0000\u0000\u0000"+
		"\f\u007f\u0001\u0000\u0000\u0000\u000e\u00aa\u0001\u0000\u0000\u0000\u0010"+
		"\u00ac\u0001\u0000\u0000\u0000\u0012\u00af\u0001\u0000\u0000\u0000\u0014"+
		"\u00bd\u0001\u0000\u0000\u0000\u0016\u00c9\u0001\u0000\u0000\u0000\u0018"+
		"\u00db\u0001\u0000\u0000\u0000\u001a\u00f2\u0001\u0000\u0000\u0000\u001c"+
		"\u00f4\u0001\u0000\u0000\u0000\u001e\"\u0003\u0002\u0001\u0000\u001f!"+
		"\u0003\u0004\u0002\u0000 \u001f\u0001\u0000\u0000\u0000!$\u0001\u0000"+
		"\u0000\u0000\" \u0001\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000#%\u0001"+
		"\u0000\u0000\u0000$\"\u0001\u0000\u0000\u0000%&\u0005\u0000\u0000\u0001"+
		"&\u0001\u0001\u0000\u0000\u0000\'(\u0005\u0001\u0000\u0000()\u0005#\u0000"+
		"\u0000)*\u0005\u0002\u0000\u0000*+\u0005\u0003\u0000\u0000+,\u0005\u0004"+
		"\u0000\u0000,-\u0005\u0005\u0000\u0000-.\u0005#\u0000\u0000./\u0005\u0006"+
		"\u0000\u0000/0\u0005\u0007\u0000\u000001\u0005\b\u0000\u000012\u0005\t"+
		"\u0000\u000023\u0005#\u0000\u000034\u0005\n\u0000\u000045\u0005\u0002"+
		"\u0000\u000056\u0003\u000e\u0007\u000067\u0005\u000b\u0000\u000078\u0005"+
		"\u000b\u0000\u00008\u0003\u0001\u0000\u0000\u00009:\u0005\u0001\u0000"+
		"\u0000:=\u0005#\u0000\u0000;<\u0005\f\u0000\u0000<>\u0005#\u0000\u0000"+
		"=;\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000"+
		"\u0000?C\u0005\u0002\u0000\u0000@B\u0003\u0006\u0003\u0000A@\u0001\u0000"+
		"\u0000\u0000BE\u0001\u0000\u0000\u0000CA\u0001\u0000\u0000\u0000CD\u0001"+
		"\u0000\u0000\u0000DI\u0001\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000"+
		"FH\u0003\b\u0004\u0000GF\u0001\u0000\u0000\u0000HK\u0001\u0000\u0000\u0000"+
		"IG\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000JL\u0001\u0000\u0000"+
		"\u0000KI\u0001\u0000\u0000\u0000LM\u0005\u000b\u0000\u0000M\u0005\u0001"+
		"\u0000\u0000\u0000NO\u0003\n\u0005\u0000OP\u0005#\u0000\u0000PQ\u0005"+
		"\r\u0000\u0000Q\u0007\u0001\u0000\u0000\u0000RS\u0005\u0003\u0000\u0000"+
		"ST\u0003\n\u0005\u0000TU\u0005#\u0000\u0000Ua\u0005\u0006\u0000\u0000"+
		"VW\u0003\n\u0005\u0000W^\u0005#\u0000\u0000XY\u0005\u000e\u0000\u0000"+
		"YZ\u0003\n\u0005\u0000Z[\u0005#\u0000\u0000[]\u0001\u0000\u0000\u0000"+
		"\\X\u0001\u0000\u0000\u0000]`\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000"+
		"\u0000^_\u0001\u0000\u0000\u0000_b\u0001\u0000\u0000\u0000`^\u0001\u0000"+
		"\u0000\u0000aV\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000bc\u0001"+
		"\u0000\u0000\u0000cd\u0005\n\u0000\u0000dh\u0005\u0002\u0000\u0000eg\u0003"+
		"\u0006\u0003\u0000fe\u0001\u0000\u0000\u0000gj\u0001\u0000\u0000\u0000"+
		"hf\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000in\u0001\u0000\u0000"+
		"\u0000jh\u0001\u0000\u0000\u0000km\u0003\u000e\u0007\u0000lk\u0001\u0000"+
		"\u0000\u0000mp\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000no\u0001"+
		"\u0000\u0000\u0000oq\u0001\u0000\u0000\u0000pn\u0001\u0000\u0000\u0000"+
		"qr\u0005\u000f\u0000\u0000rs\u0003\u0010\b\u0000st\u0005\r\u0000\u0000"+
		"tu\u0005\u000b\u0000\u0000u\t\u0001\u0000\u0000\u0000vw\u0005\u0010\u0000"+
		"\u0000wx\u0005\b\u0000\u0000x~\u0005\t\u0000\u0000y~\u0005\u0011\u0000"+
		"\u0000z~\u0005\u0010\u0000\u0000{~\u0005\u0012\u0000\u0000|~\u0005#\u0000"+
		"\u0000}v\u0001\u0000\u0000\u0000}y\u0001\u0000\u0000\u0000}z\u0001\u0000"+
		"\u0000\u0000}{\u0001\u0000\u0000\u0000}|\u0001\u0000\u0000\u0000~\u000b"+
		"\u0001\u0000\u0000\u0000\u007f\u0080\u0005\b\u0000\u0000\u0080\u0081\u0003"+
		"\u0010\b\u0000\u0081\u0082\u0005\t\u0000\u0000\u0082\r\u0001\u0000\u0000"+
		"\u0000\u0083\u0087\u0005\u0002\u0000\u0000\u0084\u0086\u0003\u000e\u0007"+
		"\u0000\u0085\u0084\u0001\u0000\u0000\u0000\u0086\u0089\u0001\u0000\u0000"+
		"\u0000\u0087\u0085\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000"+
		"\u0000\u0088\u008a\u0001\u0000\u0000\u0000\u0089\u0087\u0001\u0000\u0000"+
		"\u0000\u008a\u00ab\u0005\u000b\u0000\u0000\u008b\u008c\u0005\u0013\u0000"+
		"\u0000\u008c\u008d\u0005\u0006\u0000\u0000\u008d\u008e\u0003\u0010\b\u0000"+
		"\u008e\u008f\u0005\n\u0000\u0000\u008f\u0090\u0003\u000e\u0007\u0000\u0090"+
		"\u0091\u0005\u0014\u0000\u0000\u0091\u0092\u0003\u000e\u0007\u0000\u0092"+
		"\u00ab\u0001\u0000\u0000\u0000\u0093\u0094\u0005\u0015\u0000\u0000\u0094"+
		"\u0095\u0005\u0006\u0000\u0000\u0095\u0096\u0003\u0010\b\u0000\u0096\u0097"+
		"\u0005\n\u0000\u0000\u0097\u0098\u0003\u000e\u0007\u0000\u0098\u00ab\u0001"+
		"\u0000\u0000\u0000\u0099\u009a\u0005\u0016\u0000\u0000\u009a\u009b\u0005"+
		"\u0006\u0000\u0000\u009b\u009c\u0003\u0010\b\u0000\u009c\u009d\u0005\n"+
		"\u0000\u0000\u009d\u009e\u0005\r\u0000\u0000\u009e\u00ab\u0001\u0000\u0000"+
		"\u0000\u009f\u00a0\u0005#\u0000\u0000\u00a0\u00a1\u0005\u001c\u0000\u0000"+
		"\u00a1\u00a2\u0003\u0010\b\u0000\u00a2\u00a3\u0005\r\u0000\u0000\u00a3"+
		"\u00ab\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005#\u0000\u0000\u00a5\u00a6"+
		"\u0003\f\u0006\u0000\u00a6\u00a7\u0005\u001c\u0000\u0000\u00a7\u00a8\u0003"+
		"\u0010\b\u0000\u00a8\u00a9\u0005\r\u0000\u0000\u00a9\u00ab\u0001\u0000"+
		"\u0000\u0000\u00aa\u0083\u0001\u0000\u0000\u0000\u00aa\u008b\u0001\u0000"+
		"\u0000\u0000\u00aa\u0093\u0001\u0000\u0000\u0000\u00aa\u0099\u0001\u0000"+
		"\u0000\u0000\u00aa\u009f\u0001\u0000\u0000\u0000\u00aa\u00a4\u0001\u0000"+
		"\u0000\u0000\u00ab\u000f\u0001\u0000\u0000\u0000\u00ac\u00ad\u0003\u001a"+
		"\r\u0000\u00ad\u00ae\u0003\u0018\f\u0000\u00ae\u0011\u0001\u0000\u0000"+
		"\u0000\u00af\u00b0\u0005#\u0000\u0000\u00b0\u00b9\u0005\u0006\u0000\u0000"+
		"\u00b1\u00b6\u0003\u0010\b\u0000\u00b2\u00b3\u0005\u000e\u0000\u0000\u00b3"+
		"\u00b5\u0003\u0010\b\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b5\u00b8"+
		"\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b6\u00b7"+
		"\u0001\u0000\u0000\u0000\u00b7\u00ba\u0001\u0000\u0000\u0000\u00b8\u00b6"+
		"\u0001\u0000\u0000\u0000\u00b9\u00b1\u0001\u0000\u0000\u0000\u00b9\u00ba"+
		"\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000\u00bb\u00bc"+
		"\u0005\n\u0000\u0000\u00bc\u0013\u0001\u0000\u0000\u0000\u00bd\u00be\u0005"+
		"\u0017\u0000\u0000\u00be\u0015\u0001\u0000\u0000\u0000\u00bf\u00c0\u0005"+
		"\u001b\u0000\u0000\u00c0\u00ca\u0003\u0010\b\u0000\u00c1\u00c2\u0005\u001d"+
		"\u0000\u0000\u00c2\u00ca\u0003\u0010\b\u0000\u00c3\u00c4\u0005\u001e\u0000"+
		"\u0000\u00c4\u00ca\u0003\u0010\b\u0000\u00c5\u00c6\u0005\u001f\u0000\u0000"+
		"\u00c6\u00ca\u0003\u0010\b\u0000\u00c7\u00c8\u0005 \u0000\u0000\u00c8"+
		"\u00ca\u0003\u0010\b\u0000\u00c9\u00bf\u0001\u0000\u0000\u0000\u00c9\u00c1"+
		"\u0001\u0000\u0000\u0000\u00c9\u00c3\u0001\u0000\u0000\u0000\u00c9\u00c5"+
		"\u0001\u0000\u0000\u0000\u00c9\u00c7\u0001\u0000\u0000\u0000\u00ca\u0017"+
		"\u0001\u0000\u0000\u0000\u00cb\u00dc\u0003\u0016\u000b\u0000\u00cc\u00ce"+
		"\u0003\f\u0006\u0000\u00cd\u00cf\u0003\u0016\u000b\u0000\u00ce\u00cd\u0001"+
		"\u0000\u0000\u0000\u00ce\u00cf\u0001\u0000\u0000\u0000\u00cf\u00dc\u0001"+
		"\u0000\u0000\u0000\u00d0\u00d1\u0005\u0018\u0000\u0000\u00d1\u00d3\u0003"+
		"\u0014\n\u0000\u00d2\u00d4\u0003\u0016\u000b\u0000\u00d3\u00d2\u0001\u0000"+
		"\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000\u00d4\u00dc\u0001\u0000"+
		"\u0000\u0000\u00d5\u00d6\u0005\u0018\u0000\u0000\u00d6\u00d8\u0003\u0012"+
		"\t\u0000\u00d7\u00d9\u0003\u0016\u000b\u0000\u00d8\u00d7\u0001\u0000\u0000"+
		"\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9\u00dc\u0001\u0000\u0000"+
		"\u0000\u00da\u00dc\u0001\u0000\u0000\u0000\u00db\u00cb\u0001\u0000\u0000"+
		"\u0000\u00db\u00cc\u0001\u0000\u0000\u0000\u00db\u00d0\u0001\u0000\u0000"+
		"\u0000\u00db\u00d5\u0001\u0000\u0000\u0000\u00db\u00da\u0001\u0000\u0000"+
		"\u0000\u00dc\u0019\u0001\u0000\u0000\u0000\u00dd\u00f3\u0005\u0019\u0000"+
		"\u0000\u00de\u00f3\u0003\u001c\u000e\u0000\u00df\u00f3\u0005%\u0000\u0000"+
		"\u00e0\u00f3\u0005)\u0000\u0000\u00e1\u00f3\u0005#\u0000\u0000\u00e2\u00e3"+
		"\u0005\u001a\u0000\u0000\u00e3\u00e4\u0005\u0010\u0000\u0000\u00e4\u00e5"+
		"\u0005\b\u0000\u0000\u00e5\u00e6\u0003\u0010\b\u0000\u00e6\u00e7\u0005"+
		"\t\u0000\u0000\u00e7\u00f3\u0001\u0000\u0000\u0000\u00e8\u00e9\u0005\u001a"+
		"\u0000\u0000\u00e9\u00ea\u0005#\u0000\u0000\u00ea\u00eb\u0005\u0006\u0000"+
		"\u0000\u00eb\u00f3\u0005\n\u0000\u0000\u00ec\u00ed\u0005$\u0000\u0000"+
		"\u00ed\u00f3\u0003\u0010\b\u0000\u00ee\u00ef\u0005\u0006\u0000\u0000\u00ef"+
		"\u00f0\u0003\u0010\b\u0000\u00f0\u00f1\u0005\n\u0000\u0000\u00f1\u00f3"+
		"\u0001\u0000\u0000\u0000\u00f2\u00dd\u0001\u0000\u0000\u0000\u00f2\u00de"+
		"\u0001\u0000\u0000\u0000\u00f2\u00df\u0001\u0000\u0000\u0000\u00f2\u00e0"+
		"\u0001\u0000\u0000\u0000\u00f2\u00e1\u0001\u0000\u0000\u0000\u00f2\u00e2"+
		"\u0001\u0000\u0000\u0000\u00f2\u00e8\u0001\u0000\u0000\u0000\u00f2\u00ec"+
		"\u0001\u0000\u0000\u0000\u00f2\u00ee\u0001\u0000\u0000\u0000\u00f3\u001b"+
		"\u0001\u0000\u0000\u0000\u00f4\u00f5\u0007\u0000\u0000\u0000\u00f5\u001d"+
		"\u0001\u0000\u0000\u0000\u0013\"=CI^ahn}\u0087\u00aa\u00b6\u00b9\u00c9"+
		"\u00ce\u00d3\u00d8\u00db\u00f2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}