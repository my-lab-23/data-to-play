package bees

import com.danielasfregola.twitter4s.TwitterStreamingClient
import com.danielasfregola.twitter4s.entities.Tweet
import com.danielasfregola.twitter4s.entities.enums.Language

import my_utility.MyUtility

object Bees {
   def main(args: Array[String]): Unit = {

      val data_path = sys.env.get("MY_DATA_PATH").get

      //val tag = Seq("#RomaNapoli", "#JuventusInter")
      val tag = Seq(args:_*) 
      println(tag)

      MyUtility.clean_file(s"$data_path/twitter.txt")
      
      val streamingClient = TwitterStreamingClient()

      val trackedWords = tag

      //streamingClient.sampleStatuses(tracks = trackedWords, languages = Seq(Language.Italian)) {
      streamingClient.filterStatuses(tracks = trackedWords, languages = Seq(Language.Italian)) {
         case tweet: Tweet => {
            println("Bingo!")
    
            if(tweet.extended_tweet.isEmpty == false) {
               MyUtility.write_file(tweet.extended_tweet.get.full_text, s"$data_path/twitter.txt", true)
            } 
            else {
               MyUtility.write_file(tweet.text, s"$data_path/twitter.txt", true)   
            }
         }
      }
   }
}
