package sample.controllerImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import sample.Main;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * The class is to control the process of booking a existent course.
 * There's an calender module for user to choose a date, and then,
 * based on the date, the controller class will have some methods to search
 * and show the information of all the available coaches on that day. Users
 * can pick one coach, and see all the courses that can be ordered of that coach.
 * User can choose any time when the coach is available.
 * Then, after user ensure the order, the controller can save the order information
 * to a file, by using Jackson.
 *
 * @author Tenghao Su
 * @author Xiaojian Qi
 * @iteration 2.0
 */
public class HomeController implements Initializable {

    private boolean LoginStatus;

    private Main application;

    @FXML
    private Label helloUser;

    @FXML
    private Hyperlink login;

    @FXML
    private Hyperlink signUp;

    @FXML
    private Hyperlink signOut;

    @FXML
    private Hyperlink starttrail;

    @FXML
    private Hyperlink aboutUs;


    /**
     * goto the page of login user
     */
    public void gotoLogin(){
        application.gotoLogin();
    }
    /**
     * goto the page of Sign up user
     */
    public void gotoSignUp(){
        application.gotoSignup();
    }
    public void gotoAboutUs(){ application.gotoAboutUs(); }

    public void gotoUserCenter(){
        if (LoginStatus == false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please login first!");

            alert.showAndWait();
            application.gotoLogin();
        }else{
            application.gotoTrainingCenter();
        }
    }

    /**
     * Clear the file that records the login status
     */
    public void SignOut() {
        File file =new File("src/main/java/sample/data/LoginStatus.json");
        try {
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
            System.out.println("file Clear!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        application.gotoHome();
    }


    /**
     * The initialize process of the front-end frame, initialize all the modules here
     * and do some user authorization here. The main job here is to set login and signup
     * and userName label in different condition.
     *
     * @param url Extend from the interface.
     * @param resourceBundle Extend from the interface.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoginStatus = false;
        helloUser.setVisible(false);
        signOut.setVisited(false);
        signOut.setManaged(false);
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/main/java/sample/data/LoginStatus.json"));
            String str;
            if ((str = in.readLine()) != null){
                login.setVisited(false);
                login.setManaged(false);
                signUp.setVisited(false);
                signUp.setManaged(false);
                helloUser.setText("Hello! Dear User");
                helloUser.setVisible(true);
                signOut.setVisited(true);
                signOut.setManaged(true);
                LoginStatus = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setApp(Main application){
        this.application = application;
    }

}
