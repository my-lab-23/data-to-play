import scala.io.Source
import java.io.{File, FileOutputStream, PrintWriter}

protected object MyUtility {

   def write_file(data: String, path: String, mode: Boolean): Unit = {
      val pw = new PrintWriter(new FileOutputStream(new File(path), mode))
      pw.write(data)
      pw.close()
   }

   def read_file(path: String): List[String] = {
      val source = Source.fromFile(path)
      var list = List[String]()
      for (line <- source.getLines())
         list = line :: list
      list
   }

   def clean_file(path: String): Unit = {
      val data = ""
      MyUtility.write_file(s"$data", path, mode = false)
   }
}
