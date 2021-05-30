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
import sample.pojo.Coach;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CoachAllOrderController implements Initializable {

    private Main application;
    private String loginCoachName;

    @FXML
    private VBox orderListComponentsBox;

    public void gotoAboutUs() {
        application.gotoAboutUs("coach");
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
            // condition matches, 这里写登录的教练名, userId not empty
            if (temp.getCoach().equals(loginCoachName)){
                System.out.println("111:"+ temp.getLocation());
                OrderListComponent orderListComponent = new OrderListComponent(temp.getTime(), temp.getLocation(), temp.getUserId(), temp.getItem(), temp.getDate(),"User ID");
                orderListComponents.addColumn(0,orderListComponent);
            }
        }
    }

    public void holdLoginStatus() {
        File fileCoachLoginStatus = new File("src/main/java/sample/data/LoginStatus.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            // loginCoachName = mapper.readValue(fileCoachLoginStatus, new TypeReference<String>() {});
            BufferedReader in = new BufferedReader(new FileReader("src/main/java/sample/data/LoginStatusCoach.json"));
            String loginCoachAcc;
            if ((loginCoachAcc = in.readLine()) != null){
                loginCoachAcc= loginCoachAcc.replace("\"", "");
                ObjectMapper objectMapper = new ObjectMapper();
                File coach = new File("src/main/java/sample/data/Coach.json");
                List<Coach> coaches = objectMapper.readValue(coach, new TypeReference<List<Coach>>() {
                });
                for(int i=0;i<coaches.size();i++){
                    if(coaches.get(i).getAccount().equals(loginCoachAcc)){
                        loginCoachName = coaches.get(i).getName();
                    }
                }
            }

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
