package webserver.http

object ParseRequestHeader {
  def apply(requestString: String): RequestHeader = {
    val lines = requestString.split("\n\r"); // split by crlf.
    
    
    var accept, acceptCharset, acceptEncoding, acceptLanguage,
    authorization, expect, from, host, ifMatch,
    ifModifiedSince, ifNoneMatch, ifRange, ifUnmodifiedSince, maxForwards,
    proxyAuthorization, range, referer, te, userAgent: String = null;
    
    lines.foreach(line => {
      val lineComponents = line.split(""":""");
      
      lineComponents(0) match {
        case "Host" => {
  	      host = FieldParseMethods.fieldConcat(lineComponents);
  	    }
  	    case "Accept" => {
  	      accept = FieldParseMethods.fieldConcat(lineComponents);
  	    }
  	    case "Accept-Charset" => {
  	      acceptCharset = FieldParseMethods.fieldConcat(lineComponents);
  	    }
  	    case "Accept-Encoding" => {
  	      acceptEncoding = FieldParseMethods.fieldConcat(lineComponents);
  	    }
  	    case "Accept-Language" => {
  	      acceptLanguage = FieldParseMethods.fieldConcat(lineComponents);
  	    }
  	    case "Authorization" => {
  	      authorization = FieldParseMethods.fieldConcat(lineComponents);
  	    }
  	    case "Expect" => {
  	      expect = FieldParseMethods.fieldConcat(lineComponents)
  	    }
  	    case "From" => {
  	      from = FieldParseMethods.fieldConcat(lineComponents)
  	    }
  	    case "If-Match" => {
  	      ifMatch = FieldParseMethods.fieldConcat(lineComponents)
  	    }
  	    case "If-Modified-Since" => {
  	      ifModifiedSince = FieldParseMethods.fieldConcat(lineComponents)
  	    }
  	    case "If-None-Match" => {
  	      ifNoneMatch = FieldParseMethods.fieldConcat(lineComponents)
  	    }
  	    case "If-Range" => {
  	      ifRange = FieldParseMethods.fieldConcat(lineComponents)
  	    }
  	    case "If-Unmodified-Since" => {
  	      ifUnmodifiedSince = FieldParseMethods.fieldConcat(lineComponents)
  	    }
  	    case "Max-Forwards" => {
  	      maxForwards = FieldParseMethods.fieldConcat(lineComponents)
  	    }
  	    case "Proxy-Authorization" => {
  	      proxyAuthorization = FieldParseMethods.fieldConcat(lineComponents)
  	    }
  	    case "Range" => {
  	      range = FieldParseMethods.fieldConcat(lineComponents)
  	    }
  	    case "Referer" => {
  	      referer = FieldParseMethods.fieldConcat(lineComponents)
  	    }
  	    case "TE" => {
  	      te = FieldParseMethods.fieldConcat(lineComponents)
  	    }
  	    case "User-Agent" => {
  	      userAgent = FieldParseMethods.fieldConcat(lineComponents)
  	    }
        case _ => {}
      }
    });
    
    return new RequestHeader(accept, acceptCharset, acceptEncoding, acceptLanguage,
    							authorization, expect, from, host, ifMatch,
    								ifModifiedSince, ifNoneMatch, ifRange, ifUnmodifiedSince, maxForwards,
    									proxyAuthorization, range, referer, te, userAgent);
  }
}

class RequestHeader(accept: String, acceptCharset: String, acceptEncoding: String, acceptLanguage: String,
    authorization: String, expect: String, from: String, host: String, ifMatch: String,
    ifModifiedSince: String, ifNoneMatch: String, ifRange: String, ifUnmodifiedSince: String, maxForwards: String,
    proxyAuthorization: String, range: String, referer: String, te: String, userAgent: String){

}