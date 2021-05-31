package guiTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * This class is a Test class use junit to Test the process of homePage with user login
 *
 * @author Xiaojian Qi
 * @iteration 5.0
 */
public class HomePageTestWithLogin extends ApplicationTest {

    private HomeController controller;
    private static String userNameTest;

    @Override
    public void start (Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/homepage.fxml"));
        Parent mainNode = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }


    /**
     * Init the Login Status of the user
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
        userNameTest = "\"kiniin\"";
        File file =new File("src/main/java/sample/data/LoginStatus.json");
        try {
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write(userNameTest);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("file Clear!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.showStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }


    @Test
    public void theDisableOfTheSignOutBtnWhenNotLogin() throws InterruptedException {
//        check the out put when we login
        Assertions.assertThat((Label)lookup("#helloUser").query()).hasText("Hello! User "+userNameTest);
        Thread.sleep(2000);
    }
}
