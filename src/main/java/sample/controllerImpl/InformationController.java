package sample.controllerImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import sample.Main;
import sample.pojo.User;
import sample.utils.MakeCenterImage;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InformationController implements Initializable {

    private Main application;

    @FXML
    private Label currentDateLabel;
    private String currentDate;

    @FXML
    private ImageView userhead;
    @FXML
    private Label username;
    @FXML
    private Label phoneNum;
    @FXML
    private Label email;
    @FXML
    private PasswordField password;
    private String passwordStr;

    @FXML
    private TextField height;
    @FXML
    private TextField weight;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton male;
    @FXML
    private DatePicker birthday;

    private String LoginUser = "";


    public void gotoBookingCenter(){
        application.gotoBooking();
    }
    public void gotoHome(){
        application.gotoHome();
    }
    public void gotoVideoCenter(){
        application.gotoVideoCenter();
    }
    public void gotoTrainingCenter() {
        application.gotoTrainingCenter();
    }
    public void gotoInformationCenter(){
        application.gotoInformationCenter();
    }
    public void gotoOrderList(){
        application.gotoOrderList();
    }

    public void setApp(Main application){
        this.application = application;
    }

    public void changeInfo(){
        boolean ChangeInfo = true;
        String passwordInfo = password.getText();

        String checkPassword = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        Pattern regexPassord = Pattern.compile(checkPassword);
        Matcher matcher1 = regexPassord.matcher(passwordInfo);
        if(!matcher1.matches()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Password must be more than eight numbers or letters!");
            alert.showAndWait();
            ChangeInfo = false;
            application.gotoInformationCenter();
        }

        String genderInfo = "";
        if (male.isSelected()){
            genderInfo = "male";
        }else if (female.isSelected()){
            genderInfo = "female";
        }

        ZonedDateTime zonedDateTime = birthday.getValue().atStartOfDay(ZoneId.systemDefault());
        Date birthdayInfo = Date.from(zonedDateTime.toInstant());

        String weightInfo = weight.getText();
        String heightInfo = height.getText();

        if (ChangeInfo){
            ObjectMapper objectMapper = new ObjectMapper();
            File Userfile = new File("src/main/java/sample/data/User.json");
            try {
                List<User> users = objectMapper.readValue(Userfile, new TypeReference<List<User>>(){});
                for (int i = 0; i < users.size(); i++){
                    if (LoginUser.equals(users.get(i).getUsername())){
                        users.get(i).setPassword(passwordInfo);
                        users.get(i).setGender(genderInfo);
                        users.get(i).setBirthday(birthdayInfo);
                        users.get(i).setWeight(weightInfo);
                        users.get(i).setHeight(heightInfo);
                        break;
                    }
                }
                objectMapper.writeValue(new FileOutputStream("src/main/java/sample/data/User.json"), users);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Information updated successfully!");
            alert.showAndWait();
            application.gotoInformationCenter();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        currentDate = dateFormat.format(new Date());
        currentDateLabel.setText(currentDate);
        File file = new File("src/main/java/sample/pic/example1.jpg");
        String path = file.toURI().toString();
//        new utils
        MakeCenterImage makeCenterImage = new MakeCenterImage();
        userhead.setClip(makeCenterImage.makeCenterImageCircle(130, userhead, path));

        try {
            BufferedReader in = new BufferedReader(new FileReader("src/main/java/sample/data/LoginStatus.json"));
            if ((LoginUser = in.readLine()) != null){
                LoginUser= LoginUser.replace("\"", "");
                ObjectMapper objectMapper = new ObjectMapper();
                File Userfile = new File("src/main/java/sample/data/User.json");
                List<User> users = objectMapper.readValue(Userfile, new TypeReference<List<User>>(){});
                for (int i = 0; i < users.size(); i++){
                    if (LoginUser.equals(users.get(i).getUsername())){
                        username.setText(LoginUser);
                        password.setText(users.get(i).getPassword());
                        phoneNum.setText(users.get(i).getPhone());
                        email.setText(users.get(i).getEmail());
                        if (users.get(i).getBirthday() != null){
                            birthday.setValue(users.get(i).getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        }
                        weight.setText(users.get(i).getWeight());
                        height.setText(users.get(i).getHeight());
                        if (users.get(i).getGender() != null){
                            if (users.get(i).getGender().equals("female")){
                                female.setSelected(true);
                            }else if (users.get(i).getGender().equals("male")){
                                male.setSelected(true);
                            }
                        }
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
