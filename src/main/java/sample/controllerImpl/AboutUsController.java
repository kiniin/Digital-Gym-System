package sample.controllerImpl;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;
import sample.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutUsController implements Initializable {

    private Main application;

    @FXML
    private Hyperlink coachLogin;

    @FXML
    private Hyperlink adminLogin;

    @FXML
    private AnchorPane headBox;

    @FXML
    private Label identification;

    @FXML
    private Button signOutBtn;

    @FXML
    private Label login;

    public void setLabel(String id){
        identification.setText(id);
        if (id.equals("Label")){
            login.setVisible(false);
        }else{
            coachLogin.setVisible(false);
            adminLogin.setVisible(false);
            if (id.equals("admin")){
                login.setText("Hello! Dear Admin");
            }else if (id.equals("coach")) {
                login.setText("Hello! Dear Coach");
            }
            login.setVisible(true);
        }
    }

    public void gotoCoachLogin(){ application.gotoCoachLogin(); }

    public void gotoAdminLogin(){ application.gotoAdminLogin(); }

    public void gotoCenter(){
        String id = identification.getText();
        if (id.equals("Label")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please login first!");
            alert.showAndWait();
        }else{
            if (id.equals("admin")){
                application.gotoUserMangement();
            }else if (id.equals("coach")){
                application.gotoCoachCenter();
            }
        }
    }

    public void initExitBtn(){
        FontIcon homeIcon = new FontIcon("fa-sign-out");
        homeIcon.setIconSize(30);
        homeIcon.setIconColor(new Color(1,1,1,1));
        signOutBtn.setGraphic(homeIcon);
        signOutBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                homeIcon.setIconColor(Color.web("#dcd7d7"));
            }
        });
        signOutBtn.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                homeIcon.setIconColor(Color.web("#a59d9d"));
            }
        });
        signOutBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                homeIcon.setIconColor(Color.web("#ffffff"));
            }
        });
    }
    public void signOut(){
        File coachStatusFile =new File("src/main/java/sample/data/LoginStatusCoach.json");
        try {
            FileWriter fileWriter =new FileWriter(coachStatusFile);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
            System.out.println("file Clear!");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        可能出现的管理员登出操作
//        File adminStatusFile =new File("src/main/java/sample/data/LoginStatusCoach.json");
//        try {
//            FileWriter fileWriter =new FileWriter(coachStatusFile);
//            fileWriter.write("");
//            fileWriter.flush();
//            fileWriter.close();
//            System.out.println("file Clear!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        application.gotoAboutUs();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initExitBtn();
    }

    public void setApp(Main application){
        this.application = application;
    }
}
