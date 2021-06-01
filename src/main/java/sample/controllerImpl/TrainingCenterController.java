package sample.controllerImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;
import sample.Main;
import sample.controller.GetLoginUserable;
import sample.pojo.User;
import sample.pojo.VideoRecord;
import sample.utils.CalendarUtils;
import sample.utils.InitTableDataUtil;
import sample.utils.MakeCenterImage;
import sample.utils.MakeTheToggleEffect;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;



/**
 * This class can cooperate with the corresponding FXML file
 * to generate a training center, load the corresponding file
 * to form a unique interface belonging to each user,can help users to master their own learning situation
 *
 * @author Xiaojian Qi
 * @version 2.0
 */
public class TrainingCenterController implements Initializable, GetLoginUserable {

    private Main application;

    @FXML
    private Label username;
    @FXML
    private Label trainingtime;
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
    private ArrayList<GridPane> planeList;

    public int getNowPlaneIndex() {
        return nowPlaneIndex;
    }

    private int nowPlaneIndex;


    @FXML
    private BarChart<String, Number> dailyTraining;
    @FXML
    private CategoryAxis dailytrainingx;
    @FXML
    private NumberAxis dailytrainingy;
    @FXML
    private ProgressBar hipTraining;
    @FXML
    private Label hipProgressIndicator;
    @FXML
    private ProgressBar yoga;
    @FXML
    private Label yogaProgressIndicator;
    @FXML
    private ProgressBar legTraining;
    @FXML
    private Label legProgressIndicator;
    @FXML
    private ProgressBar hiit;
    @FXML
    private Label hiitProgressIndicator;
    @FXML
    private ProgressBar recipe;
    @FXML
    private Label recipeProgressIndicator;
    @FXML
    private ProgressBar abdominalTraining;
    @FXML
    private Label abdominalProgressIndicator;
    @FXML
    private GridPane resetBtnBox;
    @FXML
    private Button resetbtn;
    private String loginUserId;
    private User loginUserNow;
    private List<VideoRecord> videoRecordList;
    private List<VideoRecord> videoRecordListAll;
    private List<Integer> watchTimeList;




//    Carousel tools
    private MakeTheToggleEffect makeTheToggleEffect;



    /**
     * Read the user's video viewing history from videoRecord.json
     */
    public void readVideoRecord(){
        videoRecordListAll = new ArrayList<VideoRecord>();
        videoRecordList = new ArrayList<VideoRecord>();
        File fileVideoRecord = new File("src/main/java/sample/data/videoRecord.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            videoRecordListAll = mapper.readValue(fileVideoRecord, new TypeReference<List<VideoRecord>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (VideoRecord videoRecord : videoRecordListAll) {
            if (videoRecord.getUserId().equals(loginUserId)){
                videoRecordList.add(videoRecord);
            }
        }
    }


    /**
     * Initialize all layers in the progress bar and generate the data in these progress bar
     */
    // Initialize all layers in the progress bar
    public void planeListFill(){
        planeList = new ArrayList<GridPane>();
//        yoga recipe
        planeList.add(plane0);
//        abdominalTraining hiit
        planeList.add(plane1);
//        leg hip
        planeList.add(plane2);
        fillProgressBar();
        nowPlaneIndex = planeList.size();
    }

    public void fillProgressBar(){
        VideoRecord tempRecord = null;
        double yogaCount = 0;
        double recipeCount = 0;
        double abdominalCount = 0;
        double legCount = 0;
        double hipCount = 0;
        double hiitCount = 0;
        for (int i = 0; i < videoRecordList.size(); i++) {
            tempRecord = videoRecordList.get(i);
            if(tempRecord.getVideoSort().equals("yoga")){
                yogaCount++;
            }else if (tempRecord.getVideoSort().equals("Recipe")){
                recipeCount++;
            }else if (tempRecord.getVideoSort().equals("leg training")){
                legCount++;
            }else if (tempRecord.getVideoSort().equals("abdominal training")){
                abdominalCount++;
            }else if (tempRecord.getVideoSort().equals("Hip training")){
                hipCount++;
            }else if (tempRecord.getVideoSort().equals("HIIT")){
                hiitCount++;
            }
        }
//        填充进度条

        yoga.setProgress(yogaCount/5);
        yogaProgressIndicator.setText(String.format("%.1f", yogaCount / 5 * 100)+"%");
        abdominalTraining.setProgress(abdominalCount/7);
        abdominalProgressIndicator.setText(String.format("%.1f", abdominalCount / 7 * 100)+"%");
        hiit.setProgress(hiitCount/6);
        hiitProgressIndicator.setText(String.format("%.1f", hiitCount / 6 * 100)+"%");
        hipTraining.setProgress(hipCount/4);
        hipProgressIndicator.setText(String.format("%.1f", hipCount / 4 * 100)+"%");
        legTraining.setProgress(legCount/5);
        legProgressIndicator.setText(String.format("%.1f", legCount / 5 * 100)+"%");
        recipe.setProgress(recipeCount/4);
        recipeProgressIndicator.setText(String.format("%.1f", recipeCount / 4 * 100)+"%");
    }


    /**
     * Function to be triggered when the left button is clicked
     */
    //
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

    /**
     * Function to be triggered when the right button is clicked
     */
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

