package sample.controllerImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Main;
import sample.controller.ReadTextFieldable;
import sample.pojo.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController implements Initializable, ReadTextFieldable {

    private Main application;

    @FXML
    private Button loginToSignup;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private Label SignUpStatus;

    private User signUpUser;

    public void setApp(Main application){
        this.application = application;
    }

    public void toLoginPage(){
        application.gotoLogin();
    }

    public void gotoHome(){
        application.gotoHome();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    // this function can get the text in the textfield when you click this button
    public User readTextField() {
        boolean SignUpFlag = true;
        String warning = "";
        String usernameInfo = username.getText();
        String passwordInfo = password.getText();
        String emailInfo = email.getText();
        String phoneInfo = phone.getText();
        System.out.println(usernameInfo+" "+passwordInfo+ " "+ emailInfo+ " " + phoneInfo);

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/java/sample/data/User.json");

        try {
            List<User> users = objectMapper.readValue(file, new TypeReference<List<User>>(){});
            //Non-empty check
            if (usernameInfo.equals("") || passwordInfo.equals("") || emailInfo.equals("") || phoneInfo.equals("")){
                SignUpFlag = false;
                warning = "Information cannot be empty";
            }else{
                //Password Check
                String checkPassword = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
                Pattern regexPassord = Pattern.compile(checkPassword);
                Matcher matcher1 = regexPassord.matcher(passwordInfo);
                if(!matcher1.matches()){
                    SignUpFlag = false;
                    warning = "Password must be more than eight numbers or letters!";
                }

                //Email Check
                String checkEmail = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                Pattern regexEmail = Pattern.compile(checkEmail);
                Matcher matcher2 = regexEmail.matcher(emailInfo);
                if(!matcher2.matches()){
                    SignUpFlag = false;
                    warning = "Please enter the correct email!";
                }
                //PhoneNum Check

                for (int i = phoneInfo.length(); i > 0; i--) {
                    if (!Character.isDigit(phoneInfo.charAt(i-1))) {
                        SignUpFlag = false;
                        warning = "Phone number must be numbers!";
                    }
                }

                if (!(phoneInfo.length() == 10 || phoneInfo.length() == 11)){
                    SignUpFlag = false;
                    warning = "Please enter the correct phone number!";
                }

                //User name uniqueness test
                for (int i = 0; i < users.size(); i++){
                    if (usernameInfo.equals(users.get(i).getUsername())){
                        SignUpFlag = false;
                        warning = "Username already exists!";
                        break;
                    }
                }
            }
            if (SignUpFlag){
                signUpUser = new User();
                signUpUser.setPassword(passwordInfo);
                signUpUser.setUsername(usernameInfo);
                signUpUser.setEmail(emailInfo);
                signUpUser.setPhone(phoneInfo);
                users.add(signUpUser);
                objectMapper.writeValue(new FileOutputStream("src/main/java/sample/data/User.json"), users);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (SignUpFlag){
            application.gotoLogin();
            return signUpUser;
        }else{
            SignUpStatus.setText(warning);
        }
        return null;
    }
}
