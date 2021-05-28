package sample.controllerImpl;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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
    private GridPane headBox;

    @FXML
    private Button signOutBtn;

    public void gotoCoachLogin(){ application.gotoCoachLogin(); }

    public void gotoAdminLogin(){ application.gotoAdminLogin(); }

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
