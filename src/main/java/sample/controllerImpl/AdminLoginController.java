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

/**
 * The controller is used to control the AdminLogin interface, and check the login of admins
 *
 * @author Tenghao Su
 * @version 3.0
 *
 */
public class AdminLoginController implements Initializable, ReadTextFieldable {
    private Main application;

    @FXML
    private TextField account;
    @FXML
    private PasswordField password;
    @FXML
    private Label loginStatus;

    private Admin loginAdmin;

    /**
     * Jump to the AboutUs page
     */
    public void gotoAboutUs() { application.gotoAboutUs(); }

    /**
     * Initialize AdminLogin page
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Read the account and the password entered by Admins.
     * Verify the matching of account and password.
     * if they are consistent, the login is successful and jump to AdminCenter,
     * if they are inconsistent, an error prompt will be given.
     *
     * @return Admin (return the admin object with successful login)
     */
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
    /**
     * Combine this frame with the javafx main function.
     * @param application This javafx application.
     */
    public void setApp(Main application){
        this.application = application;
    }

}
