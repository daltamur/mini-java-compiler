import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree

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
          val parseTree = parser.goal()
          println(parseTree.toStringTree())
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
    parser
  }
}