package sample.controllerImpl;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;
import sample.Main;
import sample.controller.GetLoginUserable;
import sample.pojo.User;
import sample.pojo.Video;
import sample.pojo.VideoRecord;
import sample.simpleMediaPlayer.SimpleMediaPlayer;
import sample.utils.CalendarUtils;
import sample.utils.MakeCenterImage;

/**
 *
 * Initialize the entire player page, including the video player itself, video title,
 * thumb up number, video classification, video required equipment,
 * video required exercise intensity, the entire below all video resources
 * and the function of jumping to other sports items in the video player.
 * At the same time, the user's current learning progress and status
 * can be recorded when playing the video, and feedback can be given
 * in the training center {@link sample.controllerImpl.TrainingCenterController}
 *
 * @author Xiaojian Qi
 * @version 2.0
 */
public class VideoController implements Initializable, GetLoginUserable {

    private Main application;

    @FXML
    private GridPane videoBox;
    @FXML
    private Button backList;
    @FXML
    private VBox videoListBoxParent;
    @FXML
    private GridPane videoSortList;
    private GridPane videoListShowBox;

    private SimpleMediaPlayer player;
    //    加载所有视频类在一个列表中
    private List<Video> videoList;
    //    需要装配的视频列表
    private List<Video> assembleVideoList;
    //  记录当前操作的文件
    private String videoStatus;
    //    记录当前用户的登录状态
    private String loginUserId;
    //    记录视频的观看记录
    private List<VideoRecord> videoRecordList;
    //    确定当前使用者的观看记录
    private VideoRecord videoRecordNow;
    @FXML
    private Label frequency;
    @FXML
    private Label videoSort;
    @FXML
    private Button thumbup;
    private String starNum;
    @FXML
    private Label videoIntro;
    @FXML
    private Label videoTitle;
    @FXML
    private Label instrument;
    @FXML
    private Label starNumLabel;
    private User loginUserNow;
    private List<User> loginUserList;


    /**
     * The initialize process of the front-end frame, initialize all the modules here
     * and do some user authorization here. The main work here is:
     * <ul>
     *     <li>Get all the current users, initialize the login status of the user and store the user's ID.</li>
     *     <li>Obtain all video viewing records of all users, initialize the viewing progress of the current user, and initialize the video progress list of all current users.</li>
     *     <li>Get the currently watched video object and initialize the list containing all the video objects.</li>
     *     <li>Initialize the container for the video list and the container for the video category cover.</li>
     *     <li>Initialize a video player and all the corresponding information components</li>
     * </ul>
     *
     * @param location Extend from the interface.
     * @param resources Extend from the interface.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 初始化所有用户的列表
        loginUserList = new ArrayList<User>();
        // 初始化登陆状态
        getLoginStatus();
        initUserTrainingTimeInWeek();
        // VideoRecordList 的初始化
        videoRecordList = new ArrayList<VideoRecord>();
        // 当前videoRecord 的初始化
        videoRecordNow = new VideoRecord();
        videoRecordNow.setUserId(loginUserId);
        setFont();
        assembleVideoList = new ArrayList<Video>();
        videoListShowBox = new GridPane();
        videoListBoxParent.getChildren().add(videoListShowBox);
        try {
            getVideoStatus();
            readVideoInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assembleVideoListInBox();
        initCoverPageBox();
        try {
            initVideoMedia();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setApp(Main application) {
        this.application = application;
    }


    /**
     * Initialize the video player, set the size of the player at the same time,
     * add the size responsive adjustment function, initialize the video introduction,
     * the initial user's login status, judge whether to update the weekly training time according
     * to the date and week, and write the modified user
     */
    public void initVideoMedia() throws IOException, ParseException {
        player = SimpleMediaPlayer.newInstance(assembleVideoList.get(0).getVideoUrl(), 693, 200);
        videoRecordNow.setVideoId(assembleVideoList.get(0).getVideoId());
        videoRecordNow.setVideoSort(assembleVideoList.get(0).getSort());
        videoBox.getChildren().add(player);
        GridPane.setValignment(player, VPos.CENTER);
        GridPane.setHalignment(player, HPos.CENTER);
        player.setMaxHeight(Region.USE_PREF_SIZE);
        player.setMaxWidth(Region.USE_PREF_SIZE);
        adjustMediaSize();
        initVideoIntro(assembleVideoList.get(0));
        initUserTrainingTimeInWeek();
        judgeWeek();
        writeUserFile();
        writeVideoRecord();
    }

