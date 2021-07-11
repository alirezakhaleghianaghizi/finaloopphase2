package sample;

import controller.MainController;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class GamePage {
    MainController mainController;
    @FXML
    private Stage stage;
    private Scene scene;
    private Scene sceneGround;
    private Parent root;
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
        }
    };
    }
public void renew() {

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
        }



    public  void start(ActionEvent event) {
            GamePage.timer=new Timer();
            //puse.setText(Controller.mainController.personsController.CurrentUser.currentLevel.task2);
            this.renew1();
            GamePage.timer.scheduleAtFixedRate(task, 0, 1000);
            //timer.scheduleAtFixedRate(task, date.getTime(), 1000);
            //timer.schedule(task, 1000);
            //timer.schedule(task, date.getTime());
        }


    public void stop(ActionEvent event){
        GamePage.state=false;
        GamePage.timer.cancel();
    }
    //TODO mach with back work factory
    public void weavingBuild(){if(GamePage.state)this.buildWork(weavingBuild); }
    public void eggPowderBuild(){if(GamePage.state)this.buildWork(eggPowderBuild); }
    public void milkSeperatorBuild(){if(GamePage.state)this.buildWork(milkSeperatorBuild); }
    public void bakeryBuild(){if(GamePage.state)this.buildWork(bakeryBuild); }
    public void spinneryBuild(){if(GamePage.state)this.buildWork(spinneryBuild); }
    public void chickenneryBuild(){if(GamePage.state)this.buildWork(chickenneryBuild); }
    public void iceCreamBuild(){if(GamePage.state)this.buildWork(iceCreamBuild); }
    public void buildWork(Button button){
        if(button.isVisible()) button.setVisible(false);
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
            System.out.println(GamePage.min);
            System.out.println(GamePage.sec);
            System.out.println(GamePage.state);
            System.out.println(controllGame.min);
            System.out.println(controllGame.sec);
            System.out.println(controllGame.state);
       controllGame.setUsernameLable();
       controllGame.buttonColor();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
