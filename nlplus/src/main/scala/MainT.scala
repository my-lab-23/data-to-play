import my_nlp.MyNLP

object MainT {

   val data_path: String = sys.env("MY_DATA_PATH")

   def main(args: Array[String]): Unit = {
      val it_sentences = Common.common
      var en_sentences = Map[String, Seq[String]]()
      val file = s"$data_path/en_sentences.txt"

      MyUtility.clean_file(file)

      var count = 0
      val total = it_sentences.size

      for(s <- it_sentences) {
         if(s.nonEmpty) {
            en_sentences = MyNLP.it_to_en(s)
            MyUtility.write_file(
               en_sentences("translation").head+"\n", file, mode = true)
            count += 1
            print(s"$count su $total\r")
         }
      }
   }
}
