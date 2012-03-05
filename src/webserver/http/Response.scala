package webserver.http

class Response(statusCode: Int) extends Message {
   var httpVersion = "HTTP/1.1"
     
   val reasonPhrase = StatusCode(statusCode);
   
   def statusLine(): String = httpVersion + " " + statusCode.toString + " " + reasonPhrase
  
   override def toString(): String = {
     return statusLine + "\n\r";
   }
}