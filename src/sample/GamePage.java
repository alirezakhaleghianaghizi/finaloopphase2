package sample;

import controller.MainController;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePage {
    MainController mainController;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ImageView weel;
    public void weel(){
        FadeTransition fade = new FadeTransition();
        fade.setNode(weel);
        fade.setDuration(Duration.millis(500));
        fade.setCycleCount(16);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(0.70);
        fade.setToValue(1);
        fade.play();
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(weel);
        scale.setDuration(Duration.millis(500));
        scale.setCycleCount(16);
        scale.setInterpolator(Interpolator.LINEAR);
        scale.setByX(0.3);
        scale.setByY(0.3);
        scale.setAutoReverse(true);
        scale.play();
    }

}
