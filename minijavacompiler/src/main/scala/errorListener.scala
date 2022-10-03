import org.antlr.v4.runtime.misc.ParseCancellationException
import org.antlr.v4.runtime.{BaseErrorListener, FailedPredicateException, InputMismatchException, NoViableAltException, Parser, RecognitionException, Recognizer, Token, TokenStream}

import scala.collection.mutable
class errorListener extends BaseErrorListener{
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
        println("ERROR: Expected {" + expectedTokens + " } on line " + line + ":" + charPositionInLine + ", Instead found \'" + exception.getStartToken.getText + e.getOffendingToken.getText + "\'")
      case _ =>
        println(msg)
    }

  }

  object errorListener{
    val instance = new errorListener()
  }


}
