package webserver.http

 /**
   * Represents a type of the Module Session.
   */
object RequestMethod extends Enumeration {
  type RequestMethod = Value;
  val OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT, EXTMETHOD = Value;
  
  
  
  def getRequestFromString(method: String): (RequestMethod, String) = {
    
    method match {
      
      case "GET" => {
        return (RequestMethod.GET, method);
      }
      
      case "POST" => {
        return (RequestMethod.POST, method);
      }
      
      case "PUT" => {
        return (RequestMethod.PUT, method);
      }
      
      case "HEAD" => {
        return (RequestMethod.HEAD, method);
      }
      
      case "DELETE" => {
        return (RequestMethod.DELETE, method);
      }
      
      case "OPTIONS" => {
        return (RequestMethod.OPTIONS, method);
      }
      
      case "TRACE" => {
        return (RequestMethod.TRACE, method);
      }
      
      case "CONNECT" => {
        return (RequestMethod.CONNECT, method);
      }
      
      case _ => {
        return (RequestMethod.EXTMETHOD, method);
      }
    }
  }
}