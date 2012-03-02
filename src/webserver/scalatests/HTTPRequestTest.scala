package webserver.scalatests

import webserver._

object HTTPRequestTest {

  def main(args: Array[String]) {
    val HTTPRequest = "GET / HTTP/1.1\n\r" +
    				  "Host: www.exanple.com\n\r" +
    				  "Connection: close\n\r" +
    				  "User-Agent: Googlebot/2.1 (+http://www.googlebot.com/bot.html)\n\r" +
    				  "Accept-Charset: ISO-8859-1,UTF-8;q=0.7,*;q=0.7\n\r" +
    				  "Cache-Control: no-cache\n\r" +
    				  "Accept-Language: de,en;q=0.7,en-us;q=0.3\n\r" +
    				  "Referer: http://googlebot.com/\n\r" +
    				  "\n\r";
    
    val parsedRequest = new HTTPRequest(HTTPRequest);
    
    assert(parsedRequest.requestMethod._1 == HTTPRequestMethod.GET)
  }
}