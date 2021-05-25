package sample.event;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import sample.controllerImpl.BookingController;
import sample.controllerImpl.coachListComponent.CoachListComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubscribeEvent implements EventHandler<Event> {

    private CoachListComponent component;
    private String keyword;
    private ComboBox<String> freeTime;
    private Label coachNameLabel;

    public Label getCoachNameLabel() {
        return coachNameLabel;
    }

    public void setCoachNameLabel(Label coachNameLabel) {
        this.coachNameLabel = coachNameLabel;
    }

    public ComboBox<String> getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(ComboBox<String> freeTime) {
        this.freeTime = freeTime;
    }


    public CoachListComponent getComponent() {
        return component;
    }

    public void setComponent(CoachListComponent component) {
        this.component = component;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void handle(Event event) {
        String coachName = component.controller.getCoachName();
        coachNameLabel.setText(coachName);
        List<String> freeTimeList = new ArrayList<String>();
        try {
            freeTimeList = BookingController.searchTime(coachName,keyword);
            ObservableList<String> obsFreeTimeList = FXCollections.observableList(freeTimeList);
            freeTime.setItems(obsFreeTimeList);
            freeTime.getSelectionModel().selectFirst();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
