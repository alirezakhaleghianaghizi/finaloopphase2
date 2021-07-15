package sample;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            FXMLLoader loader =new FXMLLoader(getClass().getResource("start.fxml"));
            Parent root = loader.load();
            Scene scene =new Scene(root);
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void playMusic(String url){
        String uriString = new File(url).toURI().toString();
        MediaPlayer Player = new MediaPlayer(new Media(uriString));
        Player.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
