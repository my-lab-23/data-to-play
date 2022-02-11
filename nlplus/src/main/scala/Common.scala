import org.apache.spark.sql.SparkSession

object Common {

   val data_path: String = sys.env("MY_DATA_PATH")

   def common: List[String] = {
      val spark = SparkSession.builder().appName("MySpark").getOrCreate
      val sc = spark.sparkContext

      import spark.implicits._
      val data = sc.textFile(s"$data_path/it_sentences.txt")
      val dataset = data.toDF("sentence")

      val ds = dataset.select("sentence")
      ds.collect().map(_(0)).toList.asInstanceOf[List[String]]
   }
}
