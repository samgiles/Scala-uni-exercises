package webserver
import java.net.ServerSocket
import scala.actors.Actor
import scala.actors._

case object Terminate;

class HTTPServer(port: Int) extends Actor {
  
  var running = true;
  
  def act = {
    while (running) {
      receive {
        case Terminate => {
          running = false;
          System.exit(0);
        }
        case _ => {
        }
      }
    }
  }
  
  val controlHandler = new ControlHandler(this);
  controlHandler.start;
  
  def run(): Unit = {
    this.start;
    val sock = new ServerSocket(port);
    
    while(running) {
      println("Waiting for connection...");
      
      val connection = sock.accept();
      
      println("Connection from: " + connection.getRemoteSocketAddress());
    }
  }
  
}