package rss
import scala.xml._
import scala.io.Source
import java.util._
import java.net.{URL, URLConnection}
import scala.collection.mutable.{Queue, HashMap}

object XmlHelper {
	
	def importFeed(url_s: String): Node = {
	  val url = new URL(url_s);
	  val conn = url.openConnection;
	  return XML.load(conn.getInputStream);
	}
	
	def getTagName(node: Node): String = {
	  return node.label
	}
	
	def getTags(tagName: String, node: Node): List[Node] = {
	  var tags: List[Node] = new LinkedList[Node]();
	  
	  for (tag <- (node \\ tagName)) {
	    tags.add(tag);
	  }
	  
	  return tags;
	}
}