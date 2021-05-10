package sample.simpleMediaPlayer;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * create by Intellij IDEA Author: Al-assad E-mail: yulinying@1994.com Github:
 * https://github.com/Al-assad Date: 2017/1/20 14:22 Description:
 */
public class PlayerController {

    @FXML
    Button playBT;
    @FXML
    Button stopBT;
    @FXML
    Button maxBT;
    @FXML
    Button volumeBT;
    @FXML
    Label timeLB;
    @FXML
    Slider processSD;
    @FXML
    Slider volumeSD;
    @FXML
    MediaView mediaView;
    @FXML
    VBox controlBar;
    @FXML
    AnchorPane mediaPane;
    @FXML
    AnchorPane anchorPane;

    // 控件素材图片
    private String playIcon = getClass().getResource("icon/play.png").toString();
    private String pauseIcon = getClass().getResource("icon/pause.png").toString();
    private String stopIcon = getClass().getResource("icon/stop.png").toString();
    private String volOffIcon = getClass().getResource("icon/volume_off.png").toString();
    private String volOnIcon = getClass().getResource("icon/volume_On.png").toString();
    private String maxIcon = getClass().getResource("icon/max.png").toString();

    private MediaPlayer mediaPlayer;
    private Media media;
    private String url; // 资源的url地址
    private boolean popup; // 窗口弹出方式
    private Scene scene; // 父类窗口

    private boolean atEndOfMedia = false; // 记录视频是否处播放到结束
    private final boolean repeat = false; // 记录视频是否重复播放
    private double volumeValue; // 储存静音操作前的音量数据
    private Duration duration; // 记录视频持续时间
    private int mediaHeight; // 视频资源的尺寸
    private int mediaWidth;

    private int currentHeight; // 当前整个播放器的尺寸
    private int currentWidth;

    private Duration currentTime;
    private boolean clickSliderFlag;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    // 程序初始化：设置按钮图标
    public void initialize() {
        // 设置各控件图标
        setIcon(playBT, playIcon, 10);
        setIcon(stopBT, stopIcon, 10);
        setIcon(volumeBT, volOnIcon, 10);

    }

    // 程序启动项，传入必要参数
    public void start(String url, boolean popup, int width, int height) {
        this.url = url;
        this.popup = popup;

        // MediaView设置
        media = new Media(url);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        // 设置播放器，在媒体资源加载完毕后，获取相应的数据，设置组件自适应布局
        setMediaPlayer(width, height);

        // 设置各组件动作事件
        setMediaViewOnClick();
        setPlayButton();
        setStopButton();
        setVolumeButton();
        setVolumeSD();
    }

