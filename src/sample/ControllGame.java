package sample;

import controller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import view.Timing;

import java.io.IOException;
import java.util.HashMap;

public class ControllGame {
        MainController mainController;
        MyFirstJDBC myFirstJDBC;
        private int LoggerPage;
        @FXML
        private Label massageUser;
        private Stage stage;
        private Scene scene;
        private Parent root;
        @FXML
        private Label levellable;
        @FXML
        private Button a;
        @FXML
        private Button b;
        @FXML
        private Button c;
        @FXML
        private Button d;
        @FXML
        private Button e;
        @FXML
        private Button f;
        @FXML
        private Button g;
        @FXML
        private Button h;
        @FXML
        private Button l;
        @FXML
        private Button m;
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

        public void setUsernameLable(){
                massageUser.setText(mainController.personsController.CurrentUser.userName);
                levellable.setText("LEVEL = "+mainController.personsController.CurrentUser.level);
        }
        public void buttonColor(){
                int maxLevel=this.mainController.personsController.CurrentUser.level;
                Button[] buttonLevel={a,b,c,d,e,f,g,h,l,m};
                for (int i = 0; i < buttonLevel.length; i++) {
                        System.out.println(i);
                        if(maxLevel<i) buttonLevel[i].setStyle("-fx-background-color: #ff0000; ");
                        else buttonLevel[i].setStyle("-fx-background-color: #00FFFF; ");
                }
        }
        public void switchToStart(ActionEvent event) throws IOException {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("LOG OUT");
                alert.setHeaderText("You're about to LOG OUT!");
                alert.setContentText("Do you want to LOG OUT the game?");
                if (alert.showAndWait().get() == ButtonType.OK){
                        root = FXMLLoader.load(getClass().getResource("start.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        Controller.logger("WARNING","LOG OUT");
                        stage.setScene(scene);
                        stage.show();
                }
                else Controller.logger("WARNING".toUpperCase(),"NOT LOGGED OUT".toUpperCase());
        }
        public void check(ActionEvent event){
                System.out.println("ok");
        }

        public void level1Button(ActionEvent event){this.goingToLevel(1);}
        public void level2Button(ActionEvent event){this.goingToLevel(1);}
        public void level3Button(ActionEvent event){this.goingToLevel(1);}
        public void level4Button(ActionEvent event){this.goingToLevel(1);}
        public void level5Button(ActionEvent event){this.goingToLevel(1);}
        public void level6Button(ActionEvent event){this.goingToLevel(1);}
        public void level7Button(ActionEvent event){this.goingToLevel(1);}
        public void level8Button(ActionEvent event){this.goingToLevel(1);}
        public void level9Button(ActionEvent event){this.goingToLevel(1);}
        public void level10Button(ActionEvent event){this.goingToLevel(1);}
        public void goingToLevel(int level){
                if(level<=this.mainController.personsController.CurrentUser.level+1){
                        this.newingMainControllerDetails();
                        Controller.logger("WARNING".toUpperCase(),"START LEVEL "+level);
                }
                else{
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("lock");
                        alert.setHeaderText("not open level");
                        alert.setContentText(level+" is lock for you");
                        Controller.logger("WARNING".toUpperCase(),"NOT open level".toUpperCase()+" "+level);
                }
        }
        public void loggerPage(ActionEvent event){
                try {
                        this.LoggerPage=0;
                        renewTextsLoger(Controller.logger.commands.size());
                        userLoger.setText(this.mainController.personsController.CurrentUser.userName);
                        lastChangDate.setText(Controller.logger.lastChange.toString());
                        root = FXMLLoader.load(getClass().getResource("loggerPage.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        Controller.logger("ALARM","SWITCHING TO SIGN IN PAGE");
                        stage.setScene(scene);
                        stage.show();
                } catch (IOException ioException) {
                        ioException.printStackTrace();
                }

        }
        public void homepage(ActionEvent event){

        }
        public void resumeLastGame(){

        }

        public void newingMainControllerDetails(){
                this.mainController.goods=new ControllerGoods();
                this.mainController.factories=new ControllerFactory();
                this.mainController.animals=new ControllerAnimal();
                this.mainController.gadgets=new ControllerGadget();
                this.mainController.timing=new Timing();
                this.mainController.isTasksCompleted=false;
                MainController.cageTimeSet=new HashMap<>();
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
        int index=gooNumber-LoggerPage*5-1;
        if(index>0) text1.setText(Controller.logger.commands.get(index));
        index=gooNumber-LoggerPage*5-2;
        if(index>0) text2.setText(Controller.logger.commands.get(index));
        index=gooNumber-LoggerPage*5-3;
        if(index>0) text3.setText(Controller.logger.commands.get(index));
        index=gooNumber-LoggerPage*5-4;
        if(index>0) text4.setText(Controller.logger.commands.get(index));
        index=gooNumber-LoggerPage*5-5;
        if(index>0) text5.setText(Controller.logger.commands.get(index));
        }

}
