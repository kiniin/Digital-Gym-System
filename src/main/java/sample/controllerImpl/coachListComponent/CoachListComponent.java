package sample.controllerImpl.coachListComponent;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CoachListComponent extends AnchorPane {

    private CoachListController controller;

    public CoachListComponent(String coachName){
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
