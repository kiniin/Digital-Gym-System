package sample.utils;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MakeTheToggleEffect {
    public Pane makeTheToggleEffect(Pane parentBox){
        StackPane baseBox = new StackPane();
        parentBox.getChildren().add(0,baseBox);
        return parentBox;
    }
}
