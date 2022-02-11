import org.scalatest.*
import flatspec.*
import matchers.*
import org.apache.commons.io.FileUtils
import java.io.File
import my_awards.MyAwards

class MyAwardsTest extends AnyFlatSpec with should.Matchers {

   private val data_path: String = "./test_data"

   it should "write ranked awards on file" in {
      run()
      val ranked = new File(s"$data_path/data_awards2.txt")
      val test = new File(s"$data_path/awards_test/data_awards2.txt")
      val isTwoEqual = FileUtils.contentEquals(ranked, test)
      isTwoEqual should be(true)
   }

   it should "write 'pretty for web' awards on file" in {
      run()
      val pretty = new File(s"$data_path/data_awards3.txt")
      val test = new File(s"$data_path/awards_test/data_awards3.txt")
      val isTwoEqual = FileUtils.contentEquals(pretty, test)
      isTwoEqual should be(true)
   }

   private def run(): Unit = {
      val composerAwards = MyAwards.read_awards(s"$data_path/data_awards.txt")
      val ranked = MyAwards.rank(composerAwards)
      MyAwards.write(s"$data_path/data_awards2.txt", ranked)
      val read_file = s"$data_path/data_awards2.txt"
      val write_file = s"$data_path/data_awards3.txt"
      MyAwards.write_pretty_for_web(read_file, write_file)
   }
}
