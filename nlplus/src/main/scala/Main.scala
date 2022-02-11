import my_nlp.MyNLP

object Main {

   val data_path: String = sys.env("MY_DATA_PATH")

   def main(args: Array[String]): Unit = {
      val it_sentences = Common.common
      var en_sentences = Map[String, Seq[String]]()
      var sentiment = Map[String, Seq[String]]()
      val file = s"$data_path/it_en_sentiment.txt"

      MyUtility.clean_file(file)

      for(s <- it_sentences) {
         if(s.nonEmpty) {
            MyUtility.write_file(s+"\n", file, mode = true)
            en_sentences = MyNLP.it_to_en(s)
            MyUtility.write_file(
               en_sentences("translation").head+"\n", file, mode = true)
            sentiment = MyNLP.sentiment(en_sentences("translation").head)
            MyUtility.write_file(
               sentiment("sentiment").head+"\n", file, mode = true)
         }
      }
   }
}
