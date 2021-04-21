package sample.controllerImpl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import sample.Main;

public class VideoController implements Initializable{
  
  private Main application;

  @FXML
  private BorderPane videoBox;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub

  }

  public void setApp(Main application){
    System.out.println(SimpleMediaPlayer.class.getResource("../video/TestMedia.mp4").toString());
    System.out.println(SimpleMediaPlayer.newInstance(getClass().getClassLoader().getResource("../video/TestMedia.mp4").toString()));
    // videoBox.setCenter(player);
    // BorderPane.setAlignment(player ,Pos.CENTER);
    this.application = application;
  }
  

}
