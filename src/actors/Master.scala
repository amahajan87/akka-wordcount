package actors


import akka.actor._
import actors._
import messages.Messages._
import java.io.File
import org.scalatest.enablers.Length
import scala.collection.mutable.ListBuffer
import sun.security.util.Length




/**
 * @author apur8881
 */

class Master extends Actor {
    
    var nodeRefs: Array[ActorRef] = _
    var files: List[File] = _
    var fileBuffer: Set[File] = _
    
    def receive = {
        case WordCount(url, noOfWorkers) => {
            println("Master executing")
            nodeRefs = new Array[ActorRef](noOfWorkers)
            println("Start: ", url)
            val d = new File(url)
            files = getListOfFiles(d)
            println(files)
            fileBuffer = files.toSet
            println(fileBuffer)
            println("End: ", url)
            for (i <- 0 until noOfWorkers) {
                nodeRefs(i) = context.actorOf(Props(new Worker(self)), name = "Worker" + i)
                // context.actorOf(Props[Worker].withRouter(RoundRobinRouter(noOfWorkers)), name = "workerRouter")
                nodeRefs(i) ! Hello
            }
        }
 
        case Active => {
            println("Worker acknowledgement received")
            if (fileBuffer.size > 0) {
               var f: File = fileBuffer.last
               fileBuffer.-=(f)
               sender ! Count(f)
            }
        }
        
        case Done => {
            println("Count received")
        }
        
        case _ => {
            println("Message not defined ")
        }
        
    }
    
    def getListOfFiles(dir: File):List[File] = dir.listFiles.toList
    
}