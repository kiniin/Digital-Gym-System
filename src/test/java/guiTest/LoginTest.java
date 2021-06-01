package guiTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;
import sample.Main;
import sample.controllerImpl.HomeController;
import sample.controllerImpl.LoginController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * This class is a Test class use junit to Test the process of user login
 *
 * @author Xiaojian Qi
 * @version 5.0
 */
public class LoginTest extends ApplicationTest {
    private LoginController controller;

    @Override
    public void start (Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/login.fxml"));
        Parent mainNode = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }


    //    no clear of loginStatus to verify the label text
    @BeforeClass
    public static void setUp() throws Exception {
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.showStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void loginTest(){
        PasswordField passwordField = lookup("#password").query();
        TextField textField = lookup("#username").query();
        clickOn(textField);
        write("kiniin");
        clickOn(passwordField);
        write("sth16151");
//        need disable goto
        clickOn("#loginSubmit");
//        verify the test of login verify
        Assertions.assertThat((Label) lookup("#loginStatus").query()).hasText("Wrong Password or not existed user");
    }

}
