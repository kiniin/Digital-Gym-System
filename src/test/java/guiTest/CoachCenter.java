package guiTest;

import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import sample.Main;
import sample.controllerImpl.BookingController;
import sample.controllerImpl.CoachCenterController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class CoachCenter extends ApplicationTest {

    private CoachCenterController controller;
    private static String userNameTest;

    @Override
    public void start (Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/coachCenter.fxml"));
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

    /**
     * check the Arrange.json, when a new Arrangement exist
     * then it is correct but need to change the date button
     * to a new Arrangement not a same Arrangement
     * @throws InterruptedException
     */
    @Test
    public void addBookingOrder() throws InterruptedException {
//        check the Arrange.json
        clickOn("#date35");
        GridPane pane = lookup("#timePickerBox").query();
        JFXTimePicker jfxTimePicker = (JFXTimePicker) pane.getChildren().get(0);
        jfxTimePicker.setConverter(new LocalTimeStringConverter(FormatStyle.SHORT, Locale.ENGLISH));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        jfxTimePicker.setValue(LocalTime.parse(LocalTime.now().format(formatter)));
        ComboBox<String> locationInput = lookup("#locationInput").queryComboBox();
        ComboBox<String> sportItemInput = lookup("#sportItemInput").queryComboBox();
        locationInput.setValue("Arsenal");
        Thread.sleep(2000);
        clickOn("#subscribe-btn");
        clickOn("ensure");
        Thread.sleep(1000);
    }
}
