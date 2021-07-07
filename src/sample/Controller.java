package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label massagelable;
    @FXML
    private TextField usernameto;
    @FXML
    private TextField passwordto;


    public void switchToSignIn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void logIn(ActionEvent event) throws IOException {
        massagelable.setText(usernameto.getText()+"tride to log in" +
                "with pass "+passwordto.getText());
        System.out.println(usernameto.getText());
        System.out.println();
    }
    public void signIn(ActionEvent event) throws IOException {
        massagelable.setText(usernameto.getText()+"tride to sign in" +
                "with pass "+passwordto.getText());
        System.out.println(usernameto.getText());
        System.out.println();
    }

    public void exit(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("exit");
        alert.setHeaderText("You're about to exit!");
        alert.setContentText("Do you want to exit the game?");

        if (alert.showAndWait().get() == ButtonType.OK){
            System.out.println("You successfully exit ");
            stage.close();
        }



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
