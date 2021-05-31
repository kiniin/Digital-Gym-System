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

public class HomePageTestWithLogin extends ApplicationTest {

    private HomeController controller;

    @Override
    public void start (Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/homepage.fxml"));
        Parent mainNode = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }


//    no clear of loginStatus to verify the label text
    @BeforeClass
    public static void setUp() throws Exception {
        File file =new File("src/main/java/sample/data/LoginStatus.json");
        try {
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
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
        Assertions.assertThat((Label)lookup("#helloUser").query()).isInvisible();
    }
}
