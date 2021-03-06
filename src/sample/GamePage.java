package sample;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

import javafx.util.Duration;
import model.animal.Animal;
import model.animal.AnimalEnum;
import model.animal.asisstant.Cat;
import model.animal.defender.Dog;
import model.animal.producer.Bufallo;
import model.animal.producer.Chicken;
import model.animal.producer.Turkey;
import model.animal.wild.Bear;
import model.animal.wild.Lion;
import model.animal.wild.Tiger;
import model.goods.Goods;
import model.goods.GoodsEnum;
import model.goods.Grass;
import model.level.Level;
import view.InputProcessor;

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
    TextField money;
    @FXML
    TextField task1;
    @FXML
    TextField task2;
    @FXML
    TextField task3;
    @FXML
    TextArea tasks;
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
    public static HashMap<Animal,ImageView> animalImageViewHashMap=new HashMap<>();
    public static HashMap<Grass,ImageView> grassImageViewHashMap=new HashMap<>();
    public static HashMap<Goods,ImageView> goodImageViewHashMap=new HashMap<>();
    public InputProcessor inputProcessor=new InputProcessor(Controller.mainController);

    TimerTask task;

        public void setPuse(ActionEvent event){
            try {
                if(GamePage.state==false){
                    this.start(event);
                    puse.setText("puse");
                } else if(GamePage.state==true){
                    this.stop(event);
                    puse.setText("start");
                }
            }catch (Exception e){}

        }


        public void picturseNewing(MouseEvent event){
            try {
                if(state){
                    pictureGoodNewing(event);
                    renewingGrass(event);
                    this.showTask();
                }
                if(Controller.mainController.isTasksCompleted){
                    this.won(event);
                }
            }catch (Exception e){}

        }


        public void renewingGrass(MouseEvent event){
            try {
                Scene scene =  ((Node) event.getSource()).getScene();
                root1= (AnchorPane) scene.getRoot();
                ArrayList<ImageView> grasses=GamePage.goods.get("picturse\\grass.png");
                if(grasses!=null) for (ImageView grass : grasses) {
                    boolean isIn=false;
                    if(Controller.mainController.goods.grasses!=null)
                        for (Grass grass1 : Controller.mainController.goods.grasses) {
                            if(grass1.y==grass.getY()&&grass1.x==grass.getX()){
                                grass.setVisible(true);
                                isIn=true;
                                break;
                            }
                        }
                    if(!isIn){
                        grass.setVisible(false);
                    }
                }
            }catch (Exception e){}

        }


        public void renewingGrass(Stage stage){
            try {
                Scene scene =stage.getScene();
                root1= (AnchorPane) scene.getRoot();
                if(Controller.mainController.goods.grasses!=null)
                    for (Grass grass1 : Controller.mainController.goods.grasses) {
                        Image image = new Image(getClass().getResourceAsStream("picturse\\grass.png"));
                        ImageView imageView = new ImageView(image);
                        imageView.setX(grass1.x);
                        imageView.setY(grass1.y);
                        imageView.setFitHeight(60);
                        imageView.setFitWidth(60);
                        root1.getChildren().add(imageView);
                        ArrayList<ImageView > grasses=GamePage.goods.get("picturse\\grass.png");
                        grasses.add(imageView);
                        GamePage.goods.put("picturse\\grass.png",grasses);
                        break;
                    }
                scene.setRoot(root1);
                stage.setScene(scene);
                stage.show();
            }catch (Exception e){}

        }


        public void goodsVisible(){
            try {
                if(GamePage.goods.get("picturse\\egg.png")!=null)for (ImageView imageView : GamePage.goods.get("picturse\\egg.png")) imageView.setVisible(false);
                if(GamePage.goods.get("picturse\\FEATHER.png")!=null)for (ImageView imageView : GamePage.goods.get("picturse\\FEATHER.png")) imageView.setVisible(false);
                if(GamePage.goods.get("picturse\\flour.png")!=null)for (ImageView imageView : GamePage.goods.get("picturse\\flour.png")) imageView.setVisible(false);
                if(GamePage.goods.get("picturse\\milk.png")!=null)for (ImageView imageView : GamePage.goods.get("picturse\\milk.png")) imageView.setVisible(false);
                if(GamePage.goods.get("picturse\\silk.png")!=null)for (ImageView imageView : GamePage.goods.get("picturse\\silk.png")) imageView.setVisible(false);
                if( GamePage.goods.get("picturse\\cookie.png")!=null)for (ImageView imageView : GamePage.goods.get("picturse\\cookie.png")) imageView.setVisible(false);
                if( GamePage.goods.get("picturse\\liondoll.png")!=null)for (ImageView imageView : GamePage.goods.get("picturse\\liondoll.png")) imageView.setVisible(false);
                if(GamePage.goods.get("picturse\\tigerdoll.png")!=null)for (ImageView imageView : GamePage.goods.get("picturse\\tigerdoll.png")) imageView.setVisible(false);
                if(GamePage.goods.get("picturse\\beardoll.png")!=null)for (ImageView imageView : GamePage.goods.get("picturse\\beardoll.png")) imageView.setVisible(false);
                if(GamePage.goods.get("picturse\\sepratedmilk.png")!=null)for (ImageView imageView : GamePage.goods.get("picturse\\sepratedmilk.png")) imageView.setVisible(false);
                if( GamePage.goods.get("picturse\\cloth.png")!=null)for (ImageView imageView : GamePage.goods.get("picturse\\cloth.png")) imageView.setVisible(false);
                if( GamePage.goods.get("picturse\\grass.png")!=null)for (ImageView imageView : GamePage.goods.get("picturse\\grass.png")) imageView.setVisible(false);
                if( GamePage.goods.get("picturse\\ice.png")!=null)for (ImageView imageView : GamePage.goods.get("picturse\\ice.png")) imageView.setVisible(false);

            }catch (Exception e){}
            }

        public void pictureGoodNewing(MouseEvent event){
            try {
                Scene scene =  ((Node) event.getSource()).getScene();
                root1= (AnchorPane) scene.getRoot();
                this.goodsVisible();
                for (Goods productGood : Controller.mainController.goods.productGoods) {
                    if(productGood.name.equalsIgnoreCase(GoodsEnum.BEARDOLL.toString())) this.GoodSetSingleImage(productGood,"picturse\\beardoll.png",productGood,root1);
                    else if(productGood.name.equalsIgnoreCase(GoodsEnum.LIONDOLL.toString())) this.GoodSetSingleImage(productGood,"picturse\\liondoll.png",productGood,root1);
                    else if(productGood.name.equalsIgnoreCase(GoodsEnum.TIGERDOLL.toString())) this.GoodSetSingleImage(productGood,"picturse\\tigerdoll.png",productGood,root1);
                    else if(productGood.name.equalsIgnoreCase(GoodsEnum.CLOTH.toString())) this.GoodSetSingleImage(productGood,"picturse\\cloth.png",productGood,root1);
                    else if(productGood.name.equalsIgnoreCase(GoodsEnum.COOKIE.toString())) this.GoodSetSingleImage(productGood,"picturse\\cookie.png",productGood,root1);
                    else if(productGood.name.equalsIgnoreCase(GoodsEnum.ICECREAM.toString())) this.GoodSetSingleImage(productGood,"picturse\\ice.png",productGood,root1);
                    else if(productGood.name.equalsIgnoreCase(GoodsEnum.MILK.toString())) this.GoodSetSingleImage(productGood,"picturse\\milk.png",productGood,root1);
                    else if(productGood.name.equalsIgnoreCase(GoodsEnum.SEPARATEDMILK.toString())) this.GoodSetSingleImage(productGood,"picturse\\sepratedmilk.png",productGood,root1);
                    else if(productGood.name.equalsIgnoreCase(GoodsEnum.SILK.toString())) this.GoodSetSingleImage(productGood,"picturse\\silk.png",productGood,root1);
                    else if(productGood.name.equalsIgnoreCase(GoodsEnum.EGG.toString())) this.GoodSetSingleImage(productGood,"picturse\\egg.png",productGood,root1);
                    else if(productGood.name.equalsIgnoreCase(GoodsEnum.FEATHER.toString())) this.GoodSetSingleImage(productGood,"picturse\\FEATHER.png",productGood,root1);
                    else if(productGood.name.equalsIgnoreCase(GoodsEnum.FLOUR.toString())){ this.GoodSetSingleImage(productGood,"picturse\\flour.png",productGood,root1); }
                }

                for (Chicken chicken : Controller.mainController.animals.chickens) {
                    this.addCHickenAndWildeAnimals(chicken,"picturse\\chicken.png",root1);
                }
                for (Lion lion : Controller.mainController.animals.lions) {
                    this.addCHickenAndWildeAnimals(lion,"picturse\\lion.png",root1);
                }
                for (Tiger tiger : Controller.mainController.animals.tigers) {
                    this.addCHickenAndWildeAnimals(tiger,"picturse\\tiger.png",root1);
                }
                for (Bear bear : Controller.mainController.animals.bears) {
                    this.addCHickenAndWildeAnimals(bear,"picturse\\bear.png",root1);
                }
                scene.setRoot(root1);
            }catch (Exception e){}


        }

        public  void addCHickenAndWildeAnimals(Animal animal,String url,AnchorPane root1){
            try {
                if(GamePage.animalImageViewHashMap.get(animal)==null) {
                    Image image = new Image(getClass().getResourceAsStream(url));
                    ImageView imageView = new ImageView(image);
                    imageView.setX(animal.x);
                    imageView.setY(animal.y);
                    imageView.setFitHeight(80);
                    imageView.setFitWidth(80);
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
                    GamePage.animalImageViewHashMap.put(animal,imageView);
                    if(!animal.name.equalsIgnoreCase(AnimalEnum.CHICKEN.toString())){
                        imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                if(Controller.mainController.animals.cage((int)imageView.getX(),(int)imageView.getY(),Controller.mainController.goods,Controller.mainController.logger)==1)imageView.setVisible(false);
                                showTask();
                            }
                        });
                    }
                    root1.getChildren().add(imageView);
                }
            }catch (Exception e){}

        }


        public void GoodSetSingleImage(Goods goods,String url,Goods productGood,AnchorPane Roo){
            try {
                Image image = new Image(getClass().getResourceAsStream(url));
                ImageView imageView = new ImageView(image);
                imageView.setX(productGood.x);
                imageView.setY(productGood.y);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(inputProcessor.processPickUp(productGood.x, productGood.y)){
                            GoodMoving(imageView);
                            showTask();
                        }
                    }
                });
                goodImageViewHashMap.put(goods,imageView);
                this.GoodFading(imageView);
                Roo.getChildren().add(imageView);
            }catch (Exception e){}

        }


    public void GoodMoving(ImageView imageView){
        try {
            {
                TranslateTransition translate = new TranslateTransition();
                translate.setNode(imageView);
                translate.setDuration(Duration.millis(1000));
                translate.setCycleCount(1);
                translate.setToX(250-imageView.getX());
                translate.setToY(510-imageView.getX());
                translate.setAutoReverse(true);
                translate.play();
                FadeTransition fade = new FadeTransition();
                fade.setNode(imageView);
                fade.setDuration(javafx.util.Duration.millis(1000));
                fade.setCycleCount(1);
                fade.setInterpolator(Interpolator.LINEAR);
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.play();
            }
        }catch (Exception e){}

    }
    public static void GoodPickCat(ImageView imageView){
        try {
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(imageView);
            translate.setDuration(Duration.millis(1000));
            translate.setCycleCount(1);
            translate.setToX(250-imageView.getX());
            translate.setToY(510-imageView.getX());
            translate.setAutoReverse(true);
            translate.play();
            FadeTransition fade = new FadeTransition();
            fade.setNode(imageView);
            fade.setDuration(javafx.util.Duration.millis(1000));
            fade.setCycleCount(1);
            fade.setInterpolator(Interpolator.LINEAR);
            fade.setFromValue(1);
            fade.setToValue(0);
            fade.play();
        }catch (Exception e){}

    }
    public void GoodFading(ImageView imageView){
        try {
            FadeTransition fade = new FadeTransition();
            fade.setNode(imageView);
            fade.setDuration(javafx.util.Duration.millis(4000));
            fade.setCycleCount(1);
            fade.setInterpolator(Interpolator.LINEAR);
            fade.setFromValue(1);
            fade.setToValue(0);
            fade.play();
            ScaleTransition scale = new ScaleTransition();
            scale.setNode(imageView);
            scale.setDuration(javafx.util.Duration.millis(4000));
            scale.setCycleCount(16);
            scale.setInterpolator(Interpolator.LINEAR);
            scale.setToX(0.1);
            scale.setToY(0.1);
            scale.setAutoReverse(true);
            scale.play();
        }catch (Exception e){}

    }

    public void movingAnimals(){
        try {
            if( Controller.mainController.animals.turkeys!=null) for (Turkey turkey : Controller.mainController.animals.turkeys) if(GamePage.animalImageViewHashMap.get(turkey)!=null)AnimalMovement(GamePage.animalImageViewHashMap.get(turkey),GamePage.animalImageViewHashMap.get(turkey).getX(),GamePage.animalImageViewHashMap.get(turkey).getY(),turkey.x,turkey.y);
            if( Controller.mainController.animals.chickens!=null) for (Chicken chicken : Controller.mainController.animals.chickens) if(GamePage.animalImageViewHashMap.get(chicken)!=null)AnimalMovement(GamePage.animalImageViewHashMap.get(chicken),GamePage.animalImageViewHashMap.get(chicken).getX(),GamePage.animalImageViewHashMap.get(chicken).getY(),chicken.x,chicken.y);
            if( Controller.mainController.animals.bufallos!=null) for (Bufallo bufallo : Controller.mainController.animals.bufallos) if(GamePage.animalImageViewHashMap.get(bufallo)!=null)AnimalMovement(GamePage.animalImageViewHashMap.get(bufallo),GamePage.animalImageViewHashMap.get(bufallo).getX(),GamePage.animalImageViewHashMap.get(bufallo).getY(),bufallo.x,bufallo.y);
            if( Controller.mainController.animals.dogs!=null) for (Dog dog : Controller.mainController.animals.dogs) if(GamePage.animalImageViewHashMap.get(dog)!=null)AnimalMovement(GamePage.animalImageViewHashMap.get(dog),GamePage.animalImageViewHashMap.get(dog).getX(),GamePage.animalImageViewHashMap.get(dog).getY(),dog.x,dog.y);
            if( Controller.mainController.animals.cats!=null) for (Cat cat: Controller.mainController.animals.cats) if(GamePage.animalImageViewHashMap.get(cat)!=null)AnimalMovement(GamePage.animalImageViewHashMap.get(cat),GamePage.animalImageViewHashMap.get(cat).getX(),GamePage.animalImageViewHashMap.get(cat).getY(),cat.x,cat.y);
            if( Controller.mainController.animals.tigers!=null) for (Tiger tiger : Controller.mainController.animals.tigers) if(GamePage.animalImageViewHashMap.get(tiger)!=null)AnimalMovement(GamePage.animalImageViewHashMap.get(tiger),GamePage.animalImageViewHashMap.get(tiger).getX(),GamePage.animalImageViewHashMap.get(tiger).getY(),tiger.x,tiger.y);
            if( Controller.mainController.animals.lions!=null) for (Lion lion : Controller.mainController.animals.lions) if(GamePage.animalImageViewHashMap.get(lion)!=null) AnimalMovement(GamePage.animalImageViewHashMap.get(lion),GamePage.animalImageViewHashMap.get(lion).getX(),GamePage.animalImageViewHashMap.get(lion).getY(),lion.x,lion.y);
            if( Controller.mainController.animals.bears!=null) for (Bear bear : Controller.mainController.animals.bears) if(GamePage.animalImageViewHashMap.get(bear)!=null)AnimalMovement(GamePage.animalImageViewHashMap.get(bear),GamePage.animalImageViewHashMap.get(bear).getX(),GamePage.animalImageViewHashMap.get(bear).getY(),bear.x,bear.y);

        }catch (Exception e){}
          }

    public void AnimalMovement(ImageView imageView,double x1,double y1,double x2,double y2){
        try {
            {
                TranslateTransition translate = new TranslateTransition();
                translate.setNode(imageView);
                translate.setDuration(Duration.millis(1000));
                translate.setCycleCount(1);
                translate.setToX(x2-x1);
                translate.setToY(y2-y1);
                translate.setFromX(0);
                translate.setFromY(0);
                translate.setAutoReverse(true);
                translate.play();
            }
        }catch (Exception e){}

    }
    public void movingAnimals(Stage event){
        try {
            if( Controller.mainController.animals.turkeys!=null) for (Turkey turkey : Controller.mainController.animals.turkeys) AnimalMovement(GamePage.animalImageViewHashMap.get(turkey),GamePage.animalImageViewHashMap.get(turkey).getX(),GamePage.animalImageViewHashMap.get(turkey).getY(),turkey.x,turkey.y,event);
            if( Controller.mainController.animals.chickens!=null) for (Chicken chicken : Controller.mainController.animals.chickens) AnimalMovement(GamePage.animalImageViewHashMap.get(chicken),GamePage.animalImageViewHashMap.get(chicken).getX(),GamePage.animalImageViewHashMap.get(chicken).getY(),chicken.x,chicken.y,event);
            if( Controller.mainController.animals.bufallos!=null) for (Bufallo bufallo : Controller.mainController.animals.bufallos) AnimalMovement(GamePage.animalImageViewHashMap.get(bufallo),GamePage.animalImageViewHashMap.get(bufallo).getX(),GamePage.animalImageViewHashMap.get(bufallo).getY(),bufallo.x,bufallo.y,event);
            if( Controller.mainController.animals.dogs!=null) for (Dog dog : Controller.mainController.animals.dogs) AnimalMovement(GamePage.animalImageViewHashMap.get(dog),GamePage.animalImageViewHashMap.get(dog).getX(),GamePage.animalImageViewHashMap.get(dog).getY(),dog.x,dog.y,event);
            if( Controller.mainController.animals.cats!=null) for (Cat cat: Controller.mainController.animals.cats) AnimalMovement(GamePage.animalImageViewHashMap.get(cat),GamePage.animalImageViewHashMap.get(cat).getX(),GamePage.animalImageViewHashMap.get(cat).getY(),cat.x,cat.y,event);
            if( Controller.mainController.animals.tigers!=null) for (Tiger tiger : Controller.mainController.animals.tigers) AnimalMovement(GamePage.animalImageViewHashMap.get(tiger),GamePage.animalImageViewHashMap.get(tiger).getX(),GamePage.animalImageViewHashMap.get(tiger).getY(),tiger.x,tiger.y,event);
            if( Controller.mainController.animals.lions!=null) for (Lion lion : Controller.mainController.animals.lions) AnimalMovement(GamePage.animalImageViewHashMap.get(lion),GamePage.animalImageViewHashMap.get(lion).getX(),GamePage.animalImageViewHashMap.get(lion).getY(),lion.x,lion.y,event);
            if( Controller.mainController.animals.bears!=null) for (Bear bear : Controller.mainController.animals.bears) AnimalMovement(GamePage.animalImageViewHashMap.get(bear),GamePage.animalImageViewHashMap.get(bear).getX(),GamePage.animalImageViewHashMap.get(bear).getY(),bear.x,bear.y,event);

        }catch (Exception e){}
       }

    public void AnimalMovement(ImageView imageView,double x1,double y1,double x2,double y2,Stage event){
        try {
            Scene scene =   event.getScene();
            root1= (AnchorPane) scene.getRoot();
            root1.getChildren().add(imageView);
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(imageView);
            translate.setDuration(Duration.millis(1000));
            translate.setCycleCount(1);
            translate.setToX(x2-x1);
            translate.setToY(y2-y1);
            translate.setFromX(0);
            translate.setFromY(0);
            translate.setAutoReverse(true);
            translate.play();
        }catch (Exception e){}

    }

    public void setImageAnimalPosition(){
        try {
            if( Controller.mainController.animals.turkeys!=null) for (Turkey turkey : Controller.mainController.animals.turkeys) AnimalMovement1(GamePage.animalImageViewHashMap.get(turkey),GamePage.animalImageViewHashMap.get(turkey).getX(),GamePage.animalImageViewHashMap.get(turkey).getY(),turkey.x,turkey.y);
            if( Controller.mainController.animals.chickens!=null) for (Chicken chicken : Controller.mainController.animals.chickens) AnimalMovement1(GamePage.animalImageViewHashMap.get(chicken),GamePage.animalImageViewHashMap.get(chicken).getX(),GamePage.animalImageViewHashMap.get(chicken).getY(),chicken.x,chicken.y);
            if( Controller.mainController.animals.bufallos!=null) for (Bufallo bufallo : Controller.mainController.animals.bufallos) AnimalMovement1(GamePage.animalImageViewHashMap.get(bufallo),GamePage.animalImageViewHashMap.get(bufallo).getX(),GamePage.animalImageViewHashMap.get(bufallo).getY(),bufallo.x,bufallo.y);
            if( Controller.mainController.animals.dogs!=null) for (Dog dog : Controller.mainController.animals.dogs) AnimalMovement1(GamePage.animalImageViewHashMap.get(dog),GamePage.animalImageViewHashMap.get(dog).getX(),GamePage.animalImageViewHashMap.get(dog).getY(),dog.x,dog.y);
            if( Controller.mainController.animals.cats!=null) for (Cat cat: Controller.mainController.animals.cats) AnimalMovement1(GamePage.animalImageViewHashMap.get(cat),GamePage.animalImageViewHashMap.get(cat).getX(),GamePage.animalImageViewHashMap.get(cat).getY(),cat.x,cat.y);
            if( Controller.mainController.animals.tigers!=null) for (Tiger tiger : Controller.mainController.animals.tigers)if(GamePage.animalImageViewHashMap.get(tiger)!=null) AnimalMovement1(GamePage.animalImageViewHashMap.get(tiger),GamePage.animalImageViewHashMap.get(tiger).getX(),GamePage.animalImageViewHashMap.get(tiger).getY(),tiger.x,tiger.y);
            if( Controller.mainController.animals.lions!=null) for (Lion lion : Controller.mainController.animals.lions)if(GamePage.animalImageViewHashMap.get(lion)!=null) AnimalMovement1(GamePage.animalImageViewHashMap.get(lion),GamePage.animalImageViewHashMap.get(lion).getX(),GamePage.animalImageViewHashMap.get(lion).getY(),lion.x,lion.y);
            if( Controller.mainController.animals.bears!=null) for (Bear bear : Controller.mainController.animals.bears)if(GamePage.animalImageViewHashMap.get(bear)!=null) AnimalMovement1(GamePage.animalImageViewHashMap.get(bear),GamePage.animalImageViewHashMap.get(bear).getX(),GamePage.animalImageViewHashMap.get(bear).getY(),bear.x,bear.y);

        }catch (Exception e){}

    }
    public  void AnimalMovement1(ImageView imageView , double x1,double y1,double x2,double y2){
        try {
            imageView.setX(x2);
            imageView.setY(y2);
        }catch (Exception e){}
    }

    public void showTask(){
        try {
            Level level=Controller.mainController.personsController.getCurrentUser().currentLevel;
            if(!this.showSingleTask(level.task1,1, level.task1Number).equalsIgnoreCase(""))task1.setText(this.showSingleTask(level.task1,1, level.task1Number));
            if(!this.showSingleTask(level.task2,2, level.task2Number).equalsIgnoreCase(""))task2.setText(this.showSingleTask(level.task2,2, level.task2Number));
            if(!this.showSingleTask(level.task2,2, level.task2Number).equalsIgnoreCase(""))task3.setText(this.showSingleTask(level.task3,3, level.task3Number));

        }catch (Exception e){}
         }

    public String showSingleTask(String task,int taskNum,int taskNumber){
        String path="";
        try {
        for (GoodsEnum value : GoodsEnum.values()) {
            String good = value.toString();
            if(good.equalsIgnoreCase(task)){
                path=(taskNum+" : "+task+" : "+Controller.mainController.returnArrByGoodName(good).size()+"/"+taskNumber+"\n");
            }
        }
        for (AnimalEnum value : AnimalEnum.values()) {
            String animal = value.toString();
            if(animal.equalsIgnoreCase(task)){
                path=(taskNum+" : "+task+" : "+Controller.mainController.returnArrByAnimalName(animal).size()+"/"+taskNumber+"\n");
            }
        }
        if(task.equalsIgnoreCase("coin")) {
            path=(taskNum + " : coins : " + Controller.mainController.personsController.getCurrentUser().totalCoins + "/" + taskNumber + "\n");
        }

        }
        catch (Exception e){
        }
        return path;
    }



    public void renew1() {
        try {
            if(!Controller.mainController.isTasksCompleted){
                task = new TimerTask() {
                    @Override
                    public void run() {
                        try{
                            GamePage.state=true;
                            GamePage.sec++;
                            showTask();
                            try {
                                money.setText(Integer.toString(Controller.mainController.personsController.CurrentUser.totalCoins));
                            }catch (Exception e){}
                            setImageAnimalPosition();
                            inputProcessor.turn(1);
                            movingAnimals();
                            showTask();
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
            }else{
                timer.cancel();
            }
        }catch (Exception e){}
}



    public void renew() {
        try {
            if( !Controller.mainController.isTasksCompleted){
                System.out.println(Controller.mainController.isTasksCompleted);
                setImageAnimalPosition();
                inputProcessor.turn(1);
                movingAnimals();
                this.showTask();
                try {
                    money.setText(Integer.toString(Controller.mainController.personsController.CurrentUser.totalCoins));
                }catch (Exception e){}
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
            }else{
                timer.cancel();
            }
        }catch (Exception e){}

    }

    public void won(MouseEvent event){
        try {
            Controller.mainController.completeTheLevel();
            MyFirstJDBC myFirstJDBC =new MyFirstJDBC(Controller.mainController);
            myFirstJDBC.newUser();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("wone");
            alert.setHeaderText("You won the game!");
            if(alert.showAndWait().get() == ButtonType.OK){
                timer.cancel();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("gameBar.fxml"));
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
            }
            else {
                timer.cancel();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("gameBar.fxml"));
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
            }
        }catch (Exception e){}
    }

    public  void start(ActionEvent event) {
        try {
            GamePage.timer=new Timer();
            this.renew1();
            GamePage.timer.scheduleAtFixedRate(task, 0, 1000);
        }catch (Exception e){}

        }


    public void stop(ActionEvent event){
        try {
            GamePage.state=false;
            GamePage.timer.cancel();
        }catch (Exception e){}

    }


    //TODO mach with back work factory
    public void weavingBuild(){
        try {

            if(GamePage.state&&inputProcessor.build("WEAVING"))this.buildWork(weavingBuild);
        }catch (Exception e){}
        }
    public void eggPowderBuild(){
        try {
            if(GamePage.state&&inputProcessor.build("EGGPOWDER"))this.buildWork(eggPowderBuild);
        }catch (Exception e){}
        }
    public void milkSeperatorBuild(){
        try {
            if(GamePage.state&&inputProcessor.build("MILKSEPRATOR"))this.buildWork(milkSeperatorBuild);
        }catch (Exception e){}
         }
    public void bakeryBuild(){
        try {
            if(GamePage.state&&inputProcessor.build("COOKIEFACTORY"))this.buildWork(bakeryBuild);
        }catch (Exception e){} }
    public void spinneryBuild(){
        try {

            if(GamePage.state&&inputProcessor.build("SPINNERY"))this.buildWork(spinneryBuild);
        }catch (Exception e){}}
    public void chickenneryBuild(){
        try {

            if(GamePage.state&&inputProcessor.build("CHICKENERRY"))this.buildWork(chickenneryBuild);
        }catch (Exception e){} }
    public void iceCreamBuild(){
        try {

            if(GamePage.state&&inputProcessor.build("ICECRAEMFACTORY"))this.buildWork(iceCreamBuild);
        }catch (Exception e){} }
    public void buildWork(Button button){
        try {

            if(button.isVisible()) button.setVisible(false);
        }catch (Exception e){}
    }
    public void reloadFactory(){
        try {
            if(Controller.mainController.factories.chickenerryFactory!=null)this.buildWork(chickenneryBuild);
            if(Controller.mainController.factories.eggPowderFactories!=null)this.buildWork(eggPowderBuild);
            if(Controller.mainController.factories.cookieBakeryFactories!=null)this.buildWork(bakeryBuild);
            if(Controller.mainController.factories.milkSepratorFactories!=null)this.buildWork(milkSeperatorBuild);
            if(Controller.mainController.factories.spinneryFactories!=null)this.buildWork(spinneryBuild);
            if(Controller.mainController.factories.weavingFactories!=null)this.buildWork(weavingBuild);
            if(Controller.mainController.factories.iceCreamFactories!=null)this.buildWork(iceCreamBuild);

        }catch (Exception e){}
                }
    //TODO mach with back work factory
public void plant(MouseEvent event){
    try {
        if(inputProcessor.plant(event.getX(), event.getY())){
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Image image = new Image(getClass().getResourceAsStream("picturse\\grass.png"));
            ImageView imageView = new ImageView(image);
            imageView.setX(event.getX());
            imageView.setY(event.getY());
            imageView.setFitHeight(60);
            imageView.setFitWidth(60);
            ArrayList<ImageView> images=new ArrayList<>();
            if(GamePage.goods.containsKey("picturse\\grass.png")){
                images=GamePage.goods.get("picturse\\grass.png");
                images.add(imageView);
                GamePage.goods.put("picturse\\grass.png",images);
            }
            else {
                images.add(imageView);
                GamePage.goods.put("picturse\\grass.png",images);
            }
            GamePage.grassImageViewHashMap.put(Controller.mainController.goods.grasses.get(Controller.mainController.goods.grasses.size()-1),imageView);
            imageView.setVisible(true);
            Scene scene = stage.getScene();
            root1= (AnchorPane) scene.getRoot();
            root1.getChildren().add(imageView);
            scene.setRoot(root1);
            stage.setScene(scene);
            stage.show();
            Controller.logger("WARNING","GAME PAGE .");
            renew();
        }
    }catch (Exception e){}
}
    // TODO mach with back build factory
    public void weel(){
        try {

            if(GamePage.state&&inputProcessor.well())this.weelWork(weel);
        }catch (Exception e){}}
    public void weaving(){
        try {

            if(GamePage.state&&inputProcessor.work("WEAVING"))this.factoryWork(weaving,weavingBuild);
        }catch (Exception e){} }
    public void eggPowder(){
        try {
            if(GamePage.state&&inputProcessor.work("EGGPOWDER"))this.factoryWork(eggPowder,eggPowderBuild);
        }catch (Exception e){}
         }
    public void milkSeperator(){
        try {
            if(GamePage.state&&inputProcessor.work("MILKSEPRATOR"))this.factoryWork(milkSeperator,milkSeperatorBuild);
        }catch (Exception e){}
         }
    public void bakery(){
        try {
            if(GamePage.state&&inputProcessor.work("COOKIEFACTORY"))this.factoryWork(bakery,bakeryBuild);
        }catch (Exception e){}
        }
    public void spinnery(){
        try {
            if(GamePage.state&&inputProcessor.work("SPINNERY"))this.factoryWork(spinnery,spinneryBuild);
        }catch (Exception e){}
         }
    public void chickennery(){
        try {
            if(GamePage.state&&inputProcessor.work("CHICKENERRY"))this.factoryWork(chickennery,chickenneryBuild);
        }catch (Exception e){}
         }
    public void iceCream(){
        try {
            if(GamePage.state&&inputProcessor.work("ICECRAEMFACTORY"))this.factoryWork(iceCream,iceCreamBuild);
        }catch (Exception e){}
         }
    public void factoryWork(ImageView imageView,Button button){
        try {
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
        }catch (Exception e){}
    }
    public void weelWork(ImageView imageView){
        try {
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
        }catch (Exception e){}

    }
    //TODO mach with back work factory


    public void menu(ActionEvent event){
        try {
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
        }catch (Exception e){}
    }

    public void addGood(MouseEvent event,String url , int x , int y , int high,int width){
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

    public void showingGood(Stage stages){
        try {
            this.singleGoodShowing("picturse\\egg.png",stages);
            this.singleGoodShowing("picturse\\FEATHER.png",stages);
            this.singleGoodShowing("picturse\\flour.png",stages);
            this.singleGoodShowing("picturse\\milk.png",stages);
            this.singleGoodShowing("picturse\\silk.png",stages);
            this.singleGoodShowing("picturse\\cookie.png",stages);
            this.singleGoodShowing("picturse\\liondoll.png",stages);
            this.singleGoodShowing("picturse\\tigerdoll.png",stages);
            this.singleGoodShowing("picturse\\beardoll.png",stages);
            this.singleGoodShowing("picturse\\sepratedmilk.png",stages);
            this.singleGoodShowing("picturse\\cloth.png",stages);
            this.singleGoodShowing("picturse\\ice.png",stages);
        }catch (Exception e){}
    }


    public void singleGoodShowing(String url,Stage stages){
        try {
            if(GamePage.goods.get(url)!=null)
                for (ImageView imageView : GamePage.goods.get(url)) {
                    Scene scene = stages.getScene();
                    root1= (AnchorPane) scene.getRoot();
                    double x,y,high,width;
                    x=imageView.getX();
                    y=imageView.getY();
                    high=imageView.getFitHeight();
                    width=imageView.getFitWidth();
                    Image image = new Image(getClass().getResourceAsStream(url));
                    imageView=new ImageView(image);
                    imageView.setLayoutX(x);
                    imageView.setLayoutY(y);
                    imageView.setFitHeight(high);
                    imageView.setFitWidth(width);
                    ImageView finalImageView = imageView;
                    imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            if(inputProcessor.processPickUp((int)finalImageView.getLayoutX(),(int)finalImageView.getLayoutY()))finalImageView.setVisible(false);
                            showTask();
                        }
                    });
                    root1.getChildren().add(imageView);
                    scene.setRoot(root1);
                    stages.setScene(scene);
                    stages.show();
                    Controller.logger("WARNING","GAME PAGE .");
                    renew();
                }
        }catch (Exception e){}

    }

    public void addAnimal(ActionEvent event,String url , int x , int y , int high,int width,Animal animal){
        try {
            showTask();
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
            GamePage.animalImageViewHashMap.put(animal,imageView);
            Scene scene = stage.getScene();
            root1= (AnchorPane) scene.getRoot();
            root1.getChildren().add(imageView);
            scene.setRoot(root1);
            stage.setScene(scene);
            stage.show();
            Controller.logger("WARNING","GAME PAGE .");
            renew();
        }catch (Exception e){}

    }

    public void buyChicken(ActionEvent event){
        try {
            if(GamePage.state) {   if(inputProcessor.processBuy("chicken")) this.addAnimal(event,"picturse\\chicken.png",300,250,80,80,Controller.mainController.animals.chickens.get(Controller.mainController.animals.chickens.size()-1)); }

        }catch (Exception e){}
           }

    public void buyTurkey(ActionEvent event){
        try {
            if(GamePage.state)if(inputProcessor.processBuy("turkey")) this.addAnimal(event,"picturse\\turkey.png",300,250,80,80,Controller.mainController.animals.turkeys.get(Controller.mainController.animals.turkeys.size()-1));
        }catch (Exception e){}
           }
    public void buyBuffalo(ActionEvent event){
        try {
            if(GamePage.state) {
                if(inputProcessor.processBuy("bufallo")){
                    this.addAnimal(event,"picturse\\buffalo.png",300,250,80,80,Controller.mainController.animals.bufallos.get(Controller.mainController.animals.bufallos.size()-1));
                }
            }
        }catch (Exception e){}
    }
    public void buyDog(ActionEvent event){
        try {
            if(GamePage.state) {
                if(inputProcessor.processBuy("dog"))
                    this.addAnimal(event,"picturse\\dog.png",300,250,80,80,Controller.mainController.animals.dogs.get(Controller.mainController.animals.dogs.size()-1));
            }
        }catch (Exception e){}


    }
    public void buyCat(ActionEvent event){
        try {
            if(GamePage.state) {
                if(inputProcessor.processBuy("cat"))
                    this.addAnimal(event,"picturse\\cat.png",300,250,80,80,Controller.mainController.animals.cats.get(Controller.mainController.animals.cats.size()-1));
            }
        }catch (Exception e){}

    }

    public void buyLion(ActionEvent event){
        try {
            if(GamePage.state) {
                for (int i = 0; i < 300; i++) {
                    if( Controller.mainController.personsController.CurrentUser.currentLevel.TimeOfLionComes.containsKey(i))
                        this.addWildeAnimal(event,"picturse\\lion.png",300,250,80,80,Controller.mainController.animals.chickens.get(Controller.mainController.animals.lions.size()-1));
                }
            }
        }catch (Exception e){}

    }

    public void setWildeAnimal(ActionEvent event){
        try {
            this.buyBear(event);
            this.buyLion(event);
            this.buyTiger(event);
        }catch (Exception e){}

    }
    public void buyTiger(ActionEvent event){
        if(GamePage.state) {
            for (int i = 0; i < 300; i++) {
                if (Controller.mainController.personsController.CurrentUser.currentLevel.TimeOfTigerComes.containsKey(i))
                    this.addWildeAnimal(event, "picturse\\tiger.png", 300, 250, 80, 80,Controller.mainController.animals.chickens.get(Controller.mainController.animals.tigers.size()-1));
            }
        }
    }

    public void buyBear(ActionEvent event){
        try {
            if(GamePage.state) {
                for (int i = 0; i < 300; i++) {
                    if (Controller.mainController.personsController.CurrentUser.currentLevel.TimeOfBearComes.containsKey(i))
                        this.addWildeAnimal(event, "picturse\\bear.png", 300, 250, 80, 80,Controller.mainController.animals.chickens.get(Controller.mainController.animals.bears.size()-1));
                }
            }
        }catch (Exception e){}
    }

    public void addWildeAnimal(ActionEvent event,String url , int x , int y , int high,int width,Animal animal){
        try {
            showTask();
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
                    if(inputProcessor.cage((int)imageView.getLayoutX(),(int)imageView.getLayoutY()))imageView.setVisible(false);
                    showTask();
                }
            });
            GamePage.animalImageViewHashMap.put(animal,imageView);
            imageView.setVisible(false);
            Scene scene = stage.getScene();
            root1= (AnchorPane) scene.getRoot();
            root1.getChildren().add(imageView);
            scene.setRoot(root1);
            stage.setScene(scene);
            stage.show();
            Controller.logger("WARNING","GAME PAGE .");
            renew();
        }catch (Exception e){}

    }

    public void showingWildeAnimal(Stage stages){
        try {

            this.singleWildeAnimalShowing("picturse\\lion.png",stages);
            this.singleWildeAnimalShowing("picturse\\tiger.png",stages);
            this.singleWildeAnimalShowing("picturse\\bear.png",stages);
        }catch (Exception e){}
    }

    public void singleWildeAnimalShowing(String url,Stage stages){
        try {
            if(GamePage.wildeAnimal.get(url)!=null)
                for (ImageView imageView : GamePage.wildeAnimal.get(url)) {
                    showTask();
                    Scene scene = stages.getScene();
                    root1= (AnchorPane) scene.getRoot();
                    double x,y,high,width;
                    x=imageView.getX();
                    y=imageView.getY();
                    high=imageView.getFitHeight();
                    width=imageView.getFitWidth();
                    Image image = new Image(getClass().getResourceAsStream(url));
                    imageView=new ImageView(image);
                    imageView.setLayoutX(x);
                    imageView.setLayoutY(y);
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
        }catch (Exception e){}

    }
    public void warHouse(MouseEvent event){
        try {
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
        }catch (Exception e){}

    }
    public void truckPage(MouseEvent event){
        try {
            if(!GamePage.state)try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("truckpage.fxml"));
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
                controllGame.loadTruck();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception exception) {}
    }

}
