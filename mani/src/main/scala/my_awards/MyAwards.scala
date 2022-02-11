package my_awards

import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex
import java.lang.String

object MyAwards {

   def rank(composerAwards: List[String]): Map[String, Int] = {
      var awards = List[String]()
      for (cA <- composerAwards)
         awards = ("""\d+""".r findFirstIn cA).get :: awards

      var composerAwardsMapped = Map.empty[String, Int]
      var i = awards.size - 1
      for (cA <- composerAwards)
         composerAwardsMapped = composerAwardsMapped + (cA -> awards(i).toInt)
         i -= 1
      
      ListMap(composerAwardsMapped.toSeq.sortWith(_._2 > _._2): _*)
   }

   //

   def write(path: String, composerAwardsMapped: Map[String, Int]): Unit = {
      MyUtility.clean_file(path)
      for ((key, value) <- composerAwardsMapped)
         MyUtility.write_file(s"$key\n", s"$path", true)
   }

   def write_pretty_for_web(path_read: String, path_write: String): Unit = {
      MyUtility.clean_file(path_write)
      var composerAwardsRanked = MyUtility.read_file(path_read)
      composerAwardsRanked = order(composerAwardsRanked, path_write)
      for (line <- composerAwardsRanked)
         MyUtility.write_file(line, path_write, true)
   }

   private def order(data: List[String], path_write: String): List[String] = {
      val temp = ListBuffer[String]()
      for (d <- data.reverse)
         val l = d.split(" ").toList.swap(0, 4)
         /*
            Example => l(0) l(1)   l(2)  l(3)    l(4)
                       Bach Hi-Res Award Winners (228)
         */
         var s = s"${l.head} ${l(1)} ${l(2)} ${l(3)} - ${l(4)}\n"
            .replaceAll("[()]", "")
         if (l.head.length == 4) s = s"&nbsp;$s"
         temp.addOne(s)
      temp.toList
   }

   private implicit class ExtendedListString(l: List[String]) {
      def swap(i: Int, j: Int): List[String] =
         l.updated(i, l(j)).updated(j, l(i))
   }
      
   //

   def read_awards(path: String): List[String] =
      MyUtility.read_file(path)
}
