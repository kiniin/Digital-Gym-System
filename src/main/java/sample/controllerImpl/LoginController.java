package sample.controllerImpl;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;
import sample.controller.ReadTextFieldable;
import sample.pojo.User;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LoginController implements Initializable, ReadTextFieldable {

    private Main application;

    @FXML
    private Button loginToSignup;

    @FXML
    private Button loginSubmit;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label loginStatus;

    private User loginUser;

    public void setApp(Main application){
        this.application = application;
    }

    public void toSignupPage(){
        application.gotoSignup();
    }

    public void gotoHome(){
        application.gotoHome();
    }

    public User readTextField(){
        boolean loginFlag = true;
        String usernameInfo = username.getText();
        String passwordInfo = password.getText();
        loginUser = new User();
        loginUser.setUsername(usernameInfo);
        loginUser.setPassword(passwordInfo);
        if (loginFlag) {
            loginStatus.setText("Wrong Password or not existed user");
        }else{
            application.gotoUserCenter();
        }
        System.out.println(loginUser.toString());
        return loginUser;
    }

    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
