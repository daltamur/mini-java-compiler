grammar miniJava;

goal:   mainClass (classDeclaration)* EOF;

mainClass:  'class' IDENTIFIER '{' 'public' 'static' 'void' IDENTIFIER '(' 'String' '[' ']' IDENTIFIER ')' '{' statement '}' '}';

classDeclaration:   'class' IDENTIFIER ('extends' IDENTIFIER)? '{' (varDeclaration)* (methodDeclaration)* '}';

varDeclaration: type IDENTIFIER ';';

methodDeclaration:  'public' type IDENTIFIER '(' (type IDENTIFIER (',' type IDENTIFIER)*)? ')' '{' (varDeclaration)* (statement)* 'return' expression ';' '}';

type:   'int' '[' ']'                                       #integerArrayType
        | 'boolean'                                         #booleanType
        | 'int'                                             #intType
        | 'char'                                            #charType
        | IDENTIFIER                                        #identifierType
        ;

arrayIndex: '[' expression ']';

statement:  '{' statement* '}'                                      #blockStatement
            |'if' '(' expression ')' statement 'else'  statement    #ifStatement
            | 'while' '(' expression ')' statement                  #whileStatement
            | 'System.out.println' '(' expression ')' ';'           #printStatement
            | IDENTIFIER EQUALS  expression  ';'                    #assignStatement
            | IDENTIFIER arrayIndex EQUALS expression ';'           #arrayAssignStatement
            ;

expression: expressionTerminal expressionTail; //expression node rewritten to get rid of left recursion

methodFuncCall: IDENTIFIER '(' (expression (',' expression )*)? ')';

arrayLengthCall: 'length';

expressionTailOps:ADD expression           #addExpression
                  | SUBTRACT expression       #subtractExpression
                  | MULTIPLY expression       #multiplyExpression
                  ;

expressionTail: expressionTailOps                               #operatorExpression
                | arrayIndex (expressionTailOps)?               #arrayIndexCall
                | '.' arrayLengthCall (expressionTailOps)?      #getArrayLength
                | '.' methodFuncCall (expressionTailOps)?       #functionVallExpression
                | AND expression                                #andExpression
                | COMPARE expression                            #compareExpression
                |                                               #noExpressionTail
                ;

expressionTerminal: 'this'                                              #thisKeyword
                | boolVal                                               #booleanExpression
                | INTEGER_LITERAL                                       #integerExpression
                | CHARACTER_LITERAL                                     #characterExpression
                | IDENTIFIER                                            #identifierExpression
                | 'new' 'int' '[' expression ']'                        #newArrayExpression
                | 'new' IDENTIFIER '(' ')'                              #newClassExpression
                | NOT expression                                        #negatedExpression
                | '(' expression ')'                                    #parenthesizedExpression
                ;

boolVal:   TRUE
           |FALSE
           ;

//lex rules
AND:	'&&';
EQUALS:     '=';
COMPARE:	'<';
ADD:    '+';
SUBTRACT:   '-';
MULTIPLY:   '*';
TRUE:   'true';
FALSE:  'false';
IDENTIFIER:    [a-zA-Z]+[a-zA-Z0-9_]*
               ; //any string that starts with a letter followed by any collection of letters, numbers, and underscores
NOT:    '!'; //not symbol
INTEGER_LITERAL:    [0-9]+; //any number of integers
WS: [ \t\r\n]+ -> skip; //whitespace regex
COMMENT:    '//' .+? ('\n'|EOF) -> skip; //comment regex
ESCAPED_CHARACTER: '\\\''
                   |'\\b'
                   |'\\n'
                   |'\\r'
                   |'\\f'
                   |'\\"'
                   |'\\t'
                   |'\\\\'
                   ;
CHARACTER_LITERAL:  '\'' (~['\\\r\n]|ESCAPED_CHARACTER) '\''; //quotes character that can be anything other than the return or newline character. If desired, I can incorporate escaped characters into this