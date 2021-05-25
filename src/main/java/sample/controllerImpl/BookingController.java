package sample.controllerImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import sample.Main;
import sample.pojo.Arrange;
import sample.pojo.User;
import sample.utils.CalendarUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

public class BookingController implements Initializable {

    private Main application;
    private CalendarUtils calendarUtils;

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
    private Label dateShow;

    private int yearText;
    private int monthText;
    private ArrayList<ArrayList<Integer>> dateListList;
    private ArrayList<Integer> dateList;
    private ArrayList<Integer> monthList;
    private ArrayList<Integer> yearList;
    private String dateString;


    public void addMonth() {
        if (monthText < 12 && monthText >= 1){
            minusMonth.setDisable(false);
            monthText = monthText + 1;
            labelMonth.setText(String.valueOf(monthText));
            try {
                separateTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            syncTime();
        }else {
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
        if (monthText <= 12 && monthText > 1){
            addMonth.setDisable(false);
            monthText = monthText - 1;
            labelMonth.setText(String.valueOf(monthText));
            try {
                separateTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            syncTime();
        }else {
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

    public void setApp(Main application){
        this.application = application;
    }


    public void syncTime(){
        buttonDateList = dateBox.getChildren();
        Button buttonInstance;
        for(int i = 0; i < buttonDateList.size(); i++){
            buttonInstance = (Button) buttonDateList.get(i);
            buttonInstance.setText(dateList.get(i).toString());
        }
    }

    //尝试一下搜索
    public void searchTest(String keyword) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("src/main/java/sample/data/Arrangement.json");

        // Arrangement arr = objectMapper.readValue(file, Arrangement.class);
        // User user = objectMapper.readValue(file, User.class);

        //Map<String, Object> jsonMap = objectMapper.readValue(file, new TypeReference<Map<String,Object>>(){});

        List<Arrange> listArrange = objectMapper.readValue(file, new TypeReference<List<Arrange>>() {});
        List listCoach = new ArrayList();

        // System.out.println(listArrange.get(0).getCourse().get(0).getCoach());
        for(int i=0;i<listArrange.size();i++){
            Arrange temp = listArrange.get(i);
            // date equals
            if(temp.getDate().equals(keyword) && temp.getUserId().equals("")){
                // Ensure no repetition in the coach list
                if(!listCoach.contains(temp.getCoach())){
                    listCoach.add(temp.getCoach());
                }
            }
        }
        if(listCoach.size() == 0){
            System.out.println("Today we have no classes!");
        } else {
            // 这里，传参给右下角的表格！！！！
            System.out.println(listCoach);
        }
        // 试一下searchTime
        searchTime("Tom","2021-5-28");
        // 试一下searchClass
        searchClass("Tom","2021-5-28","11:00");
        // 试一下预定
        bookCourse("1234","Tom","2021-5-28","10:00");
    }
    public void searchTime(String coach,String date) throws IOException {
        // 查找教练在选定的这一天所有可用的课程时间，传给右上角的time下拉框
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/java/sample/data/Arrangement.json");
        List<Arrange> listArrange = objectMapper.readValue(file, new TypeReference<List<Arrange>>() {});
        List listTime = new ArrayList();
        for(int i=0;i<listArrange.size();i++){
            Arrange temp = listArrange.get(i);
            // condition matches
            if(temp.getDate().equals(date) && temp.getCoach().equals(coach) && temp.getUserId().equals("")){
                listTime.add(temp.getTime());
            }
        }
        System.out.println("time: "+listTime);
    }
    public void searchClass(String coach,String date,String time) throws IOException {
        // 查找特定的一节课
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/java/sample/data/Arrangement.json");
        List<Arrange> listArrange = objectMapper.readValue(file, new TypeReference<List<Arrange>>() {});
        Arrange classContent = new Arrange();
        for(int i=0;i<listArrange.size();i++){
            Arrange temp = listArrange.get(i);
            // condition matches
            if(temp.getDate().equals(date) && temp.getCoach().equals(coach) && temp.getTime().equals(time) && temp.getUserId().equals("")){
                classContent = temp;
                break;
            }
        }
        // 得到了location，item，Coach等等参数，都可以显示在右上角了
        System.out.println("Location:" + classContent.getLocation());
        // 要做一下容错，如果classContent没取到的话怎么硕？（应该也不会发生这种情况，因为searchTime函数给的肯定是合法的time）
    }

    // 预定课程
    public void bookCourse(String userId,String coach,String date,String time) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/java/sample/data/Arrangement.json");
        List<Arrange> listArrange = objectMapper.readValue(file, new TypeReference<List<Arrange>>() {});
        Arrange classContent = new Arrange();
        for(int i=0;i<listArrange.size();i++){
            Arrange temp = listArrange.get(i);
            // condition matches
            if(temp.getDate().equals(date) && temp.getCoach().equals(coach) && temp.getTime().equals(time) && temp.getUserId().equals("")){
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
        Button ButtonClicked = (Button)event.getSource();
        String dateString = null;
        String monthString = null;
        String yearString = null;
        for (int i=0; i<buttonDateList.size(); i++) {
            if (ButtonClicked.equals((Button) buttonDateList.get(i))) {
                dateString = dateList.get(i).toString();
                monthString = monthList.get(i).toString();
                yearString = yearList.get(i).toString();
            }
        }
        String combineDateString = yearString+"-"+monthString+"-"+dateString;

        System.out.println(combineDateString);
        // 根据选择的日期搜索【排班表&日程表】，找到对应日期的所有教练，返回
        // 这个【排班表&日程表】还可以给管理员、老板用，进行排班管理等等
        String test = "test";
        searchTest(combineDateString);// 按照日期搜索

        dateShow.setText(combineDateString);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        dateShow.setText(yearText+"-"+monthText+"-"+ calendarUtils.getTodayDate());
        syncTime();
    }

    public void separateTime() throws ParseException {
        dateListList = calendarUtils.getTimeNumber(monthText,yearText,buttonDateList.size());
        dateList = dateListList.get(0);
        monthList = dateListList.get(1);
        yearList = dateListList.get(2);
    }
    public void gotoBookingCenter(){
        application.gotoBooking();
    }
    public void gotoUserCenter(){
        application.gotoUserCenter();
    }
    public void gotoHome(){
        application.gotoHome();
    }
}
