package view;

import controller.MainController;
import menus.Color;
import model.factory.Factory;
import model.factory.FactoryMoney;
import model.factory.first.EggPowder;
import model.factory.first.MilkSeprator;
import model.factory.first.Spinnery;
import model.factory.seccond.Chickenerry;
import model.factory.seccond.CookieBakery;
import model.factory.seccond.IceCreamFactory;
import model.factory.seccond.Weaving;

import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;

public class InputProcessor {
    MainController mainController;
    String input;
    Matcher matcher;
    public InputProcessor( MainController mainController) {
        this.mainController = mainController;
        this.input = "";
    }

    public void run(Scanner scanner,int level){
        System.out.print(Color.BLUE_BOLD);
        System.out.println("Enter your command ");
        System.out.print(Color.RESET);
        this.mainController.personsController.getCurrentUser().currentLevel=this.mainController.allLevels.levels.get(level-1);
        this.mainController.personsController.getCurrentUser().totalCoins=this.mainController.personsController.CurrentUser.coins+this.mainController.personsController.getCurrentUser().currentLevel.startCoins;
        this.mainController.isTasksCompleted=false;
        while(!this.mainController.isTasksCompleted&&!(this.input=scanner.nextLine()).equalsIgnoreCase("exit")){
            this.mainController.logger.lastChange=new Date();
            if((this.matcher=InputAlgorithms.BUY.inputMatcher(this.input)).find()) this.processBuy(this.matcher.group(1));
            else if((this.matcher=InputAlgorithms.PICKUP.inputMatcher(this.input)).find()) this.processPickUp(Double.parseDouble(this.matcher.group(1)),Double.parseDouble(this.matcher.group(2)));
            else if(InputAlgorithms.WELL.inputMatcher(this.input).find()) this.well();
            else if((this.matcher=InputAlgorithms.PLANT.inputMatcher(this.input)).find()) this.plant(Double.parseDouble(this.matcher.group(1)),Double.parseDouble(this.matcher.group(2)));
            else if((this.matcher=InputAlgorithms.BUILD.inputMatcher(this.input)).find()) this.build(this.matcher.group(1));
            else if((this.matcher=InputAlgorithms.UPGRAID.inputMatcher(this.input)).find()) this.upgraid(this.matcher.group(1));
            else if((this.matcher=InputAlgorithms.WORK.inputMatcher(this.input)).find()) this.work(this.matcher.group(1));
            else if((this.matcher=InputAlgorithms.CAGE.inputMatcher(this.input)).find()) this.cage(Double.parseDouble(this.matcher.group(1)),Double.parseDouble(this.matcher.group(2)));
            else if((this.matcher=InputAlgorithms.TURN.inputMatcher(this.input)).find()) this.turn(Integer.parseInt(this.matcher.group(1)));
            else if((this.matcher=InputAlgorithms.TRUCKLOAD.inputMatcher(this.input)).find()) this.truckLoad(this.matcher.group(1));
            else if((this.matcher=InputAlgorithms.TRUCKUNLOAD.inputMatcher(this.input)).find())this.processTruckUnload(this.matcher.group(1));
            else if(InputAlgorithms.TRUCKGO.inputMatcher(this.input).find())this.truckGo();
            else if(InputAlgorithms.INQUIRY.inputMatcher(this.input).find())this.inquiry();
            else {
                System.err.println("invalid input");
                this.mainController.logger.commands.add("ERROR,"+this.mainController.logger.lastChange.toString()+",INVALID INPUT COMMAND.");
            }
            this.mainController.isTasksCompleted();
            System.out.print(Color.BLUE_BOLD);
            if(!this.mainController.isTasksCompleted)System.out.println("Enter your command ");
            System.out.print(Color.RESET);
            this.mainController.logger.jasonWriter(this.mainController.logger);
        }

        this.mainController.logger.lastChange=new Date();
        if(!this.mainController.isTasksCompleted)this.mainController.logger.commands.add("ERROR,"+this.mainController.logger.lastChange.toString()+",EXIT THE GAME.");
        else if(this.mainController.isTasksCompleted) {
            this.completeLevel();
            System.out.println( "CONGRATULATION !!!!!\n" +
                    "YOU COMPLETED THE LEVEL");

        }
        this.mainController.logger.jasonWriter(mainController.logger);
    }

    public void completeLevel(){
        this.mainController.completeTheLevel();
    }

