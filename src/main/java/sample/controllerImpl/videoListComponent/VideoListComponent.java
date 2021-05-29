package sample.controllerImpl.videoListComponent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.simpleMediaPlayer.PlayerController;

import java.io.IOException;

public class VideoListComponent extends AnchorPane {
    private VideoListComponentController controller;     //储存每个实例的控制器对象

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
    public String getVideoSort(){
        return controller.getSportname();
    }
}
