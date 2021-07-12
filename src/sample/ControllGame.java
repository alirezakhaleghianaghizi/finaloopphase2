package sample;

import controller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import menus.Logger;
import view.Timing;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

public class ControllGame {
        MainController mainController;
        MyFirstJDBC myFirstJDBC;
        private int LoggerPage;
        public static int sec,min;
        public static boolean state;
        public static Timer timer = new Timer();

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
        GamePage gamePage =new GamePage();

        public void setUsernameLable(){
                massageUser.setText(Controller.mainController.personsController.CurrentUser.userName);
                levellable.setText("LEVEL = "+Controller.mainController.personsController.CurrentUser.level);
        }
        public void buttonColor(){
                int maxLevel=Controller.mainController.personsController.CurrentUser.level;
                Button[] buttonLevel={a,b,c,d,e,f,g,h,l,m};
                for (int i = 0; i < buttonLevel.length; i++) {
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
                        Controller.logger=new Logger();
                        stage.setScene(scene);
                        stage.show();
                }
                else Controller.logger("WARNING".toUpperCase(),"NOT LOGGED OUT".toUpperCase());
        }
        public void check(ActionEvent event){
                System.out.println("ok");
        }

        public void level1Button(ActionEvent event){this.goingToLevel(event,1);}
        public void level2Button(ActionEvent event){this.goingToLevel(event,2);}
        public void level3Button(ActionEvent event){this.goingToLevel(event,3);}
        public void level4Button(ActionEvent event){this.goingToLevel(event,4);}
        public void level5Button(ActionEvent event){this.goingToLevel(event,5);}
        public void level6Button(ActionEvent event){this.goingToLevel(event,6);}
        public void level7Button(ActionEvent event){this.goingToLevel(event,7);}
        public void level8Button(ActionEvent event){this.goingToLevel(event,8);}
        public void level9Button(ActionEvent event){this.goingToLevel(event,9);}
        public void level10Button(ActionEvent event){this.goingToLevel(event,10);}

        public void goingToLevel(ActionEvent event,int level){
                if(level<=Controller.mainController.personsController.CurrentUser.level+1){
                        Controller.mainController.personsController.CurrentUser.currentLevel=Controller.mainController.allLevels.levels.get(level-1);
                        this.myFirstJDBC=new MyFirstJDBC(Controller.mainController);
                        this.myFirstJDBC.newUser();
                        this.newingMainControllerDetails();
                        gamePage =new GamePage();
                        Controller.logger("WARNING".toUpperCase(),"START LEVEL "+level);
                        ControllGame.min=0;
                        ControllGame.sec=0;
                        ControllGame.state=false;
                        ControllGame.timer=new Timer();
                        GamePage.animals=new HashMap<>();
                        switchingGamePage(event);
                }
                else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("lock");
                        alert.setHeaderText("not open level");
                        alert.setContentText(level+" is lock for you");
                        alert.show();
                        Controller.logger("ERROR".toUpperCase(),"NOT open level".toUpperCase()+" "+level);
                }
        }

        public void switchingGamePage(ActionEvent event){
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("gamePage.fxml"));
                        AnchorPane root1 = (AnchorPane)loader.load();
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root1);
                        Controller.logger("WARNING","GAME PAGE .");
                        GamePage gamePage = loader.getController();
                        gamePage.sec=ControllGame.sec;
                        gamePage.min=ControllGame.min;
                        gamePage.state=ControllGame.state;
                        gamePage.timer=ControllGame.timer;
                        gamePage.renew();
                        gamePage.showingAnimal(stage);
                        gamePage.showingGood(stage);
                        gamePage.reloadFactory();
                        Controller.mainController.personsController.CurrentUser.totalCoins=100000;
                        stage.setScene(scene);
                        stage.show();
                        gamePage.showingAnimal(stage);
                }
                catch (Exception e){
                        e.printStackTrace();
                }

        }

        public void resumeLastGame(ActionEvent event){
        Controller.logger("WARNING","RESUME GAME .");
        this.switchingGamePage(event);
        }



        public void newingMainControllerDetails(){
                Controller.mainController.goods=new ControllerGoods();
                Controller.mainController.factories=new ControllerFactory();
                Controller.mainController.animals=new ControllerAnimal();
                Controller.mainController.gadgets=new ControllerGadget();
                Controller.mainController.timing=new Timing();
                Controller.mainController.isTasksCompleted=false;
                MainController.cageTimeSet=new HashMap<>();
        }

        public void loggerPage(ActionEvent event){
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("loggerPage.fxml"));
                        root = loader.load();
                        LoggerGraphic loggerGraphic = loader.getController();
                        Controller.logger("ALARM","SWITCHING TO LOGGER PAGE");
                        loggerGraphic.mainController=Controller.mainController;
                        loggerGraphic.LoggerPage=0;
                        loggerGraphic.renewTextsLoger(Controller.logger.commands.size());
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                } catch (IOException ioException) {
                        ioException.printStackTrace();
                }

        }

}
