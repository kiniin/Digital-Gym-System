package sample.controllerImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sample.Main;
import sample.controller.GetLoginUserable;
import sample.controllerImpl.coachListComponent.CoachListComponent;
import sample.controllerImpl.videoListComponent.VideoListComponent;
import sample.event.SubscribeEvent;
import sample.pojo.Arrange;
import sample.pojo.Coach;
import sample.pojo.User;
import sample.utils.CalendarUtils;
import sample.utils.MakeCenterImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;

/**
 * The class is to control the process of booking a existent course.
 * There's an calender module for user to choose a date, and then,
 * based on the date, the controller class will have some methods to search
 * and show the information of all the available coaches on that day. Users
 * can pick one coach, and see all the courses that can be ordered of that coach.
 * User can choose any time when the coach is available.
 * Then, after user ensure the order, the controller can save the order information
 * to a file, by using Jackson.
 *
 * @author Ruizheng Wu
 * @iteration 2.0
 */
public class BookingController implements Initializable, GetLoginUserable {

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
    private GridPane coachList;
    private ArrayList<String> listCoachName;

    @FXML
    protected ComboBox<String> freeTime;
    @FXML
    private Label coachNameLabel;
    @FXML
    private Label sportItemLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Button ensureSubscribe;
    @FXML
    private ImageView coachPhoto;
    @FXML
    private ImageView calendarBack;
    private Map<Integer,String> monthPicPath;

    private int yearText;
    private int monthText;
    private ArrayList<ArrayList<Integer>> dateListList;
    private ArrayList<Integer> dateList;
    private ArrayList<Integer> monthList;
    private ArrayList<Integer> yearList;
    private String dateString;

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
            setMonthPic();
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
            setMonthPic();
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
     * Get the classes info by user account, and initialize the coaches's table in the front-end.
     * Use Jackson to analyze the Arrange.json file,and extract the orders information by searching
     * the input user's name. The information will be stored in a List, which contains the instances
     * of Arrange.class. Then match the user's account with the date, find all the coaches.
     * After find the coaches, pass the list of coaches to the front-end frame, and initialize
     * the front-end's table.
     *
     * @param keyword The searching keyword, here is the user's account.
     * @throws IOException Exception throwed when there're some errors in file reading and writing.
     */
    public void searchTest(String keyword) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File arrangementFile = new File("src/main/java/sample/data/Arrangement.json");

