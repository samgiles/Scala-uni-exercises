/*package webserver

abstract class HTTPObject(requestString: String) {
  
  protected val lines: Array[String] = requestString.split("\n\r"); // Split by CRLF
  
  var cacheControl: String = null;
  var connection: String = null;
  var date: String = null;
  var pragma: String = null;
  var trailer: String = null;
  var transferEncoding: String = null;
  var upgrade: String  = null;
  var via: String = null;
  var warning: String = null;
  
  

  
  var i = 0;
  lines.foreach(line => {
    if (i != 0) {
      val field = line.split(""":""");
      field(0) match {
        
        case "Connection" => {
          connection = fieldConcat(field);
        }
        case "Date" => {
          date = fieldConcat(field);
        }
        case "Pragma" => {
          pragma = fieldConcat(field);
        }
        case "Trailer" => {
          trailer = fieldConcat(field);
        }
        case "Transfer-Encoding" => {
          transferEncoding = fieldConcat(field);
        }
        case "Upgrade" => {
          upgrade = fieldConcat(field);
        }
        case "Via" => {
          via = fieldConcat(field);
        }
        case "Warning" => {
          warning = fieldConcat(field);
        }
        case "Cache-Controler" => {
          
        }
        case _ => {
        }
      }
    }
    i = i + 1;
  });
  
  var breakPoint = 0;
  
  
}*/