package sample.controllerImpl;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.kordamp.ikonli.javafx.FontIcon;
import sample.Main;
import sample.controller.GetLoginUserable;
import sample.controllerImpl.videoListComponent.VideoListComponent;
import sample.pojo.Video;
import sample.pojo.VideoRecord;
import sample.simpleMediaPlayer.SimpleMediaPlayer;
import sample.utils.MakeCenterImage;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        // 初始化登陆状态
        getLoginStatus();
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
        initVideoMedia();
    }

    public void setApp(Main application) {

        this.application = application;
    }

    public void initVideoMedia() {
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
    }

    public void adjustMediaSize() {
        videoBox.heightProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // TODO Auto-generated method stub
                player.setMediaHeight((double) newValue - 41);
            }

        });

//        videoBox.widthProperty().addListener(new ChangeListener<Number>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                // TODO Auto-generated method stub
//                player.setMediaWidth((double) newValue);
//            }
//
//        });
    }

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

    public void gotoVideoList() {
        player.destroy();
        application.gotoVideoCenter();
    }

    //  读取视频信息的json文件
    public void readVideoInfo() throws IOException {
        File fileVideoList = new File("src/main/java/sample/data/Video.json");
        ObjectMapper mapper = new ObjectMapper();
        videoList = new ArrayList<Video>();
        videoList = mapper.readValue(fileVideoList, new TypeReference<List<Video>>() {
        });
    }

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

    //  获取加载的视频列表状态
    public void getVideoStatus() throws IOException {
        File fileVideoStatus = new File("src/main/java/sample/data/VideoStatus.json");
        ObjectMapper mapper = new ObjectMapper();
        videoStatus = mapper.readValue(fileVideoStatus, new TypeReference<String>() {
        });
    }

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
                    player.changeSource(videoFinal.getVideoUrl(), 693, 200);
                    initVideoIntro(videoFinal);
                    videoRecordNow.setVideoId(videoFinal.getVideoId());
                    videoRecordNow.setVideoSort(videoFinal.getSort());
                    try {
                        writeVideoRecord();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
}
