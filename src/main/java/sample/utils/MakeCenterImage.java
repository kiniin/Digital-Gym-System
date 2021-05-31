package sample.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


/**
 * This Util aims at set the center Image by cut it with
 * setClip {@link javafx.scene.Node}, and adjust the size of a
 * picture
 *
 * @author Xiaojian Qi
 * @iteration 2.0
 */
public class MakeCenterImage {

    /**
     * this function can make a circle with property size and position to make the iamgeview looks like a circle
     * wantSize can be used to customize the size of a circle
     *
     * @param wantSize want size of your picture
     * @param imagePath image path of you want to show
     * @param imageView a imageview you want to show this pic
     * @return a circle which you can use to cut a picture to be a circle in center
     */
    public Circle makeCenterImageCircle(double wantSize, ImageView imageView, String imagePath){
        Image image = new Image(imagePath);
        double imageHeight = image.getHeight();
        double imageWidth = image.getWidth();
        imageView.setImage(image);
        double circleTranslateX = 0;
        double circleTranslateY = 0;
        if(imageHeight >= imageWidth){
            imageView.setFitWidth(wantSize);
            circleTranslateX = imageView.getFitWidth();
            circleTranslateY = imageView.prefHeight(-1);
            return new Circle(circleTranslateX/2,circleTranslateY/2,circleTranslateX/2);
        }else {
            imageView.setFitHeight(wantSize);
            circleTranslateY = imageView.getFitHeight();
            circleTranslateX = imageView.prefWidth(-1);
            return new Circle(circleTranslateX/2, circleTranslateY/2 , circleTranslateY/2);
        }
    }

    /**
     * this function can make a circle with property size and position to make the iamgeview looks like a circle
     * wantSize can be used to customize the size of a rectangle with a radio of source picture
     *
     * @param wantSize want size of your picture
     * @param imagePath image path of you want to show
     * @param imageView a imageview you want to show this pic
     * @return a rectangle which you can use to cut a picture to be a rectangle in center
     */
    public ImageView makeCenterImageRectangle(double wantSize, ImageView imageView, String imagePath){
        Image image = new Image(imagePath);
        double imageHeight = image.getHeight();
        double imageWidth = image.getWidth();
        imageView.setImage(image);
        if(imageHeight <= imageWidth){
            imageView.setFitWidth(wantSize);
            return imageView;
        }else {
            imageView.setFitHeight(wantSize);
            return imageView;
        }
    }
}
