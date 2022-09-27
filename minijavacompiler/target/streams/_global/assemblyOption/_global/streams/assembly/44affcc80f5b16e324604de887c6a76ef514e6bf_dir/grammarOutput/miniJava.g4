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

statement:  '{' statement* '}'
            |'if' '(' expression ')' statement 'else'  statement //if-else statement statement
            | 'while' '(' expression ')' statement //while loop
            | 'System.out.println' '(' expression ')' ';' //print statement
            | IDENTIFIER '='  expression  ';' //assign value to variable
            | IDENTIFIER '[' expression ']' '=' expression ';' //assign value to array, since these can only ever hold ints, no need to add the quotes check
            ;

expression: expressionTerminal expressionTail; //expression node rewritten to get rid of left recursion

methodFuncCall: IDENTIFIER '(' (expression (',' expression )*)? ')';

arrayLengthCall: 'length';

arrayIndex: '[' expression ']';


expressionTail: andExpression
                | shiftExpression
                | addExpression
                | subtractExpression
                | multiplyExpression
                | '[' expression ']' //getting a value from an array's index
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

classInstantiation:   'new' IDENTIFIER '(' ')';

negatedExpression:  NOT expression;

parenthesizedExpression:    '(' expression ')';

//lex rules
AND:	'&&';
SHIFT:	'<';
ADD:    '+';
SUBTRACT:   '-';
MULTIPLY:   '*';
OR:	'|';
TRUE:   'true';
FALSE:  'false';
IDENTIFIER: [a-zA-Z]+[a-zA-Z0-9_]*;
NOT:    '!';
APOSTROPHE: '\'';
INTEGER_LITERAL:    [0-9]+;
WS: [ \t\r\n]+ -> skip; //whitespace regex
COMMENT:    '//' .+? ('\n'|EOF) -> skip; //comment regex
CHARACTER_LITERAL:  '\'' ~['\\\r\n] '\'';