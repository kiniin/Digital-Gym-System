package sample.controllerImpl.coachListComponent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.pojo.Coach;

import java.io.IOException;

public class CoachListComponent extends AnchorPane {

    public CoachListController controller;

    public CoachListComponent(Coach coach){
        String coachName = coach.getName();
        String coachPhoto = coach.getPhotoURL();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("coachList.fxml"));
            Parent root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            this.getChildren().add(root);
            controller.setCoachName(coachName);
//            controller.setSportItem(sportItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
