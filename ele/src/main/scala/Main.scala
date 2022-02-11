import scala.io.Source
import java.io._
import my_postgres.MyPostgres

object Main {
   def main(args: Array[String]): Unit = {

      var mode = ""

      try { mode = args(0) }
      catch {
         case _: ArrayIndexOutOfBoundsException => mode = ""
      }

      val data_path = sys.env("MY_DATA_PATH")
   
      //

      mode match {

         case "--insert" =>

            val hashtag = args(1)
            val source = Source.fromFile(s"$data_path/vector.csv")
            var data = Array[String]()
            for (line <- source.getLines()) {
               data = line.split("---")
               MyPostgres.insert(hashtag, data(0), data(3).toDouble)
            }

         case "--select" =>

            val hashtag = args(1)
            val r2 = MyPostgres.select_body(hashtag)
            val pw = new PrintWriter(new FileOutputStream(
               new File(s"$data_path/$hashtag.txt"), false))
            for(tweet <- r2) { pw.write(s"$tweet\n") }
            pw.close()

         case "--hashtag" =>

            val r3 = MyPostgres.distinct_hashtag()
            for(hashtag <- r3) { println(hashtag) }

         case _ => println("""--insert #hashtag
                             |--select #hashtag
                             |--hashtag""".stripMargin)                  
      }
   }
}
