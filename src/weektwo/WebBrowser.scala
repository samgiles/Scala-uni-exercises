package weektwo

import scala.swing._

object WebBrowser extends SimpleSwingApplication {
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

		val htmlViewer = new EditorPane("text/html", "<a href=\"\">Hello</a>") {
			
			editable = false;
			
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