package sample.controllerImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import sample.Main;
import sample.controller.GetLoginUserable;
import sample.pojo.User;
import sample.utils.InitTableDataUtil;
import sample.utils.MakeCenterImage;
import sample.utils.MakeTheToggleEffect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TrainingCenterController implements Initializable, GetLoginUserable {

    private Main application;

    @FXML
    private ImageView userhead;

    @FXML
    private Button toleft;
    @FXML
    private Button toright;
    @FXML
    private Hyperlink toBookingCenter;

    //    The lower structure for the revolving lantern effect
    @FXML
    private GridPane plane0;
    //    The upper structure for the revolving lantern effect
    @FXML
    private GridPane plane1;
    @FXML
    private GridPane plane2;
    @FXML
    private GridPane plane3;
    private ArrayList<GridPane> planeList;
    private int nowPlaneIndex;


    @FXML
    private BarChart<String, Number> dailyTraining;
    @FXML
    private CategoryAxis dailytrainingx;
    @FXML
    private NumberAxis dailytrainingy;
    private String loginUserId;




//    Carousel tools
    private MakeTheToggleEffect makeTheToggleEffect;


    // Initialize all layers in the progress bar
    public void planeListFill(){
        planeList = new ArrayList<GridPane>();
        planeList.add(plane0);
        planeList.add(plane1);
        planeList.add(plane2);
        planeList.add(plane3);
        nowPlaneIndex = planeList.size();
    }


    // Function to be triggered when the left button is clicked
    public void toggleLeft() {
        System.out.println(nowPlaneIndex);
        if(nowPlaneIndex >= 1){
            for (GridPane gridPane : planeList) {
                gridPane.setVisible(false);
            }
            // Every time, make sure that as long as the current page and the next page
            // are visible, the perspective effect can be avoided
            planeList.get(nowPlaneIndex-1).setVisible(true);
            planeList.get(nowPlaneIndex).setVisible(true);
            nowPlaneIndex = makeTheToggleEffect.toggleLeftUtil(planeList.get(nowPlaneIndex-1),planeList.get(nowPlaneIndex),toright, nowPlaneIndex);
        }
        if (nowPlaneIndex >= planeList.size()){
            // If it exceeds the scope of the list, the click behavior of the button that moves to the left is prevented
            toleft.setDisable(true);
        }
    }
    // Function to be triggered when the right button is clicked
    public void toggleRight() {
        System.out.println(nowPlaneIndex);
        if(nowPlaneIndex <= planeList.size()){
            for (GridPane gridPane : planeList) {
                gridPane.setVisible(false);
            }
            planeList.get(nowPlaneIndex-1).setVisible(true);
            planeList.get(nowPlaneIndex-2).setVisible(true);
            nowPlaneIndex = makeTheToggleEffect.toggleRightUtil(planeList.get(nowPlaneIndex-2),planeList.get(nowPlaneIndex-1),toleft, nowPlaneIndex);
        }
        if (nowPlaneIndex <= 1){
            toright.setDisable(true);
        }
    }

    public void gotoBookingCenter(){
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/java/sample/data/User.json");
        try {
            List<User> users = objectMapper.readValue(file, new TypeReference<List<User>>(){});
            BufferedReader in = new BufferedReader(new FileReader("src/main/java/sample/data/LoginStatus.json"));
            String str = in.readLine();
            str= str.replace("\"", "");
            for (int i = 0; i < users.size(); i++){
                if (str.equals(users.get(i).getUsername())){
                    if (users.get(i).getVip().equals("Normal")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Only VIPs can make appointments with coaches!");
                        alert.showAndWait();
                        application.gotoVIPRechargeCenter();
                    }else {
                        application.gotoBooking();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void gotoHome(){
        application.gotoHome();
    }
    public void gotoVideoCenter(){
        application.gotoVideoCenter();
    }
    public void gotoInformationCenter(){
        application.gotoInformationCenter();
    }
    public void gotoVIPRecharge(){
        application.gotoVIPRechargeCenter();
    }


    public void initTable(){
//        Initialize the table building tool class
        InitTableDataUtil initTableDataUtil = new InitTableDataUtil();
//      Initialize the series collection
        ObservableList<XYChart.Series<String,Number>> dataSet = FXCollections.observableArrayList();
//      Initialize a single series
        XYChart.Series<String,Number> dataSetSeries1 = new XYChart.Series<String,Number>();
        dataSetSeries1.setName("Training Time");
//        Initialize the injected data
        ArrayList<XYChart.Data<String, Number>> dataSetInjection = new ArrayList<XYChart.Data<String, Number>>();
        dataSetInjection.add(new XYChart.Data<String, Number>("mon", 100));
        dataSetInjection.add(new XYChart.Data<String, Number>("wed", 100));
//        Initialize the x-axis coordinate content
        ArrayList<String> weekly = new ArrayList<String>();
        weekly.add("mon");
        weekly.add("tue");
        weekly.add("wed");
        weekly.add("thr");
        weekly.add("fri");
        weekly.add("sat");
        weekly.add("sun");
        ObservableList<XYChart.Series<String, Number>> dailyTrainingDataSet = initTableDataUtil.initTable(dailytrainingy,dailytrainingx,dataSetInjection,dataSetSeries1,dataSet,weekly);
        dailyTraining.setData(dailyTrainingDataSet);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }



    public void setApp(Main application) {
        File file = new File("src/main/java/sample/pic/example1.jpg");
        String path = file.toURI().toString();
//        new utils
        MakeCenterImage makeCenterImage = new MakeCenterImage();
        userhead.setClip(makeCenterImage.makeCenterImageCircle(130, userhead, path));
//        初始化轮播工具类
        makeTheToggleEffect = new MakeTheToggleEffect();
//        初始化轮播图List
        planeListFill();
//        初始化设置左移按钮失效
        toleft.setDisable(true);
        this.application = application;
    }

    @Override
    public void getLoginStatus() {
        File fileLoginStatus = new File("src/main/java/sample/data/LoginStatus.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            loginUserId = mapper.readValue(fileLoginStatus, new TypeReference<String>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoOrderList() {
        application.gotoOrderList();
    }
}
