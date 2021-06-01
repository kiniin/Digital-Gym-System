package sample.utils;

import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * This Util aims at set a corresponding rotation effect,
 * including left and right movements
 *
 * @author Xiaojian Qi
 * @version 2.0
 */
public class MakeTheToggleEffect {
    //    初始化动画对象
    private TranslateTransition translateTransitionLeft;
    private TranslateTransition translateTransitionRight;

    /**
     * create an effect of toggle right
     *
     * @param plane0 The object in the previous plane
     * @param plane1 The object in the next plane
     * @param toright button to toggle right
     * @param nowIndex Index of the currently displayed screen
     * @return newIndex after change to right
     */
    public int toggleRightUtil(Pane plane0, Pane plane1, Button toright, int nowIndex){
        toright.setDisable(true);

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
                toright.setDisable(false);
            }
        });
        plane1.setEffect(dismap1);
        plane0.setEffect(dismap0);
        translateTransitionLeft.play();
//        销毁对象
        translateTransitionLeft = null;
        pane = null;
        return nowIndex-1;
    }

    /**
     * create an effect of toggle left
     *
     * @param plane0 The object in the previous plane
     * @param plane1 The object in the next plane
     * @param toleft button to toggle left
     * @param nowIndex Index of the currently displayed screen
     * @return newIndex after change to right
     */
    public int toggleLeftUtil(Pane plane0,Pane plane1, Button toleft, int nowIndex){
        toleft.setDisable(true);
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
                toleft.setDisable(false);
            }
        });
        plane1.setEffect(dismap1);
        plane0.setEffect(dismap0);
        translateTransitionRight.play();
//        销毁对象
        translateTransitionRight = null;
        pane = null;
        return nowIndex+1;
    }
}
