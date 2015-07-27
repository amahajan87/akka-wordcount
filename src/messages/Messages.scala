package messages

import akka.actor._
import java.io.File

/**
 * @author apur8881
 */

object Messages {
    case object Done
    case object Hello
    case object Active
    case class Count(f: File)
    case class WordCount(url: String, noOfWorkers: Integer)
}