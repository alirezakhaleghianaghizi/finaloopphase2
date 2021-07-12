package sample;

import controller.MainController;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import model.factory.FactoryMoney;
import view.InputProcessor;
import model.animal.AnimalEnum;

public class GamePage {

    @FXML
    private AnchorPane root1;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label label;
    @FXML
    private ImageView weel;
    @FXML
    private ImageView bakery;
    @FXML
    private ImageView spinnery;
    @FXML
    private ImageView eggPowder;
    @FXML
    private ImageView iceCream;
    @FXML
    private ImageView chickennery;
    @FXML
    private ImageView weaving;
    @FXML
    private ImageView milkSeperator;
    @FXML
    private Button bakeryBuild;
    @FXML
    private Button spinneryBuild;
    @FXML
    private Button eggPowderBuild;
    @FXML
    private Button iceCreamBuild;
    @FXML
    private Button chickenneryBuild;
    @FXML
    private Button weavingBuild;
    @FXML
    private Button milkSeperatorBuild;
    @FXML
    TextField timeText;
@FXML
    Button puse;
    public static int sec=0 ;
    public static int min=0;
    public static boolean state=false;
    public static Timer timer = new Timer();
    public static HashMap<String,ArrayList<ImageView>> animals=new HashMap<>();
    public static HashMap<String,ArrayList<ImageView>> wildeAnimal=new HashMap<>();
    public static HashMap<String,ArrayList<ImageView>> goods=new HashMap<>();
    public InputProcessor inputProcessor=new InputProcessor(Controller.mainController);

    TimerTask task;

        public void setPuse(ActionEvent event){
            if(GamePage.state==false){
                this.start(event);
                puse.setText("puse");
            } else if(GamePage.state==true){
                this.stop(event);
                puse.setText("start");
            }
        }

    public void renew1() {
            task = new TimerTask() {
        @Override
        public void run() {
            try{

              //  if(sec==3) addEgg();
                GamePage.state=true;
                GamePage.sec++;

                if(GamePage.sec<60) {
                    if(GamePage.min<10){
                        if(GamePage.sec<10) timeText.setText(" 0"+GamePage.min+" : 0"+GamePage.sec);
                        else timeText.setText(" 0"+GamePage.min+" : "+GamePage.sec);
                    }
                    else{
                        if(GamePage.sec<10) timeText.setText(" "+GamePage.min+" : 0"+GamePage.sec);
                        else timeText.setText(" "+GamePage.min+" : "+GamePage.sec);
                    }
                }
                else {
                    GamePage.sec=0;
                    GamePage.min++;
                    if(GamePage.min<10){
                        if(GamePage.sec<10) timeText.setText(" 0"+GamePage.min+" : 0"+GamePage.sec);
                        else timeText.setText(" 0"+GamePage.min+" : "+GamePage.sec);
                    }
                    else{
                        if(GamePage.sec<10) timeText.setText(" "+GamePage.min+" : 0"+GamePage.sec);
                        else timeText.setText(" "+GamePage.min+" : "+GamePage.sec);
                    }
                }
            }catch (Exception e){e.printStackTrace();}
        }
    };
}