    /**
     * Monitor the height of the window to synchronize the size of the player when changes occur
     */
    public void adjustMediaSize() {
        videoBox.heightProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                player.setMediaHeight((double) newValue - 41);
            }

        });
    }


    /**
     * Initializes the icon component that returns the video list page
     * @see sample.controllerImpl.VideoCenterController
     */
    public void setFont() {
        FontIcon thumbUpIcon = new FontIcon("fa-thumbs-up");
        thumbUpIcon.setIconSize(30);
        thumbUpIcon.setIconColor(Color.web("#ffffff"));
        thumbup.setGraphic(thumbUpIcon);
        FontIcon homeIcon = new FontIcon("fa-home");
        homeIcon.setIconSize(15);
        homeIcon.setIconColor(new Color(1, 1, 1, 1));
        backList.setGraphic(homeIcon);
    }

    /**
     * Go to the video list page and destroy player
     * @see sample.controllerImpl.VideoCenterController
     */
    public void gotoVideoList() {
        player.destroy();
        application.gotoVideoCenter();
    }


    /**
     * Read the JSON file of the video information
     */
    //  读取视频信息的json文件
    public void readVideoInfo() throws IOException {
        File fileVideoList = new File("src/main/java/sample/data/Video.json");
        ObjectMapper mapper = new ObjectMapper();
        videoList = new ArrayList<Video>();
        videoList = mapper.readValue(fileVideoList, new TypeReference<List<Video>>() {
        });
    }

    /**
     * Write the JSON file of the video information
     */
    public void writeVideoInfo(Video video) throws IOException {
        File fileVideoList = new File("src/main/java/sample/data/Video.json");
        ObjectMapper mapper = new ObjectMapper();
        for (Video videoIterator : videoList) {
            if (videoIterator.getTitle().equals(video.getTitle())) {
                videoIterator = video;
            }
        }
        OutputStream outputStream = new FileOutputStream(fileVideoList);
        mapper.writeValue(outputStream, videoList);
    }


    /**
     * Gets the status of the loaded video list
     */
    //  获取加载的视频列表状态
    public void getVideoStatus() throws IOException {
        File fileVideoStatus = new File("src/main/java/sample/data/VideoStatus.json");
        ObjectMapper mapper = new ObjectMapper();
        videoStatus = mapper.readValue(fileVideoStatus, new TypeReference<String>() {
        });
    }

    /**
     * Select the type of video to load according to VideoStatus
     */
    //    根据videoStatus选择要加载的视频类型
    public List<Video> chooseVideoList() {
        assembleVideoList.clear();
        for (Video video : videoList) {
            if (video.getSort().equals(videoStatus)) {
                assembleVideoList.add(video);
            }
        }
        return assembleVideoList;
    }

    /**
     * Install the selected VideoList into the VideoList container, and add EventHandler of video jump
     */
    //    装配选出的videolist到videolist的盒子中
    public void assembleVideoListInBox() {
        chooseVideoList();
        videoListShowBox.setGridLinesVisible(true);
        videoListShowBox.getChildren().clear();
        videoListShowBox.getRowConstraints().clear();
        videoListShowBox.getColumnConstraints().clear();
        videoListShowBox.prefHeight(0);
        videoListShowBox.prefWidth(Control.USE_COMPUTED_SIZE);
        videoListShowBox.minWidth(Control.USE_COMPUTED_SIZE);
        ColumnConstraints columnConstraints0 = new ColumnConstraints();
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints0.setHgrow(Priority.NEVER);
        columnConstraints1.setHgrow(Priority.ALWAYS);
        columnConstraints0.setHalignment(HPos.CENTER);
        columnConstraints1.setHalignment(HPos.CENTER);
        columnConstraints0.setPrefWidth(136);
        columnConstraints1.setMaxWidth(Double.MAX_VALUE);
        columnConstraints1.setPrefWidth(187);
        columnConstraints0.setFillWidth(true);
        columnConstraints1.setFillWidth(true);
        videoListShowBox.getColumnConstraints().add(0, columnConstraints0);
        videoListShowBox.getColumnConstraints().add(1, columnConstraints1);
        for (int i = 0; i < assembleVideoList.size(); i++) {
            Video video = assembleVideoList.get(i);
//            形成头像
            ImageView videoPage = new ImageView();
            MakeCenterImage maker = new MakeCenterImage();
            videoPage = maker.makeCenterImageRectangle(110, videoPage, video.getCoverUrl());
            videoPage.setPreserveRatio(true);
            Button chooseVideo = new Button(video.getTitle());
            chooseVideo.setId(video.getVideoId());
            chooseVideo.setWrapText(true);
            chooseVideo = setVideoLinkButton(chooseVideo);
            chooseVideo.setMinWidth(Control.USE_COMPUTED_SIZE);
            chooseVideo.setMinHeight(Control.USE_COMPUTED_SIZE);
            chooseVideo.setMaxWidth(Double.MAX_VALUE);
            final Video videoFinal = video;
            chooseVideo.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
//            跳转视频
                    try {
                        judgeWeek();
                        writeUserFile();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    player.changeSource(videoFinal.getVideoUrl(), 693, 200);
                    initVideoIntro(videoFinal);
                    videoRecordNow.setVideoId(videoFinal.getVideoId());
                    videoRecordNow.setVideoSort(videoFinal.getSort());
                    try {
                        writeVideoRecord();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    initUserTrainingTimeInWeek();

                }
            });
            videoListShowBox.getRowConstraints().add(i, new RowConstraints());
            videoListShowBox.addColumn(0, videoPage);
            videoListShowBox.addColumn(1, chooseVideo);

        }
        for (RowConstraints rowConstraint : videoListShowBox.getRowConstraints()) {
            rowConstraint.setMaxHeight(110);
            rowConstraint.setMinHeight(110);
            rowConstraint.setPrefHeight(110);
        }
    }


    /**
     * Modify the VideoList and Cover Pic link button styles
     * @param button Need adjust button
     * @return button have been adjusted
     */
    public Button setVideoLinkButton(Button button) {
        button.setStyle("-fx-text-fill: #FFFFFF;-fx-background-color: transparent;-fx-border-width: 0px;-fx-font-size: 15px;");
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle("-fx-text-fill: #FFFFFF;-fx-background-color: transparent;-fx-border-width: 0px;-fx-font-size: 15px;");
            }
        });
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle("-fx-text-fill: #e2b300;-fx-underline:false;-fx-background-color: transparent;-fx-border-width: 0px;-fx-font-size: 15px;");
            }
        });
        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle("-fx-text-fill: #b4920a;-fx-underline:false;-fx-background-color: transparent;-fx-border-width: 0px;-fx-font-size: 15px;");
            }
        });
        return button;
    }


    /**
     * Initializes the video category list of the player page and the corresponding jump events
     */
    public void initCoverPageBox() {
        File filePath = new File(getClass().getResource("videoListComponent/pic").getPath());
        String path = filePath.toString();
        path = URLDecoder.decode(path, StandardCharsets.UTF_8);
        File file = new File(path);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            if (!f.isDirectory()) {        //若非目录(即文件)，则打印
                ImageView videoPage = new ImageView();
                MakeCenterImage maker = new MakeCenterImage();
                videoPage = maker.makeCenterImageRectangle(110, videoPage, f.toURI().toString());
                videoPage.setPreserveRatio(true);
                Button sortBtn = new Button(f.getName().substring(0, f.getName().length() - 4));
                sortBtn.setId(sortBtn.getText());
                sortBtn = setVideoLinkButton(sortBtn);
                videoSortList.addRow(i, videoPage, sortBtn);
                videoSortList.getRowConstraints().add(i, new RowConstraints());
                videoSortList.getRowConstraints().get(i).setPrefHeight(100);
                videoSortList.getRowConstraints().get(i).setMinHeight(100);
                videoSortList.getRowConstraints().get(i).setMaxHeight(100);

                final Button finalSortBtn = sortBtn;
                sortBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        writeVideoStatus(finalSortBtn.getText());
                        try {
                            getVideoStatus();
                            readVideoInfo();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        assembleVideoListInBox();
                    }
                });
            }
        }
    }

    /**
     * Write the name of the playback video to a JSON file
     * @param status The name of the currently playing video
     */
    public void writeVideoStatus(String status) {
        File file = new File("src/main/java/sample/data/VideoStatus.json");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("\"" + status + "\"");
            fileWriter.flush();
            fileWriter.close();
            System.out.println("VideoStatus file init");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Initializes video information other than the video itself into its respective component
     * and thumb up event handling
     * @param video The video need init info
     */
    public void initVideoIntro(Video video) {
        frequency.setText(video.getFrequency());
        videoSort.setText(video.getSort());
        starNumLabel.setText(video.getStar());
        thumbup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                starNum = video.getStar();
                video.setStar(Integer.toString(Integer.parseInt(starNum) + 1));
                starNumLabel.setText(video.getStar());
                try {
                    writeVideoInfo(video);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        videoIntro.setText(video.getIntroduction());
        videoTitle.setText(video.getTitle());
        instrument.setText(video.getInstrument());
    }

    /**
     * Get the UserName of the logged user
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
     * Write non-repeating video playback records to a JSON file
     * @exception IOException
     */
    public void writeVideoRecord() throws IOException {
        boolean nonRepeatFlag = true;
        File fileVideoRecord = new File("src/main/java/sample/data/videoRecord.json");
        ObjectMapper mapper = new ObjectMapper();
        videoRecordList = mapper.readValue(fileVideoRecord, new TypeReference<List<VideoRecord>>() {
        });
        if(videoRecordList.size()==0){
            videoRecordList.add(videoRecordNow);
            nonRepeatFlag = false;
        }else {
            for (int i = 0; i < videoRecordList.size(); i++) {
                if (videoRecordList.get(i).getVideoId().equals(videoRecordNow.getVideoId()) && videoRecordList.get(i).getUserId().equals(videoRecordNow.getUserId())){
                    System.out.println("equal video");
                    nonRepeatFlag = false;
                }
            }
        }
        if (nonRepeatFlag){
            videoRecordList.add(videoRecordNow);
        }
//        初始化输出流
        OutputStream outputStream = new FileOutputStream(fileVideoRecord);
        mapper.writeValue(outputStream, videoRecordList);
        outputStream.close();
    }


    /**
     * Initializes the object to which the user is currently logged in
     */
    public void initUserTrainingTimeInWeek(){
//        读取user文件
        File fileLoginStatus = new File("src/main/java/sample/data/User.json");
        ObjectMapper mapper = new ObjectMapper();
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
    }

    /**
     * Determine whether this week is a new week and the time of the day,
     * as well as watching the video to adjust the user's learning progress
     */
    public void judgeWeek() throws ParseException {
        CalendarUtils calendarUtils = new CalendarUtils();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());

        Date lastLoginDate = formatter.parse(loginUserNow.getLastStudyDate());

//        判断今天是不是第一次看视频
        if(loginUserNow.getLastStudyDate().equals(formatter.format(date))){
//            不是第一次
//            判断今天是周几
            System.out.println(calendarUtils.getTodayWeek());
            if(calendarUtils.getTodayWeek()==1){
//                   周一
                loginUserNow.setTrainingTimeInMon(loginUserNow.getTrainingTimeInMon()+1);
            }else if (calendarUtils.getTodayWeek()==2){
                loginUserNow.setTrainingTimeInTue(loginUserNow.getTrainingTimeInTue()+1);
            }else if (calendarUtils.getTodayWeek()==3){
                loginUserNow.setTrainingTimeInWed(loginUserNow.getTrainingTimeInWed()+1);
            }else if (calendarUtils.getTodayWeek()==4){
                loginUserNow.setTrainingTimeInThu(loginUserNow.getTrainingTimeInThu()+1);
            }else if (calendarUtils.getTodayWeek()==5){
                loginUserNow.setTrainingTimeInFri(loginUserNow.getTrainingTimeInFri()+1);
            }else if (calendarUtils.getTodayWeek()==6){
                loginUserNow.setTrainingTimeInSat(loginUserNow.getTrainingTimeInSat()+1);
            }else if (calendarUtils.getTodayWeek()==0){
                loginUserNow.setTrainingTimeInSun(loginUserNow.getTrainingTimeInSun()+1);
            }
        }else {
//            是今天的第一次登录
//            先把对应的登录时间修改
            loginUserNow.setLastStudyDate(formatter.format(date));
//            判断是不是周一（新的一周要清空其他的记录）
            if (calendarUtils.getTodayWeek()==1){
                loginUserNow.setTrainingTimeInMon(1);
                loginUserNow.setTrainingTimeInTue(0);
                loginUserNow.setTrainingTimeInWed(0);
                loginUserNow.setTrainingTimeInThu(0);
                loginUserNow.setTrainingTimeInFri(0);
                loginUserNow.setTrainingTimeInSat(0);
                loginUserNow.setTrainingTimeInSun(0);
            }else {
//                和上次登录是否差了一周,一周要重写
                if (CalendarUtils.differentDays(lastLoginDate,date)>=7){
                    loginUserNow.setTrainingTimeInMon(0);
                    loginUserNow.setTrainingTimeInTue(0);
                    loginUserNow.setTrainingTimeInWed(0);
                    loginUserNow.setTrainingTimeInThu(0);
                    loginUserNow.setTrainingTimeInFri(0);
                    loginUserNow.setTrainingTimeInSat(0);
                    loginUserNow.setTrainingTimeInSun(0);
                    if (calendarUtils.getTodayWeek()==2){
                        loginUserNow.setTrainingTimeInTue(1);
                    }else if (calendarUtils.getTodayWeek()==3){
                        loginUserNow.setTrainingTimeInWed(1);
                    }else if (calendarUtils.getTodayWeek()==4){
                        loginUserNow.setTrainingTimeInThu(1);
                    }else if (calendarUtils.getTodayWeek()==5){
                        loginUserNow.setTrainingTimeInFri(1);
                    }else if (calendarUtils.getTodayWeek()==6){
                        loginUserNow.setTrainingTimeInSat(1);
                    }else if (calendarUtils.getTodayWeek()==0){
                        loginUserNow.setTrainingTimeInSun(1);
                    }
                }else {
                    if(calendarUtils.getTodayWeek()==1){
//                   周一
                        loginUserNow.setTrainingTimeInMon(loginUserNow.getTrainingTimeInMon()+1);
                    }else if (calendarUtils.getTodayWeek()==2){
                        loginUserNow.setTrainingTimeInTue(loginUserNow.getTrainingTimeInTue()+1);
                    }else if (calendarUtils.getTodayWeek()==3){
                        loginUserNow.setTrainingTimeInWed(loginUserNow.getTrainingTimeInWed()+1);
                    }else if (calendarUtils.getTodayWeek()==4){
                        loginUserNow.setTrainingTimeInThu(loginUserNow.getTrainingTimeInThu()+1);
                    }else if (calendarUtils.getTodayWeek()==5){
                        loginUserNow.setTrainingTimeInFri(loginUserNow.getTrainingTimeInFri()+1);
                    }else if (calendarUtils.getTodayWeek()==6){
                        loginUserNow.setTrainingTimeInSat(loginUserNow.getTrainingTimeInSat()+1);
                    }else if (calendarUtils.getTodayWeek()==0){
                        loginUserNow.setTrainingTimeInSun(loginUserNow.getTrainingTimeInSun()+1);
                    }
                }
            }
        }
    }

    /**
     * The reprogrammed user object is injected back into
     * the user list and written to a JSON file
     */
    public void writeUserFile() throws IOException {
        for (User user : loginUserList) {
            if (user.getUsername().equals(loginUserId)){
                user = loginUserNow;
            }
        }
        File fileLoginStatus = new File("src/main/java/sample/data/User.json");
        ObjectMapper mapper = new ObjectMapper();
        OutputStream outputStream = new FileOutputStream(fileLoginStatus);
        mapper.writeValue(outputStream, loginUserList);
        outputStream.close();
    }
}
