package sample.controllerImpl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import sample.Main;

public class VideoController implements Initializable{
  
  private Main application;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
  }

  public void setApp(Main application){
    this.application = application;
  }
  

}
