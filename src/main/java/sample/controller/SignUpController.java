package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Main;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SignUpController implements Initializable, ReadTextFieldable {

    private Main application;

    @FXML
    private Button loginToSignup;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField email;

    private String usernameInfo;
    private String passwordInfo;
    private String emailInfo;

    public void setApp(Main application){
        this.application = application;
    }

    public void toLoginPage(){
        application.gotoLogin();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public HashMap readTextField() {
        usernameInfo = username.getText();
        passwordInfo = password.getText();
        emailInfo = email.getText();
        System.out.println(usernameInfo+" "+passwordInfo+ " "+ emailInfo);
        HashMap userVerifyMap = new HashMap();
        userVerifyMap.put("username",usernameInfo);
        userVerifyMap.put("password",passwordInfo);
        userVerifyMap.put("email",emailInfo);
        return userVerifyMap;
    }
}
