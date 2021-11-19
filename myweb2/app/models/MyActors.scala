package my_actors

import akka.actor.Actor

import my_scripts.MyScripts

class CoreUno extends Actor {
   def receive = {
      case "hello" => println("CoreUno ready")
      case "run" => {
         MyScripts.script1_run()
         context.stop(self)
      }
      case _ => println("error")
   }
}

class CoreDue extends Actor {
   def receive = {
      case "hello" => println("CoreDue ready")
      case "run" => {
         MyScripts.script2_run()
         context.stop(self)
      }
      case _ => println("error")
   }
}

class CoreTre extends Actor {
   def receive = {
      case "hello" => println("CoreTre ready")
      case "run" => {
         MyScripts.script3_run()
         context.stop(self)
      }
      case _ => println("error")
   }
}

class CoreQuattro extends Actor {
   def receive = {
      case "hello" => println("CoreQuattro ready")
      case _ => println("error")
   }
}
