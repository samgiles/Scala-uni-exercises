package rss
import scala.xml._
import scala.collection.JavaConversions._
import java.net.MalformedURLException

object ReadRSSToConsole {

  def main(args: Array[String]): Unit = {
    if (args.length <= 0) {
      println("Enter a valid url argument\n");
      return;
    }
    var node: Node = null;
    
    try {
       node = XmlHelper.importFeed(args(0));
    } catch {
	    case e: MalformedURLException => {
	      println("The URL `" + args(0) + "` was invalid");
	      return;
	    }
	}
   
    
    val items: java.util.List[Node] = XmlHelper.getTags("item", node);
    
    if (items.length == 0) {
      println("No items in the feed!");
      return;
    }
    
    for (item <- items) {
      val title = XmlHelper.getTags("title", item).get(0).text;
      val description = XmlHelper.getTags("description", item).get(0).text;
      println("Title: " + title + " - " + description);
    }
  }
}