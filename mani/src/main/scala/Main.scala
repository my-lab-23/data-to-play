import my_utility.MyUtility
import my_awards.MyAwards

@main def mani: Unit = 

   val data_path = sys.env.get("MY_DATA_PATH").get

   val data = MyUtility.read_file(s"$data_path/data_awards.txt")
   val map = MyAwards.rank(data)
   MyAwards.print(s"$data_path/data_awards2.txt", map)
   MyAwards.web_pretty_print(s"$data_path/data_awards2.txt", s"$data_path/data_awards3.txt")
