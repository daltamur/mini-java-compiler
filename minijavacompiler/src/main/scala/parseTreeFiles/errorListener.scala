package parseTreeFiles

import org.antlr.v4.runtime.misc.ParseCancellationException
import org.antlr.v4.runtime.*

import scala.collection.mutable
class errorListener extends BaseErrorListener{
  var hasError = false
  override def syntaxError(recognizer: Recognizer[_, _], offendingSymbol: Any, line: Int, charPositionInLine: Int, msg: String, e: RecognitionException):
    Unit = {
    e match {
      case exception: NoViableAltException =>
        val hashMap = new mutable.HashMap[Int, String]()
        recognizer.getTokenTypeMap.keySet().forEach(curKey => hashMap.put(recognizer.getTokenTypeMap.get(curKey).hashCode(), curKey))
        var expectedTokens = ""
        for (curTokenIndex <- 0 to e.getExpectedTokens.size()) {
          expectedTokens += " " + hashMap(e.getExpectedTokens.get(curTokenIndex).hashCode())
        }
        println(Console.RED+"ERROR: Expected {" + Console.GREEN + expectedTokens + Console.RED + " } on line " + Console.BLUE + "\u001b[4m" +  line + ":" + charPositionInLine + "\u001b[0m" +Console.RED + ", Instead found" + Console.GREEN + "\'" + exception.getStartToken.getText + e.getOffendingToken.getText + "\'")

      case exception =>
        println(Console.RED+"ERROR: "+Console.GREEN+msg+" on line "+Console.BLUE + "\u001b[4m" +  line + ":" + charPositionInLine + "\u001b[0m")
    }
    hasError = true
  }
}
