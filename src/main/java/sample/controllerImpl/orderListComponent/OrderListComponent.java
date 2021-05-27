package sample.controllerImpl.orderListComponent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OrderListComponent extends AnchorPane {
    public OrderListComponentController controller;

    public OrderListComponent(String time, String location, String coachName, String sportItem, String date){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("orderListComponents.fxml"));
            Parent root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            this.getChildren().add(root);
            controller.setRecord(time, location, coachName, sportItem, date);
//            controller.setSportItem(sportItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
