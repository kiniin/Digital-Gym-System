package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.controllerImpl.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
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

    public void gotoHome() {
        try {
            HomeController home = (HomeController) replaceContentScene("fxml/homepage.fxml");
            home.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoTrainingCenter() {
        try {
            TrainingCenterController usercenter = (TrainingCenterController) replaceContentScene("fxml/TrainingCenter.fxml");
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
            VideoCenterController videoCenter = (VideoCenterController) replaceContentScene("fxml/videoList.fxml");
            videoCenter.setApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoVideo() {
        try {
            VideoController video = (VideoController) replaceContentScene("fxml/video.fxml");
            video.setApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoInformationCenter() {
        try {
            InformationController informationController = (InformationController) replaceContentScene("fxml/informationCenter.fxml");
            informationController.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void gotoOrderList() {
        try {
            OrderListController orderList = (OrderListController) replaceContentScene("fxml/orderList.fxml");
            orderList.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void gotoAboutUs() {
        try {
            AboutUsController aboutUs = (AboutUsController) replaceContentScene("fxml/aboutUs.fxml");
            aboutUs.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoAboutUs(String id) {
        try {
            AboutUsController aboutUs = (AboutUsController) replaceContentScene("fxml/aboutUs.fxml");
            aboutUs.setLabel(id);
            aboutUs.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoCoachLogin(){
        try {
            CoachLoginController coach = (CoachLoginController) replaceContentScene("fxml/coachLogin.fxml");
            coach.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoAdminLogin(){
        try {
            AdminLoginController admin = (AdminLoginController) replaceContentScene("fxml/adminLogin.fxml");
            admin.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoCoachCenter(){
        try {
            CoachCenterController coachCenter = (CoachCenterController) replaceContentScene("fxml/coachCenter.fxml");
            coachCenter.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void gotoCoachAllOrderList(){
        try {
            CoachAllOrderController coachCenter = (CoachAllOrderController) replaceContentScene("fxml/coachAllOrder.fxml");
            coachCenter.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void gotoVIPRechargeCenter(){
        try {
            VIPRechargeCenterController vipRechargeCenterController = (VIPRechargeCenterController) replaceContentScene("fxml/VIPrechargeCenter.fxml");
            vipRechargeCenterController.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void gotoUserMangement(){
        try {
            UserManagementController userManagementController = (UserManagementController) replaceContentScene("fxml/userManagement.fxml");
            userManagementController.setApp(this);
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
}
