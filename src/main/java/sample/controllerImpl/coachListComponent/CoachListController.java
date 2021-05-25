package sample.controllerImpl.coachListComponent;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CoachListController {

    @FXML
    private Label coachName;
    @FXML
    private ListView<String> itemBox;

    public void setSportItem(ObservableList<String> itemList){
        itemBox.setItems(itemList);
    }
    public void setCoachName(String coachName){
        this.coachName.setText(coachName);
    }
}
