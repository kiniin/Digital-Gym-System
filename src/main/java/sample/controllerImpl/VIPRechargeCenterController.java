package sample.controllerImpl;

import javafx.application.Application;
import javafx.fxml.Initializable;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class VIPRechargeCenterController implements Initializable {

    private Main application;

    public void setApp(Main application){
        this.application = application;
    }

    public void gotoBookingCenter() {
        application.gotoBooking();
    }

    public void gotoTrainingCenter() {
        application.gotoTrainingCenter();
    }

    public void gotoHome() {
        application.gotoHome();
    }

    public void gotoVideoCenter() {
        application.gotoVideoCenter();
    }

    public void gotoInformationCenter() {
        application.gotoInformationCenter();
    }
    public void gotoOrderList(){
        application.gotoOrderList();
    }
    public void toBeVIP(){

    }
    public void gotoVIPRechargeCenter(){
        application.gotoVIPRechargeCenter();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
