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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VIPRechargeCenterController implements Initializable {

    private Main application;


    public void setApp(Main application){
        this.application = application;
    }

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

    public void gotoTrainingCenter() {
        application.gotoTrainingCenter();
    }

    public void gotoHome() {
        application.gotoHome();
    }

    public void gotoVideoCenter() {
        application.gotoVideoCenter();
    }

    public void gotoInformationCenter() {
        application.gotoInformationCenter();
    }
    public void gotoOrderList(){
        application.gotoOrderList();
    }
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void gotoVIPRechargeCenter(){
        application.gotoVIPRechargeCenter();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
