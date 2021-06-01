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
import sample.controllerImpl.VIPRechargeCenterController;
import sample.controllerImpl.VideoController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * This class is a Test class use junit to Test the process of showing VIP
 *
 * @author Xiaojian Qi
 * @version 5.0
 */
public class VIPTest extends ApplicationTest {
    private VIPRechargeCenterController controller;
    private static String userNameTest;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/VIPrechargeCenter.fxml"));
        Parent mainNode = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }


    @BeforeClass
    public static void setUp() throws Exception {
        userNameTest = "\"kiniin\"";
        File file = new File("src/main/java/sample/data/LoginStatus.json");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(userNameTest);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("file filled!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.showStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    /**
     * there will show a Dialog, and VIP or not VIP is NOT same
     *
     * @throws InterruptedException
     */
    @Test
    public void changeVideoInList() throws InterruptedException {
        clickOn("#vip-ensure-btn");
        Thread.sleep(2000);
    }

}
