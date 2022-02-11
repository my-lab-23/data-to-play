package my_spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.Row
import org.apache.spark.sql.functions.{explode, col, count, desc}
import org.apache.spark.ml.feature.Tokenizer
import org.apache.spark.ml.feature.StopWordsRemover
import org.apache.spark.ml.feature.{HashingTF, IDF}
import org.apache.spark.ml.linalg.SparseVector

object MySpark {

   def my_tokenizer(dataset: Dataset[Row]): Dataset[Row] = {
      val tokenizer = new Tokenizer()
         .setInputCol("sentence")
         .setOutputCol("words")
      val tokenized = tokenizer.transform(dataset)
      tokenized
   }

   def my_stop_words_remover(dataset: Dataset[Row]): Dataset[Row] = {   
      val loadStopWords = StopWordsRemover
         .loadDefaultStopWords("italian") ++ Array("", "rt", "-?-?-")
      val remover = new StopWordsRemover()
         .setInputCol("words")
         .setOutputCol("f_words")
         .setStopWords(loadStopWords)
      val removed = remover.transform(dataset)
      removed
   }

   def my_tf_idf(dataset: Dataset[Row]): Dataset[Row] = {
      val hashingTF = new HashingTF()
         .setInputCol("f_words")
         .setOutputCol("rawFeatures")
         .setNumFeatures(20)
      val featuredData = hashingTF.transform(dataset)
      val idf = new IDF().setInputCol("rawFeatures").setOutputCol("features")
      val idfModel = idf.fit(featuredData)
      val rescaledData = idfModel.transform(featuredData)
      rescaledData
   }

   def rank(dataset: Dataset[Row]): Dataset[Row] = {
      val ranked = dataset
         .withColumn("f_words", explode(col("f_words")))
         .groupBy("f_words")
         .agg(count("*"))
         .sort(desc("count(1)"))
      ranked
   }

   def vect(dataset: Dataset[Row], spark: SparkSession): Dataset[Row] = {
      import spark.implicits._

      var ds = dataset.select("sentence", "features")

      val features = ds.select("features").collect().map(_(0)).toList
      var features_avg = Array[Double]()

      for(f <- features) {
         val a = f.asInstanceOf[SparseVector].values
         if(!a.isEmpty) {
            features_avg = features_avg :+ (a.sum/a.length)
         }
      }

      ds = dataset
         .collect()
         .map(_.getAs[String]("sentence"))
         .zip(features_avg)
         .toList
         .toDF("sentence", "index")

      ds.orderBy(desc("index"))
   }   

   //

   def write_single_csv_file(dataset: Dataset[Row], path: String): Unit = {
      MyUtility.clean_file(path)
      for(d <- dataset.collect()) {
         val row = d(0).toString+"---"+d(1).toString+"\n"
         MyUtility.write_file(row, path, mode = true)
      }       
   }
}
