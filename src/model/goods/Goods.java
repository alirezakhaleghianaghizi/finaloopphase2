package model.goods;

import model.Model;
import view.Timing;

public abstract class Goods extends Model {
    public int capacity;
    public int cost;
    public int timeOfRemaining;
    public Timing dateOfCreated;
    boolean isCatch;
    boolean isAvailable;
    boolean isSoled;

    public Goods(String name, double x, double y,int cost,int capacity,int timeOfRemaining) {
        super(name, x, y);
        this.cost=cost;
        this.capacity=capacity;
        this.timeOfRemaining=timeOfRemaining;
        this.dateOfCreated=new Timing();
        this.isCatch=false;
        this.isAvailable=true;
        this.isSoled=false;
    }
    public boolean isGoodAvailableAfterTimePassing(){
        if(!this.isCatch&&!this.isGoodAvailableInTheEarth())return false;

        else if(this.isCatch&&this.isSoled) return false;

        return true;
    }
    public boolean isGoodAvailableInTheEarth(){
        if(this.dateOfCreated.getDate()+this.timeOfRemaining>Timing.getCurrentTime()){
            return true;
        }
        return false;
    }

    public boolean catchTheGoodFromEarth(){
        if(this.isCatch) return false;
        else if(!this.isGoodAvailableInTheEarth()) return false;
        this.isCatch=true;
        return true;
    }

    public boolean sold(){
        if(this.isSoled) return false;
        if (!this.isCatch) return false;
        this.isSoled=true;
        return true;
    }
}