    // 设置mediaPlayer(参数：整个播放器的尺寸)
    void setMediaPlayer(int width, int height) {
        // 视频就绪时更新 进度条 、时间标签、音量条数据,设置布局尺寸
        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                duration = mediaPlayer.getMedia().getDuration();
                volumeValue = mediaPlayer.getVolume();

                mediaHeight = media.getHeight();
                mediaWidth = media.getWidth();
                System.out.println("mediaWidth" + mediaWidth);
                System.out.println("mediaHeight" + mediaHeight);

                // 设置布局尺寸
                setSize(width, height);
//                updateValues();
                mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                    @Override
                    public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                        if (clickSliderFlag == false){
                            updateValues(newValue);
                        }
                    }
                });

                processSD.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        clickSliderFlag = true;
                    }
                });

                processSD.setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        clickSliderFlag = false;
                        mediaPlayer.seek(Duration.seconds(processSD.getValue()* duration.toSeconds()));
                    }
                });
            }
        });

    }



    // 设置点击MediaView时暂停或开始
    private void setMediaViewOnClick() {
        mediaView.setOnMouseClicked(event -> {
            if (media == null)
                return;
            MediaPlayer.Status status = mediaPlayer.getStatus();
            if (status == MediaPlayer.Status.UNKNOWN || status == MediaPlayer.Status.HALTED) {
                return;
            }
            // 当资源处于暂停或停止状态时
            if (status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.READY
                    || status == MediaPlayer.Status.STOPPED) {
                // 当资源播放结束时，重绕资源
                if (atEndOfMedia) {
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                    atEndOfMedia = false;
                }
                mediaPlayer.play();
                setIcon(playBT, pauseIcon, 10);
            } else { // 当资源处于播放状态时
                mediaPlayer.pause();
                setIcon(playBT, playIcon, 10);
            }
        });
    }

    // 设置播放按钮动作
    private void setPlayButton() {
        playBT.setOnAction((ActionEvent e) -> {
            if (media == null)
                return;
            MediaPlayer.Status status = mediaPlayer.getStatus();
            if (status == MediaPlayer.Status.UNKNOWN || status == MediaPlayer.Status.HALTED) {
                return;
            }
            // 当资源处于暂停或停止状态时
            if (status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.READY||status == MediaPlayer.Status.STOPPED) {
                // 当资源播放结束时，重绕资源
                if (atEndOfMedia) {
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                    atEndOfMedia = false;
                }
                mediaPlayer.play();
                setIcon(playBT, pauseIcon, 10);
            }else { // 当资源处于播放状态时
                mediaPlayer.pause();
                setIcon(playBT, playIcon, 10);
            }
        });
    }

    // 设置停止按钮动作
    private void setStopButton() {
        stopBT.setOnAction((ActionEvent e) -> {
            if (media == null)
                return;
            mediaPlayer.stop();
            atEndOfMedia = true;
            setIcon(playBT, playIcon, 10);
        });
    }

    // 设置音量按钮动作
    private void setVolumeButton() {
        volumeBT.setOnAction((ActionEvent e) -> {
            if (media == null)
                return;

            if (mediaPlayer.getVolume() > 0) {
                volumeValue = mediaPlayer.getVolume();
                volumeSD.setValue(0);
                setIcon(volumeBT, volOffIcon, 10);
            } else {
                mediaPlayer.setVolume(volumeValue);
                volumeSD.setValue(volumeValue * 100);
                setIcon(volumeBT, volOnIcon, 10);
            }
        });
    }

    // 设置音量滑条动作
    private void setVolumeSD() {
        volumeSD.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(newValue.doubleValue() / 100);
            }
        });
    }

    // 更新视频数据（进度条 、时间标签、音量条数据）
    protected void updateValues(Duration newValue) {
        if (processSD != null && timeLB != null && volumeSD != null && volumeBT != null) {
                    currentTime = mediaPlayer.getCurrentTime();
                    timeLB.setText(formatTime(newValue, duration)); // 设置时间标签
                    processSD.setDisable(duration.isUnknown()); // 无法读取时间是隐藏进度条
                    if (!processSD.isDisabled() && duration.greaterThan(Duration.ZERO)
                            && !processSD.isValueChanging()) {
                        processSD.setValue(newValue.toMillis() / duration.toMillis()); // 设置进度条
                    }
                    if (!volumeSD.isValueChanging()) {
                        volumeSD.setValue((int) Math.round(mediaPlayer.getVolume() * 100)); // 设置音量条
                        if (mediaPlayer.getVolume() == 0) { // 设置音量按钮
                            setIcon(volumeBT, volOffIcon, 10);
                        } else {
                            setIcon(volumeBT, volOnIcon, 10);
                        }
                    }
                }
    }

    // 将Duration数据格式化，用于播放时间标签
    protected String formatTime(Duration elapsed, Duration duration) {
        // 将两个Duartion参数转化为 hh：mm：ss的形式后输出
        int intElapsed = (int) Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        int elapsedMinutes = (intElapsed - elapsedHours * 60 * 60) / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;
        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            int durationMinutes = (intDuration - durationHours * 60 * 60) / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;

            if (durationHours > 0) {
                return String.format("%02d:%02d:%02d / %02d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d / %02d:%02d", elapsedMinutes, elapsedSeconds, durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%02d:%02d:%02d / %02d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d / %02d:%02d", elapsedMinutes, elapsedSeconds);
            }
        }
    }

    // 为按钮获取图标
    private void setIcon(Button button, String path, int size) {
        Image icon = new Image(path);
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(size);
        imageView.setFitHeight((int) (size * icon.getHeight() / icon.getWidth()));
        button.setGraphic(imageView);
        // 设置图标点击时发亮
        ColorAdjust colorAdjust = new ColorAdjust();
        button.setOnMousePressed(event -> {
            colorAdjust.setBrightness(0.5);
            button.setEffect(colorAdjust);
        });
        button.setOnMouseReleased(event -> {
            colorAdjust.setBrightness(0);
            button.setEffect(colorAdjust);
        });
    }

    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    // 设置关闭窗口时的动作，手动释放资源，回收内存
    public void destroy() {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }
        mediaPlayer.dispose(); // 释放meidaPlayer的Media资源
        media = null;
        mediaPlayer = null;
        System.gc(); // 通知JVM垃圾回收器

    }

    // 设置播放器尺寸
    public void setSize(int width, int height) {
        currentWidth = width;
        currentHeight = height;
        setUISuitable();
    }

    public void setSizeHeight(double height){
        currentHeight = (int) height;
        setUISuitable();
    }

    public void setSizeWidth(double width){
        currentWidth = (int) width;
        setUISuitable();
    }

    // UI控件自适应大小
    private void setUISuitable() {
        anchorPane.setPrefSize(currentWidth, currentHeight);
        AnchorPane.setBottomAnchor(controlBar, 0.0); // 设置控制条位置
        AnchorPane.setTopAnchor(mediaPane, 0.0); // 设置视频面板位置
        AnchorPane.setBottomAnchor(mediaPane, 52.0);
        mediaView.setFitWidth((double)currentWidth-60); // 设置MediaView尺寸
        // mediaView.setFitHeight((double)currentWidth*(double)mediaHeight / (double)mediaHeight);
        mediaView.setFitHeight((double) currentHeight);
        controlBar.setPrefWidth(currentWidth); // 设置工具条宽度
        System.out.println("currentWidth" + currentWidth);
        System.out.println("currentHeight" + currentHeight);
        System.out.println("mediaWidth" + mediaWidth);
        System.out.println("mediaHeight" + mediaHeight);

    }

    public boolean getPopup() {
        return this.popup;
    }
    
    

}
