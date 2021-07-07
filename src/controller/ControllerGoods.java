package controller;

import menus.Logger;
import model.goods.*;

import java.util.ArrayList;

public class ControllerGoods {

        ArrayList<Cloth> clothes;
        ArrayList<Cookie> cookies;
        ArrayList<Egg> eggs;
        ArrayList<Feather> feathers;
        ArrayList<Flour> flours;
        ArrayList<Grass> grasses;
        ArrayList<IceCream> iceCreams;
        ArrayList<Milk> milks;
        ArrayList<SepratedMilk> sepratedMilks;
        ArrayList<Silk> silks;
        ArrayList<Water> Waters;
        ArrayList<LionDoll> lionDolls ;
        ArrayList<TigerDoll> tigerDolls;
        ArrayList<BearDoll> bearDolls;
        public  ArrayList<Goods> productGoods ;

    public ControllerGoods() {
            this.clothes = new ArrayList<>();
            this.cookies = new ArrayList<>();
            this.eggs = new ArrayList<>();
            this.feathers = new ArrayList<>();
            this.flours = new ArrayList<>();
            this.grasses = new ArrayList<>();
            this.iceCreams = new ArrayList<>();
            this.milks = new ArrayList<>();
            this.sepratedMilks = new ArrayList<>();
            this.silks = new ArrayList<>();
            this.Waters = new ArrayList<>();
            this.lionDolls = new ArrayList<>();
            this.tigerDolls= new ArrayList<>();
            this.bearDolls= new ArrayList<>();
            this.productGoods = new ArrayList<>();
        }

    public int plant(double x , double y,ControllerFactory factory,ControllerGadget gadget){
        if ( factory.cookieBakeryFactories!=null) {
            if(factory.cookieBakeryFactories.x==x&&factory.cookieBakeryFactories.y==y){return 1;}
        }
        if (factory.eggPowderFactories!=null) {
            if(factory.eggPowderFactories.x==x&&factory.eggPowderFactories.y==y){return 1;}
        }
        if (factory.iceCreamFactories!=null) {
            if(factory.iceCreamFactories.x==x&&factory.iceCreamFactories.y==y){return 1;}
        }
        if (factory.milkSepratorFactories!=null) {
            if(factory.milkSepratorFactories.x==x&&factory.milkSepratorFactories.y==y){return 1;}
        }

        if (factory.spinneryFactories!=null) {
            if(factory.spinneryFactories.x==x&&factory.spinneryFactories.y==y){return 1;}
        }
        if (factory.weavingFactories!=null) {
            if(factory.weavingFactories.x==x&&factory.weavingFactories.y==y){return 1;}
        }
        if(gadget.well.x==x&&gadget.well.y==y){return 0;}
        if(gadget.truck.x==x&&gadget.truck.y==y){return 0;}
        if(gadget.warehouse.x==x&&gadget.warehouse.y==y){return 0;}
        if(gadget.well.capacity<GoodsEnum.GRASS.getCapacity()){
            gadget.well.isfull=false;
            return -1;
        }
            gadget.well.capacity--;
            grasses.add(new Grass(x,y));
            return 2;
    }

    public boolean pickUp(double x , double y, ControllerGadget gadget, Logger logger){
        for (Goods e : productGoods) {
            if(e.x==x&&e.y==y){
                if(gadget.warehouse.haveSpace(e)){
                    gadget.warehouse.existence.add(e);
                    gadget.warehouse.seprateGoods(e);
                    productGoods.remove(e);
                    logger.commands.add("INFO,"+logger.lastChange.toString()+",PICKUP "+e.name+" TO WAREHOUSE.");
                    return true;
                }else {
                    logger.commands.add("ERROR,"+logger.lastChange.toString()+",NOT ENOUGH SPACE IN WAREHOUSE TO PICKUP "+e.name+" .");
                    System.err.println("WareHouse does not have enough space ");
                    return false;
                }
            }
        }
        logger.commands.add("ERROR ,"+logger.lastChange.toString()+",NO GOODS TO PICKUP.");
        System.err.println("there is no Goods ");
        return false;
    }

    public void showGrass(){
        int [] [] grassNum = new int[6][6];
        for (int i=0;i<6;i++){
            for (int j=0;j<6;j++){
                for (Grass grass : grasses) {
                    if(grass.x==i+1&&grass.y==j+1){
                        grassNum[i][j]++;
                    }
                }
            }
        }
        for (int i=0;i<6;i++){
            for (int j=0;j<6;j++){
                System.out.print(grassNum[i][j]+" ");
            }
            System.out.println();
        }
    }

}
