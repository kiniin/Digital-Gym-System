package sample.controllerImpl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class DataManagerController implements Initializable {

    private Main application;

    @FXML
    private Hyperlink ModifyInformation;

    @FXML
    private Hyperlink ManageVideos ;

    @FXML
    private Hyperlink ManageInventory ;

    public void gotoModifyInformation(){
        application.gotoModifyInformation();
    }
    public void gotoManageVideos(){
        application.gotoManageVideos();
    }
    public void gotoManageInventory(){
        application.gotoManageInventory();
    }

    public void setApp(Main application){
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
