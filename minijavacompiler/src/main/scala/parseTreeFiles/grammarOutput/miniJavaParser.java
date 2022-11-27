package parseTreeFiles.grammarOutput;// Generated from miniJava.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.util.List;

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
		RULE_expression = 8, RULE_expressionAnd = 9, RULE_methodFuncCall = 10, 
		RULE_arrayLengthCall = 11, RULE_expressionComp = 12, RULE_expressionTailOps = 13, 
		RULE_expressionTail = 14, RULE_expressionTerminal = 15, RULE_boolVal = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"goal", "mainClass", "classDeclaration", "varDeclaration", "methodDeclaration", 
			"type", "arrayIndex", "statement", "expression", "expressionAnd", "methodFuncCall", 
			"arrayLengthCall", "expressionComp", "expressionTailOps", "expressionTail", 
			"expressionTerminal", "boolVal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'class'", "'{'", "'public'", "'static'", "'void'", "'('", "'String'", 
			"'['", "']'", "')'", "'}'", "'extends'", "';'", "','", "'return'", "'int'", 
			"'boolean'", "'char'", "'if'", "'else'", "'while'", "'System.out.println'", 
			"'.length'", "'.'", "'this'", "'new'", "'&&'", "'='", "'<'", "'+'", "'-'", 
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterGoal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitGoal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor) return ((miniJavaVisitor<? extends T>)visitor).visitGoal(this);
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
			setState(34);
			mainClass();
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(35);
				classDeclaration();
				}
				}
				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(41);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterMainClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitMainClass(this);
		}
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
			setState(43);
			match(T__0);
			setState(44);
			match(IDENTIFIER);
			setState(45);
			match(T__1);
			setState(46);
			match(T__2);
			setState(47);
			match(T__3);
			setState(48);
			match(T__4);
			setState(49);
			match(IDENTIFIER);
			setState(50);
			match(T__5);
			setState(51);
			match(T__6);
			setState(52);
			match(T__7);
			setState(53);
			match(T__8);
			setState(54);
			match(IDENTIFIER);
			setState(55);
			match(T__9);
			setState(56);
			match(T__1);
			setState(57);
			statement();
			setState(58);
			match(T__10);
			setState(59);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitClassDeclaration(this);
		}
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
			setState(61);
			match(T__0);
			setState(62);
			match(IDENTIFIER);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(63);
				match(T__11);
				setState(64);
				match(IDENTIFIER);
				}
			}

			setState(67);
			match(T__1);
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(68);
				varDeclaration();
				}
				}
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(74);
				methodDeclaration();
				}
				}
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(80);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitVarDeclaration(this);
		}
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
			setState(82);
			type();
			setState(83);
			match(IDENTIFIER);
			setState(84);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitMethodDeclaration(this);
		}
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
			setState(86);
			match(T__2);
			setState(87);
			type();
			setState(88);
			match(IDENTIFIER);
			setState(89);
			match(T__5);
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(90);
				type();
				setState(91);
				match(IDENTIFIER);
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__13) {
					{
					{
					setState(92);
					match(T__13);
					setState(93);
					type();
					setState(94);
					match(IDENTIFIER);
					}
					}
					setState(100);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(103);
			match(T__9);
			setState(104);
			match(T__1);
			setState(108);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(105);
					varDeclaration();
					}
					} 
				}
				setState(110);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__18) | (1L << T__20) | (1L << T__21) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(111);
				statement();
				}
				}
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(117);
			match(T__14);
			setState(118);
			expression();
			setState(119);
			match(T__12);
			setState(120);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterCharType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitCharType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitCharType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanTypeContext extends TypeContext {
		public BooleanTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterBooleanType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitBooleanType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitBooleanType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntTypeContext extends TypeContext {
		public IntTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterIntType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitIntType(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterIdentifierType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitIdentifierType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitIdentifierType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerArrayTypeContext extends TypeContext {
		public IntegerArrayTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterIntegerArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitIntegerArrayType(this);
		}
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
			setState(129);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new IntegerArrayTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				match(T__15);
				setState(123);
				match(T__7);
				setState(124);
				match(T__8);
				}
				break;
			case 2:
				_localctx = new BooleanTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(125);
				match(T__16);
				}
				break;
			case 3:
				_localctx = new IntTypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(126);
				match(T__15);
				}
				break;
			case 4:
				_localctx = new CharTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(127);
				match(T__17);
				}
				break;
			case 5:
				_localctx = new IdentifierTypeContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(128);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterArrayIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitArrayIndex(this);
		}
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
			setState(131);
			match(T__7);
			setState(132);
			expression();
			setState(133);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitWhileStatement(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterPrintStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitPrintStatement(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitBlockStatement(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterArrayAssignStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitArrayAssignStatement(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterAssignStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitAssignStatement(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitIfStatement(this);
		}
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
			setState(174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new BlockStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(135);
				match(T__1);
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__18) | (1L << T__20) | (1L << T__21) | (1L << IDENTIFIER))) != 0)) {
					{
					{
					setState(136);
					statement();
					}
					}
					setState(141);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(142);
				match(T__10);
				}
				break;
			case 2:
				_localctx = new IfStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				match(T__18);
				setState(144);
				match(T__5);
				setState(145);
				expression();
				setState(146);
				match(T__9);
				setState(147);
				statement();
				setState(148);
				match(T__19);
				setState(149);
				statement();
				}
				break;
			case 3:
				_localctx = new WhileStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(151);
				match(T__20);
				setState(152);
				match(T__5);
				setState(153);
				expression();
				setState(154);
				match(T__9);
				setState(155);
				statement();
				}
				break;
			case 4:
				_localctx = new PrintStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(157);
				match(T__21);
				setState(158);
				match(T__5);
				setState(159);
				expression();
				setState(160);
				match(T__9);
				setState(161);
				match(T__12);
				}
				break;
			case 5:
				_localctx = new AssignStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(163);
				match(IDENTIFIER);
				setState(164);
				match(EQUALS);
				setState(165);
				expression();
				setState(166);
				match(T__12);
				}
				break;
			case 6:
				_localctx = new ArrayAssignStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(168);
				match(IDENTIFIER);
				setState(169);
				arrayIndex();
				setState(170);
				match(EQUALS);
				setState(171);
				expression();
				setState(172);
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
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExpressionValueContext extends ExpressionContext {
		public ExpressionCompContext expressionComp() {
			return getRuleContext(ExpressionCompContext.class,0);
		}
		public ExpressionAndContext expressionAnd() {
			return getRuleContext(ExpressionAndContext.class,0);
		}
		public ExpressionValueContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterExpressionValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitExpressionValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitExpressionValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expression);
		int _la;
		try {
			_localctx = new ExpressionValueContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			expressionComp();
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AND) {
				{
				setState(177);
				expressionAnd();
				}
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

	public static class ExpressionAndContext extends ParserRuleContext {
		public ExpressionAndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionAnd; }
	 
		public ExpressionAndContext() { }
		public void copyFrom(ExpressionAndContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AndexpressionContext extends ExpressionAndContext {
		public TerminalNode AND() { return getToken(miniJavaParser.AND, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AndexpressionContext(ExpressionAndContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterAndexpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitAndexpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitAndexpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionAndContext expressionAnd() throws RecognitionException {
		ExpressionAndContext _localctx = new ExpressionAndContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expressionAnd);
		try {
			_localctx = new AndexpressionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(AND);
			setState(181);
			expression();
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterMethodFuncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitMethodFuncCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitMethodFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodFuncCallContext methodFuncCall() throws RecognitionException {
		MethodFuncCallContext _localctx = new MethodFuncCallContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_methodFuncCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(IDENTIFIER);
			setState(184);
			match(T__5);
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__24) | (1L << T__25) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIER) | (1L << NOT) | (1L << INTEGER_LITERAL) | (1L << CHARACTER_LITERAL))) != 0)) {
				{
				setState(185);
				expression();
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__13) {
					{
					{
					setState(186);
					match(T__13);
					setState(187);
					expression();
					}
					}
					setState(192);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(195);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterArrayLengthCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitArrayLengthCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitArrayLengthCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLengthCallContext arrayLengthCall() throws RecognitionException {
		ArrayLengthCallContext _localctx = new ArrayLengthCallContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_arrayLengthCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
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

	public static class ExpressionCompContext extends ParserRuleContext {
		public ExpressionCompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionComp; }
	 
		public ExpressionCompContext() { }
		public void copyFrom(ExpressionCompContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CompareExpressionContext extends ExpressionCompContext {
		public ExpressionTerminalContext expressionTerminal() {
			return getRuleContext(ExpressionTerminalContext.class,0);
		}
		public ExpressionTailContext expressionTail() {
			return getRuleContext(ExpressionTailContext.class,0);
		}
		public TerminalNode COMPARE() { return getToken(miniJavaParser.COMPARE, 0); }
		public ExpressionCompContext expressionComp() {
			return getRuleContext(ExpressionCompContext.class,0);
		}
		public CompareExpressionContext(ExpressionCompContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterCompareExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitCompareExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitCompareExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionCompContext expressionComp() throws RecognitionException {
		ExpressionCompContext _localctx = new ExpressionCompContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expressionComp);
		int _la;
		try {
			_localctx = new CompareExpressionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(199);
			expressionTerminal();
			setState(200);
			expressionTail();
			}
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMPARE) {
				{
				setState(202);
				match(COMPARE);
				setState(203);
				expressionComp();
				}
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
	public static class AddExpressionContext extends ExpressionTailOpsContext {
		public TerminalNode ADD() { return getToken(miniJavaParser.ADD, 0); }
		public ExpressionTerminalContext expressionTerminal() {
			return getRuleContext(ExpressionTerminalContext.class,0);
		}
		public ExpressionTailContext expressionTail() {
			return getRuleContext(ExpressionTailContext.class,0);
		}
		public AddExpressionContext(ExpressionTailOpsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterAddExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitAddExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitAddExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubtractExpressionContext extends ExpressionTailOpsContext {
		public TerminalNode SUBTRACT() { return getToken(miniJavaParser.SUBTRACT, 0); }
		public ExpressionTerminalContext expressionTerminal() {
			return getRuleContext(ExpressionTerminalContext.class,0);
		}
		public ExpressionTailContext expressionTail() {
			return getRuleContext(ExpressionTailContext.class,0);
		}
		public SubtractExpressionContext(ExpressionTailOpsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterSubtractExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitSubtractExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitSubtractExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiplyExpressionContext extends ExpressionTailOpsContext {
		public TerminalNode MULTIPLY() { return getToken(miniJavaParser.MULTIPLY, 0); }
		public ExpressionTerminalContext expressionTerminal() {
			return getRuleContext(ExpressionTerminalContext.class,0);
		}
		public ExpressionTailContext expressionTail() {
			return getRuleContext(ExpressionTailContext.class,0);
		}
		public MultiplyExpressionContext(ExpressionTailOpsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterMultiplyExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitMultiplyExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitMultiplyExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionTailOpsContext expressionTailOps() throws RecognitionException {
		ExpressionTailOpsContext _localctx = new ExpressionTailOpsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expressionTailOps);
		try {
			setState(218);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MULTIPLY:
				_localctx = new MultiplyExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(206);
				match(MULTIPLY);
				{
				setState(207);
				expressionTerminal();
				setState(208);
				expressionTail();
				}
				}
				break;
			case ADD:
				_localctx = new AddExpressionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(210);
				match(ADD);
				{
				setState(211);
				expressionTerminal();
				setState(212);
				expressionTail();
				}
				}
				break;
			case SUBTRACT:
				_localctx = new SubtractExpressionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(214);
				match(SUBTRACT);
				{
				setState(215);
				expressionTerminal();
				setState(216);
				expressionTail();
				}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterArrayIndexCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitArrayIndexCall(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterOperatorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitOperatorExpression(this);
		}
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
		public ExpressionTailContext expressionTail() {
			return getRuleContext(ExpressionTailContext.class,0);
		}
		public FunctionVallExpressionContext(ExpressionTailContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterFunctionVallExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitFunctionVallExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitFunctionVallExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NoExpressionTailContext extends ExpressionTailContext {
		public NoExpressionTailContext(ExpressionTailContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterNoExpressionTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitNoExpressionTail(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterGetArrayLength(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitGetArrayLength(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitGetArrayLength(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionTailContext expressionTail() throws RecognitionException {
		ExpressionTailContext _localctx = new ExpressionTailContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expressionTail);
		try {
			setState(234);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				_localctx = new OperatorExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(220);
				expressionTailOps();
				}
				break;
			case 2:
				_localctx = new ArrayIndexCallContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(221);
				arrayIndex();
				setState(223);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(222);
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
				setState(225);
				arrayLengthCall();
				setState(227);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(226);
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
				setState(229);
				match(T__23);
				setState(230);
				methodFuncCall();
				{
				setState(231);
				expressionTail();
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterParenthesizedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitParenthesizedExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitParenthesizedExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThisKeywordContext extends ExpressionTerminalContext {
		public ThisKeywordContext(ExpressionTerminalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterThisKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitThisKeyword(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterIdentifierExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitIdentifierExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitIdentifierExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegatedExpressionContext extends ExpressionTerminalContext {
		public TerminalNode NOT() { return getToken(miniJavaParser.NOT, 0); }
		public ExpressionTerminalContext expressionTerminal() {
			return getRuleContext(ExpressionTerminalContext.class,0);
		}
		public ExpressionTailContext expressionTail() {
			return getRuleContext(ExpressionTailContext.class,0);
		}
		public NegatedExpressionContext(ExpressionTerminalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterNegatedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitNegatedExpression(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterNewArrayExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitNewArrayExpression(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterNewClassExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitNewClassExpression(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterBooleanExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitBooleanExpression(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterCharacterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitCharacterExpression(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterIntegerExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitIntegerExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitIntegerExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionTerminalContext expressionTerminal() throws RecognitionException {
		ExpressionTerminalContext _localctx = new ExpressionTerminalContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expressionTerminal);
		try {
			setState(259);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				_localctx = new ThisKeywordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(236);
				match(T__24);
				}
				break;
			case 2:
				_localctx = new BooleanExpressionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(237);
				boolVal();
				}
				break;
			case 3:
				_localctx = new IntegerExpressionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(238);
				match(INTEGER_LITERAL);
				}
				break;
			case 4:
				_localctx = new CharacterExpressionContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(239);
				match(CHARACTER_LITERAL);
				}
				break;
			case 5:
				_localctx = new IdentifierExpressionContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(240);
				match(IDENTIFIER);
				}
				break;
			case 6:
				_localctx = new NewArrayExpressionContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(241);
				match(T__25);
				setState(242);
				match(T__15);
				setState(243);
				match(T__7);
				setState(244);
				expression();
				setState(245);
				match(T__8);
				}
				break;
			case 7:
				_localctx = new NewClassExpressionContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(247);
				match(T__25);
				setState(248);
				match(IDENTIFIER);
				setState(249);
				match(T__5);
				setState(250);
				match(T__9);
				}
				break;
			case 8:
				_localctx = new NegatedExpressionContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(251);
				match(NOT);
				{
				setState(252);
				expressionTerminal();
				setState(253);
				expressionTail();
				}
				}
				break;
			case 9:
				_localctx = new ParenthesizedExpressionContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(255);
				match(T__5);
				setState(256);
				expression();
				setState(257);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).enterBoolVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof miniJavaListener ) ((miniJavaListener)listener).exitBoolVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof miniJavaVisitor ) return ((miniJavaVisitor<? extends T>)visitor).visitBoolVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolValContext boolVal() throws RecognitionException {
		BoolValContext _localctx = new BoolValContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_boolVal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
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
		"\u0004\u0001)\u0108\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0005\u0000%\b\u0000"+
		"\n\u0000\f\u0000(\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002B\b\u0002\u0001\u0002\u0001\u0002"+
		"\u0005\u0002F\b\u0002\n\u0002\f\u0002I\t\u0002\u0001\u0002\u0005\u0002"+
		"L\b\u0002\n\u0002\f\u0002O\t\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0005\u0004a\b\u0004\n\u0004\f\u0004d\t\u0004\u0003\u0004"+
		"f\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004k\b\u0004\n\u0004"+
		"\f\u0004n\t\u0004\u0001\u0004\u0005\u0004q\b\u0004\n\u0004\f\u0004t\t"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005\u0082\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0005\u0007\u008a\b\u0007\n\u0007\f\u0007"+
		"\u008d\t\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00af\b\u0007\u0001\b"+
		"\u0001\b\u0003\b\u00b3\b\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0005\n\u00bd\b\n\n\n\f\n\u00c0\t\n\u0003\n\u00c2\b"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u00cd\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00db\b\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00e0\b\u000e\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u00e4\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0003\u000e\u00eb\b\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0104\b\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0000\u0000\u0011\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \u0000\u0001\u0001\u0000"+
		"!\"\u011c\u0000\"\u0001\u0000\u0000\u0000\u0002+\u0001\u0000\u0000\u0000"+
		"\u0004=\u0001\u0000\u0000\u0000\u0006R\u0001\u0000\u0000\u0000\bV\u0001"+
		"\u0000\u0000\u0000\n\u0081\u0001\u0000\u0000\u0000\f\u0083\u0001\u0000"+
		"\u0000\u0000\u000e\u00ae\u0001\u0000\u0000\u0000\u0010\u00b0\u0001\u0000"+
		"\u0000\u0000\u0012\u00b4\u0001\u0000\u0000\u0000\u0014\u00b7\u0001\u0000"+
		"\u0000\u0000\u0016\u00c5\u0001\u0000\u0000\u0000\u0018\u00c7\u0001\u0000"+
		"\u0000\u0000\u001a\u00da\u0001\u0000\u0000\u0000\u001c\u00ea\u0001\u0000"+
		"\u0000\u0000\u001e\u0103\u0001\u0000\u0000\u0000 \u0105\u0001\u0000\u0000"+
		"\u0000\"&\u0003\u0002\u0001\u0000#%\u0003\u0004\u0002\u0000$#\u0001\u0000"+
		"\u0000\u0000%(\u0001\u0000\u0000\u0000&$\u0001\u0000\u0000\u0000&\'\u0001"+
		"\u0000\u0000\u0000\')\u0001\u0000\u0000\u0000(&\u0001\u0000\u0000\u0000"+
		")*\u0005\u0000\u0000\u0001*\u0001\u0001\u0000\u0000\u0000+,\u0005\u0001"+
		"\u0000\u0000,-\u0005#\u0000\u0000-.\u0005\u0002\u0000\u0000./\u0005\u0003"+
		"\u0000\u0000/0\u0005\u0004\u0000\u000001\u0005\u0005\u0000\u000012\u0005"+
		"#\u0000\u000023\u0005\u0006\u0000\u000034\u0005\u0007\u0000\u000045\u0005"+
		"\b\u0000\u000056\u0005\t\u0000\u000067\u0005#\u0000\u000078\u0005\n\u0000"+
		"\u000089\u0005\u0002\u0000\u00009:\u0003\u000e\u0007\u0000:;\u0005\u000b"+
		"\u0000\u0000;<\u0005\u000b\u0000\u0000<\u0003\u0001\u0000\u0000\u0000"+
		"=>\u0005\u0001\u0000\u0000>A\u0005#\u0000\u0000?@\u0005\f\u0000\u0000"+
		"@B\u0005#\u0000\u0000A?\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000"+
		"BC\u0001\u0000\u0000\u0000CG\u0005\u0002\u0000\u0000DF\u0003\u0006\u0003"+
		"\u0000ED\u0001\u0000\u0000\u0000FI\u0001\u0000\u0000\u0000GE\u0001\u0000"+
		"\u0000\u0000GH\u0001\u0000\u0000\u0000HM\u0001\u0000\u0000\u0000IG\u0001"+
		"\u0000\u0000\u0000JL\u0003\b\u0004\u0000KJ\u0001\u0000\u0000\u0000LO\u0001"+
		"\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000"+
		"NP\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000PQ\u0005\u000b\u0000"+
		"\u0000Q\u0005\u0001\u0000\u0000\u0000RS\u0003\n\u0005\u0000ST\u0005#\u0000"+
		"\u0000TU\u0005\r\u0000\u0000U\u0007\u0001\u0000\u0000\u0000VW\u0005\u0003"+
		"\u0000\u0000WX\u0003\n\u0005\u0000XY\u0005#\u0000\u0000Ye\u0005\u0006"+
		"\u0000\u0000Z[\u0003\n\u0005\u0000[b\u0005#\u0000\u0000\\]\u0005\u000e"+
		"\u0000\u0000]^\u0003\n\u0005\u0000^_\u0005#\u0000\u0000_a\u0001\u0000"+
		"\u0000\u0000`\\\u0001\u0000\u0000\u0000ad\u0001\u0000\u0000\u0000b`\u0001"+
		"\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000cf\u0001\u0000\u0000\u0000"+
		"db\u0001\u0000\u0000\u0000eZ\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000"+
		"\u0000fg\u0001\u0000\u0000\u0000gh\u0005\n\u0000\u0000hl\u0005\u0002\u0000"+
		"\u0000ik\u0003\u0006\u0003\u0000ji\u0001\u0000\u0000\u0000kn\u0001\u0000"+
		"\u0000\u0000lj\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mr\u0001"+
		"\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000oq\u0003\u000e\u0007\u0000"+
		"po\u0001\u0000\u0000\u0000qt\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000"+
		"\u0000rs\u0001\u0000\u0000\u0000su\u0001\u0000\u0000\u0000tr\u0001\u0000"+
		"\u0000\u0000uv\u0005\u000f\u0000\u0000vw\u0003\u0010\b\u0000wx\u0005\r"+
		"\u0000\u0000xy\u0005\u000b\u0000\u0000y\t\u0001\u0000\u0000\u0000z{\u0005"+
		"\u0010\u0000\u0000{|\u0005\b\u0000\u0000|\u0082\u0005\t\u0000\u0000}\u0082"+
		"\u0005\u0011\u0000\u0000~\u0082\u0005\u0010\u0000\u0000\u007f\u0082\u0005"+
		"\u0012\u0000\u0000\u0080\u0082\u0005#\u0000\u0000\u0081z\u0001\u0000\u0000"+
		"\u0000\u0081}\u0001\u0000\u0000\u0000\u0081~\u0001\u0000\u0000\u0000\u0081"+
		"\u007f\u0001\u0000\u0000\u0000\u0081\u0080\u0001\u0000\u0000\u0000\u0082"+
		"\u000b\u0001\u0000\u0000\u0000\u0083\u0084\u0005\b\u0000\u0000\u0084\u0085"+
		"\u0003\u0010\b\u0000\u0085\u0086\u0005\t\u0000\u0000\u0086\r\u0001\u0000"+
		"\u0000\u0000\u0087\u008b\u0005\u0002\u0000\u0000\u0088\u008a\u0003\u000e"+
		"\u0007\u0000\u0089\u0088\u0001\u0000\u0000\u0000\u008a\u008d\u0001\u0000"+
		"\u0000\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008b\u008c\u0001\u0000"+
		"\u0000\u0000\u008c\u008e\u0001\u0000\u0000\u0000\u008d\u008b\u0001\u0000"+
		"\u0000\u0000\u008e\u00af\u0005\u000b\u0000\u0000\u008f\u0090\u0005\u0013"+
		"\u0000\u0000\u0090\u0091\u0005\u0006\u0000\u0000\u0091\u0092\u0003\u0010"+
		"\b\u0000\u0092\u0093\u0005\n\u0000\u0000\u0093\u0094\u0003\u000e\u0007"+
		"\u0000\u0094\u0095\u0005\u0014\u0000\u0000\u0095\u0096\u0003\u000e\u0007"+
		"\u0000\u0096\u00af\u0001\u0000\u0000\u0000\u0097\u0098\u0005\u0015\u0000"+
		"\u0000\u0098\u0099\u0005\u0006\u0000\u0000\u0099\u009a\u0003\u0010\b\u0000"+
		"\u009a\u009b\u0005\n\u0000\u0000\u009b\u009c\u0003\u000e\u0007\u0000\u009c"+
		"\u00af\u0001\u0000\u0000\u0000\u009d\u009e\u0005\u0016\u0000\u0000\u009e"+
		"\u009f\u0005\u0006\u0000\u0000\u009f\u00a0\u0003\u0010\b\u0000\u00a0\u00a1"+
		"\u0005\n\u0000\u0000\u00a1\u00a2\u0005\r\u0000\u0000\u00a2\u00af\u0001"+
		"\u0000\u0000\u0000\u00a3\u00a4\u0005#\u0000\u0000\u00a4\u00a5\u0005\u001c"+
		"\u0000\u0000\u00a5\u00a6\u0003\u0010\b\u0000\u00a6\u00a7\u0005\r\u0000"+
		"\u0000\u00a7\u00af\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005#\u0000\u0000"+
		"\u00a9\u00aa\u0003\f\u0006\u0000\u00aa\u00ab\u0005\u001c\u0000\u0000\u00ab"+
		"\u00ac\u0003\u0010\b\u0000\u00ac\u00ad\u0005\r\u0000\u0000\u00ad\u00af"+
		"\u0001\u0000\u0000\u0000\u00ae\u0087\u0001\u0000\u0000\u0000\u00ae\u008f"+
		"\u0001\u0000\u0000\u0000\u00ae\u0097\u0001\u0000\u0000\u0000\u00ae\u009d"+
		"\u0001\u0000\u0000\u0000\u00ae\u00a3\u0001\u0000\u0000\u0000\u00ae\u00a8"+
		"\u0001\u0000\u0000\u0000\u00af\u000f\u0001\u0000\u0000\u0000\u00b0\u00b2"+
		"\u0003\u0018\f\u0000\u00b1\u00b3\u0003\u0012\t\u0000\u00b2\u00b1\u0001"+
		"\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u0011\u0001"+
		"\u0000\u0000\u0000\u00b4\u00b5\u0005\u001b\u0000\u0000\u00b5\u00b6\u0003"+
		"\u0010\b\u0000\u00b6\u0013\u0001\u0000\u0000\u0000\u00b7\u00b8\u0005#"+
		"\u0000\u0000\u00b8\u00c1\u0005\u0006\u0000\u0000\u00b9\u00be\u0003\u0010"+
		"\b\u0000\u00ba\u00bb\u0005\u000e\u0000\u0000\u00bb\u00bd\u0003\u0010\b"+
		"\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bd\u00c0\u0001\u0000\u0000"+
		"\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000"+
		"\u0000\u00bf\u00c2\u0001\u0000\u0000\u0000\u00c0\u00be\u0001\u0000\u0000"+
		"\u0000\u00c1\u00b9\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000"+
		"\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u00c4\u0005\n\u0000\u0000"+
		"\u00c4\u0015\u0001\u0000\u0000\u0000\u00c5\u00c6\u0005\u0017\u0000\u0000"+
		"\u00c6\u0017\u0001\u0000\u0000\u0000\u00c7\u00c8\u0003\u001e\u000f\u0000"+
		"\u00c8\u00c9\u0003\u001c\u000e\u0000\u00c9\u00cc\u0001\u0000\u0000\u0000"+
		"\u00ca\u00cb\u0005\u001d\u0000\u0000\u00cb\u00cd\u0003\u0018\f\u0000\u00cc"+
		"\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000\u00cd"+
		"\u0019\u0001\u0000\u0000\u0000\u00ce\u00cf\u0005 \u0000\u0000\u00cf\u00d0"+
		"\u0003\u001e\u000f\u0000\u00d0\u00d1\u0003\u001c\u000e\u0000\u00d1\u00db"+
		"\u0001\u0000\u0000\u0000\u00d2\u00d3\u0005\u001e\u0000\u0000\u00d3\u00d4"+
		"\u0003\u001e\u000f\u0000\u00d4\u00d5\u0003\u001c\u000e\u0000\u00d5\u00db"+
		"\u0001\u0000\u0000\u0000\u00d6\u00d7\u0005\u001f\u0000\u0000\u00d7\u00d8"+
		"\u0003\u001e\u000f\u0000\u00d8\u00d9\u0003\u001c\u000e\u0000\u00d9\u00db"+
		"\u0001\u0000\u0000\u0000\u00da\u00ce\u0001\u0000\u0000\u0000\u00da\u00d2"+
		"\u0001\u0000\u0000\u0000\u00da\u00d6\u0001\u0000\u0000\u0000\u00db\u001b"+
		"\u0001\u0000\u0000\u0000\u00dc\u00eb\u0003\u001a\r\u0000\u00dd\u00df\u0003"+
		"\f\u0006\u0000\u00de\u00e0\u0003\u001a\r\u0000\u00df\u00de\u0001\u0000"+
		"\u0000\u0000\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0\u00eb\u0001\u0000"+
		"\u0000\u0000\u00e1\u00e3\u0003\u0016\u000b\u0000\u00e2\u00e4\u0003\u001a"+
		"\r\u0000\u00e3\u00e2\u0001\u0000\u0000\u0000\u00e3\u00e4\u0001\u0000\u0000"+
		"\u0000\u00e4\u00eb\u0001\u0000\u0000\u0000\u00e5\u00e6\u0005\u0018\u0000"+
		"\u0000\u00e6\u00e7\u0003\u0014\n\u0000\u00e7\u00e8\u0003\u001c\u000e\u0000"+
		"\u00e8\u00eb\u0001\u0000\u0000\u0000\u00e9\u00eb\u0001\u0000\u0000\u0000"+
		"\u00ea\u00dc\u0001\u0000\u0000\u0000\u00ea\u00dd\u0001\u0000\u0000\u0000"+
		"\u00ea\u00e1\u0001\u0000\u0000\u0000\u00ea\u00e5\u0001\u0000\u0000\u0000"+
		"\u00ea\u00e9\u0001\u0000\u0000\u0000\u00eb\u001d\u0001\u0000\u0000\u0000"+
		"\u00ec\u0104\u0005\u0019\u0000\u0000\u00ed\u0104\u0003 \u0010\u0000\u00ee"+
		"\u0104\u0005%\u0000\u0000\u00ef\u0104\u0005)\u0000\u0000\u00f0\u0104\u0005"+
		"#\u0000\u0000\u00f1\u00f2\u0005\u001a\u0000\u0000\u00f2\u00f3\u0005\u0010"+
		"\u0000\u0000\u00f3\u00f4\u0005\b\u0000\u0000\u00f4\u00f5\u0003\u0010\b"+
		"\u0000\u00f5\u00f6\u0005\t\u0000\u0000\u00f6\u0104\u0001\u0000\u0000\u0000"+
		"\u00f7\u00f8\u0005\u001a\u0000\u0000\u00f8\u00f9\u0005#\u0000\u0000\u00f9"+
		"\u00fa\u0005\u0006\u0000\u0000\u00fa\u0104\u0005\n\u0000\u0000\u00fb\u00fc"+
		"\u0005$\u0000\u0000\u00fc\u00fd\u0003\u001e\u000f\u0000\u00fd\u00fe\u0003"+
		"\u001c\u000e\u0000\u00fe\u0104\u0001\u0000\u0000\u0000\u00ff\u0100\u0005"+
		"\u0006\u0000\u0000\u0100\u0101\u0003\u0010\b\u0000\u0101\u0102\u0005\n"+
		"\u0000\u0000\u0102\u0104\u0001\u0000\u0000\u0000\u0103\u00ec\u0001\u0000"+
		"\u0000\u0000\u0103\u00ed\u0001\u0000\u0000\u0000\u0103\u00ee\u0001\u0000"+
		"\u0000\u0000\u0103\u00ef\u0001\u0000\u0000\u0000\u0103\u00f0\u0001\u0000"+
		"\u0000\u0000\u0103\u00f1\u0001\u0000\u0000\u0000\u0103\u00f7\u0001\u0000"+
		"\u0000\u0000\u0103\u00fb\u0001\u0000\u0000\u0000\u0103\u00ff\u0001\u0000"+
		"\u0000\u0000\u0104\u001f\u0001\u0000\u0000\u0000\u0105\u0106\u0007\u0000"+
		"\u0000\u0000\u0106!\u0001\u0000\u0000\u0000\u0014&AGMbelr\u0081\u008b"+
		"\u00ae\u00b2\u00be\u00c1\u00cc\u00da\u00df\u00e3\u00ea\u0103";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}