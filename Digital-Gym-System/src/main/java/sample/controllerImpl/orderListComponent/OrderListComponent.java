package sample.controllerImpl.orderListComponent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


/**
 * Initialize the list of the order, and register it in the page.
 *
 * @author Ruizheng Wu
 * @author Xiaojian Qi
 * @version 3.0
 */
public class OrderListComponent extends AnchorPane {
    public OrderListComponentController controller;
    /**
     * This function can determine whether the user is a customer or coach to
     * generate different order components and componentize the entire order display list
     *
     * @param time The time of this order.
     * @param location The location of this order
     * @param userName The user reserve this class
     * @param sportItem The sportItem for this course
     * @param date The date of This class
     * @param userKind This User is a Coach or a Client
     */
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
