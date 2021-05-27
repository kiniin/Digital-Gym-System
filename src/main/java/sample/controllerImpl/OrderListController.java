package sample.controllerImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sample.Main;
import sample.controllerImpl.orderListComponent.OrderListComponent;
import sample.pojo.Arrange;
import sample.utils.MakeCenterImage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderListController implements Initializable {
    private Main application;
    private String loginUserId;

    @FXML
    private VBox orderListComponentsBox;

    public void gotoBookingCenter() {
        application.gotoBooking();
    }

    public void gotoHome() {
        application.gotoHome();
    }

    public void gotoVideoCenter() {
        application.gotoVideoCenter();
    }

    public void gotoTrainingCenter() {
        application.gotoTrainingCenter();
    }

    public void gotoInformationCenter() {
        application.gotoInformationCenter();
    }

    public void setApp(Main application) {
        this.application = application;
    }

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
                OrderListComponent orderListComponent = new OrderListComponent(temp.getTime(), temp.getLocation(), temp.getCoach(), temp.getItem(), temp.getDate());
                orderListComponents.addColumn(0,orderListComponent);
            }
        }
    }

    public void holdLoginStatus() {
        File fileLoginStatus = new File("src/main/java/sample/data/LoginStatus.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            loginUserId = mapper.readValue(fileLoginStatus, new TypeReference<String>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        holdLoginStatus();
        try {
            addRecord();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
