package sample.controllerImpl;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import sample.Main;
import sample.controllerImpl.videoListComponent.VideoListComponent;
import sample.controllerImpl.videoListComponent.VideoListComponentController;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class VideoCenterController implements Initializable {
    private Main application;

    @FXML
    private TilePane videoList;



    public void gotoBookingCenter(){
        application.gotoBooking();
    }
    public void gotoHome(){
        application.gotoHome();
    }
    public void gotoVideoCenter(){
        application.gotoVideoCenter();
    }
    public void gotoTrainingCenter() {
        application.gotoTrainingCenter();
    }
    public void gotoInformationCenter(){
        application.gotoInformationCenter();
    }
    public void gotoVideo(){
        application.gotoVideo();
    }

    public void setApp(Main application){
        this.application = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        videoList.setAlignment(Pos.CENTER);
        File filePath = new File(getClass().getResource("videoListComponent/pic").getPath());
        String path = filePath.toString();
        File file = new File(path);
        File[] files = file.listFiles();
        for(File f:files){					//遍历File[]数组
            if(!f.isDirectory()) {        //若非目录(即文件)，则打印
                VideoListComponent component = new VideoListComponent(f.toURI().toString(),(f.getName().substring(0,f.getName().length()-4)));
                videoList.getChildren().add(component);
                component.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        gotoVideo();
                    }
                });
            }
        }
//        String imagePath1 = image1.toURI().toString();
//        videoList.getChildren().add(new VideoListComponent(imagePath1,"running"));
//        File image2 = new File("src/main/java/sample/controllerImpl/videoListComponent/pic/soccer.jpg");
//        String imagePath2 = image2.toURI().toString();
//        videoList.getChildren().add(new VideoListComponent(imagePath2,"soccer"));
//        System.out.println(imagePath2);
    }
}
