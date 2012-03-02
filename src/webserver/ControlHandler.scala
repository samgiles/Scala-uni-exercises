package webserver
import scala.actors.Actor
import scala.actors._
import java.util.Scanner

class ControlHandler(parent: Actor) extends Actor {
  var running = true;
  
  def act = {
    val scanner: Scanner = new Scanner(System.in);
    
    while (running) {
      System.out.print("#");
      val string = scanner.nextLine();

      string match {
        
        case "terminate" => {
          println("Terminating Server.");
          parent ! Terminate
          running = false;
        }
        
        case _ => {
          System.out.println("Unknown command: " + string + ".  Use 'help' command for list of commands");
        }
      }
    } 
  }
}