    public boolean build(String workShopName) {
        switch (workShopName.toUpperCase()) {
            case "EGGPOWDER":
                if (this.situationOfOpeningWorkShop(mainController.factories.eggPowderFactories,FactoryMoney.EGGPOWDER)==1) {
                    this.mainController.factories.eggPowderFactories=new EggPowder(0,1);
                    return true;
                }
                return false;
            case "COOKIEFACTORY":
                if (this.situationOfOpeningWorkShop(mainController.factories.cookieBakeryFactories ,FactoryMoney.COOKIEBAKERY)== 1) {
                    mainController.factories.cookieBakeryFactories = new CookieBakery(0, 3);
                    return true;
                }
                return false;
            case "MILKSEPRATOR":
                if (this.situationOfOpeningWorkShop(mainController.factories.milkSepratorFactories,FactoryMoney.MILKSEPERATOR) ==1) {
                    mainController.factories.milkSepratorFactories = new MilkSeprator(0, 5);
                     return true;
                }
                return false;
            case "ICECRAEMFACTORY":
                if ( this.situationOfOpeningWorkShop(mainController.factories.iceCreamFactories,FactoryMoney.ICECREAMFACTORY)== 1) {
                    mainController.factories.iceCreamFactories = new IceCreamFactory(7, 1);
                    return true;
                }
                return false;
            case "SPINNERY":
                if (this.situationOfOpeningWorkShop(mainController.factories.spinneryFactories,FactoryMoney.SPINNERY)==1) {
                    mainController.factories.spinneryFactories = new Spinnery(7, 3);
                    return true;
                }
                return false;
            case "WEAVING":
                if (this.situationOfOpeningWorkShop(mainController.factories.weavingFactories,FactoryMoney.WEAVING)==1) {
                    mainController.factories.weavingFactories = new Weaving(7, 5);
                    return true;
                }
            case "CHICKENERRY":
                if (this.situationOfOpeningWorkShop(mainController.factories.chickenerryFactory,FactoryMoney.CHICKENERRY)==1) {
                    mainController.factories.chickenerryFactory = new Chickenerry(7, 7);
                    return true;
                }
                return false;
        }
                this.mainController.logger.commands.add("ERROR," + this.mainController.logger.lastChange.toString() + ",THERE IS NO WORKSHOP WITH NAME " + workShopName);
                System.err.println("THERE IS NO WORKSHOP WITH NAME " + workShopName);
                return false;
    }

    public int situationOfOpeningWorkShop(Factory factory, FactoryMoney factoryMoney){
        if(factory!=null){
            System.err.println(factoryMoney.toString() +" already has been built  ...");
            this.mainController.logger.commands.add("ERROR," + this.mainController.logger.lastChange.toString() + "," +factoryMoney.toString() + "  has been built");
            return -1;
        }
        if(this.mainController.personsController.getCurrentUser().totalCoins<factoryMoney.getMoney()){
            System.err.println(factoryMoney.toString() + " cant open related to low money  ...");
            this.mainController.logger.commands.add("ERROR," + this.mainController.logger.lastChange.toString() + "," + factoryMoney.toString() + " cant open related to low money");
            return 0;
        }
        System.out.println(factoryMoney.toString() + " factory built");
        this.mainController.personsController.getCurrentUser().totalCoins-=factoryMoney.getMoney();
        this.mainController.logger.commands.add("INFO," + this.mainController.logger.lastChange.toString() + "," + factoryMoney.toString() + " built");
        return 1;
    }

    public boolean processBuy(String animalName){
        int situation=mainController.animals.buyAnimal(animalName,mainController.personsController.getCurrentUser());
        if( situation==1) {
           System.out.println(animalName+" Have bought");
           this.mainController.logger.commands.add("INFO,"+this.mainController.logger.lastChange.toString()+",BUY "+animalName+" .");
           return true;
       }else if(situation==0) {
           this.mainController.logger.commands.add("ERROR,"+this.mainController.logger.lastChange.toString()+",not have enough coins TO BUY"+animalName+".");
           System.err.println("not have enough coins");
           return false;
       }
       else if(situation==-1){
           this.mainController.logger.commands.add("ERROR,"+this.mainController.logger.lastChange.toString()+",not include animal name : "+animalName+" ");
           System.err.println("there is no animal with "+animalName+" name ");
           return false;
       }
        return false;
    }

    public boolean processPickUp(double x, double y){
        if(x>6|x<0|y<0|y>6){
            this.mainController.logger.commands.add("ERROR,"+this.mainController.logger.lastChange.toString()+",OUT OF SURFACE SPOT IN PICKUP+.");
            System.err.println("the spot you choose is not in the surface");
            return false;
        }else {
            if (mainController.goods.pickUp(x, y, mainController.gadgets, this.mainController.logger)) {
                System.out.println("to the WareHouse ...");
                // mainController.goods.productGoods.remove();
                return true;
            }
            return false;
        }
    }

