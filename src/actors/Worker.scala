package actors

import akka.actor._
import actors._
import messages.Messages._
import java.io.File

/**
 * @author apur8881
 */

class Worker(master: ActorRef) extends Actor {

    def receive = {
        case Hello => {
            println("Hello received by worker")
            sender ! Active
        }
        
        case Count(f: File) => {
            println("Count: ", f.getAbsolutePath)
            sender ! Done
            sender ! Active
        }
        
        case _ => {
            println("Message not defined ")
        }
    }

}