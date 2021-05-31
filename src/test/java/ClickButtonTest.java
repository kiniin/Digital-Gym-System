import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxToolkit;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.service.finder.NodeFinder;
import org.testfx.service.query.NodeQuery;
import org.testfx.util.WaitForAsyncUtils;
import sample.Main;
import sample.controllerImpl.*;

import java.io.IOException;
import java.io.InputStream;

public class ClickButtonTest extends ApplicationTest {

    private TrainingCenterController controller;



    @Override
    public void start (Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/TrainingCenter.fxml"));
        Parent mainNode = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }


    @Before
    public void setUp () throws Exception {
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.showStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

//    @Test
//    public void TrainingCenterControllerResetProgressBtnTest(){
//
//
////        如果有fx:id就用fx:id,有css id优先使用css id
//        clickOn("#reset-progress");
//
//        Assertions.assertThat(lookup("#reset-progress").tryQuery()).isNotEmpty();
//    }

    /**
     * test when click toright button the left button is disabled
     */
    @Test
    public void TrainingCenterControllerToggleDisabledTest() throws InterruptedException {
//        如果有fx:id就用fx:id,有css id优先使用css id
        clickOn("#toright");
        Assertions.assertThat((Button) lookup("#toleft").query()).isDisabled();
    }
    /**
     * test the last watch video date is correct
     *
     * last watch video date: 2021-05-21
     * expect output: 21
     */
    @Test
    public void TrainingCenterControllerResetProgressBtnTest(){
        Assertions.assertThat((Label) lookup("#trainingtime").query()).hasText("21");
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
