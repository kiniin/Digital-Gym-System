package sample.controllerImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;
import sample.controller.ReadTextFieldable;
import sample.pojo.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.List;

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
        boolean loginFlag = false;
        String usernameInfo = username.getText();
        String passwordInfo = password.getText();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/java/sample/data/User.json");
        try {
            List<User> users = objectMapper.readValue(file, new TypeReference<List<User>>(){});
            for (int i = 0; i < users.size(); i++){
                if (usernameInfo.equals(users.get(i).getUsername())){
                    if (passwordInfo.equals(users.get(i).getPassword())){
                        loginFlag = true;
                        loginUser = new User();
                        loginUser.setUsername(usernameInfo);
                        loginUser.setPassword(passwordInfo);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (loginFlag) {
            try {
                objectMapper.writeValue(new FileOutputStream("src/main/java/sample/data/LoginStatus.json"), usernameInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            application.gotoHome();
            System.out.println(loginUser.toString());
            return loginUser;
        }else{
            loginStatus.setText("Wrong Password or not existed user");
        }
        return null;
    }

    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
