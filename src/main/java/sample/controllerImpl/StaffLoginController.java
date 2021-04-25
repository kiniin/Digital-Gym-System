package sample.controllerImpl;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import sample.Main;
import sample.controller.ReadTextFieldable;
import sample.pojo.User;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class StaffLoginController implements Initializable, ReadTextFieldable {

    private Main application;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button login;

    @FXML
    private Hyperlink staffSignup;

    private User loginStaff;

    public void gotoStaffSignup(){ application.gotoStaffSignup(); }

    public User readTextField(){
        String usernameInfo = username.getText();
        String passwordInfo = password.getText();
        loginStaff = new User();
        loginStaff.setUsername(usernameInfo);
        loginStaff.setPassword(passwordInfo);
        System.out.println(loginStaff.toString());
        return loginStaff;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setApp(Main application){
        this.application = application;
    }
}
