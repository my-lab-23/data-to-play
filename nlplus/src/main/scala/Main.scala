import org.apache.spark.sql.SparkSession

import my_nlp.MyNLP
import my_utility.MyUtility

object Nlplus {
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
      var sent = Map[String, Seq[String]]()

      //
      MyUtility.clean_file(s"$data_path/it_sentiment.txt")

      for(l <- list) {
         if(!l.toString.isEmpty) {
            MyUtility.write_file(l.toString+"\n", s"$data_path/it_sentiment.txt", true)
            en = MyNLP.it_to_en(l.toString)  
            MyUtility.write_file(en("translation")(0)+"\n", s"$data_path/it_sentiment.txt", true)
            sent = MyNLP.sentiment(en("translation")(0))
            MyUtility.write_file(sent("sentiment")(0)+"\n", s"$data_path/it_sentiment.txt", true)
         }
      }
   }
}