    public void renew() {
        try{ if(GamePage.sec<60) {
            if(GamePage.min<10){
                if(GamePage.sec<10) timeText.setText(" 0"+GamePage.min+" : 0"+GamePage.sec);
                else timeText.setText(" 0"+GamePage.min+" : "+GamePage.sec);
            }
            else{

                if(GamePage.sec<10) timeText.setText(" "+GamePage.min+" : 0"+GamePage.sec);
                else timeText.setText(" "+GamePage.min+" : "+GamePage.sec);
            }

        }
        else {
            GamePage.sec=0;
            GamePage.min++;
            if(GamePage.min<10){
                if(GamePage.sec<10) timeText.setText(" 0"+GamePage.min+" : 0"+GamePage.sec);
                else timeText.setText(" 0"+GamePage.min+" : "+GamePage.sec);
            }
            else{

                if(GamePage.sec<10) timeText.setText(" "+GamePage.min+" : 0"+GamePage.sec);
                else timeText.setText(" "+GamePage.min+" : "+GamePage.sec);
            }
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public  void start(ActionEvent event) {
            GamePage.timer=new Timer();
            this.renew1();
            GamePage.timer.scheduleAtFixedRate(task, 0, 1000);
        }


    public void stop(ActionEvent event){
        GamePage.state=false;
        GamePage.timer.cancel();
    }


    //TODO mach with back work factory
    public void weavingBuild(){if(GamePage.state&&inputProcessor.build("WEAVING"))this.buildWork(weavingBuild); }
    public void eggPowderBuild(){if(GamePage.state&&inputProcessor.build("EGGPOWDER"))this.buildWork(eggPowderBuild); }
    public void milkSeperatorBuild(){if(GamePage.state&&inputProcessor.build("MILKSEPRATOR"))this.buildWork(milkSeperatorBuild); }
    public void bakeryBuild(){if(GamePage.state&&inputProcessor.build("COOKIEFACTORY"))this.buildWork(bakeryBuild); }
    public void spinneryBuild(){if(GamePage.state&&inputProcessor.build("SPINNERY"))this.buildWork(spinneryBuild); }
    public void chickenneryBuild(){if(GamePage.state&&inputProcessor.build("CHICKENERRY"))this.buildWork(chickenneryBuild); }
    public void iceCreamBuild(){if(GamePage.state&&inputProcessor.build("ICECRAEMFACTORY"))this.buildWork(iceCreamBuild); }
    public void buildWork(Button button){
        if(button.isVisible()) button.setVisible(false);
    }
    public void reloadFactory(){
            if(Controller.mainController.factories.chickenerryFactory!=null)this.buildWork(chickenneryBuild);
            if(Controller.mainController.factories.eggPowderFactories!=null)this.buildWork(eggPowderBuild);
            if(Controller.mainController.factories.cookieBakeryFactories!=null)this.buildWork(bakeryBuild);
            if(Controller.mainController.factories.milkSepratorFactories!=null)this.buildWork(milkSeperatorBuild);
            if(Controller.mainController.factories.spinneryFactories!=null)this.buildWork(spinneryBuild);
            if(Controller.mainController.factories.weavingFactories!=null)this.buildWork(weavingBuild);
            if(Controller.mainController.factories.iceCreamFactories!=null)this.buildWork(iceCreamBuild);
    }
    //TODO mach with back work factory

    // TODO mach with back build factory
    public void weel(){ if(GamePage.state)this.weelWork(weel); }
    public void weaving(){if(GamePage.state)this.factoryWork(weaving,weavingBuild); }
    public void eggPowder(){if(GamePage.state)this.factoryWork(eggPowder,eggPowderBuild); }
    public void milkSeperator(){if(GamePage.state)this.factoryWork(milkSeperator,milkSeperatorBuild); }
    public void bakery(){if(GamePage.state)this.factoryWork(bakery,bakeryBuild); }
    public void spinnery(){if(GamePage.state)this.factoryWork(spinnery,spinneryBuild); }
    public void chickennery(){if(GamePage.state)this.factoryWork(chickennery,chickenneryBuild); }
    public void iceCream(){if(GamePage.state)this.factoryWork(iceCream,iceCreamBuild); }
    public void factoryWork(ImageView imageView,Button button){
            if(!button.isVisible()){
                FadeTransition fade = new FadeTransition();
                fade.setNode(imageView);
                fade.setDuration(javafx.util.Duration.millis(500));
                fade.setCycleCount(16);
                fade.setInterpolator(Interpolator.LINEAR);
                fade.setFromValue(0.70);
                fade.setToValue(1);
                fade.play();
                ScaleTransition scale = new ScaleTransition();
                scale.setNode(imageView);
                scale.setDuration(javafx.util.Duration.millis(500));
                scale.setCycleCount(16);
                scale.setInterpolator(Interpolator.LINEAR);
                scale.setByX(0.3);
                scale.setByY(0.3);
                scale.setAutoReverse(true);
                scale.play();
            }
    }
    public void weelWork(ImageView imageView){
           {
                FadeTransition fade = new FadeTransition();
                fade.setNode(imageView);
                fade.setDuration(javafx.util.Duration.millis(500));
                fade.setCycleCount(16);
                fade.setInterpolator(Interpolator.LINEAR);
                fade.setFromValue(0.70);
                fade.setToValue(1);
                fade.play();
                ScaleTransition scale = new ScaleTransition();
                scale.setNode(imageView);
                scale.setDuration(javafx.util.Duration.millis(500));
                scale.setCycleCount(16);
                scale.setInterpolator(Interpolator.LINEAR);
                scale.setByX(0.3);
                scale.setByY(0.3);
                scale.setAutoReverse(true);
                scale.play();
            }
    }
    //TODO mach with back work factory


    public void menu(ActionEvent event){
        if(!GamePage.state)try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gameBar.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            Controller.logger("WARNING","menu game bar .");
             ControllGame controllGame = loader.getController();
            controllGame.gamePage=this;
            controllGame.min=GamePage.min;
            controllGame.sec=GamePage.sec;
            controllGame.state=GamePage.state;
            controllGame.timer=GamePage.timer;
            controllGame.setUsernameLable();
            controllGame.buttonColor();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addGood(ActionEvent event,String url , int x , int y , int high,int width){
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Image image = new Image(getClass().getResourceAsStream(url));
            ImageView imageView = new ImageView(image);
            imageView.setX(x);
            imageView.setY(y);
            imageView.setFitHeight(high);
            imageView.setFitWidth(width);
            ArrayList<ImageView> images=new ArrayList<>();
            if(GamePage.goods.containsKey(url)){
               images=GamePage.goods.get(url);
                images.add(imageView);
                GamePage.goods.put(url,images);
            }
            else {
                images.add(imageView);
                GamePage.goods.put(url,images);
            }
        imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(inputProcessor.processPickUp((int)imageView.getLayoutX(),(int)imageView.getLayoutY()))imageView.setVisible(false);
            }
        });
            imageView.setVisible(false);
            Scene scene = stage.getScene();
            root1= (AnchorPane) scene.getRoot();
            root1.getChildren().add(imageView);
            scene.setRoot(root1);
            stage.setScene(scene);
            stage.show();
            Controller.logger("WARNING","GAME PAGE .");
            renew();
    }
    public static int a=0;
    public void showingGood(Stage stages){
        this.singleGoodShowing("picturse\\egg.png",stages);
        this.singleGoodShowing("picturse\\FEATHER.png",stages);
        this.singleGoodShowing("picturse\\flour.png",stages);
        this.singleGoodShowing("picturse\\milk.png",stages);
        this.singleGoodShowing("picturse\\silk.png",stages);
    }
    public void singleGoodShowing(String url,Stage stages){
        if(GamePage.animals.get(url)!=null)
            for (ImageView imageView : GamePage.animals.get(url)) {
                a++;
            Scene scene = stages.getScene();
            root1= (AnchorPane) scene.getRoot();
            double x,y,high,width;
            x=imageView.getX();
            y=imageView.getY();
            high=imageView.getFitHeight();
            width=imageView.getFitWidth();
            Image image = new Image(getClass().getResourceAsStream(url));
               imageView=new ImageView(image);
               imageView.setLayoutX(x+30*a);
               imageView.setLayoutY(y+30*a);
               imageView.setFitHeight(high);
               imageView.setFitWidth(width);
                ImageView finalImageView = imageView;
                imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
                   @Override
                   public void handle(MouseEvent mouseEvent) {
                        if(inputProcessor.processPickUp((int)finalImageView.getLayoutX(),(int)finalImageView.getLayoutY()))finalImageView.setVisible(false);
                    }
               });
            root1.getChildren().add(imageView);
            scene.setRoot(root1);
            stages.setScene(scene);
            stages.show();
            Controller.logger("WARNING","GAME PAGE .");
            renew();
        }
    }


    public void addSILK(ActionEvent event){
        if(GamePage.state) {
            this.addAnimal(event,"picturse\\silk.png",300,250,80,80);
        }
    }

    public void addBEARDOLL(ActionEvent event){
        if(GamePage.state) {
            this.addAnimal(event,"picturse\\chicken.png",300,250,80,80);
        }
    }
    public void addLIONDOLL(ActionEvent event){
        if(GamePage.state) {
            this.addAnimal(event,"picturse\\turkey.png",300,250,80,80);
        }
    }
    public void addTIGERDOLL(ActionEvent event){
        if(GamePage.state) {
            this.addAnimal(event,"picturse\\chicken.png",300,250,80,80);
        }
    }
    public void addGRASS(ActionEvent event){
        if(GamePage.state) {
            this.addAnimal(event,"picturse\\chicken.png",300,250,80,80);
        }
    }
    public void addICECREAM(ActionEvent event){
        if(GamePage.state) {
            this.addAnimal(event,"picturse\\turkey.png",300,250,80,80);
        }
    }
    public void addMILK(ActionEvent event){
        if(GamePage.state) {
            this.addAnimal(event,"picturse\\milk.png",300,250,80,80);
        }
    }
    public void addSEPARATEDMILK(ActionEvent event){
        if(GamePage.state) {
            this.addAnimal(event,"picturse\\turkey.png",300,250,80,80);
        }
    }
    public void addEgg(ActionEvent event){
        if(GamePage.state) {
            this.addGood(event,"picturse\\egg.png",300,250,80,80);
        }
    }
    public void addCLOTH(ActionEvent event){
        if(GamePage.state) {
            this.addAnimal(event,"picturse\\turkey.png",300,250,80,80);
        }
    }
    public void addCOOKIE(ActionEvent event){
        if(GamePage.state) {
            this.addAnimal(event,"picturse\\buffalo.png",300,250,80,80);

        }
    }
    public void addFEATHER(ActionEvent event){
        if(GamePage.state) {
            this.addAnimal(event,"picturse\\FEATHER.png",300,250,80,80);
        }

    }
    public void addFLOUR(ActionEvent event){
        if(GamePage.state) {
            this.addAnimal(event,"picturse\\flour.png",300,250,80,80);
        }
    }







    public void addAnimal(ActionEvent event,String url , int x , int y , int high,int width){
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Image image = new Image(getClass().getResourceAsStream(url));
            ImageView imageView = new ImageView(image);
            imageView.setX(x);
            imageView.setY(y);
            imageView.setFitHeight(high);
            imageView.setFitWidth(width);
            ArrayList<ImageView> images=new ArrayList<>();
            if(GamePage.animals.containsKey(url)){
               images=GamePage.animals.get(url);
                images.add(imageView);
                GamePage.animals.put(url,images);
            }
            else {
                images.add(imageView);
                GamePage.animals.put(url,images);
            }
            Scene scene = stage.getScene();
            root1= (AnchorPane) scene.getRoot();
            root1.getChildren().add(imageView);
            scene.setRoot(root1);
            stage.setScene(scene);
            stage.show();
            Controller.logger("WARNING","GAME PAGE .");
            renew();
    }

    public void showingAnimal(Stage stages){
        this.singleAnimalShowing("picturse\\chicken.png",stages);
        this.singleAnimalShowing("picturse\\turkey.png",stages);
        this.singleAnimalShowing("picturse\\buffalo.png",stages);
        this.singleAnimalShowing("picturse\\dog.png",stages);
        this.singleAnimalShowing("picturse\\cat.png",stages);
        this.singleAnimalShowing("picturse\\tiger.png",stages);
        this.singleAnimalShowing("picturse\\lion.png",stages);
        this.singleAnimalShowing("picturse\\bear.png",stages);
    }

    public void singleAnimalShowing(String url,Stage stages){

        if(GamePage.animals.get(url)!=null)
            for (ImageView imageView : GamePage.animals.get(url)) {
                a++;
            Scene scene = stages.getScene();
            root1= (AnchorPane) scene.getRoot();
            double x,y,high,width;
            x=imageView.getX();
            y=imageView.getY();
            high=imageView.getFitHeight();
            width=imageView.getFitWidth();
            Image image = new Image(getClass().getResourceAsStream(url));
               imageView=new ImageView(image);
               imageView.setLayoutX(x+30*a);
               imageView.setLayoutY(y+30*a);
               imageView.setFitHeight(high);
               imageView.setFitWidth(width);
            root1.getChildren().add(imageView);
            scene.setRoot(root1);
            stages.setScene(scene);
            stages.show();
            Controller.logger("WARNING","GAME PAGE .");
            renew();
        }
    }


    public void buyChicken(ActionEvent event){
        if(GamePage.state) {
            if(inputProcessor.processBuy("chicken"))this.addAnimal(event,"picturse\\chicken.png",300,250,80,80);
        }
    }
    public void buyTurkey(ActionEvent event){
        if(GamePage.state) {
            if(inputProcessor.processBuy("turkey")) this.addAnimal(event,"picturse\\turkey.png",300,250,80,80);
        }
    }
    public void buyBuffalo(ActionEvent event){
        if(GamePage.state) {
            if(inputProcessor.processBuy("bufallo"))this.addAnimal(event,"picturse\\buffalo.png",300,250,80,80);

        }
    }
    public void buyDog(ActionEvent event){
        if(GamePage.state) {
            if(inputProcessor.processBuy("cat"))this.addAnimal(event,"picturse\\dog.png",300,250,80,80);
        }

    }
    public void buyCat(ActionEvent event){
        if(GamePage.state) {
            if(inputProcessor.processBuy("cat")) this.addAnimal(event,"picturse\\cat.png",300,250,80,80);
        }
    }




    public void buyLion(ActionEvent event){
        if(GamePage.state) {
            for (int i = 0; i < 300; i++) {
               if( Controller.mainController.personsController.CurrentUser.currentLevel.TimeOfLionComes.containsKey(i))
                   this.addWildeAnimal(event,"picturse\\lion.png",300,250,80,80);
            }
            }
    }

    public void buyTiger(ActionEvent event){
        if(GamePage.state) {
            for (int i = 0; i < 300; i++) {
                if (Controller.mainController.personsController.CurrentUser.currentLevel.TimeOfTigerComes.containsKey(i))
                    this.addWildeAnimal(event, "picturse\\tiger.png", 300, 250, 80, 80);
            }
        }
    }

    public void buyBear(ActionEvent event){
        if(GamePage.state) {
            for (int i = 0; i < 300; i++) {
                if (Controller.mainController.personsController.CurrentUser.currentLevel.TimeOfBearComes.containsKey(i))
                    this.addWildeAnimal(event, "picturse\\bear.png", 300, 250, 80, 80);
            }
        }
    }

    public void addWildeAnimal(ActionEvent event,String url , int x , int y , int high,int width){
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Image image = new Image(getClass().getResourceAsStream(url));
        ImageView imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitHeight(high);
        imageView.setFitWidth(width);
        ArrayList<ImageView> images=new ArrayList<>();
        if(GamePage.wildeAnimal.containsKey(url)){
            images=GamePage.wildeAnimal.get(url);
            images.add(imageView);
            GamePage.wildeAnimal.put(url,images);
        }
        else {
            images.add(imageView);
            GamePage.wildeAnimal.put(url,images);
        }
        imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(inputProcessor.processPickUp((int)imageView.getLayoutX(),(int)imageView.getLayoutY()))imageView.setVisible(false);
            }
        });
        imageView.setVisible(false);
        Scene scene = stage.getScene();
        root1= (AnchorPane) scene.getRoot();
        root1.getChildren().add(imageView);
        scene.setRoot(root1);
        stage.setScene(scene);
        stage.show();
        Controller.logger("WARNING","GAME PAGE .");
        renew();
    }

    public void showingWildeAnimal(Stage stages){
       this.singleWildeAnimalShowing("picturse\\lion.png",stages);
       this.singleWildeAnimalShowing("picturse\\tiger.png",stages);
       this.singleWildeAnimalShowing("picturse\\bear.png",stages);
    }

    public void singleWildeAnimalShowing(String url,Stage stages){
        if(GamePage.wildeAnimal.get(url)!=null)
            for (ImageView imageView : GamePage.wildeAnimal.get(url)) {
                a++;
                Scene scene = stages.getScene();
                root1= (AnchorPane) scene.getRoot();
                double x,y,high,width;
                x=imageView.getX();
                y=imageView.getY();
                high=imageView.getFitHeight();
                width=imageView.getFitWidth();
                Image image = new Image(getClass().getResourceAsStream(url));
                imageView=new ImageView(image);
                imageView.setLayoutX(x+30*a);
                imageView.setLayoutY(y+30*a);
                imageView.setFitHeight(high);
                imageView.setFitWidth(width);
                ImageView finalImageView = imageView;
                imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(inputProcessor.processPickUp((int)finalImageView.getLayoutX(),(int)finalImageView.getLayoutY()))finalImageView.setVisible(false);
                    }
                });
                root1.getChildren().add(imageView);
                scene.setRoot(root1);
                stages.setScene(scene);
                stages.show();
                Controller.logger("WARNING","GAME PAGE .");
                renew();
            }
    }
    public void warHouse(MouseEvent event){
            if(!GamePage.state)try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("warhouse.fxml"));
                root = loader.load();
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                Controller.logger("WARNING","menu game bar .");
                ControllGame controllGame = loader.getController();
                controllGame.gamePage=this;
                controllGame.min=GamePage.min;
                controllGame.sec=GamePage.sec;
                controllGame.state=GamePage.state;
                controllGame.timer=GamePage.timer;
                controllGame.loadAllWareHouse();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public void truckPage(MouseEvent event){
            if(!GamePage.state)try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("truckpage.fxml"));
                root = loader.load();
                System.out.println("ok");
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                Controller.logger("WARNING","menu game bar .");
                ControllGame controllGame = loader.getController();
                controllGame.gamePage=this;
                controllGame.min=GamePage.min;
                controllGame.sec=GamePage.sec;
                controllGame.state=GamePage.state;
                controllGame.timer=GamePage.timer;
                controllGame.loadTruck();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
