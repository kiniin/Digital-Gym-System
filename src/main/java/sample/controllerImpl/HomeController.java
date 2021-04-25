package sample.controllerImpl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private Main application;

    @FXML
    private Hyperlink login;

    @FXML
    private Hyperlink signUp;

    @FXML
    private Hyperlink staffEntry;

    @FXML
    private Hyperlink starttrail;

    public void gotoLogin(){
        application.gotoLogin();
    }
    public void gotoSignUp(){
        application.gotoSignup();
    }
    public void gotoStaffEntry(){ application.gotoStaffEntry(); }
    public void gotoUserCenter(){
        application.gotoUserCenter();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setApp(Main application){
        this.application = application;
    }
}
