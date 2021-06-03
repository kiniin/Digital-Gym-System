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



/**
 *The controller can populate element attributes in FXML, including the image position and the text displayed
 *
 * @author Xiaojian Qi
 * @version 2.0
 */
public class VideoListComponentController {
    @FXML
    private ImageView videoPic;

    @FXML
    private Label sportname;


    /**
     * Set Image for this component.
     * @param url The url of the cover picture
     */
    public void setVideoPic(String url){
        videoPic.setImage(new Image(url));
    }
    /**
     * Set Sport sort for this component
     * @param sportname The name of sport sort
     */
    public void setSportname(String sportname){
        this.sportname.setText(sportname);
    }
    /**
     * Get Sport sort for this component
     * @return The Sport name of the component
     */
    public String getSportname(){
        return sportname.getText();
    }
}
