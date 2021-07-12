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
import model.goods.Goods;
import model.goods.GoodsEnum;
import view.InputProcessor;
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
        public InputProcessor inputProcessor=new InputProcessor(Controller.mainController);
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
        private Label egglable;
        @FXML
        private Label flourlable;
        @FXML
        private Label milklable;
        @FXML
        private Label bakerylable;
        @FXML
        private Label seperatedmilk;
        @FXML
        private Label silk;
        @FXML
        private Label cloth;
        @FXML
        private Label icecream;
        @FXML
        private Label beardoll;
        @FXML
        private Label liondoll;
        @FXML
        private Label tigerdoll;
        @FXML
        private Label feather;

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
        public void loadAllWareHouse(){
                egglable.setText(Integer.toString(Controller.mainController.gadgets.warehouse.eggInWareHouse.size()));
                feather.setText(Integer.toString(Controller.mainController.gadgets.warehouse.featherInWareHouse.size()));
                flourlable.setText(Integer.toString(Controller.mainController.gadgets.warehouse.flourInWareHouse.size()));
                milklable.setText(Integer.toString(Controller.mainController.gadgets.warehouse.milkInWareHouse.size()));
                seperatedmilk.setText(Integer.toString(Controller.mainController.gadgets.warehouse.sepratedMilkInWareHouse.size()));
                bakerylable.setText(Integer.toString(Controller.mainController.gadgets.warehouse.cookieInWareHouse.size()));
                icecream.setText(Integer.toString(Controller.mainController.gadgets.warehouse.iceCreamInWareHouse.size()));
                silk.setText(Integer.toString(Controller.mainController.gadgets.warehouse.silkInWareHouse.size()));
                cloth.setText(Integer.toString(Controller.mainController.gadgets.warehouse.clothInWareHouse.size()));
                tigerdoll.setText(Integer.toString(Controller.mainController.gadgets.warehouse.tigerDollInWareHouse.size()));
                liondoll.setText(Integer.toString(Controller.mainController.gadgets.warehouse.lionDollInWareHouse.size()));
                beardoll.setText(Integer.toString(Controller.mainController.gadgets.warehouse.bearDollInWareHouse.size()));

        }




        public String inTruck(String name){
                int size=0;
                for (Goods truckgood : Controller.mainController.gadgets.truckgoods) {
                        if(truckgood.name.equalsIgnoreCase(name))size++;
                }
                return Integer.toString(size);
        }
        public void loadTruck(){
                egglable.setText(inTruck(GoodsEnum.EGG.toString()));
                bakerylable.setText(inTruck(GoodsEnum.COOKIE.toString()));
                flourlable.setText(inTruck(GoodsEnum.FLOUR.toString()));
                milklable.setText(inTruck(GoodsEnum.MILK.toString()));
                seperatedmilk.setText(inTruck(GoodsEnum.SEPARATEDMILK.toString()));
                liondoll.setText(inTruck(GoodsEnum.LIONDOLL.toString()));
                beardoll.setText(inTruck(GoodsEnum.BEARDOLL.toString()));
                tigerdoll.setText(inTruck(GoodsEnum.TIGERDOLL.toString()));
                feather.setText(inTruck(GoodsEnum.FEATHER.toString()));
                silk.setText(inTruck(GoodsEnum.SILK.toString()));
                cloth.setText(inTruck(GoodsEnum.CLOTH.toString()));
                icecream.setText(inTruck(GoodsEnum.ICECREAM.toString()));
        }
        public void loadEgg(){
                inputProcessor.truckLoad("EGG");
                loadAllWareHouse();
        }
        public void loadFeather(){
                inputProcessor.truckLoad("FEATHER");
                loadAllWareHouse();
        }
        public void loadFlour(){
                inputProcessor.truckLoad("FLOUR");
                loadAllWareHouse();
        }
        public void loadMilk(){
                inputProcessor.truckLoad("MILK");
                loadAllWareHouse();
        }
        public void loadSeparated(){
                inputProcessor.truckLoad("SEPARATEDMILK");
                loadAllWareHouse();
        }
        public void loadBakery(){
                inputProcessor.truckLoad("COOKIE");
                loadAllWareHouse();
        }
        public void loadIceCream(){
                inputProcessor.truckLoad("ICECREAM");
                loadAllWareHouse();
        }
        public void loadSilk(){
                inputProcessor.truckLoad("SILK");
                loadAllWareHouse();
        }
        public void loadCloth(){
                inputProcessor.truckLoad("CLOTH");
                loadAllWareHouse();
        }
        public void loadBear(){
                inputProcessor.truckLoad("BEARDOLL");
                loadAllWareHouse();
        }
        public void loadLion(){
                inputProcessor.truckLoad("LIONDOLL");
                loadAllWareHouse();
        }
        public void loadTiger(){
                inputProcessor.truckLoad("TIGERDOLL");
                loadAllWareHouse();
        }

        public void unloadEgg(){
                inputProcessor.processTruckUnload("EGG");
                loadTruck();
        }
        public void unloadFeather(){
                inputProcessor.processTruckUnload("FEATHER");
                loadTruck();
        }
        public void unloadFlour(){
                inputProcessor.processTruckUnload("FLOUR");
                loadTruck();
        }
        public void unloadMilk(){
                inputProcessor.processTruckUnload("MILK");
                loadTruck();
        }
        public void unloadSeparated(){
                inputProcessor.processTruckUnload("SEPARATEDMILK");
                loadTruck();
        }
        public void unloadBakery(){
                inputProcessor.processTruckUnload("COOKIE");
                loadTruck();
        }
        public void unloadIceCream(){
                inputProcessor.processTruckUnload("ICECREAM");
                loadTruck();
        }
        public void unloadSilk(){
                inputProcessor.processTruckUnload("SILK");
                loadTruck();
        }
        public void unloadCloth(){
                inputProcessor.processTruckUnload("CLOTH");
                loadTruck();
        }
        public void unloadBear(){
                inputProcessor.processTruckUnload("BEARDOLL");
                loadTruck();
        }
        public void unloadLion(){
                inputProcessor.processTruckUnload("LIONDOLL");
                loadTruck();
        }
        public void unloadTiger(){
                inputProcessor.processTruckUnload("TIGERDOLL");
                loadTruck();
        }

        public  void truckGo(){
                inputProcessor.truckGo();
                this.loadTruck();
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
