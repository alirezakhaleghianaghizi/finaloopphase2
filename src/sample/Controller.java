package sample;


import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import menus.Logger;

import java.io.IOException;
import java.util.Date;

public class Controller {
    public static MainController  mainController;
    MyFirstJDBC myFirstJDBC;
    public static Logger logger=new Logger();
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
        Controller.logger("ALARM","SWITCHING TO SIGN IN PAGE");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        Controller.logger("ALARM","SWITCHING TO LOG IN PAGE");
        stage.setScene(scene);
        stage.show();
    }
    public void switchToStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        Controller.logger("ALARM","SWITCHING TO START PAGE");
        stage.setScene(scene);
        stage.show();
    }
    public void logIn(ActionEvent event) throws IOException {
        this.mainController =new MainController();
        this.mainController.allLevels=this.mainController.allLevels.FILE.reloadLevels(this.mainController.allLevels);
            mainController.logger=Controller.logger;
        this.myFirstJDBC=new MyFirstJDBC(this.mainController);
        if(myFirstJDBC.reloadUser(usernameto.getText(),passwordto.getText())==-1){
            massagelable.setText("WRONG USERNAME OR PASSWORD");
            //massagelable.setStyle("-fx-background-color: #ff0000; ");
            Controller.logger("ERROR","WRONG USERNAME OR PASSWORD");
        }
        else if(myFirstJDBC.reloadUser(usernameto.getText(),passwordto.getText())==1) {
            massagelable.setText("YOU LOG IN");
            Controller.logger("ALARM","LOG IN");
            this.switchToGameBar(event);
        }
    }
    public void signIn(ActionEvent event) throws IOException {
        this.mainController =new MainController();
        this.mainController.allLevels=this.mainController.allLevels.FILE.reloadLevels(this.mainController.allLevels);
        mainController.logger=Controller.logger;
        this.myFirstJDBC=new MyFirstJDBC(this.mainController);
        if(myFirstJDBC.addUser(usernameto.getText(),passwordto.getText())==false){
            massagelable.setText("user already exist".toUpperCase());
            //massagelable.setStyle("-fx-background-color: #ff0000; ");
            Controller.logger("ERROR","user already exist".toUpperCase());
        }
        else {
            massagelable.setText("YOU SIGN IN");
            Controller.logger("ALARM","SIGN IN");
            this.switchToGameBar(event);
        }
    }

    public void exit(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("exit");
        alert.setHeaderText("You're about to exit!");
        alert.setContentText("Do you want to exit the game?");

        if (alert.showAndWait().get() == ButtonType.OK){
            System.out.println("You successfully exit ");
            FXMLLoader loader =new FXMLLoader(getClass().getResource("start.fxml"));
            root =loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.close();
            Controller.logger("Alarm".toUpperCase(),"succesfully exit".toUpperCase());
        }else{

            Controller.logger("WARNING".toUpperCase(),"NOT EXITED".toUpperCase());
        }
    }

    public static void logger(String kinde,String massage){
        Controller.logger.lastChange=new Date();
        Controller.logger.commands.add(kinde+","+Controller.logger.lastChange.toString()+","+massage+". ");
        Controller.logger.jasonWriter( Controller.logger);
    }


    public void switchToGameBar(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameBar.fxml"));
        try {
            root = loader.load();
            ControllGame controllGame = loader.getController();
            controllGame.mainController=this.mainController;
            controllGame.setUsernameLable();
            controllGame.buttonColor();
            //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }






}
