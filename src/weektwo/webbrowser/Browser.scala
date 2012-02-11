package weektwo.webbrowser
import scala.swing._
import javax.swing.event.HyperlinkListener
import javax.swing.event.HyperlinkEvent
import java.net.URL
import scala.collection.mutable.Stack

object Browser extends SimpleSwingApplication {
  
  /**
   * A List of registered BrowserMessage listeners, each Browser message specifies a callback method and a Message that they are listening for.
   */
  private var messagelisteners: scala.collection.mutable.ListBuffer[BrowserMessage] = new scala.collection.mutable.ListBuffer[BrowserMessage]();
  
  /**
   * Register a listening BrowserMessage.
   */
  def listen(message: BrowserMessage) {   
    messagelisteners += message;
  }
  
  /**
   * Notify Listeners of a browser message using a browser message data object.
   */
  def notifyListeners(data: BrowserMessageData) {
    // Filter the messageListeners and notify their callback if they are listening for the current message with the BrowserMessageData.
	 messagelisteners.foreach(listener => {
	   if (listener.getMessage == data.getMessage) {
		   listener.notify(data);
	   }
	 });
  }
  
  
   /**
    * The viewer for the HTML.  This renders the HTML and responds to Browser Events, (Currently limited to hyperlink Active click events).
    */
   val htmlViewer = new EditorPane("text/html", "<a href=\"http://google.co.uk\">Google</a>") {
     
      // Make the EditorPane non editable.
      editable = false

      // Set the current default URL.
      var currentUrl: URL = new URL("http://google.co.uk");
      
      /**
       * Sets the page to the URL specified by the String url.
       */
      def setPage(url: String) {
        val uri = new URL(url);
        peer.setPage(uri);
        currentUrl = uri;
      }
      
      /**
       * Sets the page URL using the given URL.
       */
      def setPage(url: URL) {
        peer.setPage(url);
        currentUrl = url;
      }
      
      /**
       * Append HTML to the viewer.
       */
      def appendHTML(html: String) {
        text = text + html;
      }
      
      def onSetPage(data: BrowserMessageData): Unit = {
        data.getData match {
          case a : String => { setPage(a)}
          case u : URL => { setPage(u)}
        }
      }
        
      listen(new BrowserMessage(Message.SetPage, onSetPage));
      
      // Add a link listener to the EditorPane.
      peer.addHyperlinkListener(new HyperlinkListener() {
        /**
         * Listens for updates to hyperlink state.
         */
        def hyperlinkUpdate(ev: HyperlinkEvent) {
          // If the user has ACTIVATED a link, then notify Listeners to add the current link to the history, and then set the page to the new URL.
          if ((ev.getEventType == HyperlinkEvent.EventType.ACTIVATED)) {   
            notifyListeners(new BrowserMessageData(Message.AddToHistory, currentUrl));
            notifyListeners(new BrowserMessageData(Message.SetPage, ev.getURL));
          }
        }
      });
    }
  
   /**
    * Specifies the UI for the SimpleSwingApplication.
    */
  def top = new MainFrame {

    /**
     * Definition of the navigation Bar.
     */
    val navigationBar = new FlowPanel {
      
      /**
       * Browser back button.
       */
      val backButton = new Button {	
        peer.setEnabled(false)
        action = Action("Back") {
          var url = History.getLastURL;
          Browser.notifyListeners(new BrowserMessageData(Message.SetPage, url));
        }	
      }
		
      /**
       * Browser Home Button.
       */
      val homeButton = new Button {
        action = Action("Home") {
          Browser.notifyListeners(new BrowserMessageData(Message.SetPage, "http://google.co.uk"));
        }
      }
			
      /**
       * Browser Address bar.
       */
      val addressBar = new TextField {
        columns = 100
        enabled = true	
        
        
        /**
         * The On Set page handler for the address bar.  The address bar needs to update itself when the page is changed.
         */
        def onSetPage(data : BrowserMessageData): Unit = {
          data.getData match {
          	 case a : String => { 
        	   text = a;
          	 }
          	 case b : URL => {
        	   text = b.toString;
          	 }
          }
        }
      
        Browser.listen(new BrowserMessage(Message.SetPage, onSetPage));
        
      }

      /**
       * Browser Go Button.
       */
      val goButton = new Button {
        action = Action("Go") {
          
          if (addressBar.text.indexOf("http") != 0) {
            addressBar.text = "http://" + addressBar.text;
          }
          notifyListeners(new BrowserMessageData(Message.AddToHistory, htmlViewer.currentUrl));
          Browser.notifyListeners(new BrowserMessageData(Message.SetPage, addressBar.text));
        }
      }
      
      

      contents += backButton
      contents += homeButton
      contents += addressBar
      contents += goButton	
    }
    
    /**
     * The Scroll Area that the HtmlViewer is contained within.
     */
    val scrollArea = new ScrollPane (htmlViewer) {
    }

    // Set some extra settings such as the visibility of the window, title and size.
    visible = true;
    title = "Web Browser";
    preferredSize = new Dimension(800, 500);
    // Add the navigation bar and the scroll area to the window.
    contents = new BorderPanel {
      add(navigationBar, BorderPanel.Position.North)
      add(scrollArea, BorderPanel.Position.Center)
    }
  }
  
}

/**
 * Browser Messages.
 */
object Message extends Enumeration {
    type Message = Value;
    val SetPage, AddToHistory = Value;
}

/**
 * Browser History.
 */
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