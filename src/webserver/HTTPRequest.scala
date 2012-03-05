/**
 * 
 */
/*package webserver


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
  	
  	var host: String = null;
  	var accept: String = null;
  	var acceptCharset:String = null;
  	var acceptEncoding: String = null;
  	var acceptLanguage:String = null;
  	var authorization:String = null;
  	var expect:String = null;
  	var from:String = null;
  	var ifMatch:String = null;
  	var ifModifiedSince:String = null;
  	var ifNoneMatch:String = null;
  	var ifRange:String = null;
  	var ifUnmodifiedSince:String = null;
  	var maxForwards:String = null;
  	var proxyAuthorization:String = null;
  	var range:String = null;
  	var referer:String = null;
  	var te:String = null;
  	var userAgent:String = null;
  	
  	
  	
  	lines.foreach(line => {
  	  var lineComponents = line.split(""":""");
  	  lineComponents(0) match {
  	    case "Host" => {
  	      host = HTTPFieldMethods.fieldConcat(lineComponents);
  	    }
  	    case "Accept" => {
  	      accept = HTTPFieldMethods.fieldConcat(lineComponents);
  	    }
  	    case "Accept-Charset" => {
  	      acceptCharset = HTTPFieldMethods.fieldConcat(lineComponents);
  	    }
  	    case "Accept-Encoding" => {
  	      acceptEncoding = HTTPFieldMethods.fieldConcat(lineComponents);
  	    }
  	    case "Accept-Language" => {
  	      acceptLanguage = HTTPFieldMethods.fieldConcat(lineComponents);
  	    }
  	    case "Authorization" => {
  	      authorization = HTTPFieldMethods.fieldConcat(lineComponents);
  	    }
  	    case "Expect" => {
  	      expect = HTTPFieldMethods.fieldConcat(lineComponents)
  	    }
  	    case "From" => {
  	      from = HTTPFieldMethods.fieldConcat(lineComponents)
  	    }
  	    case "If-Match" => {
  	      ifMatch = HTTPFieldMethods.fieldConcat(lineComponents)
  	    }
  	    case "If-Modified-Since" => {
  	      ifModifiedSince = HTTPFieldMethods.fieldConcat(lineComponents)
  	    }
  	    case "If-None-Match" => {
  	      ifNoneMatch = HTTPFieldMethods.fieldConcat(lineComponents)
  	    }
  	    case "If-Range" => {
  	      ifRange = HTTPFieldMethods.fieldConcat(lineComponents)
  	    }
  	    case "If-Unmodified-Since" => {
  	      ifUnmodifiedSince = HTTPFieldMethods.fieldConcat(lineComponents)
  	    }
  	    case "Max-Forwards" => {
  	      maxForwards = HTTPFieldMethods.fieldConcat(lineComponents)
  	    }
  	    case "Proxy-Authorization" => {
  	      proxyAuthorization = HTTPFieldMethods.fieldConcat(lineComponents)
  	    }
  	    case "Range" => {
  	      range = HTTPFieldMethods.fieldConcat(lineComponents)
  	    }
  	    case "Referer" => {
  	      referer = HTTPFieldMethods.fieldConcat(lineComponents)
  	    }
  	    case "TE" => {
  	      te = HTTPFieldMethods.fieldConcat(lineComponents)
  	    }
  	    case "User-Agent" => {
  	      userAgent = HTTPFieldMethods.fieldConcat(lineComponents)
  	    }
  	    case _ => {
  	    }
  	   }  
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
}*/