package weektwo.webbrowser
import scala.swing._
import javax.swing.event.HyperlinkListener
import javax.swing.event.HyperlinkEvent
import java.net.URL
import scala.collection.mutable.Stack

object Browser extends SimpleSwingApplication {
  
  
  
   val htmlViewer = new EditorPane("text/html", "<a href=\"http://google.co.uk\">Google</a>") {
      editable = false

      var currentUrl: URL = new URL("http://google.co.uk");
      
      def setPage(url: String) {
        val uri = new URL(url);
        peer.setPage(uri);
        currentUrl = uri;
      }
      
      def setPage(url: URL) {
        peer.setPage(url);
        currentUrl = url;
      }
      
      def appendHTML(html: String) {
        text = text + html;
      }
      
      peer.addHyperlinkListener(new HyperlinkListener() {
        def hyperlinkUpdate(ev: HyperlinkEvent) {
          if ((ev.getEventType == HyperlinkEvent.EventType.ACTIVATED)) {   
            notifyListeners(new BrowserMessageData(Message.AddToHistory, currentUrl));
            notifyListeners(new BrowserMessageData(Message.SetPage, ev.getURL));
          }
        }
      });
    }
  
  def top = new MainFrame {

    val navigationBar = new FlowPanel {
      val backButton = new Button {	
        peer.setEnabled(false)
        action = Action("Back") {
          var url = History.getLastURL;
          Browser.notifyListeners(new BrowserMessageData(Message.SetPage, url));
        }	
      }
			
      val homeButton = new Button {
        action = Action("Home") {
          Browser.notifyListeners(new BrowserMessageData(Message.SetPage, "http://google.co.uk"));
        }
      }
			
      val addressBar = new TextField {
        columns = 100
        enabled = true			
      }

      val goButton = new Button {
        action = Action("Go") {
          
          if (addressBar.text.indexOf("http") != 0) {
            addressBar.text = "http://" + addressBar.text;
          }
          
          Browser.notifyListeners(new BrowserMessageData(Message.SetPage, addressBar.text));
        }
      }
      
      def onSetPage(data : BrowserMessageData): Unit = {
        data.getData match {
          case a : String => { 
            addressBar.text = a;
          }
          case b : URL => {
            addressBar.text = b.toString;
          }
        }
      }
      
      Browser.listen(new BrowserMessage(Message.SetPage, onSetPage));

      contents += backButton
      contents += homeButton
      contents += addressBar
      contents += goButton	
    }
    
    
    val scrollArea = new ScrollPane (htmlViewer) {
    }
    
    
    def changeTitle (newTitle: String) : Unit = {
      title = newTitle;
    }
    
    visible = true;
    title = "Web Browser";
    preferredSize = new Dimension(500, 500);
    contents = new BorderPanel {
      add(navigationBar, BorderPanel.Position.North)
      add(scrollArea, BorderPanel.Position.Center)
    }
  }
  
  def onSetPage(data: BrowserMessageData): Unit = {
    data.getData match {
      case a : String => { htmlViewer.setPage(a)}
      case u : URL => { htmlViewer.setPage(u)}
    }
  }
 
  private val messagelisteners: scala.collection.mutable.ListBuffer[BrowserMessage] = new scala.collection.mutable.ListBuffer[BrowserMessage]()
  
  
  def listen(message: BrowserMessage) {
    messagelisteners += message;
  }
  
  def notifyListeners(data: BrowserMessageData) {
	 messagelisteners.foreach(listener => {
	   if (listener.getMessage == data.getMessage) {
		   listener.notify(data);
	   }
	 });
  }
  
  listen(new BrowserMessage(Message.SetPage, onSetPage));
}

object Message extends Enumeration {
    type Message = Value;
    val SetPage, AddToHistory = Value;
}

object History {
  val prev: Stack[URL] = new Stack[URL]();
  
  def getLastURL() : URL = {
    if (prev.size > 0) {
    	var url = prev.pop;
    	return url;
    } else {
      return new URL("http://google.co.uk");
    }
  }
  
  def onHistoryUpdate(data: BrowserMessageData): Unit = {
    data.getData match {
      case a : String => {
        prev.push(new URL(a));
      }
      case b : URL => {
        prev.push(b);
      }
      case _ => {}
    }
  }
  
  Browser.listen(new BrowserMessage(Message.AddToHistory, onHistoryUpdate));
}