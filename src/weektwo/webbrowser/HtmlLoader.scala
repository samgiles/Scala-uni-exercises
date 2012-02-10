package weektwo.webbrowser
import javax.swing.event.HyperlinkEvent
import scala.xml.XML
import scala.io._
import scala.xml.pull.XMLEventReader
import org.xml.sax.XMLReader
import scala.xml.parsing.XhtmlParser
import java.net.URL
import java.io.ByteArrayOutputStream
import java.net.MalformedURLException
import java.io.FileNotFoundException
import java.io.BufferedReader
import java.io.InputStreamReader

object HtmlLoader {
  
	def loadPage(data: BrowserMessageData): Unit = {
	 val theObject = data.getData;
	 
	  theObject match {
	   case a : java.net.URL => {
	     var html: String = "";
	     try {
	    	 html = loadUrl(a);
	     } catch {
	       case e : Exception => {
	         println("Exception! " + e.toString);
	       }
	     }
	   }
	   case _ => {
	     // Cannot handle this message :S
	     println("Invalid Messsage Handled in HtmlLoader: loadPage");
	   }
	  }
	}
	
	def loadUrl(url: URL): String = {
	   var html: Iterator[String] = null;
	   var returnString = "";
	   
	   
	   
	   def downloadFile(url: URL) = {
		   val connection = url.openConnection
		   val reader = new BufferedReader(
				   new InputStreamReader(connection.getInputStream()))
		   var line = reader.readLine
		   while (line != null) {
			  // Browser.notifyListeners(new BrowserMessageData(Message.AppendToPage, line));
			   line = reader.readLine;
		   }
		   reader.close();
	   }
	   
	   try { 
	    	downloadFile(url);
	   } catch {
	     case f : FileNotFoundException => returnString = "file not found"
	     case u : MalformedURLException => returnString = "bad url"
	   	  case e: Exception => { 
	   	    returnString = "Error Loading Page: " + e.toString;
	   	  }
	   }
	   returnString;
	}
	
//	Browser.listen(new BrowserMessage(Message.LoadPage, loadPage));
}