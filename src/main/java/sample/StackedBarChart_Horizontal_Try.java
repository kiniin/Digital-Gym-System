package sample;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

public class StackedBarChart_Horizontal_Try extends Application {

    final static String itemA = "A";
    final static String itemB = "B";
    final static String itemC = "C";
    @Override
    public void start(Stage stage) {
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();

        final BarChart<Number, String> bc = new BarChart<>(xAxis, yAxis);

        //bc.setBarGap(0);
//        bc.setCategoryGap(100);

        bc.categoryGapProperty().bind(Bindings.createDoubleBinding(() ->
                        yAxis.getHeight() / (2 * bc.getData().size()),
                Bindings.size(bc.getData()), yAxis.heightProperty()));


        bc.setTitle("Summary");
        xAxis.setLabel("Value");
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Item");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data(2, itemA));


        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data(41, itemB));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2005");
        series3.getData().add(new XYChart.Data(18, itemC));

        Scene scene = new Scene(bc, 800, 600);
        bc.getData().addAll(series1, series2, series3);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}