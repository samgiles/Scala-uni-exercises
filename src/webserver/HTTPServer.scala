package webserver
import java.net.ServerSocket

class HTTPServer(port: Int) {
  
  def start(): Unit = {
    val sock = new ServerSocket(port);
    
    while(true) {
      println("Waiting for connection...");
      
      val connection = sock.accept();
      
      println("Connection from: " + connection.getRemoteSocketAddress());
    }
  }
  
}