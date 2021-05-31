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
import sample.controllerImpl.VideoController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * This class is a Test class use junit to Test the process of showing video and video jump
 *
 * @author Xiaojian Qi
 * @iteration 5.0
 */
public class VideoTest extends ApplicationTest {
    private VideoController controller;
    private static String userNameTest;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/video.fxml"));
        Parent mainNode = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }


    //    no clear of loginStatus to verify the label text
    @BeforeClass
    public static void setUp() throws Exception {
        userNameTest = "\"Hip training\"";
        File file = new File("src/main/java/sample/data/VideoStatus.json");
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
     * Test the video jump and video play
     *
     * @throws InterruptedException
     */
    @Test
    public void changeVideoInList() throws InterruptedException {
        Thread.sleep(5000);
        clickOn("#19");
        Thread.sleep(5000);
    }

    /**
     * Test the video jump and list jump
     *
     * @throws InterruptedException
     */
    @Test
    public void changeList() throws InterruptedException {
        Thread.sleep(5000);
        clickOn("#HIIT");
        Thread.sleep(5000);
    }
}
