package graphic;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


















    /*@FXML
    private Circle my;
    private double x;
    private double y;

    public void up(ActionEvent e){
       // System.out.println("up");
        my.setCenterY(y-=10);
    }
     public void down(ActionEvent e){
         my.setCenterY(y+=10);
         // System.out.println("down");
    }
     public void left(ActionEvent e){
         my.setCenterX(x-=10);
         // System.out.println("left");
    }
     public void right(ActionEvent e){
         my.setCenterX(x+=10);
         // System.out.println("right");
    }*/





}