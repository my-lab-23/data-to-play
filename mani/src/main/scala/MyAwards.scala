package my_awards

import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer
import java.lang.String

import my_utility.MyUtility

object MyAwards {

   def rank(data: List[String]): Map[String, Int] =
      var list = List[String]()
      var map = Map.empty[String, Int]
      for(line <- data)
         list = ("""\d+""".r findFirstIn line).get :: list
      var i = list.size - 1
      for(e <- data)
         map = map + (e -> list(i).toInt)
         i -= 1   
      return ListMap(map.toSeq.sortWith(_._2 > _._2):_*)

   //

   def print(path: String, map: Map[String, Int]): Unit =
      MyUtility.clean_file(path)
      for((key, value) <- map)
         MyUtility.write_file(s"$key\n", s"$path", true)

   def web_pretty_print(path: String, path_write: String): Unit =
      MyUtility.clean_file(path_write)
      var list = MyUtility.read_file(path)
      list = this.order(list, path_write)
      for(line <- list)
         MyUtility.write_file(line, path_write, true)

   private def order(data: List[String], path_write: String): List[String] =
      var array = Array[String]()
      var v0, v1, v2, v3, v4 = ""
      var list = ListBuffer[String]()
      
      for(d <- data.reverse)
         var array = d.split(" ")
         var v0 = array(0)
         var v1 = array(1)
         var v2 = array(2)
         var v3 = array(3)
         var v4 = array(4).replace("(", "").replace(")", "")
         if v4.size == 2 then list += s"&nbsp;$v4 $v1 $v2 $v3 - $v0\n"
         if v4.size == 3 then list += s"$v4 $v1 $v2 $v3 - $v0\n"
      return list.toList   
}
