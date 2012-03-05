package webserver.http

class Request(requestString: String) extends Message {
  protected val lines: Array[String] = requestString.split("\n\r"); // Split by CRLF
  
  // We need to parse the lines according to the RFC specification rules.
  
  if (lines.length <= 0) {
    // Something went wrong, log the error. TODO
  } 
  
  // Parse the "Start Line" as a Request-Line (RFC 2616 Section 4.1) / http://www.w3.org/Protocols/rfc2616/rfc2616-sec4.html#sec4.5
  private val requestLineComponents = lines(0).split("""\s""");
  
  val requestMethod = RequestMethod.getRequestFromString(requestLineComponents(0));
  
  val uri = requestLineComponents(1);
  
  val httpVersion = requestLineComponents(2);
  
  val entityHeader: EntityHeader = ParseEntityHeader(requestString);
  
  val requestHeader: RequestHeader = ParseRequestHeader(requestString);
}