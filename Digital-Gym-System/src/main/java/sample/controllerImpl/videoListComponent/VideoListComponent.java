package sample.controllerImpl.videoListComponent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.simpleMediaPlayer.PlayerController;

import java.io.IOException;


/**
 * This class is the basic component class for a video classification list.
 * It can build a list element component in a classification list
 *
 * @author Xiaojian Qi
 * @version 2.0
 */
public class VideoListComponent extends AnchorPane {
    private VideoListComponentController controller;     //储存每个实例的控制器对象


    /**
     * The constructor initializes a list component, including loading the
     * component's FXML file and initializing the component's contents
     *
     * @param imagePath the cover page url for this component
     * @param sportname the name for this component
     */
    public VideoListComponent(String imagePath, String sportname){
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("videoListComponent.fxml"));
            Parent root = fxmlloader.load();
            controller = fxmlloader.getController();
            controller.setSportname(sportname);
            controller.setVideoPic(imagePath);
            this.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the sort name of the component
     *
     * @return the sort name of this component
     */
    public String getVideoSort(){
        return controller.getSportname();
    }
}
