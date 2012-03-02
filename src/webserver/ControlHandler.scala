package webserver
import scala.actors.Actor
import scala.actors._

class ControlHandler(parent: Actor) extends Actor {
  var running = true;
  
  def act = {
    while (running) {
      System.out.print("#");
      var bytes: Array[Byte] = new Array[Byte](100);
      System.in.read(bytes);
      val string = new String(bytes);
      
      string match {
        case _ => {
          System.out.println("Unknown command: " + string + ".  Use 'help' command for list of commands");
        }
      }
    } 
  }
}