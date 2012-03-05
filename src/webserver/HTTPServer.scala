package webserver
import java.net.ServerSocket
import scala.actors.Actor
import scala.actors._
import java.net.Socket
import java.io.BufferedReader
import java.io.InputStreamReader
import webserver.http.Request

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
      
      val connection: Socket = sock.accept();
      var handler = new ConnectionHandler(connection);
      handler.start
    }
  }
  
}