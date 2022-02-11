package my_nlp

import com.johnsnowlabs.nlp.pretrained.PretrainedPipeline

object MyNLP {

   def it_to_en(it: String): Map[String,Seq[String]] = {
      val pipeline = new PretrainedPipeline("translate_it_en", lang = "xx")
      pipeline.annotate(it)
   }

   def sentiment(en: String): Map[String,Seq[String]] = {
      val pipeline = new PretrainedPipeline(
         "analyze_sentimentdl_use_twitter", lang = "en")
      pipeline.annotate(en)
   }
}
