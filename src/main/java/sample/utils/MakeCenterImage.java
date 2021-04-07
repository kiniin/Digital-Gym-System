package sample.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class MakeCenterImage {
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
}
