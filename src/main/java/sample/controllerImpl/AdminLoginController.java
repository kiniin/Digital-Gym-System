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
import sample.pojo.Admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminLoginController implements Initializable, ReadTextFieldable {
    private Main application;

    @FXML
    private TextField account;
    @FXML
    private PasswordField password;
    @FXML
    private Label loginStatus;

    private Admin loginAdmin;


    public void gotoAboutUs() { application.gotoAboutUs(); }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public Admin readTextField() {
        boolean loginFlag = false;
        String accountInfo = account.getText();
        String passwordInfo = password.getText();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/java/sample/data/Admin.json");

        try {
            List<Admin> admins = objectMapper.readValue(file, new TypeReference<List<Admin>>(){});
            for (int i = 0; i < admins.size(); i++){
                if (accountInfo.equals(admins.get(i).getAccount())){
                    if (passwordInfo.equals(admins.get(i).getPassword())){
                        loginFlag = true;
                        loginAdmin = new Admin();
                        loginAdmin.setAccount(accountInfo);
                        loginAdmin.setPassword(passwordInfo);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (loginFlag){
            application.gotoUserMangement();
            System.out.println(loginAdmin.toString());
            return loginAdmin;
        }else {
            loginStatus.setText("Wrong Password or not existed coach");
        }
        return null;
    }

    public void setApp(Main application){
        this.application = application;
    }

}
