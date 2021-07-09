package sample;

import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoggerGraphic {
    MainController mainController;
    MyFirstJDBC myFirstJDBC;
    public int LoggerPage;
    @FXML
    private Label massageUser;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button home;
    @FXML
    private Button next;
    @FXML
    private Button previous;
    @FXML
    private TextField text1;
    @FXML
    private TextField text2;
    @FXML
    private TextField text3;
    @FXML
    private TextField text4;
    @FXML
    private TextField text5;
    @FXML
    private TextField userLoger;
    @FXML
    private TextField lastChangDate;
    @FXML
    private TextField startDate;

    public void homepage(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gameBar.fxml"));
            root = loader.load();
            ControllGame controllGame = loader.getController();
            Controller.logger("ALARM","SWITCHING TO HOME PAGE");
            controllGame.mainController=this.mainController;
            controllGame.setUsernameLable();
            controllGame.buttonColor();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public void nextKey(){
        int goodNumbers=Controller.logger.commands.size();
        if(goodNumbers/5>LoggerPage){
            this.LoggerPage++;
            this.renewTextsLoger(goodNumbers);
        }
    }
    public void previousKey(){
        int goodNumbers=Controller.logger.commands.size();
        if(0<LoggerPage){
            this.LoggerPage--;
            this.renewTextsLoger(goodNumbers);
        }
    }
    public void renewTextsLoger(int gooNumber){
        userLoger.setText(this.mainController.personsController.CurrentUser.userName);
        lastChangDate.setText(Controller.logger.lastChange.toString());
        startDate.setText(Controller.logger.dateStartOperatingLogger.toString());
        int index=gooNumber-LoggerPage*5-1;
        if(index>0) text1.setText(Controller.logger.commands.get(index));
        else text1.setText("");
        index=gooNumber-LoggerPage*5-2;
        if(index>0) text2.setText(Controller.logger.commands.get(index));
        else text2.setText("");
        index=gooNumber-LoggerPage*5-3;
        if(index>0) text3.setText(Controller.logger.commands.get(index));
        else text3.setText("");
        index=gooNumber-LoggerPage*5-4;
        if(index>0) text4.setText(Controller.logger.commands.get(index));
        else text4.setText("");
        index=gooNumber-LoggerPage*5-5;
        if(index>0) text5.setText(Controller.logger.commands.get(index));
        else text5.setText("");
    }
}
