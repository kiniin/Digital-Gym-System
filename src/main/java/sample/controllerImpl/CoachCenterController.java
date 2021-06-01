package sample.controllerImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import sample.Main;
import sample.controller.GetLoginCoachable;
import sample.pojo.Arrange;
import sample.pojo.Coach;
import sample.utils.CalendarUtils;
import sample.utils.MakeCenterImage;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

/**
 * The controller that deal with coach's class arrangement.
 * Initialize the javafx front-end pages, import the calender
 * and combobox modules and so on.
 * When Coaches try to add a class, it can generate a new class,
 * with the chosen date, time, item, location, and the current coach,
 * and write that into the Arrangement.json file.
 * If there's some errors during the setting process, such us forget to
 * choose a time or time repetition, this class have some methods to
 * give some tips,
 *
 * @author Ruizheng Wu
 * @author Xiaojian Qi
 * @iteration 2.0
 */
public class CoachCenterController implements Initializable, GetLoginCoachable {

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
    private String currentCoach = null;

    /**
     * Do the calculation of the month in the calender module.
     * Add one month, and do the necessary robust.
     */
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
    /**
     * Do the calculation of the year in the calender module.
     * Add one year, and do the necessary robust.
     */
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
    /**
     * Do the calculation of the month in the calender module.
     * Minus one month, and do the necessary robust.
     */
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
    /**
     * Do the calculation of the year in the calender module.
     * Minus one year, and do the necessary robust.
     */
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
    /**
     * Combine this frame with the javafx main function.
     * @param application This javafx application.
     */
    public void setApp(Main application) {
        this.application = application;
    }

    /**
     * Sync the time in the whole calender module.
     */
    public void syncTime() {
        buttonDateList = dateBox.getChildren();
        Button buttonInstance;
        for (int i = 0; i < buttonDateList.size(); i++) {
            buttonInstance = (Button) buttonDateList.get(i);
            buttonInstance.setText(dateList.get(i).toString());
        }
    }

