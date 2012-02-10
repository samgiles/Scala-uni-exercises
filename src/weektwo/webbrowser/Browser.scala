package weektwo.webbrowser
import scala.swing._
import javax.swing.event.HyperlinkListener
import javax.swing.event.HyperlinkEvent

object Browser extends SimpleSwingApplication {
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

      contents += backButton
      contents += forwardButton
      contents += homeButton
      contents += addressBar
      contents += goButton	
    }

    val htmlViewer = new EditorPane("text/html", "<a href=\"http://www.facebook.com\">Hello</a>") {
      editable = false;
      
      def setHTML(html: String) {
        text = html;
      }
      
      peer.addHyperlinkListener(new HyperlinkListener() {
        def hyperlinkUpdate(ev: HyperlinkEvent) {
          if (ev.getEventType() == HyperlinkEvent.EventType.ACTIVATED) 
            
            try {        
             notifyListeners(new BrowserMessageData(Message.LoadPage, ev));
            } catch  {
            	case ex: java.io.IOException => {}
            }
        }
      });
    }
    visible = true;
    title = "Web Browser";
    preferredSize = new Dimension(500, 500);
    contents = new BorderPanel {
      add(navigationBar, BorderPanel.Position.North)
      add(htmlViewer, BorderPanel.Position.Center)
    }
  }
  
  
  
  private val listeners: scala.collection.mutable.ListBuffer[BrowserMessage] = new scala.collection.mutable.ListBuffer[BrowserMessage]()
  
  def listen(message: BrowserMessage) {
    listeners += message;
  }
  
  def notifyListeners(data: BrowserMessageData) {
	 listeners.foreach(listener => {
	   if (listener.getMessage == data.getMessage) {
		   listener.notify(data);
	   }
	 });
  }
  
  val htmlLoader = HtmlLoader;
}

object Message extends Enumeration {
    type Message = Value;
    val LoadPage = Value;
}