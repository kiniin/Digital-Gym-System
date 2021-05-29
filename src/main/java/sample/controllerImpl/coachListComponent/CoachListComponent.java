package sample.controllerImpl.coachListComponent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.pojo.Arrange;
import sample.pojo.Coach;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CoachListComponent extends AnchorPane {

    public CoachListController controller;


    public CoachListComponent(String coachName) throws IOException {
        // String coachPhoto = coach.getPhotoURL();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/java/sample/data/Coach.json");
        List<Coach> listCoach = objectMapper.readValue(file, new TypeReference<List<Coach>>() {
        });
        Coach oneCoach = null;
        for(int i=0;i<listCoach.size();i++){
            if(listCoach.get(i).getName().equals(coachName)){
                oneCoach = listCoach.get(i);
            }
        }
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("coachList.fxml"));
            Parent root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            this.getChildren().add(root);
            controller.setCoachName(oneCoach.getName());
            controller.setCoachPhoto(oneCoach.getPhotoURL());
//            controller.setSportItem(sportItem);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            // 教练容错~~~~~
            System.out.println("this couch is not exist or there is problem in Arrangement.json file");
        }
    }
}
