package controllers

import javax.inject._
import play.api._
import play.api.mvc._

import sys.process._
import scala.language.postfixOps
import scala.io.Source

import bees.Bees
import my_utility.MyUtility

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

   def index() = Action { implicit request: Request[AnyContent] =>
      Ok(views.html.index())
   }

   def bees(hashtag: String) = Action { implicit request: Request[AnyContent] =>
      val a = Array(s"#$hashtag")
      Bees.main(a)
      Redirect("/")
   }

   def text() = Action { implicit request: Request[AnyContent] =>
      val data_path = sys.env.get("MY_DATA_PATH").get
      var list = MyUtility.read_file(s"$data_path/twitter.txt")
      Ok(list.mkString("\n"))
   }
}
