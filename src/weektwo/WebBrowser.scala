package weektwo

import scala.swing._
import javax.swing.event._
import scala.io.Source
import scala.io.BufferedSource
import scala.xml._

object WebBrowser extends SimpleSwingApplication {
  
  object htmlLoader {
    
    var html: BufferedSource = null;
    var xml: scala.xml.Elem = null;
    
    def loadUrl(url: java.net.URL): Unit = {
     html = Source.fromURL(url);
    }
    
    def getHtmlString() : String ={
      var string = "";
      html.foreach(s => string = string + s);
      string;
    }
    
    def getTitleTag() : String = {
      val titlenode = xml.find(n => { n.label.toUpperCase == "TITLE"});
      titlenode.toString;
    }
    
    def getHtmlXml() : Unit = {
    	xml = XML.loadString(getHtmlString);
    }
  };
  
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
            	htmlLoader.loadUrl(ev.getURL());
            	//setHTML(htmlLoader.getHtml);
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
}