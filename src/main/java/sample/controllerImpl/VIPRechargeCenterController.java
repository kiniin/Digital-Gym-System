package sample.controllerImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import sample.Main;
import sample.pojo.User;

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 *The controller is used to control the VIPRechargeCenter interface, and realize the functions including the page jump of the user center and the user VIP purchase
 *
 * @author Tenghao Su
 * @author Xiaojian Qi
 * @iteration 5.0
 *
 */
public class VIPRechargeCenterController implements Initializable {

    private Main application;

    /**
     * Combine this frame with the javafx main function.
     * @param application This javafx application.
     */
    public void setApp(Main application){
        this.application = application;
    }

    /**
     * Jump to the course booking interface.
     * Determine whether the currently logged-in user is a VIP, if yes, then jump, if not, jump to the VIP purchase page.
     */
    public void gotoBookingCenter() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/java/sample/data/User.json");
        try {
            List<User> users = objectMapper.readValue(file, new TypeReference<List<User>>(){});
            BufferedReader in = new BufferedReader(new FileReader("src/main/java/sample/data/LoginStatus.json"));
            String str = in.readLine();
            str= str.replace("\"", "");
            for (int i = 0; i < users.size(); i++){
                if (str.equals(users.get(i).getUsername())){
                    if (users.get(i).getVip().equals("Normal")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Only VIPs can make appointments with coaches!");
                        alert.showAndWait();
                        application.gotoVIPRechargeCenter();
                    }else {
                        application.gotoBooking();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Jump to the TrainingCenter page
     */
    public void gotoTrainingCenter() {
        application.gotoTrainingCenter();
    }
    /**
     * Jump to the Home page
     */
    public void gotoHome() {
        application.gotoHome();
    }
    /**
     * Jump to the VideoCenter page
     */
    public void gotoVideoCenter() {
        application.gotoVideoCenter();
    }
    /**
     * Jump to the User InformationCenter page
     */
    public void gotoInformationCenter() {
        application.gotoInformationCenter();
    }
    /**
     * Jump to the order query page
     */
    public void gotoOrderList(){
        application.gotoOrderList();
    }

    /**
     * After clicking the purchase button, a payment messageDialog pops up and the user level is upgraded to VIP
     */
    public void toBeVIP(){
        try {
            ImageIcon icon;
            icon = new ImageIcon(new URL("http://www.tangxinweb.cn/WechatPayment.jpg"));
            JOptionPane.showMessageDialog(null, null, "Payment", -1, icon);

            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("src/main/java/sample/data/User.json");
            try {
                List<User> users = objectMapper.readValue(file, new TypeReference<List<User>>(){});
                BufferedReader in = new BufferedReader(new FileReader("src/main/java/sample/data/LoginStatus.json"));
                String str = in.readLine();
                str= str.replace("\"", "");
                for (int i = 0; i < users.size(); i++){
                    if (str.equals(users.get(i).getUsername())){
                        users.get(i).setVip("VIP");
                        break;
                    }
                }
                objectMapper.writeValue(new FileOutputStream("src/main/java/sample/data/User.json"), users);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Jump to the VIPRechargeCenter page
     */
    public void gotoVIPRechargeCenter(){
        application.gotoVIPRechargeCenter();
    }

    /**
     *Initialize VIPRechargeCenter page
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
