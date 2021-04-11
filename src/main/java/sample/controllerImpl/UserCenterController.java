package sample.controllerImpl;

import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import sample.Main;
import sample.utils.MakeCenterImage;
import sample.utils.MakeTheToggleEffect;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void initTable(){
        dailyTraining.setCategoryGap(10);
        dailytrainingy.setLowerBound(0);
        dailytrainingy.setUpperBound(600);
        dailytrainingy.setTickUnit(20);
        ArrayList<String> weekly = new ArrayList<String>();
        weekly.add("mon");
        weekly.add("tues");
        weekly.add("wed");
        weekly.add("thr");
        weekly.add("fri");
        weekly.add("sat");
        weekly.add("sun");
        ObservableList<String> categoriesOfDate = FXCollections.observableArrayList(weekly);
        dailytrainingx.setCategories(categoriesOfDate);

//        dataset -> series -> Data(List) -> data
        ObservableList<XYChart.Series<String,Number>> dataSet = FXCollections.observableArrayList();
        XYChart.Series<String,Number> dataSetSeries1 = new XYChart.Series<String,Number>();
        XYChart.Series<String,Number> dataSetSeries2 = new XYChart.Series<String, Number>();
        dataSetSeries1.setName("Training Time");
        dataSetSeries2.setName("Video Time");
//        ObservableList<XYChart.Data<String,Number>> dataSetData = FXCollections.observableArrayList();
        XYChart.Data<String,Number> d1 = new XYChart.Data<String,Number>("mon",100);
        XYChart.Data<String,Number> d2 = new XYChart.Data<String,Number>("mon",200);
        XYChart.Data<String,Number> d3 = new XYChart.Data<String,Number>("wed",120);
        XYChart.Data<String,Number> d4 = new XYChart.Data<String,Number>("wed",160);

        dataSetSeries1.getData().add(d1);
        dataSetSeries2.getData().add(d2);
        dataSetSeries1.getData().add(d3);
        dataSetSeries2.getData().add(d4);

        dataSet.add(dataSetSeries1);
        dataSet.add(dataSetSeries2);
        dailyTraining.setData(dataSet);
        System.out.println(dailytrainingx.getCategorySpacing());
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
