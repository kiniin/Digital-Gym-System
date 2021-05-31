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

/**
 * The class is to search and show the history orders and classes of a coach.
 * It can user Java IO and Jackson to search and analyze the file, match the
 * chosen coach's name, and then search the coach's courses information on
 * Arrangement.json file. And then, show it on javafx front-end pages.
 *
 * @author Ruizheng Wu
 * @author Xiaojian Qi
 * @iteration 3.0
 */
public class CoachAllOrderController implements Initializable {

    private Main application;
    private String loginCoachName;

    @FXML
    private VBox orderListComponentsBox;

    /**
     * Button-click event handler,jump to aboutUs frame.
     */
    public void gotoAboutUs() {
        application.gotoAboutUs("coach");
    }

    /**
     * Button-click event handler,jump to coach-center frame.
     */
    public void gotoCoachCenter() {
        application.gotoCoachCenter();
    }

    /**
     * Button-click event handler,jump to Coach's order list frame.
     */
    public void gotoCoachAllOrderList(){
        application.gotoCoachAllOrderList();
    }

    /**
     * Combine this frame with the javafx application.
     * @param application The javafx project.
     */
    public void setApp(Main application) {
        this.application = application;
    }

    /**
     * Read the Arrangement.json file, and put the matched information into the order module.
     * Use jackson to map the information to a List of Class:Arrange,
     * then search the list by coach's name, if the condition matches,
     * generate a record of the class, then show the information on the front-end page.
     *
     * @throws IOException Exception throwed when there're some errors in file reading and writing.
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
            // condition matches, 这里写登录的教练名, userId not empty
            if (temp.getCoach().equals(loginCoachName)){
                OrderListComponent orderListComponent = new OrderListComponent(temp.getTime(), temp.getLocation(), temp.getUserId(), temp.getItem(), temp.getDate(),"User ID");
                orderListComponents.addColumn(0,orderListComponent);
            }
        }
    }

    /**
     * Get the current coach login status by checking the file.
     * Read the current login coach's account from a file, then
     * use Jackson to map the information to a List of Class:Coach,
     * matching the login status.
     */
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

    /**
     * The lifecycle function of javafx.Add record the order module into the plane.
     *
     * @param location
     * @param resources
     */
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
