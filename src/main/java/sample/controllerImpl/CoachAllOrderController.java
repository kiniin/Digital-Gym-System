package sample.controllerImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sample.Main;
import sample.controllerImpl.orderListComponent.OrderListComponent;
import sample.pojo.Arrange;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CoachAllOrderController implements Initializable {

    private Main application;
    private String loginCoachName;

    @FXML
    private VBox orderListComponentsBox;

    public void gotoHome() {
        application.gotoHome();
    }

    public void gotoCoachCenter() {
        application.gotoCoachCenter();
    }
    public void gotoCoachAllOrderList(){
        application.gotoCoachAllOrderList();
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
            // condition matches, 这里写登录的教练名
            if (temp.getCoach().equals("Tom")){
                System.out.println(temp.getLocation());
                OrderListComponent orderListComponent = new OrderListComponent(temp.getTime(), temp.getLocation(), temp.getUserId(), temp.getItem(), temp.getDate(),"User ID");
                orderListComponents.addColumn(0,orderListComponent);
            }
        }
    }

    public void holdLoginStatus() {
        File fileCoachLoginStatus = new File("src/main/java/sample/data/LoginStatus.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            loginCoachName = mapper.readValue(fileCoachLoginStatus, new TypeReference<String>() {
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
