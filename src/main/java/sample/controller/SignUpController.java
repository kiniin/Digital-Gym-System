package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    private Main application;

    @FXML
    private Button loginToSignup;

    public void setApp(Main application){
        this.application = application;
    }

    public void toLoginPage(){
        application.gotoLogin();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
