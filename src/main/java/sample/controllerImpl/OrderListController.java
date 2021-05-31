package sample.controllerImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sample.Main;
import sample.controller.GetLoginUserable;
import sample.controllerImpl.orderListComponent.OrderListComponent;
import sample.pojo.Arrange;
import sample.pojo.User;
import sample.utils.MakeCenterImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


/**
 * According to the componentized order list, each order can
 * be registered so that the user can see all their personal
 * education course information
 *
 * @author Xiaojian Qi
 * @iteration 2.0
 */
public class OrderListController implements Initializable, GetLoginUserable {
    private Main application;
    private String loginUserId;

    @FXML
    private VBox orderListComponentsBox;

    /**
     * Button-click event handler,Jump to VIP-Recharge frame.
     */
    public void gotoVIPRecharge(){
        application.gotoVIPRechargeCenter();
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
     * Button-click event handler,Jump to home frame.
     */
    public void gotoHome() {
        application.gotoHome();
    }


    /**
     * Button-click event handler,Jump to video-center frame.
     */
    public void gotoVideoCenter() {
        application.gotoVideoCenter();
    }

    /**
     * Button-click event handler,Jump to training-center frame.
     */
    public void gotoTrainingCenter() {
        application.gotoTrainingCenter();
    }

    /**
     * Button-click event handler,Jump to information-center frame.
     */
    public void gotoInformationCenter() {
        application.gotoInformationCenter();
    }

    /**
     * Button-click event handler,Jump to order-list frame.
     */
    public void gotoOrderList(){
        application.gotoOrderList();
    }

    public void setApp(Main application) {
        this.application = application;
    }

    /**
     * Iterate through the Arrangement.json to find all of
     * the scheduled classes for this user
     * @exception IOException
     */
    public void addRecord() throws IOException {
        GridPane orderListComponents = new GridPane();
        orderListComponentsBox.getChildren().add(orderListComponents);
        ObjectMapper objectMapper = new ObjectMapper();
        File fileArrage = new File("src/main/java/sample/data/Arrangement.json");
        List<Arrange> listArrange = objectMapper.readValue(fileArrage, new TypeReference<List<Arrange>>() {
        });
        for (int i = 0; i < listArrange.size(); i++) {
            Arrange temp = listArrange.get(i);
            // condition matches
            if (temp.getUserId().equals(loginUserId)){
                System.out.println(temp.getLocation());
                OrderListComponent orderListComponent = new OrderListComponent(temp.getTime(), temp.getLocation(), temp.getCoach(), temp.getItem(), temp.getDate(),"Coach Name");
                orderListComponents.addColumn(0,orderListComponent);
            }
        }
    }

    /**
     * The initialize process of the front-end frame, initialize all the modules here
     * and do some user authorization here. The main job here is to Get the username of
     * the current logged-in user, search for all his personal training courses,
     * and load them into the page with the componentized components
     *
     * @param location Extend from the interface.
     * @param resources Extend from the interface.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getLoginStatus();
        try {
            addRecord();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the LoginStatus.json file,get the current user of the system.
     */
    @Override
    public void getLoginStatus() {
        File fileLoginStatus = new File("src/main/java/sample/data/LoginStatus.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            loginUserId = mapper.readValue(fileLoginStatus, new TypeReference<String>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
