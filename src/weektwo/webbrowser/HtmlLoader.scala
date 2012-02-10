package weektwo.webbrowser
import javax.swing.event.HyperlinkEvent

object HtmlLoader {
	def loadPage(data: BrowserMessageData): Unit = {
	 data.getData match {
	   case a : HyperlinkEvent => {
	     
	   }
	   case _ => {
	     // Cannot handle this message :S
	   }
	 }
	}
	
	Browser.listen(new BrowserMessage(Message.LoadPage, loadPage));
	
}