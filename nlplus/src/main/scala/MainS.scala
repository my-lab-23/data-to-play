import my_nlp.MyNLP

object MainS {

   val data_path: String = sys.env("MY_DATA_PATH")

   def main(args: Array[String]): Unit = {
      val it_sentences = Common.common
      var sentiment = Map[String, Seq[String]]()
      val file = s"$data_path/it_sentiment.txt"

      MyUtility.clean_file(file)

      var positive: Int = 0
      var negative: Int = 0

      for(s <- it_sentences) {
         if(s.nonEmpty) {
            MyUtility.write_file(s+"\n", file, mode = true)
            sentiment = MyNLP.sentiment(s)
            MyUtility.write_file(
               sentiment("sentiment").head+"\n", file, mode = true)
            if (sentiment("sentiment").head == "positive") { positive += 1 }
            if (sentiment("sentiment").head == "negative") { negative += 1 }
         }
      }

      println(positive)
      println(negative)
   }
}
