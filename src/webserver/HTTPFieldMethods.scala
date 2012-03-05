package webserver

object HTTPFieldMethods {
  def fieldConcat(fields: Array[String]): String = {
    // re-Concatenate fields if they were split when splitting by ":".
    var string = "";
    var t = 0;
    if (fields.length > 2) {
      var length = fields.length;
      fields.foreach(field => {
        if (t != 0) {
          string = string + field;
          if (t != length - 1) {
            string = string + ":";
          }
        }
        t = t + 1;
      });
    } else {
      if (fields.length == 2) {
        return fields(1);
      }
    }
    
    return string;
  }
}