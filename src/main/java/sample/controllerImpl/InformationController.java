package sample.controllerImpl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import sample.Main;
import sample.utils.MakeCenterImage;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class InformationController implements Initializable {

    private Main application;

    @FXML
    private Label currentDateLabel;
    private String currentDate;

    @FXML
    private ImageView userhead;
    @FXML
    private Label username;
    @FXML
    private PasswordField password;
    private String passwordStr;



    public void gotoBookingCenter(){
        application.gotoBooking();
    }
    public void gotoHome(){
        application.gotoHome();
    }
    public void gotoVideoCenter(){
        application.gotoVideoCenter();
    }
    public void gotoTrainingCenter() {
        application.gotoTrainingCenter();
    }
    public void gotoInformationCenter(){
        application.gotoInformationCenter();
    }

    public void setApp(Main application){
        this.application = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        currentDate = dateFormat.format(new Date());
        currentDateLabel.setText(currentDate);
        File file = new File("src/main/java/sample/pic/example1.jpg");
        String path = file.toURI().toString();
//        new utils
        MakeCenterImage makeCenterImage = new MakeCenterImage();
        userhead.setClip(makeCenterImage.makeCenterImageCircle(130, userhead, path));
    }
}