    /**
     * Jump to the course booking interface.
     * Determine whether the currently logged-in user is a VIP, if yes, then jump, if not, jump to the VIP purchase page.
     */
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
    /**
     * Button-click event handler,Jump to home frame.
     */
    public void gotoHome(){
        application.gotoHome();
    }
    /**
     * Button-click event handler,Jump to video-center frame.
     */
    public void gotoVideoCenter(){
        application.gotoVideoCenter();
    }

    /**
     * Button-click event handler,Jump to information-center frame.
     */
    public void gotoInformationCenter(){
        application.gotoInformationCenter();
    }
    /**
     * Button-click event handler,Jump to VIP-Recharge frame.
     */
    public void gotoVIPRecharge(){
        application.gotoVIPRechargeCenter();
    }

    /**
     * Initialize a whole table of data and generate a corresponding table to
     * record the training situation for a week, and update it every Monday
     */
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
        dataSetInjection.add(new XYChart.Data<String, Number>("mon", loginUserNow.getTrainingTimeInMon()));
        dataSetInjection.add(new XYChart.Data<String, Number>("tue", loginUserNow.getTrainingTimeInTue()));
        dataSetInjection.add(new XYChart.Data<String, Number>("wed", loginUserNow.getTrainingTimeInWed()));
        dataSetInjection.add(new XYChart.Data<String, Number>("thu", loginUserNow.getTrainingTimeInThu()));
        dataSetInjection.add(new XYChart.Data<String, Number>("fri", loginUserNow.getTrainingTimeInFri()));
        dataSetInjection.add(new XYChart.Data<String, Number>("sat", loginUserNow.getTrainingTimeInSat()));
        dataSetInjection.add(new XYChart.Data<String, Number>("sun", loginUserNow.getTrainingTimeInSun()));
//        Initialize the x-axis coordinate content
        ArrayList<String> weekly = new ArrayList<String>();
        weekly.add("mon");
        weekly.add("tue");
        weekly.add("wed");
        weekly.add("thu");
        weekly.add("fri");
        weekly.add("sat");
        weekly.add("sun");
        ObservableList<XYChart.Series<String, Number>> dailyTrainingDataSet = initTableDataUtil.initTable(dailytrainingy,dailytrainingx,dataSetInjection,dataSetSeries1,dataSet,weekly);
        dailyTraining.setData(dailyTrainingDataSet);
    }


    /**
     * The initialize process of the front-end frame, initialize all the modules here
     * and do some user authorization here. The main job here is to Obtain the login
     * status of the user, register the corresponding tool classes, initialize the
     * system of tables and rotatinggraphs, initialize the data of the progress bar,
     * initialize the user name and the number of days the user has not watched the video
     *
     * @param url Extend from the interface.
     * @param resourceBundle Extend from the interface.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        获得登录的用户状态
        getLoginStatus();
        try {
            initUserTrainingTimeInWeek();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        username.setText(loginUserNow.getUsername());
//        初始化进度条数据
        readVideoRecord();
        File file = new File("src/main/java/sample/pic/example1.jpg");
        String path = file.toURI().toString();
//        new utils
        MakeCenterImage makeCenterImage = new MakeCenterImage();
        userhead.setClip(makeCenterImage.makeCenterImageCircle(100, userhead, path));
//        初始化轮播工具类
        makeTheToggleEffect = new MakeTheToggleEffect();
//        初始化轮播图List
        planeListFill();
//        初始化设置左移按钮失效
        toleft.setDisable(true);
        initTable();
//        Init reset btn
        FontIcon resetIcon = new FontIcon("fa-refresh");
        resetIcon.setIconSize(35);
        resetIcon.setIconColor(Color.ORANGE);
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.seconds(1.5));
        rotateTransition.setNode(resetIcon);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.play();
        resetBtnBox.add(resetIcon,0,0);
    }



    public void setApp(Main application) {
        this.application = application;
    }


    /**
     * Read the LoginStatus.json file,get the current user of the system.
     */
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

    /**
     * Based on the current logged in user, set the value of the Label to the number of days the user has left
     */
    public void initUserTrainingTimeInWeek() throws ParseException {
//        读取user文件
        File fileLoginStatus = new File("src/main/java/sample/data/User.json");
        ObjectMapper mapper = new ObjectMapper();
        List<User> loginUserList = new ArrayList<User>();
        try {
            loginUserList = mapper.readValue(fileLoginStatus, new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (User user : loginUserList) {
            if (user.getUsername().equals(loginUserId)){
                loginUserNow = user;
            }
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        trainingtime.setText(String.valueOf(CalendarUtils.differentDays(dateFormat.parse(loginUserNow.getLastStudyDate()),new Date())));
    }


    /**
     * Button-click event handler,Jump to order-list frame.
     */
    public void gotoOrderList() {
        application.gotoOrderList();
    }

    public void resetProgress(){
        File fileVideoRecord = new File("src/main/java/sample/data/videoRecord.json");
        ObjectMapper mapper = new ObjectMapper();
        videoRecordListAll.removeIf(videoRecord -> videoRecord.getUserId().equals(loginUserId));
        try {
            OutputStream outputStream = new FileOutputStream(fileVideoRecord);
            mapper.writeValue(outputStream,videoRecordListAll);
        } catch (IOException e) {
            e.printStackTrace();
        }
        videoRecordList.clear();
        fillProgressBar();
    }


}
