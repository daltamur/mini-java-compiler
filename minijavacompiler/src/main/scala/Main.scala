import AST_Grammar.{ASTNode, symbolTable, symbolTableBuilder, typeCheckingVisitor}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream, ParserRuleContext}
import org.antlr.v4.runtime.tree.{ErrorNode, ParseTree, ParseTreeListener, ParseTreeWalker, TerminalNode}

import java.io.File
import java.nio.file.{Files, Paths}

object Main {
  def main(args: Array[String]): Unit =
    if(args.length == 1) {
      var fileLocation: String = null
      for (arg <- args) {
        fileLocation = arg
      }

      if(fileLocation.endsWith(".java")) {
        if(Files.exists(Paths.get(fileLocation))) {
          val parser = parse(fileLocation)
          //make the ast
          val programAST = buildAST(parser)
          if(programAST._2){
            System.exit(1)
          }

          //make the symbol table
          val symbolTable = new symbolTable("goal")
//          symbolTable.putClassVal("int", AST_Grammar.classVal(new symbolTable("int"), None, 0))
//          symbolTable.putClassVal("boolean", AST_Grammar.classVal(new symbolTable("boolean"), None, 0))
//          symbolTable.putClassVal("char", AST_Grammar.classVal(new symbolTable("char"), None, 0))
//          symbolTable.putClassVal("int[]", AST_Grammar.classVal(new symbolTable("int[]"), None, 0))
          val symbolTableBuilder = new symbolTableBuilder
          symbolTableBuilder.visit(programAST._1.get, symbolTable)

          //make sure there were no var dec or method errors
          if (symbolTableBuilder.getError.isDefined) {
            println(symbolTableBuilder.getError.get.errorVal)
            System.exit(1)
          }

          //make sure no undefined method parameters were made
          symbolTableBuilder.checkMethodParams(symbolTable)
          if (symbolTableBuilder.getError.isDefined) {
            println(symbolTableBuilder.getError.get.errorVal)
            System.exit(1)
          }

          //check for circular inheritance
          symbolTableBuilder.checkForCircularInheritance(symbolTable)
          if (symbolTableBuilder.getError.isDefined) {
            println(symbolTableBuilder.getError.get.errorVal)
            System.exit(1)
          }

          symbolTableBuilder.checkMethodReturnTypes(symbolTable)
          if(symbolTableBuilder.getError.isDefined){
            println(symbolTableBuilder.getError.get.errorVal)
            System.exit(1)
          }

          //type check statements & method return values now, this ends symbol resolution & type checking
          val statementTypeChecker = new typeCheckingVisitor
          statementTypeChecker.visit(programAST._1.get, symbolTable)
          if(statementTypeChecker.getCurError.isDefined){
            println(statementTypeChecker.getCurError.get.errorVal)
            System.exit(1)
          }

          //Code Generation

          //Code Optimization

        }else{
          println("ERROR: No file found at " + fileLocation)
        }
      }else{
        println("ERROR: Please give a filename with a '.java' extension.")
      }
    } else {
      println("ERROR: Please only give one filename argument to the compiler.")
    }

  def parse(fileLocation: String): miniJavaParser = {
    val charStream = CharStreams.fromFileName(fileLocation)
    val lexer = new miniJavaLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    val parser = new miniJavaParser(tokenStream)
    parser.removeErrorListeners()
    val parserErrorListener = new errorListener()
    parser.addErrorListener(parserErrorListener)
    //do a quick parse check for errors
    val _ = parser.goal()
    if(parserErrorListener.hasError){
      System.exit(1)
    }
    parser.reset()
    parser
  }

  def buildAST(parser: miniJavaParser): (Option[ASTNode], Boolean) = {
    val visitor = new MiniJavaVisitor()
    val program = visitor.visitGoal(parser.goal())
    (program, visitor.mainMethodError)
  }
}