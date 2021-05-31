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

/**
 * The controller show information about the gym,
 * and be as the entrance of admin and coaches.
 * Coaches and admins can find the login & management center
 * here, login or sign out the system.
 *
 * @author Tenghao Su
 * @author Ruizheng Wu
 * @author Xiaojian Qi
 * @iteration 5.0
 */
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

    /**
     * Judge the current user of the system, show the correct text on the page.
     * If no one logins, hide the label.
     * If the current one is admin, show "Dear admin"
     * If the current one is coach, show “Dear coach"
     *
     * @param id The text that the front-end page shows.
     */
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

    /**
     * Button-click event handler,Jump to coach login frame.
     */
    public void gotoCoachLogin(){ application.gotoCoachLogin(); }

    /**
     * Button-click event handler,Jump to admin login frame.
     */
    public void gotoAdminLogin(){ application.gotoAdminLogin(); }

    /**
     * Button-click event handler,check the login status and jump to some page.
     * Check the login status, if no login, ask the user to
     * login first and jump to login pages.
     */
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

    /**
     * Initialize the exit button after the user login.
     * Do some basic settings on styles, register that javafx component.
     */
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

    /**
     * Sign out the current user, clear the file that record the login status.
     */
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
        application.gotoAboutUs();
    }

    /**
     * Initialize VIPRechargeCenter page
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initExitBtn();
    }

    /**
     * Combine this frame with the javafx main function.
     * @param application This javafx application.
     */
    public void setApp(Main application){
        this.application = application;
    }
}
