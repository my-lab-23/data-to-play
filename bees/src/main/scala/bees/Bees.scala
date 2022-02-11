package bees

import com.danielasfregola.twitter4s.TwitterStreamingClient
import com.danielasfregola.twitter4s.entities.Tweet
import com.danielasfregola.twitter4s.entities.enums.Language

object Bees {

   private val data_path = sys.env("MY_DATA_PATH")

   //

   def start_receive_tweets(args: Array[String]): Unit = {

      //val tag = Seq("#RomaNapoli", "#JuventusInter")
      val tag = Seq(args:_*); println(tag)
      val streamingClient = TwitterStreamingClient()
      val trackedWords = tag

      MyUtility.clean_file(s"$data_path/twitter.txt")

      //streamingClient.sampleStatuses(
      streamingClient.filterStatuses(
         tracks = trackedWords, languages = Seq(Language.Italian)) {
         case tweet: Tweet => write_tweet(tweet); println("Bingo!")
      }
   }

   private def write_tweet(tweet: Tweet): Unit = {

      val file = s"$data_path/twitter.txt"

      if (tweet.extended_tweet.isDefined) {
         val text = tweet.extended_tweet.get.full_text
         MyUtility.write_file(text, file, mode = true)
      }
      else {
         val text = tweet.text
         MyUtility.write_file(text, file, mode = true)
      }
   }

   //

   def read_tweets(path: String): List[String] = {
      MyUtility.read_file(path)
   }
}
