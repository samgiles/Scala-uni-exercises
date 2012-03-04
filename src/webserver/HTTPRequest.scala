/**
 * 
 */
package webserver


import scala.util.matching.Regex
import scala.collection.mutable.HashMap

/**
 * @author sam
 *
 */
class HTTPRequest(requestString: String) extends HTTPObject(requestString) {
    
    // First line is always the HTTP request line as defined in the RFC: [Request-Line   = Method SP Request-URI SP HTTP-Version CRLF]
  	if (lines.size < 1) {
  	  // Something went wrong, maybe an empty request? TODO: Log.
  	}
    
  	private val requestLineComponents = lines(0).split("""\s""");
  	
  	// Method will be the first component.
  	val requestMethod: (HTTPRequestMethod.Value, String) = HTTPRequestMethod.getRequestFromString(requestLineComponents(0));
  	
  	val uri: String = requestLineComponents(1);
  	
  	val httpVersion: String = requestLineComponents(2);
  	
  	lines.foreach(line => {
  	  
  	  
  	  
  	});
  	
  	
}

  /**
   * Represents a type of the Module Session.
   */
object HTTPRequestMethod extends Enumeration {
  type HTTPRequestMethod = Value;
  val OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT, EXTMETHOD = Value;
  
  
  
  def getRequestFromString(method: String): (HTTPRequestMethod, String) = {
    
    method match {
      
      case "GET" => {
        return (HTTPRequestMethod.GET, method);
      }
      
      case "POST" => {
        return (HTTPRequestMethod.POST, method);
      }
      
      case "PUT" => {
        return (HTTPRequestMethod.PUT, method);
      }
      
      case "HEAD" => {
        return (HTTPRequestMethod.HEAD, method);
      }
      
      case "DELETE" => {
        return (HTTPRequestMethod.DELETE, method);
      }
      
      case "OPTIONS" => {
        return (HTTPRequestMethod.OPTIONS, method);
      }
      
      case "TRACE" => {
        return (HTTPRequestMethod.TRACE, method);
      }
      
      case "CONNECT" => {
        return (HTTPRequestMethod.CONNECT, method);
      }
      
      case _ => {
        return (HTTPRequestMethod.EXTMETHOD, method);
      }
    }
  }
}