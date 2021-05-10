package sample.controllerImpl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import sample.Main;
import sample.simpleMediaPlayer.SimpleMediaPlayer;

public class VideoController implements Initializable{
  
  private Main application;

  @FXML
  private GridPane videoBox;

  private SimpleMediaPlayer player;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub

  }

  public void setApp(Main application){
    System.out.println(SimpleMediaPlayer.class.getResource(""));
    // System.out.println(SimpleMediaPlayer.newInstance(getClass().getResource("../video/TestMedia.mp4").toString()));
    // videoBox.setCenter(player);
    // BorderPane.setAlignment(player ,Pos.CENTER);
    // TODO 播放器自带的bug（当播放结束时不可以调进度了）
    // TODO 工具栏的布局bug
    player = SimpleMediaPlayer.newInstance(getClass().getResource("../video/TestMedia.mp4").toString(),693, 390);
    videoBox.getChildren().add(player);
    GridPane.setValignment(player, VPos.CENTER);
    GridPane.setHalignment(player, HPos.CENTER);
    player.setMaxHeight(Region.USE_PREF_SIZE);
    player.setMaxWidth(Region.USE_PREF_SIZE);
    adjustMediaSize();
    this.application = application;
  }

  public void adjustMediaSize(){
    videoBox.heightProperty().addListener(new ChangeListener<Number>(){

      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        // TODO Auto-generated method stub
        player.setMediaHeight((double)newValue*0.9);
      }
      
    });

    videoBox.widthProperty().addListener(new ChangeListener<Number>(){

      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        // TODO Auto-generated method stub
        player.setMediaWidth((double)newValue);
      }
      
    });
  }
}
