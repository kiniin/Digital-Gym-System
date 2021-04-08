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

    public void gotoLogin(){
        application.gotoLogin();
    }
    public void gotoSignUp(){
        application.gotoSignup();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setApp(Main application){
        this.application = application;
    }
}
