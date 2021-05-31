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

/**
 * The controller that deal with the login request of Coach.
 * Check the coach's input account and password, compare those
 * with the date saved in the file. If match, allow login.
 * If not, refuse login.
 *
 * @author Tenghao Su
 * @author Ruizheng Wu
 * @iteration 3.0
 */
public class CoachLoginController implements Initializable, ReadTextFieldable {
    private Main application;

    @FXML
    private TextField account;
    @FXML
    private PasswordField password;
    @FXML
    private Label loginStatus;


    private Coach loginCoach;

    /**
     * Button-click event handler,Jump to AboutUs frame.
     */
    public void gotoAboutUs() { application.gotoAboutUs(); }

    /**
     * Initialize VIPRechargeCenter page
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Check the coach's input account and password.
     * Read the Coach.json file, compare the input and the date saved in
     * the file. If mathces, allow the login, and jump to coach center.
     * If not match, alert error information, and refuse the login.
     * @return
     */
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
            application.gotoCoachCenter();
            System.out.println(loginCoach.toString());
            return loginCoach;
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
