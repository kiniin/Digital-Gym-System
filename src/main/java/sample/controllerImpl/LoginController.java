package sample.controllerImpl;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;
import sample.controller.ReadTextFieldable;
import sample.pojo.User;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LoginController implements Initializable, ReadTextFieldable {

    private Main application;

    @FXML
    private Button loginToSignup;

    @FXML
    private Button loginSubmit;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label loginStatus;

    private User loginUser;

    public void setApp(Main application){
        this.application = application;
    }

    public void toSignupPage(){
        application.gotoSignup();
    }

    public void gotoHome(){
        application.gotoHome();
    }

    public User readTextField(){
        boolean loginFlag = false;
        String usernameInfo = username.getText();
        String passwordInfo = password.getText();
        loginUser = new User();
        loginUser.setUsername(usernameInfo);
        loginUser.setPassword(passwordInfo);
        if (loginFlag) {
            loginStatus.setText("Wrong Password or not existed user");
        }else{

            ObjectMapper mapper = new ObjectMapper();
            ArrayNode arrayNode = mapper.createArrayNode();
            //生成对象结点
            ObjectNode objNode = mapper.createObjectNode();
            objNode.put("username", usernameInfo);    /*在jdk1.8中，简单值用put设置*/
            objNode.put("loginStatus", true);    /*在jdk1.8中，子节点用set设置*/
            arrayNode.add(objNode);
            //写入
            try {
                OutputStream out = new FileOutputStream(new File("src/main/java/sample/file/loginStatus.json"));
                mapper.writeValue(out,arrayNode);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            application.gotoTrainingCenter();
        }
        System.out.println(loginUser.toString());
        return loginUser;
    }

    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
