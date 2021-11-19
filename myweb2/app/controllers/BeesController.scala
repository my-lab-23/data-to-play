package controllers

import sys.process._
import scala.language.postfixOps

import javax.inject._
import play.api._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

case class HashtagForm(hashtag: String)

object HashtagForm {
   val form: Form[HashtagForm] = Form(mapping("hashtag" -> text)
                                     (HashtagForm.apply)
                                     (HashtagForm.unapply))
}

@Singleton
class BeesController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {

   def beesForm() = Action { implicit request: Request[AnyContent] =>
      Ok(views.html.beesForm(HashtagForm.form))
   }

   def beesFormPost() = Action { implicit request =>
      val formData: HashtagForm = HashtagForm.form.bindFromRequest.get
      val query = formData.hashtag.toString

      val ip_port = sys.env.get("MY_BEES_IP_PORT").get
      s"curl $ip_port/bees?hashtag=$query" !
      
      Redirect("/")
   }
}
