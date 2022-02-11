import org.apache.commons.io.FileUtils
import java.io.File
import my_awards.MyAwards

@main def mani(): Unit = 
   val data_path = sys.env("MY_DATA_PATH")
   val composerAwards = MyAwards.read_awards(s"$data_path/data_awards.txt")
   val ranked = MyAwards.rank(composerAwards)
   MyAwards.write(s"$data_path/data_awards2.txt", ranked)
   val read_file = s"$data_path/data_awards2.txt"
   val write_file = s"$data_path/data_awards3.txt"
   MyAwards.write_pretty_for_web(read_file, write_file)
