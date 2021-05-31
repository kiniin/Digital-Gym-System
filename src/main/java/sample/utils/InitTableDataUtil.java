package sample.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

/**
 * Initialize the core ObservalbleList of a Category table,
 * a list of initialized series, specify an xy axis, source data collection,
 * and a Category collection, and finally return an integrated ObservableList
 *
 * @author Xioajian Qi
 * @iteration 2.0
 */
public class InitTableDataUtil {

    /**
     * Initialize the core ObservalbleList of a Category table,
     * a list of initialized series, specify an xy axis, source data collection,
     * and a Category collection, and finally return an integrated ObservableList
     *
     * @param chartAxisX x axis of this table
     * @param chartAxisY y axis of this table
     * @param dataSet final dataset need show in the table
     * @param dataSetSeries the series of the dataset
     * @param dataSetInjection the data you want to inject to the table
     * @param categoryXInjection the label you want set in the X axis
     */
    //Initialize the core ObservalbleList of a Category table, a list of initialized series, specify an xy axis, source data collection, and a Category collection, and finally return an integrated ObservableList
    public ObservableList<XYChart.Series<String, Number>> initTable(NumberAxis chartAxisY, CategoryAxis chartAxisX,
            ArrayList<XYChart.Data<String, Number>> dataSetInjection, XYChart.Series<String, Number> dataSetSeries,
            ObservableList<XYChart.Series<String, Number>> dataSet, ArrayList<String> categoryXInjection) {
        chartAxisY.setLowerBound(0);
        chartAxisY.setUpperBound(600);
        chartAxisY.setTickUnit(20);
        ObservableList<String> categoriesX = FXCollections.observableArrayList(categoryXInjection);
        chartAxisX.setCategories(categoriesX);

        for (XYChart.Data<String, Number> dataInjection: dataSetInjection) {
            dataSetSeries.getData().add(dataInjection);
        }

        dataSet.add(dataSetSeries);
        return dataSet;
    }
}