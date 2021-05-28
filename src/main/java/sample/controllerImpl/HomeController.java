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

    public void gotoLogin(){
        application.gotoLogin();
    }
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
                str= str.replace("\"", "");
                helloUser.setText("Hello! User "+str);
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
