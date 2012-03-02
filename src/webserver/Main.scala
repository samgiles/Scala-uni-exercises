/**
 * 
 */
package webserver

/**
 * @author sam
 *
 */
object Main {
  def main(args: Array[String]): Unit = {
    val httpServer: HTTPServer = new HTTPServer(9999);
  
  	httpServer.run;
  }
 
}