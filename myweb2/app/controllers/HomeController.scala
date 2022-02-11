package controllers

import play.api.mvc._
import javax.inject._
import models.MyScripts

@Singleton
class HomeController @Inject()(cc: ControllerComponents)
   extends AbstractController(cc) {

   def index(): Action[AnyContent] = Action {
      implicit request: Request[AnyContent] => Ok(views.html.index())
   }

   def awards(): Action[AnyContent] = Action {
      implicit request: Request[AnyContent] => Ok(views.html.awards())
   }

   def trends(): Action[AnyContent] = Action {
      implicit request: Request[AnyContent] => Ok(views.html.trends())
   }

   def twitters(): Action[AnyContent] = Action {
      implicit request: Request[AnyContent] => Ok(views.html.twitters())
   }

   def script1(): Action[AnyContent] = Action {
      implicit request: Request[AnyContent] =>
         MyScripts.script1()
         Redirect("/")
   }

   def script2(): Action[AnyContent] = Action {
      implicit request: Request[AnyContent] =>
         MyScripts.script2()
         Redirect("/")
   }

   def script3(): Action[AnyContent] = Action {
      implicit request: Request[AnyContent] =>
         MyScripts.script3()
         Redirect("/")
   }   

   def reset(): Action[AnyContent] = Action {
      implicit request: Request[AnyContent] =>
         //"pwd" run
         MyScripts.reset()
         Redirect("/")
   }   
}
