package sample.controllerImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import sample.Main;
import sample.utils.InitTableDataUtil;
import sample.utils.MakeCenterImage;
import sample.utils.MakeTheToggleEffect;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserCenterController implements Initializable {

    private Main application;

    @FXML
    private ImageView userhead;

    @FXML
    private Button toleft;
    @FXML
    private Button toright;
    @FXML
    private Hyperlink toBookingCenter;

    //    为了走马灯效果的下方结构
    @FXML
    private GridPane plane0;
    //    为了走马灯效果的上方结构
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


//    轮播工具类
    private MakeTheToggleEffect makeTheToggleEffect;

    public void planeListFill(){
        planeList = new ArrayList<GridPane>();
        planeList.add(plane0);
        planeList.add(plane1);
        planeList.add(plane2);
        planeList.add(plane3);
        nowPlaneIndex = planeList.size();
    }



    public void toggleLeft() {
        System.out.println(nowPlaneIndex);
        if(nowPlaneIndex >= 1){
            for (GridPane gridPane : planeList) {
                gridPane.setVisible(false);
            }
            planeList.get(nowPlaneIndex-1).setVisible(true);
            planeList.get(nowPlaneIndex).setVisible(true);
            nowPlaneIndex = makeTheToggleEffect.toggleLeftUtil(planeList.get(nowPlaneIndex-1),planeList.get(nowPlaneIndex),toright, nowPlaneIndex);
        }
        if (nowPlaneIndex >= planeList.size()){
            toleft.setDisable(true);
        }
    }

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
        application.gotoBooking();
    }
    public void gotoHome(){
        application.gotoHome();
    }

    public void initTable(){
//        初始化建表工具类
        InitTableDataUtil initTableDataUtil = new InitTableDataUtil();
//      初始化series集合
        ObservableList<XYChart.Series<String,Number>> dataSet = FXCollections.observableArrayList();
//      初始化单个series
        XYChart.Series<String,Number> dataSetSeries1 = new XYChart.Series<String,Number>();
        dataSetSeries1.setName("Training Time");
//        初始化注入的数据
        ArrayList<XYChart.Data<String, Number>> dataSetInjection = new ArrayList<XYChart.Data<String, Number>>();
        dataSetInjection.add(new XYChart.Data<String, Number>("mon", 100));
        dataSetInjection.add(new XYChart.Data<String, Number>("wed", 100));
//        初始化x轴坐标内容
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

}