    public boolean plant(double x,double y){
        if(x>6|x<0|y<0|y>6){
            this.mainController.logger.commands.add("ERROR,"+this.mainController.logger.lastChange.toString()+",OUT OF SURFACE SPOT IN PLANT+.");
            System.err.println("the spot you choose is not in the surface");
            return false;
        }
        int situation=mainController.goods.plant(x,y, mainController.factories, mainController.gadgets);
        if(situation==2){
            this.mainController.logger.commands.add("INFO,"+this.mainController.logger.lastChange.toString()+",plant in x : "+x+" ,y : "+y+".");
            System.out.println("Plant [x:"+x+",y:"+y+"]");
            return true;
        }else if(situation==1) {
            this.mainController.logger.commands.add("ERROR,"+this.mainController.logger.lastChange.toString()+",planting one factory.");
            System.err.println("cant plant one factory ");
            return false;
        }
        else if(situation==0){
            this.mainController.logger.commands.add("ERROR,"+this.mainController.logger.lastChange.toString()+",planting one gadget.");
            System.err.println("cant plant one gadget ");
            return false;
        }
        else if(situation==-1){
            this.mainController.logger.commands.add("ERROR,"+this.mainController.logger.lastChange.toString()+",well is empty for planting.");
            System.err.println("well is empty ");
            return false;
        }
        return true;
    }

    public boolean well(){
        if(mainController.gadgets.well()){
            this.mainController.logger.commands.add("INFO,"+this.mainController.logger.lastChange.toString()+",well working.");
            System.out.println("well working");
            return true;
        }else {
            this.mainController.logger.commands.add("ERROR,"+this.mainController.logger.lastChange.toString()+",well is full.");
            System.err.println("The well is still full ");
            return false;
        }
    }

    public boolean workSituation(Factory factory){
        if (factory == null) {
            System.err.println("factory  is not build yet");
            this.mainController.logger.commands.add("ERROR," + this.mainController.logger.lastChange.toString() + ", factory is not build");
            return false;
        }
        int situation=mainController.factories.workFactory(factory, mainController.gadgets);
        if(situation==1) {
            System.out.println(factory.name + " working ...");
            this.mainController.logger.commands.add("INFO,"+this.mainController.logger.lastChange.toString()+","+factory.name+" started working");
            return true;
        }
        else if(situation==-1) {
            System.err.println(factory.name + " is already working ...");
            this.mainController.logger.commands.add("ERROR,"+this.mainController.logger.lastChange.toString()+","+factory.name+" is already working");
            return false;
        }
        else if(situation==0){
            System.err.println("there is no enough good to work ...");
            this.mainController.logger.commands.add("ERROR,"+this.mainController.logger.lastChange.toString()+",not enough good");
            return false;

        }
        return false;
    }

    public boolean work(String workShopName){
        switch (workShopName.toUpperCase()){
            case "EGGPOWDER": return workSituation(mainController.factories.eggPowderFactories);
            case "COOKIEFACTORY":return workSituation(mainController.factories.cookieBakeryFactories);
            case "MILKSEPRATOR":return workSituation(mainController.factories.milkSepratorFactories);
            case "ICECRAEMFACTORY":return workSituation(mainController.factories.iceCreamFactories);
            case "SPINNERY":return workSituation(mainController.factories.spinneryFactories);
            case "WEAVING":return workSituation(mainController.factories.weavingFactories);
            case "CHICKENNERY":return workSituation(mainController.factories.chickenerryFactory);
        }
        this.mainController.logger.commands.add("ERROR,"+this.mainController.logger.lastChange.toString()+",THERE IS NO WORKSHOP WITH NAME "+workShopName);
        System.err.println("THERE IS NO WORKSHOP WITH NAME "+workShopName);
        return false;
    }

    public boolean upgraid(String workShopName){
        switch (workShopName.toUpperCase()){
            case "EGGPOWDER": return upgraidSituation(mainController.factories.eggPowderFactories);
            case "COOKIEFACTORY":return upgraidSituation(mainController.factories.cookieBakeryFactories);
            case "MILKSEPRATOR":return upgraidSituation(mainController.factories.milkSepratorFactories);
            case "ICECRAEMFACTORY":return upgraidSituation(mainController.factories.iceCreamFactories);
            case "SPINNERY":return upgraidSituation(mainController.factories.spinneryFactories);
            case "WEAVING":return upgraidSituation(mainController.factories.weavingFactories);
            case "CHICKENNERY":return upgraidSituation(mainController.factories.chickenerryFactory);
        }
        this.mainController.logger.commands.add("ERROR,"+this.mainController.logger.lastChange.toString()+",THERE IS NO WORKSHOP WITH NAME "+workShopName);
        System.err.println("THERE IS NO WORKSHOP WITH NAME "+workShopName);
        return false;
    }

