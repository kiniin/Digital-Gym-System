package guiTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
import sample.controllerImpl.LoginController;
import sample.controllerImpl.SignUpController;

public class SignUpTest extends ApplicationTest {
    private SignUpController controller;

    @Override
    public void start (Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/signup.fxml"));
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
    public void signUpTestEmail(){
        PasswordField passwordField = lookup("#password").query();
        TextField textField = lookup("#username").query();
        clickOn(textField);
        write("kinii");
        clickOn(passwordField);
        write("sth161das5");
        clickOn("#email");
        write("21345125");
        clickOn("#phone");
        write("13051621788");
//        need disable goto
        clickOn("#signUpSubmit");
//        verify the test of login verify
        Assertions.assertThat((Label) lookup("#SignUpStatus").query()).hasText("Please enter the correct email!");
    }
    @Test
    public void signUpTestOfUsername(){
        PasswordField passwordField = lookup("#password").query();
        TextField textField = lookup("#username").query();
        clickOn(textField);
        write("kiniin");
        clickOn(passwordField);
        write("sth1615");
        clickOn("#email");
        write("21345125");
        clickOn("#phone");
        write("13051621788");
//        need disable goto
        clickOn("#signUpSubmit");
//        verify the test of login verify
        Assertions.assertThat((Label) lookup("#SignUpStatus").query()).hasText("Username already exists!");
    }
    @Test
    public void signUpTestOfPassword(){
        PasswordField passwordField = lookup("#password").query();
        TextField textField = lookup("#username").query();
        clickOn(textField);
        write("kiniidas");
        clickOn(passwordField);
        write("sth1615");
        clickOn("#email");
        write("21345125@qq.com");
        clickOn("#phone");
        write("13051621788");
//        need disable goto
        clickOn("#signUpSubmit");
//        verify the test of login verify
        Assertions.assertThat((Label) lookup("#SignUpStatus").query()).hasText("Password must be more than eight numbers or letters!");
    }

    @Test
    public void signUpTestOfPhone(){
        PasswordField passwordField = lookup("#password").query();
        TextField textField = lookup("#username").query();
        clickOn(textField);
        write("kiniidas");
        clickOn(passwordField);
        write("sth1615daw");
        clickOn("#email");
        write("21345125@qq.com");
        clickOn("#phone");
        write("1305162178a");
//        need disable goto
        clickOn("#signUpSubmit");
//        verify the test of login verify
        Assertions.assertThat((Label) lookup("#SignUpStatus").query()).hasText("Phone number must be numbers!");
    }
}
