package controllers

import sys.process._
import scala.language.postfixOps

import javax.inject._
import play.api._
import play.api.mvc._

import my_scripts.MyScripts

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

   def awards() = Action { implicit request: Request[AnyContent] =>
      Ok(views.html.awards())
   }

   def trends() = Action { implicit request: Request[AnyContent] =>
      Ok(views.html.trends())
   }

   def twitters() = Action { implicit request: Request[AnyContent] =>
      Ok(views.html.twitters())
   }

   def script1() = Action { implicit request: Request[AnyContent] =>
      MyScripts.script1()
      Redirect("/")
   }

   def script2() = Action { implicit request: Request[AnyContent] =>
      MyScripts.script2()
      Redirect("/")
   }

   def script3() = Action { implicit request: Request[AnyContent] =>
      MyScripts.script3()
      Redirect("/")
   }   

   def reset() = Action { implicit request: Request[AnyContent] =>
      
      "pwd" run

      MyScripts.reset()
      Redirect("/")
   }   
}
