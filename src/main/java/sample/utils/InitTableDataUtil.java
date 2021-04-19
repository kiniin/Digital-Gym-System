package sample.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class InitTableDataUtil {
    //Initialize the core ObservalbleList of a Category table, a list of initialized series, specify an xy axis, source data collection, and a Category collection, and finally return an integrated ObservableList
    public ObservableList<XYChart.Series<String, Number>> initTable(NumberAxis chartAxisY, CategoryAxis chartAxisX,
            ArrayList<XYChart.Data<String, Number>> dataSetInjection, XYChart.Series<String, Number> dataSetSeries,
            ObservableList<XYChart.Series<String, Number>> dataSet, ArrayList<String> categoryYInjection) {
        chartAxisY.setLowerBound(0);
        chartAxisY.setUpperBound(600);
        chartAxisY.setTickUnit(20);
        ObservableList<String> categoriesY = FXCollections.observableArrayList(categoryYInjection);
        chartAxisX.setCategories(categoriesY);

        for (XYChart.Data<String, Number> dataInjection: dataSetInjection) {
            dataSetSeries.getData().add(dataInjection);
        }

        dataSet.add(dataSetSeries);
        return dataSet;
    }
}