package main

import akka.actor._
import actors._
import messages.Messages._

/**
 * @author apur8881
 */

object Main {

    def main(args : Array[String]) = {      
        println(args.length, args(0), args(1))
        val system = ActorSystem("RemoteActors")

        if (args.length == 2) { 
            val master = system.actorOf(Props[Master], "Master")
            master ! WordCount(args(0), Integer.parseInt(args(1)))
        }
        else {
            println("Error: Incorrect arguments")
        }

    }

}