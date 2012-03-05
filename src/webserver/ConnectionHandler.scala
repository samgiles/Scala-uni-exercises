package webserver
import scala.actors.Actor
import java.net.Socket
import java.io.BufferedReader
import java.io.InputStreamReader
import webserver.http.Request

class ConnectionHandler(connection: Socket) extends Actor {

  def act = {
     println("Connection from: " + connection.getRemoteSocketAddress());
      val is = connection.getInputStream()  
      val in = new BufferedReader(new InputStreamReader(is))
      var request = "";
      var line = in.readLine
      while(line!=null) {
         request = request + line + "\n\r";
         println(line);
         line = in.readLine
         if (line == "\n\r") {
           line == null;
         }
      }
      
      var req = new Request(request);   
      
      connection.close;
  }
}