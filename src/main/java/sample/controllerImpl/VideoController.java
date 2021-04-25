package sample.controllerImpl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import sample.Main;
import sample.simpleMediaPlayer.SimpleMediaPlayer;

public class VideoController implements Initializable{
  
  private Main application;

  @FXML
  private BorderPane videoBox;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub

  }

  public void setApp(Main application){
    System.out.println(SimpleMediaPlayer.class.getResource(""));
    // System.out.println(SimpleMediaPlayer.newInstance(getClass().getResource("../video/TestMedia.mp4").toString()));
    // videoBox.setCenter(player);
    // BorderPane.setAlignment(player ,Pos.CENTER);
    SimpleMediaPlayer player = SimpleMediaPlayer.newInstance(getClass().getResource("../video/TestMedia.mp4").toString());
    videoBox.setCenter(player);
    this.application = application;
  }
  

}
