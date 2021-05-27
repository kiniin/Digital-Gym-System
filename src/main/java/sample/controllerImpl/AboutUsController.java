package sample.controllerImpl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutUsController implements Initializable {

    private Main application;

    @FXML
    private Hyperlink coachLogin;

    @FXML
    private Hyperlink adminLogin;

//    public void gotoCoachLogin(){ application.gotoCoachLogin(); }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setApp(Main application){
        this.application = application;
    }
}
