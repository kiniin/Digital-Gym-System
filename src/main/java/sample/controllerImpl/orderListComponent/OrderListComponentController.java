package sample.controllerImpl.orderListComponent;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

    public void setRecord(String time, String location, String userName, String sportItem, String date,String userKind){
        this.time.setText(time);
        this.userName.setText(userName);
        this.locationLabel.setText(location);
        this.sportItem.setText(sportItem);
        this.date.setText(date);
        this.userKind.setText(userKind);
    }
}
