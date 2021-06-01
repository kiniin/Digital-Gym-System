package sample.controllerImpl.coachListComponent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import sample.utils.MakeCenterImage;

/**
 * This controller is to set the coach information into the javafx pages.
 *
 * @author Ruizheng Wu
 * @author Xiaojian Qi
 * @version 3.0
 */
public class CoachListController {

    @FXML
    private Label coachName;
    @FXML
    private ImageView coachPhoto;
    @FXML
    public Button subscribe;

    /**
     * Get the coach's name from the javafx label.
     * @return The text of the javafx label
     */
    public String getCoachName(){
        return this.coachName.getText();
    }

    /**
     * Set the javafx label's text, as the coach's name.
     * @param coachName The name of the Coach.
     */
    public void setCoachName(String coachName){
        this.coachName.setText(coachName);
    }

    /**
     * Set the javafx label's text, as the coach's photo URL address.
     * @param photoURL The name of the Coach.
     */
    public void setCoachPhoto(String photoURL) {
        MakeCenterImage makeCenterImage = new MakeCenterImage();
        coachPhoto.setClip(makeCenterImage.makeCenterImageCircle(67,coachPhoto,photoURL));
    }
}