    public boolean upgraidSituation(Factory factory){
        if (factory == null) {
            System.err.println( "factory is not build yet");
            this.mainController.logger.commands.add("ERROR," + this.mainController.logger.lastChange.toString() + ", factory is not build");
            return false;
        }
        if (factory.level==1){
            factory.level++;
            System.out.println(factory.name + " upgraded");
            this.mainController.logger.commands.add("INFO," + this.mainController.logger.lastChange.toString() + "," + factory.name + " UPGRADED");
        }
        else {
            System.err.println(factory.name + " is in max level");
            this.mainController.logger.commands.add("ERROR," + this.mainController.logger.lastChange.toString() + "," + factory.name + "is in max level");
        }
        return true;
    }
    //TODO for tomorrow

    public boolean cage(double x,double y){
        System.out.println("cage x : "+x+"\n y : "+y);
        if(x>6|x<0|y<0|y>6){
            this.mainController.logger.commands.add("ERROR ,"+this.mainController.logger.lastChange.toString()+", out surface spot to cage ");
            System.err.println("the spot you choose is not in the surface");
            return false;
        }
        int situation=mainController.animals.cage(x,y, mainController.goods, mainController.logger);
        if(situation==1){
            System.out.println("wild animal caged and send to warehouse");
            return true;
        }
        else if(situation==0){

            System.out.println("wild animal caged ");
            return true;
        }
        System.err.println("there is no wild animal there");
        return false ;
    }

    public boolean truckLoad(String itemName){
        if(mainController.returnGoodInWarehouseByName(itemName)==null){
            System.err.println("there is no "+itemName);
            this.mainController.logger.commands.add("ERROR ,"+this.mainController.logger.lastChange.toString()+",null item name"+itemName);
            return false;
        }
       int situation=mainController.gadgets.truckLoad(mainController.returnGoodInWarehouseByName(itemName),mainController);
        if(situation==-1){
            System.err.println("truck has no enough space");
            this.mainController.logger.commands.add("ERROR ,"+this.mainController.logger.lastChange.toString()+",not enough space in truck to load"+itemName);
            return false;
        }
        else if(situation==0){
            System.err.println("truck has go");
            this.mainController.logger.commands.add("ERROR ,"+this.mainController.logger.lastChange.toString()+", truck has gone ");
            return false;
        }
        else if(situation==1){
            System.out.println(itemName+" loaded to truck");
            this.mainController.logger.commands.add("INFO ,"+this.mainController.logger.lastChange.toString()+",truck load "+itemName);
            return true;
        }
        return true;
    }

    public boolean processTruckUnload(String itemName) {
        if(mainController.returnInTruckGoodsByName(itemName)==null){
            System.err.println("there is "+itemName);
            this.mainController.logger.commands.add("ERROR ,"+this.mainController.logger.lastChange.toString()+",null item name"+itemName);
            return false;
        }
        int situation=mainController.gadgets.truckUnload(mainController.returnInTruckGoodsByName(itemName));
        if (situation==1) {
            System.out.println("truck unload " + itemName);

            this.mainController.logger.commands.add("INFO ,"+this.mainController.logger.lastChange.toString()+",truck un load" + itemName);
            return true;
        }
        else if(situation==0){
            System.err.println("truck dose not have "+itemName);
            this.mainController.logger.commands.add("ERROR ,"+this.mainController.logger.lastChange.toString()+",truck DOSE NOT HAVE " + itemName);
            return false;
        }
        else if(situation==-1) {
            System.err.println("truck have gone ");
            this.mainController.logger.commands.add("ERROR,"+this.mainController.logger.lastChange.toString()+",truck have gone" );
            return false;
        }
        return true;
    }

    public boolean truckGo(){

        if(mainController.gadgets.truckGo()){
            System.out.println("truck go");
            this.mainController.logger.commands.add("INFO,"+this.mainController.logger.lastChange.toString()+",truck STARTED THE TRAVEL" );
            return true;
        }
        System.err.println("truck is one the way");
        this.mainController.logger.commands.add("ERROR,"+this.mainController.logger.lastChange.toString()+",CANT SEND THE GONE TRUCK" );
        return true;
    }

    public boolean inquiry(){
        this.mainController.logger.commands.add("INFO ,"+this.mainController.logger.lastChange.toString()+", inquiry command");
        mainController.showAfterTurn();
        return true;
    }

    public boolean turn(int n){
        mainController.turn(n);
        System.out.println("time of turning : "+n);
        //turn time in manager
        return true;
    }

}
