package sample.controllerImpl.orderListComponent;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderListComponentController {
    @FXML
    private Label time;
    @FXML
    private Label locationLabel;
    @FXML
    private Label coachName;
    @FXML
    private Label sportItem;
    @FXML
    private Label date;

    public void setRecord(String time, String location, String coachName, String sportItem, String date){
        this.time.setText(time);
        this.coachName.setText(coachName);
        this.locationLabel.setText(location);
        this.sportItem.setText(sportItem);
        this.date.setText(date);
    }
}
