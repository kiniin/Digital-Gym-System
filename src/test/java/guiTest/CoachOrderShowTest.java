package guiTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import sample.Main;
import sample.controllerImpl.CoachAllOrderController;
import sample.controllerImpl.CoachCenterController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is a Test class use junit to Test the process of Coach see all his course
 *
 * @author Xiaojian Qi
 * @version 5.0
 */
public class CoachOrderShowTest extends ApplicationTest {
    private CoachAllOrderController controller;
    private static String userNameTest;

    @Override
    public void start (Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/coachAllOrder.fxml"));
        Parent mainNode = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }


    //    no clear of loginStatus to verify the label text
    @BeforeClass
    public static void setUp() throws Exception {
        userNameTest = "\"jack\"";
        File file =new File("src/main/java/sample/data/LoginStatusCoach.json");
        try {
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write(userNameTest);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("file filled!");
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
    public void checkOrderOfCoach() throws InterruptedException {
        Thread.sleep(10000);
    }
}
