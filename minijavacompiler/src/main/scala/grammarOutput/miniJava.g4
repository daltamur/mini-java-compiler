grammar miniJava;

goal:   mainClass (classDeclaration)* EOF;

mainClass:  'class' IDENTIFIER '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' IDENTIFIER ')' '{' statement '}' '}';

classDeclaration:   'class' IDENTIFIER ('extends' IDENTIFIER)? '{' (varDeclaration)* (methodDeclaration)* '}';

varDeclaration: type IDENTIFIER ';';

methodDeclaration:  'public' type IDENTIFIER '(' (type IDENTIFIER (',' type IDENTIFIER)*)? ')' '{' (varDeclaration)* (statement)* 'return' expression ';' '}';

type:   'int' '[' ']' //int array
        | 'boolean' //boolean type
        | 'int' //int type
        | 'char' //character
        | IDENTIFIER //object
        ;

arrayIndex: '[' expression ']';

statement:  '{' statement* '}'
            |'if' '(' expression ')' statement 'else'  statement //if-else statement statement
            | 'while' '(' expression ')' statement //while loop
            | 'System.out.println' '(' expression ')' ';' //print statement
            | IDENTIFIER EQUALS  expression  ';' //assign value to variable
            | IDENTIFIER arrayIndex EQUALS expression ';' //assign value to array
            ;

expression: expressionTerminal expressionTail; //expression node rewritten to get rid of left recursion

methodFuncCall: IDENTIFIER '(' (expression (',' expression )*)? ')';

arrayLengthCall: 'length';

expressionTail: andExpression
                | shiftExpression //bitshift
                | addExpression //add one expression to another
                | subtractExpression //subtract one expression from another
                | multiplyExpression //multiply one expression by another
                | arrayIndex //getting a value from an array's index
                | '.' arrayLengthCall //calling .length for an array
                | '.' methodFuncCall
                | //null
                ;

andExpression: AND expression;
shiftExpression: SHIFT expression;
addExpression: ADD expression;
subtractExpression: SUBTRACT expression;
multiplyExpression: MULTIPLY expression;

expressionTerminal: 'this'
                | boolVal
                | integer //for making an int
                | character //for making a char
                | identifier //some variable/method/function name
                | arrayInstantiation  //making a new int array (I think that's the only array type that minijava can do)
                | classInstantiation //making a new class
                | negatedExpression //negated boolean expression
                | parenthesizedExpression //parenthesized expression
                ;

boolVal:   TRUE
           |FALSE
           ;

integer:   INTEGER_LITERAL;

character:  CHARACTER_LITERAL;

identifier: IDENTIFIER;

arrayInstantiation:   'new' 'int' '[' expression ']';

classInstantiation:   'new' IDENTIFIER '(' ')'; //making a new class

negatedExpression:  NOT expression;

parenthesizedExpression:    '(' expression ')';

//lex rules
AND:	'&&';
EQUALS:     '=';
SHIFT:	'<';
ADD:    '+';
SUBTRACT:   '-';
MULTIPLY:   '*';
TRUE:   'true';
FALSE:  'false';
IDENTIFIER: [a-zA-Z]+[a-zA-Z0-9_]*; //any string that starts with a letter followed by any collection of letters, numbers, and underscores
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