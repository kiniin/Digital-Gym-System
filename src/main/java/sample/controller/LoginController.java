package sample.controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Main;
import sample.pojo.User;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LoginController implements Initializable, ReadTextFieldable{

    private Main application;

    @FXML
    private Button loginToSignup;

    @FXML
    private Button loginSubmit;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    private String usernameInfo;
    private String passwordInfo;

    public void setApp(Main application){
        this.application = application;
    }

    public void toSignupPage(){
        application.gotoSignup();
    }

    public User readTextField(){
        usernameInfo = username.getText();
        passwordInfo = password.getText();
        User user = new User();
        user.setUsername(usernameInfo);
        user.setPassword(passwordInfo);
        System.out.println(user.toString());
        return user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
