import org.apache.spark.sql.SparkSession

import my_spark.MySpark
import my_utility.MyUtility

object Bumble {
   def main(args: Array[String]): Unit = {

      //
      val data_path = sys.env.get("MY_DATA_PATH").get

      //
      MyUtility.clean_tweet(s"$data_path")

      //
      val spark = SparkSession.builder().appName("MySpark").getOrCreate
      val sc = spark.sparkContext

      //
      val data = sc.textFile(s"$data_path/clean_twitter.txt")
      val mapped = data.map(x=>x.toString)

      //
      import spark.implicits._
      val dataset = mapped.toDF("sentence").distinct()

      //
      var result = MySpark.my_tokenizer(dataset)
      result = MySpark.my_stop_words_remover(result)
      result = MySpark.my_tf_idf(result)

      //
      val rank = MySpark.rank(result)
      MySpark.write_single_csv_file(rank, s"$data_path/spark.csv")

      //
      val vect = MySpark.vect(result, spark)
      MySpark.write_single_csv_file(vect, s"$data_path/vector.csv")     
   }
}
