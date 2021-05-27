package sample.controllerImpl.coachListComponent;

import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import sample.utils.MakeCenterImage;

public class CoachListController {

    @FXML
    private Label coachName;
    @FXML
    private ImageView coachPhoto;
    @FXML
    public Button subscribe;



//    public void setSportItem(ObservableList<String> itemList){
//        itemBox.setItems(itemList);
//    }
//    获取当前选中框的教练名
    public String getCoachName(){
        return this.coachName.getText();
    }
    public void setCoachName(String coachName){
        this.coachName.setText(coachName);
    }

    public void setCoachPhoto(String photoURL) {
        MakeCenterImage makeCenterImage = new MakeCenterImage();
        coachPhoto.setClip(makeCenterImage.makeCenterImageCircle(67,coachPhoto,photoURL));
    }
}
