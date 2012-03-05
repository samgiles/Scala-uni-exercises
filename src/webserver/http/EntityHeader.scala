package webserver.http

object ParseEntityHeader {
	def apply(requestString: String): EntityHeader = {
    val lines: Array[String] = requestString.split("\n\r"); // Split by CRLF
    
    var allow, contentEncoding, contentLanguage, contentLocation, contentMD5, contentRange, contentType, contentLength,
    	expires, lastModified: String = null;
    
    lines.foreach(line => {
    	var field = line.split(""":""");
    		field(0) match {
    			case "Allow" => {
    				allow = FieldParseMethods.fieldConcat(field);
    			}
    			case "Content-Encoding" => {
    				contentEncoding = FieldParseMethods.fieldConcat(field);
    			}
    			case "Content-Language" => {
    				contentLanguage = FieldParseMethods.fieldConcat(field);
    			}
    			case "Content-Length" => {
    				contentLength = FieldParseMethods.fieldConcat(field);
    			}
    			case "Content-Location" => {
    				contentLocation = FieldParseMethods.fieldConcat(field);
    			}
    			case "Content-MD5" => {
    				contentMD5 = FieldParseMethods.fieldConcat(field);
    			}
    			case "Content-Range" => {
    				contentRange = FieldParseMethods.fieldConcat(field);
    			}
    			case "Content-Type" => {
    				contentType = FieldParseMethods.fieldConcat(field);
    			}
    			case "Expires" => {
    				expires = FieldParseMethods.fieldConcat(field);
    			}
    			case "Last-Modified" => {
    				lastModified = FieldParseMethods.fieldConcat(field);
    			}
    			case _ => {}
    		}
    	});
    
     	return new EntityHeader(allow, contentEncoding, contentLanguage, contentLength, contentLocation, contentMD5, contentRange, contentType, expires, lastModified);
	}
}


class EntityHeader(allow: String, contentEncoding: String, 
    contentLanguage: String, contentLength: String, contentLocation: String, contentMD5: String, 
    contentRange: String, contentType: String, expires: String, lastModified: String) {
}