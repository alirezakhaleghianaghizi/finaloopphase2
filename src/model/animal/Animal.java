package model.animal;
import javafx.scene.image.ImageView;
import model.Model;
import view.Timing;

import java.util.Random;

public class Animal extends Model {
    Timing startMoving ;
    public double footStep;
    public int livies ;
    public int cost;
    public int timeMovingPeriode;
    public Animal(String name, double x, double y, double footStep, int livies,int cost) {
        super(name, 300, 250);
        this.footStep = footStep;
        this.livies = livies;
        this.cost=cost;
    }

    public void randomMoving (){
        startMoving = new Timing();
        Random random = new Random();
        int randSituation = random.nextInt(4);
        switch (randSituation){
            case 0:
                if(this.x+footStep>=120&&this.x+footStep<=600) {
                    //this.timeMovingPeriode=footStep/animal.footStep;
                    this.x += footStep;
                }
                else   this.x-=footStep;
                break;
            case 1:
                if(this.x-footStep>=120&&this.x-footStep<=600) this.x-=footStep;
                else   this.x+=footStep;
                break;
            case 2:
                if(this.y+footStep>=140&&this.y+footStep<=490)this.y+=footStep;
                else    this.y-=footStep;
                break;
            case 3:
                if(this.y-footStep>=140&&this.y-footStep<=490) this.y-=footStep;
                  else    this.y+=footStep;
                break;
        }
    }


}
