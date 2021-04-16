package sample.controllerImpl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Main;
import sample.controller.ReadTextFieldable;
import sample.pojo.User;

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

    private User signUpUser;

    public void setApp(Main application){
        this.application = application;
    }

    public void toLoginPage(){
        application.gotoLogin();
    }

    public void gotoHome(){
        application.gotoHome();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public User readTextField() {
        String usernameInfo = username.getText();
        String passwordInfo = password.getText();
        String emailInfo = email.getText();
        System.out.println(usernameInfo+" "+passwordInfo+ " "+ emailInfo);
        signUpUser = new User();
        signUpUser.setPassword(passwordInfo);
        signUpUser.setUsername(usernameInfo);
        signUpUser.setEmail(emailInfo);
        return signUpUser;
    }
}
