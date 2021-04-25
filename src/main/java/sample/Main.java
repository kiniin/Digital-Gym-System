package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.controllerImpl.*;

import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("FXML Login Sample");
        gotoHome();
        stage.show();
    }

    public void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceContentScene("fxml/login.fxml");
            login.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoSignup() {
        try {
            SignUpController signUp = (SignUpController) replaceContentScene("fxml/signup.fxml");
            signUp.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoStaffEntry(){
        try {
            StaffLoginController staffEntry = (StaffLoginController) replaceContentScene("fxml/staffLogin.fxml");
            staffEntry.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoHome(){
        try {
            HomeController home = (HomeController) replaceContentScene("fxml/homepage.fxml");
            home.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void gotoStaffSignup(){
        try {
            SignUpController staffSignup = (SignUpController) replaceContentScene("fxml/staffSignup.fxml");
            staffSignup.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoUserCenter(){
        try {
            UserCenterController usercenter = (UserCenterController) replaceContentScene("fxml/usercenter.fxml");
            usercenter.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoBooking() {
        try {
            BookingController booking = (BookingController) replaceContentScene("fxml/booking.fxml");
            booking.setApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoVideoCenter() {
        try {
            VideoController video = (VideoController) replaceContentScene("fxml/video.fxml");
            video.setApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoModifyInformation() {
        try {
            DataManagerController modifyInfo = (DataManagerController) replaceContentScene("fxml/modifyInfo.fxml");
            modifyInfo.setApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoManageVideos() {
        try {
            DataManagerController manageVD = (DataManagerController) replaceContentScene("fxml/manageVD.fxml");
            manageVD.setApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoManageInventory() {
        try {
            DataManagerController inventory = (DataManagerController) replaceContentScene("fxml/inventory.fxml");
            inventory.setApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 跳转的工具方法
    private Initializable replaceContentScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        // 拿到fxml文件的输入流(利用反射让Main动态获取fxml文件（ps：如果写一个类需要动态的获
        // 取某个文件的位置，从而能够获取此文件的资源。那么，使用Class.getResourceAsStream()
        // 方法便可以。）
        InputStream in = Main.class.getResourceAsStream(fxml);
        // 加载BuilderFactory
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        // 设置基准路径
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.setMinHeight(721);
            stage.setMinWidth(1066);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        return (Initializable) loader.getController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
