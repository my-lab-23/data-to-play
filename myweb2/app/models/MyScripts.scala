package models

import scala.language.postfixOps
import sys.process._
import akka.actor.ActorSystem
import akka.actor.Props

object MyScripts {

   val actorSystem: ActorSystem = ActorSystem("Core")

   //

   var pin1 = 0

   def script1(): Unit = {
      
      if (pin1 == 0) {
         val coreUno = actorSystem.actorOf(Props[CoreUno](), name = "coreUno")
         coreUno ! "hello"
         coreUno ! "run"
      }
   }

   def script1_run(): Unit = {
      pin1 = 1    
      pin1 = "./my_scripts/script1.sh" !

      println(pin1)
   }

   //

   var pin2 = 0

   def script2(): Unit = {
      
      if (pin2 == 0) {
         val coreDue = actorSystem.actorOf(Props[CoreDue](), name = "coreDue")
         coreDue ! "hello"
         coreDue ! "run"
      }
   }

   def script2_run(): Unit = {
      pin2 = 1    
      pin2 = "./my_scripts/script2.sh" !

      println(pin2)
   }

   //

   var pin3 = 0

   def script3(): Unit = {
      
      if (pin3 == 0) {
         val coreTre = actorSystem.actorOf(Props[CoreTre](), name = "coreTre")
         coreTre ! "hello"
         coreTre ! "run"
      }
   }

   def script3_run(): Unit = {
      pin3 = 1    
      pin3 = "./my_scripts/script3.sh" !

      println(pin3)
   }

   //

   def reset(): Unit = {
      pin1 = 0
      pin2 = 0
      pin3 = 0
   }
}
