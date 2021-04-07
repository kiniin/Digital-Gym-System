package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import sample.Main;
import sample.utils.MakeCenterImage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UserCenterController implements Initializable {

    private Main application;

    @FXML
    private ImageView userhead;

    private Circle userheadShape;
    private Image headSrcImage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setApp(Main application){
        File file = new File("src/main/java/sample/pic/example1.jpg");
        String path = file.toURI().toString();
//        new utils
        MakeCenterImage makeCenterImage = new MakeCenterImage();
        userhead.setClip(makeCenterImage.makeCenterImageCircle(130,userhead,path));
        this.application = application;
    }

}
