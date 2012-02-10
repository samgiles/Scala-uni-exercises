package weektwo.webbrowser

class BrowserMessageData (message : Message.Value, data: Object){
	
  def getMessage(): Message.Value = {
    message;
  }
  
  def getData(): Object = {
    data;
  }
}