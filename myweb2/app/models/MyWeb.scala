package my_web

import scala.io.Source
import java.io._

import my_utility.MyUtility

object MyIndex {

   val title = "MyWeb2"
   val link1 = """<a href="/twitters">Twitter Trends</a>
                  <br>
                  <a href="/trends">Google Trends</a>
                  <br>
                  <a href="/awards">Hi-Res Awards</a>"""
   val link2 = """<a href="/beesForm">Bees</a>
                  <br>
                  <a href="/script1">Run Bumble</a>
                  <br>
                  <a href="/script2">Run gTrends</a>
                  <br>
                  <a href="/script3">Run Hi-Res</a>
                  <br>                  
                  <a href="/reset">Reset</a>"""   
}

object MyAwards {

   val data_path = sys.env.get("MY_DATA_PATH").get

   val title = "MyWeb2"
   
   def read() = {   
      MyUtility.read(s"$data_path/data_awards3.txt")
   }
}

object MyTrends {

   val data_path = sys.env.get("MY_DATA_PATH").get

   val title = "MyWeb2"
   
   val title1 = """-------------------------------
                Trends Italia"""
   val title2 = """-------------------------------
                Trends Francia"""
   
   def read1() = {                
      MyUtility.read(s"$data_path/searches_italy.txt")
   }

   def read2() = {                
      MyUtility.read(s"$data_path/searches_france.txt")
   }   
}

object MyTwitters {

   val data_path = sys.env.get("MY_DATA_PATH").get

   val title = "MyWeb2"

   val title1 = """-------------------------------
                Vettori"""
   val title2 = """-------------------------------
                Conteggi"""

   def read1() = {                
      MyUtility.read(s"$data_path/vector.csv")
   }

   def read2() = {                
      MyUtility.read(s"$data_path/spark.csv")
   }
}
