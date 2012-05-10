package rss
import scala.xml._
import scala.collection.JavaConversions._

object ReadRSSToConsole {

  def main(args: Array[String]): Unit = {
    if (args.length <= 0) {
      println("Enter a valid url argument\n");
      return;
    }
    
    val node = XmlHelper.importFeed(args(0));
    
    val items: java.util.List[Node] = XmlHelper.getTags("item", node);
    
    for (item <- items) {
      val title = XmlHelper.getTags("title", item).get(0).text;
      val description = XmlHelper.getTags("description", item).get(0).text;
      println("Title: " + title + " - " + description);
    }
  }
}