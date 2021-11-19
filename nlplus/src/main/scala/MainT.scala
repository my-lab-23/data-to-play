import org.apache.spark.sql.SparkSession

import my_nlp.MyNLP
import my_utility.MyUtility

object NlplusT {
   def main(args: Array[String]): Unit = {

      //
      val data_path = sys.env.get("MY_DATA_PATH").get

      //
      val spark = SparkSession.builder().appName("MySpark").getOrCreate
      val sc = spark.sparkContext

      //
      val data = sc.textFile(s"$data_path/it_sentences.txt")
      val mapped = data.map(x=>x.toString)

      //
      import spark.implicits._
      val dataset = mapped.toDF("sentence")

      //
      var ds = dataset.select("sentence")
      val list = ds.collect().map(_(0)).toList
      var en = Map[String, Seq[String]]()
      
      //
      MyUtility.clean_file(s"$data_path/en_sentences.txt")

      var x = 0
      val y = list.size

      for(l <- list) {
         if(!l.toString.isEmpty) {
            en = MyNLP.it_to_en(l.toString) 

            var translation = ""

            for(i <- en("translation")) {
               translation = translation + " " + i
            }

            MyUtility.write_file(translation+"\n", s"$data_path/en_sentences.txt", true)

            x += 1
            print(s"$x su $y\r")
         }
      }
   }
}
