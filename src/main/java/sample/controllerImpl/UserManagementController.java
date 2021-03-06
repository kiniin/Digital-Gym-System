package sample.controllerImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import sample.Main;
import sample.pojo.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The controller is used to control the UserManagement interface,
 * and show the information of all the users of this application
 *
 * @author Tenghao Su
 * @version 3.0
 *
 */
public class UserManagementController implements Initializable {

    private Main application;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User,String> userName;

    @FXML
    private TableColumn<User,String> gender;

    @FXML
    private TableColumn<User,String> phone;

    @FXML
    private TableColumn<User,String> email;

    @FXML
    private TableColumn<User,String> VIP;

    /**
     * Jump to the aboutUs page with the parameter "admin"
     */
    public void gotoAboutUs(){ application.gotoAboutUs("admin"); }


    /**
     * Assign a value to each TableColumn in the TableView to display user information
     * Information includes username, gender, VIP, phone number and email
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/java/sample/data/User.json");
        try {
            List<User> usersList = objectMapper.readValue(file, new TypeReference<List<User>>(){});
            ObservableList<User> users = FXCollections.observableList(usersList);

            userTable.setItems(users);
            userName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                    SimpleStringProperty name = new SimpleStringProperty(param.getValue().getUsername());
                    return name;
                }
            });
            gender.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                    SimpleStringProperty gender = new SimpleStringProperty(param.getValue().getGender());
                    return gender;
                }
            });
            phone.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                    SimpleStringProperty phone = new SimpleStringProperty(param.getValue().getPhone());
                    return phone;
                }
            });
            email.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                    SimpleStringProperty email = new SimpleStringProperty(param.getValue().getEmail());
                    return email;
                }
            });
            VIP.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                    SimpleStringProperty VIP = new SimpleStringProperty(param.getValue().getVip());
                    return VIP;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Combine this frame with the javafx main function.
     * @param application This javafx application.
     */
    public void setApp(Main application){
        this.application = application;
    }
}
