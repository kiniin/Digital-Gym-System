package sample.controllerImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXTimePicker;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.converter.LocalTimeStringConverter;
import sample.Main;
import sample.pojo.Arrange;
import sample.pojo.Coach;
import sample.utils.CalendarUtils;
import sample.utils.MakeCenterImage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.format.FormatStyle;
import java.util.*;

public class CoachCenterController implements Initializable {

    private Main application;
    private CalendarUtils calendarUtils;
    private String loginUserId;

    @FXML
    private Label labelYear;
    @FXML
    private Label labelMonth;

    @FXML
    private Button minusYear;

    @FXML
    private Button minusMonth;

    @FXML
    private Button addYear;

    @FXML
    private Button addMonth;

    @FXML
    private GridPane dateBox;

    private ObservableList<Node> buttonDateList;

    @FXML
    private Button date1;
    @FXML
    private Button date2;
    @FXML
    private Button date3;
    @FXML
    private Button date4;
    @FXML
    private Button date5;
    @FXML
    private Button date6;
    @FXML
    private Button date7;
    @FXML
    private Button date8;
    @FXML
    private Button date9;
    @FXML
    private Button date11;
    @FXML
    private Button date12;
    @FXML
    private Button date13;
    @FXML
    private Button date14;
    @FXML
    private Button date15;
    @FXML
    private Button date16;
    @FXML
    private Button date17;
    @FXML
    private Button date18;
    @FXML
    private Button date19;
    @FXML
    private Button date20;
    @FXML
    private Button date21;
    @FXML
    private Button date22;
    @FXML
    private Button date23;
    @FXML
    private Button date24;
    @FXML
    private Button date25;
    @FXML
    private Button date26;
    @FXML
    private Button date27;
    @FXML
    private Button date28;
    @FXML
    private Button date29;
    @FXML
    private Button date30;
    @FXML
    private Button date31;
    @FXML
    private Button date32;
    @FXML
    private Button date33;
    @FXML
    private Button date34;
    @FXML
    private Button date35;
    @FXML
    private Button date36;
    @FXML
    private Button date37;
    @FXML
    private Button date38;
    @FXML
    private Button date39;
    @FXML
    private Button date40;
    @FXML
    private Button date41;
    @FXML
    private Button date42;

    @FXML
    private Button dateShow;
    @FXML
    protected ComboBox<String> freeTime;
    @FXML
    private Label sportItemLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Button ensureSubscribe;
    @FXML
    private GridPane timePickerBox;
    private int yearText;
    private int monthText;
    private ArrayList<ArrayList<Integer>> dateListList;
    private ArrayList<Integer> dateList;
    private ArrayList<Integer> monthList;
    private ArrayList<Integer> yearList;
    private String dateString;
    @FXML
    private Button timePicker;
    private JFXTimePicker jfxTimePicker;
    @FXML
    private ImageView coachPhoto;
    @FXML
    private Label coachName;
    @FXML
    private ComboBox<String> locationInput;
    @FXML
    private ComboBox<String> sportItemInput;