    /**
     * Get the current date chose by users in the calender module.
     *
     * @param event The front-end event, user click the button.
     * @throws IOException Exception throwed when there're some errors in file reading and writing.
     */
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
        // search the Arrangement.json by the date, return all the coaches that day.
        dateShow.setText(combineDateString);
    }

    /**
     * The initialize process of the front-end frame, initialize all the modules here
     * and do some user authorization here. The main job here is to introduce and
     * set the calender module, ensure it can correctly show and change the date.
     *
     * @param url Extend from the interface.
     * @param resourceBundle Extend from the interface.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize the calender.
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
        // add date choose module.
        initTimePicker();
        try {
            initItemComboBox();
            initLocationComboBox();
            getCoachStatus();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Get the current coach login status by checking the file.
     * Read the current login coach's account from a file, then
     * use Jackson to map the information to a List of Class:Coach,
     * matching the login status.
     *
     * @throws IOException
     */
    public void getCoachStatus() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("src/main/java/sample/data/LoginStatusCoach.json"));
        String str;
        if ((str = in.readLine()) != null){
            str= str.replace("\"", "");
            ObjectMapper objectMapper = new ObjectMapper();
            File coach = new File("src/main/java/sample/data/Coach.json");
            List<Coach> coaches = objectMapper.readValue(coach, new TypeReference<List<Coach>>() {
            });
            for(int i=0;i<coaches.size();i++){
                if(coaches.get(i).getAccount().equals(str)){
                    System.out.println("ok?");
                    initCoachPhoto(coaches.get(i).getPhotoURL());
                    coachName.setText("Hi,"+coaches.get(i).getName()+" !");
                    currentCoach=coaches.get(i).getName();
                    break;
                }
            }
        }
    }


    /**
     * Change the format of the chose date.
     * Pick out the day, month, year separately from the origin data.
     *
     * @throws ParseException Errors may happen in the calender module.
     */
    public void separateTime() throws ParseException {
        dateListList = calendarUtils.getTimeNumber(monthText, yearText, buttonDateList.size());
        dateList = dateListList.get(0);
        monthList = dateListList.get(1);
        yearList = dateListList.get(2);
    }
    /**
     * Button-click event handler,Jump to AboutUs frame.
     */
    public void gotoAboutUs(){
        application.gotoAboutUs("coach");
    }
    /**
     * Button-click event handler,Jump to CoachCener frame.
     */
    public void gotoCoachCenter(){
        application.gotoCoachCenter();
    }
    /**
     * Button-click event handler,Jump to Coach's order list frame.
     */
    public void gotoCoachAllOrderList(){
        application.gotoCoachAllOrderList();
    }

    /**
     * Introduce jfxTimePicker module, and do the basic settings
     * and initialization.
     */
    public void initTimePicker(){
        jfxTimePicker = new JFXTimePicker();
        jfxTimePicker.setId("jfxTimePicker");
        jfxTimePicker.getEditor().setStyle("-fx-text-fill: white;-fx-font-size: 20px");
        Color color = Color.rgb(50,50,50,0.9);
        jfxTimePicker.setDefaultColor(color);
        timePickerBox.add(jfxTimePicker,0,0);
    }
    /**
     * Set the coach's photo in the front-end frame.
     *
     * @param photoURL The address of the coach's photo.
     */
    public void initCoachPhoto(String photoURL) {
        MakeCenterImage makeCenterImage = new MakeCenterImage();
        coachPhoto.setClip(makeCenterImage.makeCenterImageCircle(100,coachPhoto,photoURL));
    }

    /**
     * Initialize the item combobox by reading the file.
     * Read all the available choices using Java IO,
     * save the choices in a list, then set value of the combobox.
     *
     * @throws IOException Exception throwed when there're some errors in file reading and writing.
     */
    public void initItemComboBox() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File fileAvailableItem = new File("src/main/java/sample/data/SportItem.json");
        List<String> itemChoice = objectMapper.readValue(fileAvailableItem, new TypeReference<List<String>>() {
        });
        sportItemInput.setItems(FXCollections.observableList(itemChoice));
        sportItemInput.getSelectionModel().selectFirst();
    }

    /**
     * Initialize the location combobox by reading the file.
     * Read all the available choices using Java IO,
     * save the choices in a list, then set value of the combobox.
     *
     * @throws IOException Exception throwed when there're some errors in file reading and writing.
     */
    public void initLocationComboBox() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File fileAvailableLocation = new File("src/main/java/sample/data/Location.json");
        List<String> locationChoice = objectMapper.readValue(fileAvailableLocation, new TypeReference<List<String>>() {
        });
        locationInput.setItems(FXCollections.observableList(locationChoice));
        locationInput.getSelectionModel().selectFirst();
    }

    /**
     * Do the robust test of a coach's chocies on adding classes, and
     * then record the added classes into a file.
     * Using Jackson to analyze and map the information in Arrangement.json,
     * map them to a list, and then check the coach's choices. If there's empty
     * or errors, alert. If all ok, record the new class into the file.
     *
     * @throws IOException Exception throwed when there're some errors in file reading and writing.
     */
    public boolean ensureCourse() throws IOException {
        // 这再加个非空的alert，如果时间空的，不让他选。
        String location = locationInput.getSelectionModel().getSelectedItem();
        String item = sportItemInput.getSelectionModel().getSelectedItem();
        String date = dateShow.getText();
        String time = null;
        if (jfxTimePicker.getValue()!=null){
             time = jfxTimePicker.getValue().toString();
        } else {
            initAlertOfTime("no");
            return false;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        File fileArrange = new File("src/main/java/sample/data/Arrangement.json");
        List<Arrange> listArrange = objectMapper.readValue(fileArrange, new TypeReference<List<Arrange>>() {});

        for(int i=0;i<listArrange.size();i++){
            if (listArrange.get(i).getTime().equals(time) && listArrange.get(i).getDate().equals(date) && listArrange.get(i).getCoach().equals(currentCoach)){
                initAlertOfTime("wrong");
                return false;
            }
        }

        // In the alert, if the user choose yes, then run the following codes.
        if(initAlertOfCourse()){
            Arrange newArrange = new Arrange();

            newArrange.setCoach(currentCoach); // 记得调成传过来的 当前登录教练的参数
            newArrange.setDate(date);
            newArrange.setTime(time);
            newArrange.setItem(item);
            newArrange.setLocation(location);
            newArrange.setUserId("");

            listArrange.add(newArrange);
            objectMapper.writeValue(new FileOutputStream("src/main/java/sample/data/Arrangement.json"), listArrange);
        }
        return true;
    }
    /**
     * Create an alert window, used when user try to subscribe an class.
     * When use choose cancel, the buttonBar's value will be set to No,
     * and if user choose ensure, the buttonBar's value will be set to Yes.
     * Base on the value, the function will return true or false.
     *
     * @return Choose "cancel",return false; Choose "Ensure", return false.
     */
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
    /**
     * Create an alert window, used when user choose the wrong time.
     * If no choose time or choose re repeted time, give user
     * the information.
     */
    public void initAlertOfTime(String type) {
        Alert _alert = null;
        if(type.equals("no")){
            _alert = new Alert(Alert.AlertType.ERROR, "Please pick time!", new ButtonType("I know", ButtonBar.ButtonData.YES));
        }
        if (type.equals("wrong")){
            _alert = new Alert(Alert.AlertType.ERROR, "Time repeated! Please choose a new time!", new ButtonType("I know", ButtonBar.ButtonData.YES));
        }

        _alert.setTitle("Time Check");
        _alert.setHeaderText(null);
        Optional<ButtonType> buttonType = _alert.showAndWait();
    }
}
