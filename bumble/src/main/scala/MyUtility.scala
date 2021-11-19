package my_utility

import util.Try
import scala.io.Codec
import scala.io.Source

import java.io._
import java.nio.charset.CodingErrorAction

object MyUtility {

   def write_file(data: String, path: String, mode: Boolean): Unit = {
      val pw = new PrintWriter(new FileOutputStream(new File(path), mode))
      pw.print(data)
      pw.close
   }

   def read_file(path: String): List[String] = {
     val decoder = Codec.UTF8.decoder.onMalformedInput(CodingErrorAction.IGNORE)
     val source = Source.fromFile(path)(decoder)
      var list = List[String]()
      for (line <- source.getLines())
         list = line.toString :: list
      return list
   }


   def clean_file(path: String): Unit = {
      var data: Any = ""
      MyUtility.write_file(s"$data", path, false)
   }

   def mv(oldName: String, newName: String): Unit = 
      Try(new File(oldName).renameTo(new File(newName))).getOrElse(false)

   //

   def clean_tweet(path: String): Unit = {
      val list = this.read_file(s"$path/twitter.txt")
      var line = ""
      var all_line = ""

      for(l <- list) {

         if(l == "-?-?-") { all_line = all_line + "\n" }

         if(!(l.isEmpty || l == "-?-?-" || l == " " || l == "")) {
            line = l.replaceAll("\n","").replaceAll("\r","")
            all_line = all_line + " " + line
         }
      }

      this.clean_file(s"$path/clean_twitter.txt")
      all_line = this.clean_text(all_line)
      this.write_file(s"$all_line", s"$path/clean_twitter.txt", true)   
   }

   private def clean_text(text: String): String = {
      text.toLowerCase()
         .replaceAll("&#13;"," ")
         .replaceAll("\\.", "\\. ")
         .replaceAll("nbsp", " ")         
         .replaceAll("  "," ")
   }
}
