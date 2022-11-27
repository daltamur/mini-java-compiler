import AST_Grammar.{ASTNode, symbolTable, symbolTableBuilder, typeCheckingVisitor}
import codeGen.codeGenerator
import grammarOutput.{miniJavaLexer, miniJavaParser}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream, ParserRuleContext}
import org.antlr.v4.runtime.tree.{ErrorNode, ParseTree, ParseTreeListener, ParseTreeWalker, TerminalNode}
import parseTreeFiles.{MiniJavaVisitor, errorListener}

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
          generateCode(programAST,fileLocation)
        }else{
          println("ERROR: No file found at " + fileLocation)
        }
      }else{
        println("ERROR: Please give a filename with a '.java' extension.")
      }
    } else if(args.length == 0){
      var Logo = "....................................................................................................\n" +
        "..............................::....................................................................\n" +
        "..............................~^....................................................................\n" +
        "............................:~!:.............  |  \\/  (_).....(_)....................................\n" +
        "..........................:~!!:.............   | \\  / |_ _ __  _ ....................................\n" +
        "........................^~!~:.:::...........   | |\\/| | | '_ \\| |...................................\n" +
        "......................^!7~:.^~:.............   | |  | | | | | | |.....................................\n" +
        ".....................~7~:.:!7:..............   |_|  |_|_|_| |_|_|.....................................\n" +
        ".....................!7:..:77^......................................................................\n" +
        ".....................:!~...^77^.................^~^.................................................\n" +
        "......................:^^:..:7!................:5PJ.................................................\n" +
        "................:~~::.......:^.......:~?~......:5PJ...::^~~~^:..:^^:.....^^:.::^~~~^:...............\n" +
        "................~?J77!!!~~~~!!!!!!~:...J5:.....:5PJ..:?????J5Y!.:JPJ....~PP~.7J???J557..............\n" +
        "..................^!~:^^^^^^^^:::....:!J~......:5PJ.........?P5:.^PP7...JPJ........!PP^.............\n" +
        ".................:!J?777777777777:.:^~^........:5PJ...^!????YP5:..7P5^.^PP^..:!????JPP^.............\n" +
        "....................^~:::^^::::................:5PJ..7P5    ?P5:..:YPJ.JP7..~5P!    PP^.............\n" +
        ".............^~~:..:?YJ????????^......:........:5PJ..JPY    YP5:...~PPYPJ...7P5~    PP^.............\n" +
        "............~YPJ~^::::::^^^^::::::^^~7^........:5P?..:7JYJ?7~??:....7JJ?:...:7JYJ?7~?J^.............\n" +
        ".............:^!!777777!!!!!!!!!!!!!!~~^.......!PY^.................................................\n" +
        "...................:^^^^^~~^~~~~~~^^^::.......:7?:..................................................\n" +
        "....................................................................................................\n"
      Logo = Logo.replace(".", " ")
      println(Logo)
      println("\n")
      println("Welcome to this Mini Java Compiler!")
      println("____________________________________")
      println("This Compiler uses: ")
      println("-ANTLR for Parsing. Learn about ANTLR at: https://www.antlr.org/")
      println("-Java ASM for code generation. Learn more about ASM at: https://asm.ow2.io/")
      println("I used this handy Intellij Plugin to analyze JVM bytecode when writing this compiler: https://plugins.jetbrains.com/plugin/16970-byte-code-analyzer")
      println("____________________________________")
      println("Learn about how to write in Mini Java at: https://www.cambridge.org/resources/052182060X/")
      println("This version of Mini Java also supports characters and will print them out")
      println("View the source code for this compiler at: https://github.com/daltamur/mini-java-compiler")
      println("To compile a Java file that follows the rules of MiniJava, run this program with the file location as a command line argument")
    }else{
      println("ERROR: Please give ONE filename argument to the compiler. Run with no arguments to see the INFO screen")
    }

  def parse(fileLocation: String): miniJavaParser = {
    println("Current File: "+fileLocation)
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

  def generateCode(GoalNode: (Option[ASTNode], Boolean), sourcePath: String): Unit ={
    //if we get here, we can assume that there does indeed exist a goal
    //AST node, no need to do pattern matching.
    val codeGenerator = new codeGenerator
    codeGenerator.visitGoal(GoalNode._1.get.asInstanceOf[AST_Grammar.goal], sourcePath)
  }
}