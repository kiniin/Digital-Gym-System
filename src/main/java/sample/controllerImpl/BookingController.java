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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sample.Main;
import sample.controller.GetLoginUserable;
import sample.controllerImpl.coachListComponent.CoachListComponent;
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
import java.text.ParseException;
import java.util.*;

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

    private int yearText;
    private int monthText;
    private ArrayList<ArrayList<Integer>> dateListList;
    private ArrayList<Integer> dateList;
    private ArrayList<Integer> monthList;
    private ArrayList<Integer> yearList;
    private String dateString;


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

    //尝试一下搜索
    public void searchTest(String keyword) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File arrangementFile = new File("src/main/java/sample/data/Arrangement.json");
//        ArrayList<ObservableList<String>> coachesItems = new ArrayList<ObservableList<String>>();

        // Arrangement arr = objectMapper.readValue(file, Arrangement.class);
        // User user = objectMapper.readValue(file, User.class);

        //Map<String, Object> jsonMap = objectMapper.readValue(file, new TypeReference<Map<String,Object>>(){});

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
            // 这里，传参给右下角的表格！！！！
            coachList.getChildren().clear();
            CoachListComponent component = null;
            for (int i = 0; i < listCoachName.size(); i++) {
                // 参数：字符串，教练的名字
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

    //TODO 查找教练在选定的这一天所有可用的课程时间，传给右上角的time下拉框
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
        // 得到了location，item，Coach等等参数，都可以显示在右上角了
//        System.out.println("Location:" + classContent.getLocation());
//       内部类必须使用final的字段
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
        // 要做一下容错，如果classContent没取到的话怎么硕？（应该也不会发生这种情况，因为searchTime函数给的肯定是合法的time）
    }


    //DONE 预定课程
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
        searchTest(combineDateString);// 按照日期搜索

        dateShow.setText(combineDateString);
    }

    public void setCoachPhoto(String photoURL){
        MakeCenterImage makeCenterImage = new MakeCenterImage();
        coachPhoto.setClip(makeCenterImage.makeCenterImageCircle(67,coachPhoto,photoURL));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getLoginStatus();
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
    }

    public void separateTime() throws ParseException {
        dateListList = calendarUtils.getTimeNumber(monthText, yearText, buttonDateList.size());
        dateList = dateListList.get(0);
        monthList = dateListList.get(1);
        yearList = dateListList.get(2);
    }

    public void gotoBookingCenter() {
        application.gotoBooking();
    }

    public void gotoTrainingCenter() {
        application.gotoTrainingCenter();
    }

    public void gotoHome() {
        application.gotoHome();
    }

    public void gotoVideoCenter() {
        application.gotoVideoCenter();
    }

    public void gotoInformationCenter() {
        application.gotoInformationCenter();
    }
    public void gotoOrderList(){
        application.gotoOrderList();
    }

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
    public void gotoVIPRecharge(){
        application.gotoVIPRechargeCenter();
    }

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
}
