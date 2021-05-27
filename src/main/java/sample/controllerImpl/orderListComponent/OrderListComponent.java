package sample.controllerImpl.orderListComponent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OrderListComponent extends AnchorPane {
    public OrderListComponentController controller;

    public OrderListComponent(String time, String location, String userName, String sportItem, String date, String userKind){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("orderListComponents.fxml"));
            Parent root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            this.getChildren().add(root);
            controller.setRecord(time, location, userName, sportItem, date, userKind);
//            controller.setSportItem(sportItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
