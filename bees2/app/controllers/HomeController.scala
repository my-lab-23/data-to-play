package controllers

import scala.language.postfixOps
import play.api.mvc._
import javax.inject._
import bees.Bees

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents)
   extends BaseController {

   def index(): Action[AnyContent] = Action {
      implicit request: Request[AnyContent] => Ok(views.html.index())
   }

   def bees(hashtag: String): Action[AnyContent] = Action {
      implicit request: Request[AnyContent] =>
         val a = Array(s"#$hashtag")
         Bees.start_receive_tweets(a)
         Redirect("/")
   }

   def text(): Action[AnyContent] = Action {
      implicit request: Request[AnyContent] =>
         val data_path = sys.env("MY_DATA_PATH")
         val tweets = Bees.read_tweets(s"$data_path/twitter.txt")
         Ok(tweets.mkString("\n"))
   }
}