        // Use Jackson to map the files Arrange.json to the List<Arrange>
        List<Arrange> listArrange = objectMapper.readValue(arrangementFile, new TypeReference<List<Arrange>>() {
        });
        listCoachName = new ArrayList();
        File coachFile = new File("src/main/java/sample/data/Coach.json");
        List<Coach> listCoach = objectMapper.readValue(coachFile, new TypeReference<List<Coach>>() {
        });
        // System.out.println(listArrange.get(0).getCourse().get(0).getCoach());
        for (int i = 0; i < listArrange.size(); i++) {
            Arrange temp = listArrange.get(i);
            // date equals
            if (temp.getDate().equals(keyword) && temp.getUserId().equals("")) {
                // Ensure no repetition in the coach list
                if (!listCoachName.contains(temp.getCoach())) {
                    listCoachName.add(temp.getCoach());
                }

            }
        }
        if (listCoachName.size() == 0) {
            coachList.getChildren().clear();
            System.out.println("Today we have no classes!");
        } else {
            coachList.getChildren().clear();
            CoachListComponent component = null;
            for (int i = 0; i < listCoachName.size(); i++) {
                // parameter： The name of teh coach
                Coach oneCoach = null;
                for(int j=0;j<listCoach.size();j++){
                    if(listCoach.get(j).getName().equals(listCoachName.get(i))){
                        oneCoach = listCoach.get(j);
                    }
                }
                component = new CoachListComponent(listCoachName.get(i));
//                coachList.getChildren().add(component);
//                coachList.addRow(i, component);
                coachList.addColumn(0,component);
                CoachListComponent finalComponent = component;
                Coach finalOneCoach = oneCoach;
                component.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        fillBookingTable(finalComponent,finalOneCoach,keyword);
                    }
                });
            }
            for(int j=0;j<listCoach.size();j++){
                if(listCoach.get(j).getName().equals(listCoachName.get(0))){
                    fillBookingTable((CoachListComponent) coachList.getChildren().get(0),listCoach.get(j),keyword);
                    searchClass(coachNameLabel.getText(), keyword, freeTime.getValue());
                }
            }
            freeTime.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        searchClass(coachNameLabel.getText(), keyword, freeTime.getValue());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * Put empty times of a coach into the time picker.
     * Call searchTime() function, get the time list and set the value.
     *
     * @param component The time picker component on the front page.
     * @param oneCoach The coach the chosen by the user.
     * @param keyword The user's account
     */
    private void fillBookingTable(CoachListComponent component, Coach oneCoach, String keyword){
        String coachName = component.controller.getCoachName();
        coachNameLabel.setText(coachName);
        List<String> freeTimeList = new ArrayList<String>();
        try {
            freeTimeList = searchTime(coachName, keyword);
            ObservableList<String> obsFreeTimeList = FXCollections.observableList(freeTimeList);
            freeTime.setItems(obsFreeTimeList);
            freeTime.getSelectionModel().selectFirst();
            setCoachPhoto(oneCoach.getPhotoURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Search all the available time of a chosen Coach.
     * The funtion will use Jackson to map the Arrangement.json to a list,
     * and search the list by the coach's name and the chosen date,
     * if the condition matches, record the time in a list, and finally,
     * return all the available time.
     *
     * @param coach The coach chose by user in the table.
     * @param date The date that the user chose on the calender module.
     * @return An ArrayList, saving the String of time that are available.
     * @throws IOException
     */
    public List<String> searchTime(String coach, String date) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/java/sample/data/Arrangement.json");
        List<Arrange> listArrange = objectMapper.readValue(file, new TypeReference<List<Arrange>>() {
        });
        List listTime = new ArrayList();
        for (int i = 0; i < listArrange.size(); i++) {
            Arrange temp = listArrange.get(i);
            // condition matches
            if (temp.getDate().equals(date) && temp.getCoach().equals(coach) && temp.getUserId().equals("")) {
                listTime.add(temp.getTime());
            }
        }
//        System.out.println("time: "+listTime);
        return listTime;
    }

    /**
     * Search all the information of a chosen Coach.
     * The function will use Jackson to map the Arrangement.json to a list,
     * and search the list by the coach's name,the chosen date and the chosen time.
     * if the condition matches, record the information in a list, and finally,
     * return all the information.
     *
     * @param coach The coach chose by user in the table.
     * @param date The date that the user chose on the calender module.
     * @param time The time chose by user in the time-list.
     * @throws IOException
     */
    public void searchClass(String coach, String date, String time) throws IOException {
        // 查找特定的一节课
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/java/sample/data/Arrangement.json");
        List<Arrange> listArrange = objectMapper.readValue(file, new TypeReference<List<Arrange>>() {
        });
        Arrange classContent = new Arrange();
        for (int i = 0; i < listArrange.size(); i++) {
            Arrange temp = listArrange.get(i);
            // condition matches
            if (temp.getDate().equals(date) && temp.getCoach().equals(coach) && temp.getTime().equals(time) && temp.getUserId().equals("")) {
                classContent = temp;
                break;
            }
        }
        locationLabel.setText(classContent.getLocation());
        sportItemLabel.setText(classContent.getItem());
        // get the params like coach and so on.
//        System.out.println("Location:" + classContent.getLocation());
//       internal class, use final.
        final Arrange finalClassContent = classContent;
        ensureSubscribe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(!initAlertOfSubScribe()){
                        return;
                    }else {
                        bookCourse(loginUserId, finalClassContent.getCoach(), finalClassContent.getDate(), finalClassContent.getTime());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Search the Arrangement.json file by the parameters,find the specific
     * class in a time point.
     *
     * @param userId The account of the current user.
     * @param coach The name of the chosen coach.
     * @param date The dated picked in the calender.
     * @param time The time chose in the time selected bar.
     * @throws IOException Exception throwed when there're some errors in file reading and writing.
     */
    public void bookCourse(String userId, String coach, String date, String time) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File fileArrage = new File("src/main/java/sample/data/Arrangement.json");
        List<Arrange> listArrange = objectMapper.readValue(fileArrage, new TypeReference<List<Arrange>>() {
        });
        Arrange classContent = new Arrange();
        for (int i = 0; i < listArrange.size(); i++) {
            Arrange temp = listArrange.get(i);
            // condition matches
            if (temp.getDate().equals(date) && temp.getCoach().equals(coach) && temp.getTime().equals(time) && temp.getUserId().equals("")) {
                temp.setUserId(userId);
                break;
            }
        }
        //classContent.setUserId(userId);
        System.out.println("Look!");
        System.out.println(objectMapper.writeValueAsString(listArrange));
        System.out.println("Write!!");
        objectMapper.writeValue(new FileOutputStream("src/main/java/sample/data/Arrangement.json"), listArrange);
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
        searchTest(combineDateString);// search by date

        dateShow.setText(combineDateString);
    }

    /**
     * Set the coach's photo in the front-end frame.
     *
     * @param photoURL The address of the coach's photo.
     */
    public void setCoachPhoto(String photoURL){
        MakeCenterImage makeCenterImage = new MakeCenterImage();
        coachPhoto.setClip(makeCenterImage.makeCenterImageCircle(67,coachPhoto,photoURL));
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
        getLoginStatus();
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
        InitMonthPicPath();
        setMonthPic();
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
     * Button-click event handler,jump to booking-center frame.
     */
    public void gotoBookingCenter() {
        application.gotoBooking();
    }

    /**
     * Button-click event handler,Jump to training-center frame.
     */
    public void gotoTrainingCenter() {
        application.gotoTrainingCenter();
    }

    /**
     * Button-click event handler,Jump to home frame.
     */
    public void gotoHome() {
        application.gotoHome();
    }

    /**
     * Button-click event handler,Jump to video-center frame.
     */
    public void gotoVideoCenter() {
        application.gotoVideoCenter();
    }

    /**
     * Button-click event handler,Jump to information-center frame.
     */
    public void gotoInformationCenter() {
        application.gotoInformationCenter();
    }

    /**
     * Button-click event handler,Jump to order-list frame.
     */
    public void gotoOrderList(){
        application.gotoOrderList();
    }

    /**
     * Read the LoginStatus.json file,get the current user of the system.
     */
    public void getLoginStatus() {
        File fileLoginStatus = new File("src/main/java/sample/data/LoginStatus.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            loginUserId = mapper.readValue(fileLoginStatus, new TypeReference<String>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Button-click event handler,Jump to VIP-Recharge frame.
     */
    public void gotoVIPRecharge(){
        application.gotoVIPRechargeCenter();
    }

    /**
     * Create an alert window, used when user try to subscribe an class.
     * When use choose cancel, the buttonBar's value will be set to No,
     * and if user choose ensure, the buttonBar's value will be set to Yes.
     * Baes on the value, the function will return true or false.
     *
     * @return Choose "cancel",return false; Choose "Ensure", return false.
     */
    public boolean initAlertOfSubScribe() {
        Alert _alert = new Alert(Alert.AlertType.CONFIRMATION, "Ensure this SubScribe?", new ButtonType("cancel", ButtonBar.ButtonData.NO), new ButtonType("ensure", ButtonBar.ButtonData.YES));
        _alert.setTitle("Ensure Subscribe");
        _alert.setHeaderText(null);
        Optional<ButtonType> buttonType = _alert.showAndWait();
        if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Initialize the pic paths of the calender header pictures.
     * By using Java IO, save the paths into a map.
     */
    public void InitMonthPicPath(){
        File filePath = new File(getClass().getResource("../pic/month").getPath());
        String path = filePath.toString();
        path = URLDecoder.decode(path, StandardCharsets.UTF_8);
        File file = new File(path);
        File[] files = file.listFiles();
        monthPicPath = new HashMap<Integer,String>();
        for(File f:files){					//遍历File[]数组
            if(!f.isDirectory()) {        //若非目录(即文件)，则放入map中
                monthPicPath.put(Integer.parseInt(f.getName().substring(0,f.getName().length()-4)),f.toURI().toString());
            }
        }
    }

    /**
     * Change the head picture when the month is changed.
     */
    public void setMonthPic(){
        String showPicPath = monthPicPath.get(monthText);
        calendarBack.setImage(new Image(showPicPath));
        calendarBack.setPreserveRatio(false);
    }
}
