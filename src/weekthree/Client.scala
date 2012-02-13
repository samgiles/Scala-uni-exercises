package weekthree

import java.net.Socket
import java.io._
import java.net._

object Client {
  def displayUsageInfo = {
    println("Parameters are:")
    println("  1) The address of the server that you wish to connect to")
    println("  2) The port number that the server is listening on")
  }
  def main(args: Array[String]) : Unit = {
    if(args.length != 2) {
      displayUsageInfo
    } else {
      try {
        val sock =new Socket(args(0), Integer.parseInt(args(1)))
        val out = sock.getOutputStream();
        val writer = new PrintStream(out);
        
        val in = sock.getInputStream()
        val reader = new BufferedReader(new InputStreamReader(in))

        
        val commandLineReader = new BufferedReader(new InputStreamReader(System.in))
        
        var finished = false;
        
        while(!finished) {
        	System.out.println("Enter Something: ")
          	var input = commandLineReader.readLine();
        	if (":quit" == input) {
        	  finished = true;
        	} else {
        	  writer.println(input);
        	}
        	
            var line = reader.readLine()
        	while(line != null) {
        		System.out.println("received: " + line)
        		line = null;
        		
        	}
        }
      } catch {
        case e:NumberFormatException => println("Port number should be an integer")
        case e:ConnectException => println("Problems connecting to server. Is it running?")
        case e:UnknownHostException => println("Unknown host. Check the hostname or ip address of the server")
        case e:IllegalArgumentException => println("The port number needs to be less than 65536")
        case unknown => {println("Unexpected exception" + unknown); unknown.printStackTrace}
      }
    }
  }
}
