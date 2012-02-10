package weektwo.webbrowser
import scala.swing._
import javax.swing.event.HyperlinkListener
import javax.swing.event.HyperlinkEvent
import java.net.URL

object Browser extends SimpleSwingApplication {
  
   val htmlViewer = new EditorPane("text/html", "<a href=\"http://sam-giles.co.uk/?c=Index\">Hello</a>") {
      editable = false

      def setPage(url: String) {
        peer.setPage(url);
      }
      
      def setPage(url: URL) {
        peer.setPage(url);
      }
      
      def appendHTML(html: String) {
        text = text + html;
      }
      
      peer.addHyperlinkListener(new HyperlinkListener() {
        def hyperlinkUpdate(ev: HyperlinkEvent) {
          if ((ev.getEventType == HyperlinkEvent.EventType.ACTIVATED)) {      
            notifyListeners(new BrowserMessageData(Message.SetPage, ev.getURL));
          }
        }
      });
    }
  
  def top = new MainFrame {

    val navigationBar = new FlowPanel {
      val backButton = new Button {		
        action = Action("Back") {
        }	
      }
      
      val forwardButton = new Button {
        action = Action("Forward") {
        }				
      }
			
      val homeButton = new Button {
        action = Action("Home") {
        }
      }
			
      val addressBar = new TextField {
        columns = 50
        enabled = true			
      }

      val goButton = new Button {
        action = Action("Go") {
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
      contents += forwardButton
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
  
  val htmlLoader = HtmlLoader;  
    listen(new BrowserMessage(Message.SetPage, onSetPage));
}

object Message extends Enumeration {
    type Message = Value;
    val SetPage = Value;
}