package guiTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;
import sample.Main;
import sample.controllerImpl.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * This class is a Test class use junit to Test the process of showing user Training center's data
 *
 * @author Xiaojian Qi
 * @version 5.0
 */
public class TrainingCenterTest extends ApplicationTest {

    private TrainingCenterController controller;
    private static String userNameTest;



    @Override
    public void start (Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/TrainingCenter.fxml"));
        Parent mainNode = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }


    @BeforeClass
    public static void setUp () throws Exception {
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
    public void TrainingCenterControllerToggleDisabledTest() throws InterruptedException {
//        ?????????fx:id??????fx:id,???css id????????????css id
        clickOn("#toright");
        Assertions.assertThat((Button) lookup("#toleft").query()).isDisabled();
    }
    /**
     * test the last watch video date is correct
     *
     * last watch video date: 2021-05-10
     * expect output: 21
     */
    @Test
    public void TrainingCenterControllerResetProgressBtnTest(){
        Assertions.assertThat((Label) lookup("#trainingtime").query()).hasText("2");
    }
    /**
     * test the value of progress bar is correct
     *
     * the progress of hip training is 0.25
     * expect output: 0.25
     */
    @Test
    public void TrainingCenterControllerProgress(){
        ProgressBar hipProgress = (ProgressBar)lookup("#hipTraining").query();
        Assertions.assertThat(hipProgress.getProgress()).isEqualTo(0.25);
    }
}
