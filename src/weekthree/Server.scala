package weekthree

import java.net._
import java.io._
import java.util._

object Server{

  def displayUsageInfo = {
    println("Parameters are:");
    println("  1) The port number that the server will listen on")
  }

  def main(args: Array[String]):Unit = {
    if(args.length != 1) {
      displayUsageInfo
    } else {
      try{
        val sock=new ServerSocket(Integer.parseInt(args(0)))
        var finished = false
        while(!finished){
          println("waiting");
          val connection=sock.accept()
          println("connection from " + connection.getRemoteSocketAddress())

          val is = connection.getInputStream()  
          val in = new BufferedReader(new InputStreamReader(is))

          
          val os = connection.getOutputStream();
          val out = new PrintStream(os);
          
          var line = in.readLine
          while(line!=null && !finished) {
            line match {
              case ":quit" => finished = true
              case _ => {
            	  println("Received " + line);
                  out.println(line);
                  out.flush();
              	}
            }
            if(!finished){
              line =in.readLine
            }
          }

          connection.close
        }
      } catch {
        case e:BindException => println("Cannot bind to port. Is a server already running?")
        case e:NumberFormatException => println("Port number should be an integer")
        case e:IllegalArgumentException => println("The port number needs to be less than 65536")
        case ex => println("Exception: " +ex.toString())
      }
    }
  }

}
