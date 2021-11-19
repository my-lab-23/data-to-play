import org.apache.spark.sql.SparkSession

import my_nlp.MyNLP
import my_utility.MyUtility

object NlplusS {
   def main(args: Array[String]): Unit = {

      //
      val data_path = sys.env.get("MY_DATA_PATH").get

      //
      val spark = SparkSession.builder().appName("MySpark").getOrCreate
      val sc = spark.sparkContext

      //
      val data = sc.textFile(s"$data_path/en_sentences.txt")
      val mapped = data.map(x=>x.toString)

      //
      import spark.implicits._
      val dataset = mapped.toDF("sentence")

      var ds = dataset.select("sentence")
      val list = ds.collect().map(_(0)).toList
      var sent = Map[String, Seq[String]]()

      //
      MyUtility.clean_file(s"$data_path/sentiment.txt")

      var positive: Int = 0
      var negative: Int = 0

      for(l <- list) {
         if(!l.toString.isEmpty) {
            MyUtility.write_file(l.toString+"\n", s"$data_path/sentiment.txt", true)
            sent = MyNLP.sentiment(l.toString)
            MyUtility.write_file(sent("sentiment")(0)+"\n", s"$data_path/sentiment.txt", true)
            if (sent("sentiment")(0) == "positive") { positive += 1 }
            if (sent("sentiment")(0) == "negative") { negative += 1 }
         }
      }

      println(positive)
      println(negative)
   }
}
