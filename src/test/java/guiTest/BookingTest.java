package guiTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import sample.controllerImpl.BookingController;
import sample.controllerImpl.HomeController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * This class is a Test class use junit to Test the process of booking
 *
 * @author Xiaojian Qi
 * @version 5.0
 */
public class BookingTest extends ApplicationTest {

    private BookingController controller;
    private static String userNameTest;

    @Override
    public void start (Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/booking.fxml"));
        Parent mainNode = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }


    //    no clear of loginStatus to verify the label text
    @BeforeClass
    public static void setUp() throws Exception {
        userNameTest = "\"kiniin\"";
        File file =new File("src/main/java/sample/data/LoginStatus.json");
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

    /**
     * check the Arrange.json, when the field of userId is kiniin then it is correct
     * @throws InterruptedException
     */
    @Test
    public void bookingAnCourse() throws InterruptedException {
//        check the Arrange.json
        clickOn("#date1");
        ScrollPane bookingBox = (ScrollPane) lookup("#bookingBox").query();
        bookingBox.setVvalue(1);
        clickOn("#submit-order");
        clickOn("ensure");
        Thread.sleep(2000);
    }
}
