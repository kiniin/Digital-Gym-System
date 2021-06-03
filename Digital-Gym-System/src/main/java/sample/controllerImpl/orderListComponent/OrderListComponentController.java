package sample.controllerImpl.orderListComponent;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


/**
 * This controller is to set the order information into the javafx pages.
 *
 * @author Ruizheng Wu
 * @author Xiaojian Qi
 * @version 3.0
 */
public class OrderListComponentController {
    @FXML
    private Label time;
    @FXML
    private Label locationLabel;
    @FXML
    private Label userName;
    @FXML
    private Label userKind;
    @FXML
    private Label sportItem;
    @FXML
    private Label date;

    /**
     * This function can set Value for the components in OrderListComponents.fxml,
     * and according to
     *
     * @param time The time of this order.
     * @param location The location of this order
     * @param userName The user reserve this class
     * @param sportItem The sportItem for this course
     * @param date The date of This class
     * @param userKind This User is a Coach or a Client
     */
    public void setRecord(String time, String location, String userName, String sportItem, String date,String userKind){
        this.time.setText(time);
        this.userName.setText(userName);
        this.locationLabel.setText(location);
        this.sportItem.setText(sportItem);
        this.date.setText(date);
        this.userKind.setText(userKind);
    }
}
