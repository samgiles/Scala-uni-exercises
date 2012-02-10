package weektwo.webbrowser

class BrowserMessage(message : Message.Value, callback: (BrowserMessageData) => Unit) {

  def getMessage() : Message.Value = {
    message;
  }
  
  def notify(data: BrowserMessageData): Unit = {
    callback(data);
  }
}