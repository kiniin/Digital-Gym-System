package sample.controllerImpl.videoListComponent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class VideoListComponentController {
    @FXML
    private ImageView videoPic;

    @FXML
    private Label sportname;

    public void setVideoPic(String url){
        videoPic.setImage(new Image(url));
    }
    public void setSportname(String sportname){
        this.sportname.setText(sportname);
    }
}
