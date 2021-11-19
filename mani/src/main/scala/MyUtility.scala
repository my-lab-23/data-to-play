package my_utility

import scala.io.Source

import java.io._

object MyUtility {

   def write_file(data: String, path: String, mode: Boolean): Unit =
      val pw = new PrintWriter(new FileOutputStream(new File(path), mode))
      pw.write(data)
      //pw.write("\n\n---\n\n")
      pw.close

   def read_file(path: String): List[String] =
      val source = Source.fromFile(path)
      var list = List[String]()
      for (line <- source.getLines())
         list = line.toString :: list
      return list

   def clean_file(path: String): Unit =
      var data: Any = ""
      MyUtility.write_file(s"$data", path, false)
}
