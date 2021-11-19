package my_utility

import scala.io.Source
import scala.util.{Try,Success,Failure}
import scala.io.Codec
import java.nio.charset.CodingErrorAction

object MyUtility {

   private def read_text_file(filename: String): Try[List[String]] = {
      val decoder = Codec.UTF8.decoder.onMalformedInput(CodingErrorAction.IGNORE)
      Try(Source.fromFile(filename)(decoder).getLines.toList)
   }

   private def read_file(path: String): List[String] = {
      val filename = path
      var list = List[String]()
      read_text_file(filename) match {
         case Success(lines) => {
            for (line <- lines.reverse)
               list = s"<p>$line</p>" :: list
            return list
         }
         case Failure(f) => return List("<p>File non trovato.</p>")
      }
   }

   def read(path: String): String = {
      val list = this.read_file(path)
      list.mkString
   }
}
