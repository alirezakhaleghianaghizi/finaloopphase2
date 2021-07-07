package model.animal.producer;

import model.animal.Animal;
import view.Timing;

public  abstract class producerAnimal extends Animal {
    public Timing produce;
    public Timing decreaseLive;
    public Timing startToWalk;
    public int produceTime;
    public boolean isFull;

    public producerAnimal(String name, double x, double y, double footStep, int livies, int cost) {
        super(name, x, y, footStep, livies, cost);
    }

    public boolean haveEaten() {
        if(this.livies>=80){
            this.isFull = true;
            return true;
        }else {
            this.isFull=false;
            return false;
        }
    }

    public boolean produce() {
        this.haveEaten();
        if(this.isFull&&this.produce==null){
            produce = new Timing();
            return true;
        }
        return false;
    }

    public boolean decreaseLive(){
        this.decreaseLive= new Timing();
        return true;
    }

    public boolean isDie(){
        if(this.livies<10)
        {
            this.livies=0;
            return true;
        }
        return false;
    }



}