    public void addMonth() {
        if (monthText < 12 && monthText >= 1) {
            minusMonth.setDisable(false);
            monthText = monthText + 1;
            labelMonth.setText(String.valueOf(monthText));
            try {
                separateTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            syncTime();
        } else {
            addMonth.setDisable(true);
        }
    }

    public void addYear() {
        yearText = yearText + 1;
        labelYear.setText(String.valueOf(yearText));
        try {
            separateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        syncTime();
    }

    public void minusMonth() {
        if (monthText <= 12 && monthText > 1) {
            addMonth.setDisable(false);
            monthText = monthText - 1;
            labelMonth.setText(String.valueOf(monthText));
            try {
                separateTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            syncTime();
        } else {
            minusMonth.setDisable(true);
        }
    }

    public void minusYear() {
        yearText = yearText - 1;
        labelYear.setText(String.valueOf(yearText));
        try {
            separateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        syncTime();
    }

    public void setApp(Main application) {
        this.application = application;
    }


    public void syncTime() {
        buttonDateList = dateBox.getChildren();
        Button buttonInstance;
        for (int i = 0; i < buttonDateList.size(); i++) {
            buttonInstance = (Button) buttonDateList.get(i);
            buttonInstance.setText(dateList.get(i).toString());
        }
    }

    public void getDateByButton(Event event) throws IOException {
        Button ButtonClicked = (Button) event.getSource();
        String dateString = null;
        String monthString = null;
        String yearString = null;
        for (int i = 0; i < buttonDateList.size(); i++) {
            if (ButtonClicked.equals(buttonDateList.get(i))) {
                dateString = dateList.get(i).toString();
                monthString = monthList.get(i).toString();
                yearString = yearList.get(i).toString();
            }
        }
        String combineDateString = yearString + "-" + monthString + "-" + dateString;
        // 根据选择的日期搜索【排班表&日程表】，找到对应日期的所有教练，返回
        // 这个【排班表&日程表】还可以给管理员、老板用，进行排班管理等等
        String test = "test";
        dateShow.setText(combineDateString);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        初始化booking的确认弹窗
        calendarUtils = new CalendarUtils();
        yearText = calendarUtils.getTodayYear();
        monthText = calendarUtils.getTodayMonth();
        labelYear.setText(String.valueOf(yearText));
        labelMonth.setText(String.valueOf(monthText));
        try {
            buttonDateList = dateBox.getChildren();
            separateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateShow.setText(yearText + "-" + monthText + "-" + calendarUtils.getTodayDate());
        syncTime();
//        添加日期按钮事件
        initTimePicker();
        try {
            initItemComboBox();
            initLocationComboBox();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void separateTime() throws ParseException {
        dateListList = calendarUtils.getTimeNumber(monthText, yearText, buttonDateList.size());
        dateList = dateListList.get(0);
        monthList = dateListList.get(1);
        yearList = dateListList.get(2);
    }
    public void gotoHome(){
        application.gotoHome();
    }
    public void gotoCoachCenter(){
        application.gotoCoachCenter();
    }
    public void gotoCoachAllOrderList(){
        application.gotoCoachAllOrderList();
    }
    public void initTimePicker(){
        jfxTimePicker = new JFXTimePicker();
        jfxTimePicker.getEditor().setStyle("-fx-text-fill: white;-fx-font-size: 20px");
        Color color = Color.rgb(50,50,50,0.9);
        jfxTimePicker.setDefaultColor(color);
        jfxTimePicker.setConverter(new LocalTimeStringConverter(FormatStyle.SHORT,Locale.ENGLISH));
        timePickerBox.add(jfxTimePicker,0,0);
    }
    public void initCoachPhoto(String photoURL) {
        MakeCenterImage makeCenterImage = new MakeCenterImage();
        coachPhoto.setClip(makeCenterImage.makeCenterImageCircle(67,coachPhoto,photoURL));
    }
    public void initItemComboBox() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File fileAvailableItem = new File("src/main/java/sample/data/Location.json");
        List<String> itemChoice = objectMapper.readValue(fileAvailableItem, new TypeReference<List<String>>() {
        });
        sportItemInput.setItems(FXCollections.observableList(itemChoice));
        sportItemInput.getSelectionModel().selectFirst();
    }
    public void initLocationComboBox() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File fileAvailableLocation = new File("src/main/java/sample/data/SportItem.json");
        List<String> locationChoice = objectMapper.readValue(fileAvailableLocation, new TypeReference<List<String>>() {
        });
        locationInput.setItems(FXCollections.observableList(locationChoice));
        locationInput.getSelectionModel().selectFirst();
    }
    public void ensureCourse(){
        initAlertOfCourse();
    }
    public boolean initAlertOfCourse() {
        Alert _alert = new Alert(Alert.AlertType.CONFIRMATION, "Ensure this Course?", new ButtonType("cancel", ButtonBar.ButtonData.NO), new ButtonType("ensure", ButtonBar.ButtonData.YES));
        _alert.setTitle("Ensure Course");
        _alert.setHeaderText(null);
        Optional<ButtonType> buttonType = _alert.showAndWait();
        if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
            return true;
        } else {
            return false;
        }
    }
}
