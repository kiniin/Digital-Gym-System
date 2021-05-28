package sample.controllerImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Main;
import sample.controller.ReadTextFieldable;
import sample.pojo.Coach;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CoachLoginController implements Initializable, ReadTextFieldable {
    private Main application;

    @FXML
    private TextField account;
    @FXML
    private PasswordField password;
    @FXML
    private Label loginStatus;


    private Coach loginCoach;


    public void gotoAboutUs() { application.gotoAboutUs(); }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public Coach readTextField() {
        boolean loginFlag = false;
        String accountInfo = account.getText();
        String passwordInfo = password.getText();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/java/sample/data/Coach.json");

        try {
            List<Coach> coaches = objectMapper.readValue(file, new TypeReference<List<Coach>>(){});
            for (int i = 0; i < coaches.size(); i++){
                if (accountInfo.equals(coaches.get(i).getAccount())){
                    if (passwordInfo.equals(coaches.get(i).getPassword())){
                        loginFlag = true;
                        loginCoach = new Coach();
                        loginCoach.setAccount(accountInfo);
                        loginCoach.setPassword(passwordInfo);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (loginFlag){
            try {
                objectMapper.writeValue(new FileOutputStream("src/main/java/sample/data/LoginStatusCoach.json"), accountInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            application.gotoHome();
            System.out.println(loginCoach.toString());
            return loginCoach;
        }else {
            loginStatus.setText("Wrong Password or not existed coach");
        }
        return null;
    }

    public void setApp(Main application){
        this.application = application;
    }

}
