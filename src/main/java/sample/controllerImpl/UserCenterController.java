package sample.controllerImpl;

import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import sample.Main;
import sample.utils.MakeCenterImage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UserCenterController implements Initializable {

    private Main application;

    @FXML
    private ImageView userhead;

    @FXML
    private Button toleft;

    //    为了走马灯效果的下方结构
    @FXML
    private GridPane plane0;
    //    为了走马灯效果的上方结构
    @FXML
    private GridPane plane1;
    private Circle userheadShape;
    private Image headSrcImage;

//    初始化动画对象
    private TranslateTransition translateTransitionLeft;
    private TranslateTransition translateTransitionRight;


    public void toggleLeft() {

//        模拟引擎
        Pane pane = new Pane();
//        模拟动画效果
        translateTransitionLeft = new TranslateTransition();
        translateTransitionLeft.setDuration(Duration.seconds(3));
        translateTransitionLeft.setNode(pane);
        translateTransitionLeft.setFromX(0);
        translateTransitionLeft.setToX(plane0.getWidth());
        DisplacementMap dismap1 = new DisplacementMap();
        DisplacementMap dismap0 = new DisplacementMap();
        dismap1.setWrap(false);
        dismap0.setWrap(false);
//        监听值的变化
        plane0.setVisible(true);
        pane.translateXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                dismap1.setOffsetX(-t1.doubleValue()/plane0.getWidth());
                dismap0.setOffsetX(1-(t1.doubleValue()/plane0.getWidth()));
            }
        });
        translateTransitionLeft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                plane1.setVisible(false);
            }
        });
        plane1.setEffect(dismap1);
        plane0.setEffect(dismap0);
        translateTransitionLeft.play();
//        销毁对象
        translateTransitionLeft = null;
        pane = null;
    }

    public void toggleRight() {

//        模拟引擎
        Pane pane = new Pane();
//        模拟动画效果
        translateTransitionRight = new TranslateTransition();
        translateTransitionRight.setDuration(Duration.seconds(3));
        translateTransitionRight.setNode(pane);
        translateTransitionRight.setFromX(plane0.getWidth());
        translateTransitionRight.setToX(0);
        DisplacementMap dismap1 = new DisplacementMap();
        DisplacementMap dismap0 = new DisplacementMap();
        dismap1.setWrap(false);
        dismap0.setWrap(false);
//        监听值的变化
        plane1.setVisible(true);
        pane.translateXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                dismap1.setOffsetX(-t1.doubleValue()/plane0.getWidth());
                dismap0.setOffsetX(1-(t1.doubleValue()/plane0.getWidth()));
            }
        });
        translateTransitionRight.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                plane0.setVisible(false);
            }
        });
        plane1.setEffect(dismap1);
        plane0.setEffect(dismap0);
        translateTransitionRight.play();
//        销毁对象
        translateTransitionRight = null;
        pane = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setApp(Main application) {
        File file = new File("src/main/java/sample/pic/example1.jpg");
        String path = file.toURI().toString();
//        new utils
        MakeCenterImage makeCenterImage = new MakeCenterImage();
        userhead.setClip(makeCenterImage.makeCenterImageCircle(130, userhead, path));
        Pane pane = new Pane();
        this.application = application;
    }

}
