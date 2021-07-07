package model.factory;

import model.Model;
import view.Timing;

public abstract class Factory extends Model {
    public int timeOfProducingGood;
    public Timing dateOfGettingWork;
    public int level;
    public boolean isWorking;
    public int numberOfGoodsCatch;
    public int coin;
    public Factory(int coin,String name, double x, double y,int timeOfProducingGood) {
        super(name, x, y);
        this.timeOfProducingGood=timeOfProducingGood;
        this.level=1;
        this.isWorking=false;
        this.numberOfGoodsCatch=0;
    }
    public boolean isItWorking(){
        if(this.isWorking)return true;
        return false;
    }
    public boolean work(int numberOfGoods){
        if(this.isItWorking())return false;
        if(numberOfGoods>this.level) return false;
        this.numberOfGoodsCatch=numberOfGoods;
        this.isWorking=true;
        this.dateOfGettingWork=new Timing();
        return true;
    }
    public boolean emptyingTheFactory(){
        this.numberOfGoodsCatch=0;
        this.isWorking=false;
        this.dateOfGettingWork=null;
        return true;
    }

    public boolean isItFinished(){
        int timeWorking=this.timeOfProducingGood/this.level*this.numberOfGoodsCatch;
        if(this.level==2&&this.timeOfProducingGood%2==1&&this.numberOfGoodsCatch==1){
            timeWorking=(this.timeOfProducingGood+1)/this.level*this.numberOfGoodsCatch;
        }
        if(!this.isWorking)return false;
        else if(this.dateOfGettingWork.getDate()+timeWorking<Timing.getCurrentTime()) return false;
        return this.emptyingTheFactory();
    }
    
}
