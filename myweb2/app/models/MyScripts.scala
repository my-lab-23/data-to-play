package my_scripts

import sys.process._
import scala.language.postfixOps
import akka.actor.ActorSystem
import akka.actor.Props

import my_actors._

object MyScripts {

   val actorSystem = ActorSystem("Core")

   //

   var pin1 = 0

   def script1() = {
      
      if (pin1 == 0) {
         val coreUno = actorSystem.actorOf(Props[CoreUno](), name = "coreUno")
         coreUno ! "hello"
         coreUno ! "run"
      }
   }

   def script1_run() = {
      pin1 = 1    
      pin1 = "./my_scripts/script1.sh" !

      println(pin1)
   }

   //

   var pin2 = 0

   def script2() = {
      
      if (pin2 == 0) {
         val coreDue = actorSystem.actorOf(Props[CoreDue](), name = "coreDue")
         coreDue ! "hello"
         coreDue ! "run"
      }
   }

   def script2_run() = {
      pin2 = 1    
      pin2 = "./my_scripts/script2.sh" !

      println(pin2)
   }

   //

   var pin3 = 0

   def script3() = {
      
      if (pin3 == 0) {
         val coreTre = actorSystem.actorOf(Props[CoreTre](), name = "coreTre")
         coreTre ! "hello"
         coreTre ! "run"
      }
   }

   def script3_run() = {
      pin3 = 1    
      pin3 = "./my_scripts/script3.sh" !

      println(pin3)
   }

   //

   def reset() = {
      pin1 = 0
      pin2 = 0
      pin3 = 0
   }
}
