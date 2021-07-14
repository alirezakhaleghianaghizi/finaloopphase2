package controller;

import menus.Logger;
import model.animal.Animal;
import model.animal.AnimalEnum;
import model.animal.asisstant.Cat;
import model.animal.defender.Dog;
import model.animal.producer.Bufallo;
import model.animal.producer.Chicken;
import model.animal.producer.Turkey;
import model.animal.producer.producerAnimal;
import model.animal.wild.Bear;
import model.animal.wild.Lion;
import model.animal.wild.Tiger;
import model.animal.wild.WildAnimal;
import model.factory.Factory;
import model.goods.*;
import model.level.Level;
import view.Timing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class MainController {

    public ControllerGoods goods;
    public ControllerFactory factories;
    public ControllerAnimal animals;
    public AllLevels allLevels;
    public ControllerGadget gadgets;
    public PersonsController personsController;
    public Timing timing;
    public Logger logger ;
    public boolean isTasksCompleted;
    public static HashMap<WildAnimal, HashMap<Integer, Integer>> cageTimeSet;

    public MainController() {
        this.goods = new ControllerGoods();
        this.factories = new ControllerFactory();
        this.animals = new ControllerAnimal();
        this.allLevels = new AllLevels();
        this.gadgets = new ControllerGadget();
        this.personsController = new PersonsController();
        this.timing = new Timing();
        this.logger=new Logger();
        this.isTasksCompleted=false;
        cageTimeSet= new HashMap<>();
    }


    public void turn(int n) {
        for (int i = 1; i <= n; i++) {
            this.expire();
            this.wildComing();
            this.movingAllAnimal();
            this.eatAllanimal();
            this.timing.goForward();
            this.wellFulling();
            this.producing();
            this.decreaseLive();
            this.productingGoodWorkshop();
            if(this.isTasksCompleted()) break;
        }
        this.showAfterTurn();
    }

    public void completeTheLevel(){
        this.goods=new ControllerGoods();
        this.factories=new ControllerFactory();
        this.animals=new ControllerAnimal();
        this.gadgets=new ControllerGadget();
       if(this.personsController.CurrentUser.currentLevel.maxTime>=Timing.getCurrentTime()) this.personsController.CurrentUser.coins=this.personsController.CurrentUser.totalCoins+this.personsController.CurrentUser.currentLevel.presentCoin;
        else this.personsController.CurrentUser.coins=this.personsController.CurrentUser.totalCoins;
        this.personsController.CurrentUser.totalCoins=0;
        if(this.personsController.CurrentUser.level==this.allLevels.levels.indexOf(this.personsController.CurrentUser.currentLevel))this.personsController.CurrentUser.level++;
        this.logger.commands.add("ALARM,"+this.logger.lastChange.toString()+",COMPLETED LEVEL "+this.allLevels.levels.indexOf(this.personsController.CurrentUser.currentLevel)+" .");
    }

    public boolean isTasksCompleted(){
        if(!this.isSingleTaskCompleted(this.personsController.CurrentUser.currentLevel.task1,this.personsController.CurrentUser.currentLevel.task1Number)) return false;
        if(!this.isSingleTaskCompleted(this.personsController.CurrentUser.currentLevel.task2,this.personsController.CurrentUser.currentLevel.task2Number)) return false;
        if(!this.isSingleTaskCompleted(this.personsController.CurrentUser.currentLevel.task3,this.personsController.CurrentUser.currentLevel.task3Number)) return false;
        this.isTasksCompleted=true;
        return true;
    }

    public boolean isSingleTaskCompleted(String task,int taskNumber){
        for (GoodsEnum value : GoodsEnum.values()) {
            String good = value.toString();
            if(good.equalsIgnoreCase(task)){
                if(this.returnArrByGoodName(good).size()<taskNumber)return false;
            }
        }
        for (AnimalEnum value : AnimalEnum.values()) {
            String animal = value.toString();
            if(animal.equalsIgnoreCase(task)){
                if(this.returnArrByAnimalName(animal).size()<taskNumber) return false;
            }
        }
        if(task.equalsIgnoreCase("coin")){
                if(this.personsController.getCurrentUser().totalCoins<taskNumber)return false;
        }
        return true;
    }
    public void showAfterTurn() {
        System.out.println("YOU HAVE "+this.personsController.CurrentUser.totalCoins+" coins.");
        this.showGood();
        System.out.println("The time passed :" + timing.getCurrentTime());
        goods.showGrass();
        animals.showAnimal();
        showTask(personsController.getCurrentUser().currentLevel);
    }

    public void productingGoodWorkshop(){
        if(this.factories.eggPowderFactories!=null)this.singleWorkShopEndedSituation(this.factories.eggPowderFactories,new Flour(this.factories.eggPowderFactories.x,this.factories.eggPowderFactories.y),new Flour(this.factories.eggPowderFactories.x,this.factories.eggPowderFactories.y));
        if(this.factories.weavingFactories!=null)this.singleWorkShopEndedSituation(this.factories.weavingFactories,new Cloth(this.factories.weavingFactories.x,this.factories.weavingFactories.y),new Cloth(this.factories.weavingFactories.x,this.factories.weavingFactories.y));
        if(this.factories.spinneryFactories!=null)this.singleWorkShopEndedSituation(this.factories.spinneryFactories,new Silk(this.factories.spinneryFactories.x,this.factories.spinneryFactories.y),new Silk(this.factories.spinneryFactories.x,this.factories.spinneryFactories.y));
        if(this.factories.iceCreamFactories!=null)this.singleWorkShopEndedSituation(this.factories.iceCreamFactories,new IceCream(this.factories.iceCreamFactories.x,this.factories.iceCreamFactories.y),new Flour(this.factories.iceCreamFactories.x,this.factories.iceCreamFactories.y));
        if(this.factories.milkSepratorFactories!=null)this.singleWorkShopEndedSituation(this.factories.milkSepratorFactories,new SepratedMilk(this.factories.milkSepratorFactories.x,this.factories.milkSepratorFactories.y),new SepratedMilk(this.factories.milkSepratorFactories.x,this.factories.milkSepratorFactories.y));
        if(this.factories.cookieBakeryFactories!=null)this.singleWorkShopEndedSituation(this.factories.cookieBakeryFactories,new Cookie(this.factories.cookieBakeryFactories.x,this.factories.cookieBakeryFactories.y),new Cookie(this.factories.cookieBakeryFactories.x,this.factories.cookieBakeryFactories.y));
        if(this.factories.chickenerryFactory!=null)this.singleWorkShopEndedSituationChicken(this.factories.chickenerryFactory,new Chicken(),new Chicken());
    }
    public void singleWorkShopEndedSituation(Factory factory,Goods goods1,Goods goods2){
        int capacityAlraedyAddedToFactory=factory.numberOfGoodsCatch;
        if(factory.isItFinished()){
            if(capacityAlraedyAddedToFactory==1){
                this.goods.productGoods.add(goods1);
            }
            if(capacityAlraedyAddedToFactory==2){
                this.goods.productGoods.add(goods1);
                this.goods.productGoods.add(goods2);
            }
        }
    }
    public void singleWorkShopEndedSituationChicken(Factory factory, Animal animal1, Animal animal2){
        int capacityAlraedyAddedToFactory=factory.numberOfGoodsCatch;
        if(factory.isItFinished()){
            if(capacityAlraedyAddedToFactory==1){
                this.animals.chickens.add((Chicken) animal1);
                animal1.x=factory.x;
                animal1.y=factory.y;
            }
            if(capacityAlraedyAddedToFactory==2){
                this.animals.chickens.add((Chicken) animal1);
                this.animals.chickens.add((Chicken) animal2);
                animal1.x=factory.x;
                animal1.y=factory.y;
                animal2.x=factory.x;
                animal2.y=factory.y;
            }
        }
    }

    public void showSingleTask(String task,int taskNum,int taskNumber){
        for (GoodsEnum value : GoodsEnum.values()) {
            String good = value.toString();
            if(good.equalsIgnoreCase(task)){
                System.out.println(taskNum+" : "+task+" : "+returnArrByGoodName(good).size()+"/"+taskNumber);
            }
        }
        for (AnimalEnum value : AnimalEnum.values()) {
            String animal = value.toString();
            if(animal.equalsIgnoreCase(task)){
                System.out.println(taskNum+" : "+task+" : "+returnArrByAnimalName(animal).size()+"/"+taskNumber);
            }
        }
        if(task.equalsIgnoreCase("coin")){
            System.out.println(taskNum+" : coins : "+personsController.getCurrentUser().totalCoins+"/"+taskNumber);
        }
    }

    public void showTask(Level level){
        this.showSingleTask(level.task1,1, level.task1Number);
        this.showSingleTask(level.task2,2, level.task2Number);
        this.showSingleTask(level.task3,3, level.task3Number);

    }

    public void showGood(){
        if(this.goods.productGoods!=null)
        for (Goods productGood : this.goods.productGoods) {
            System.out.println(productGood.name+"\t x :"+productGood.x+"\t y: "+ productGood.y);
        }
    }

    //Search methods :
    public ArrayList returnArrByGoodName(String GoodName){
        switch (GoodName.toUpperCase(Locale.ROOT)){
            case "EGG" : return gadgets.warehouse.eggInWareHouse;
            case "COOKIE":return gadgets.warehouse.cookieInWareHouse;
            case "SILK":return gadgets.warehouse.silkInWareHouse;
            case "MILK":return gadgets.warehouse.milkInWareHouse;
            case "SEPRATEDMILK":return gadgets.warehouse.sepratedMilkInWareHouse;
            case"FEATHER":return gadgets.warehouse.featherInWareHouse;
            case "CLOTH" :return gadgets.warehouse.clothInWareHouse;
            case "FLOUR" :return gadgets.warehouse.flourInWareHouse;
            case "ICECREAM" :return gadgets.warehouse.iceCreamInWareHouse;
            case "BEARDOLL" :return gadgets.warehouse.bearDollInWareHouse;
            case "LIONDOLL" :return gadgets.warehouse.lionDollInWareHouse;
            case "TIGERDOLL" :return gadgets.warehouse.tigerDollInWareHouse;
        }
        return null;
    }

    public ArrayList returnArrByAnimalName(String animalName){
        switch (animalName){
            case "BUFALLO" :return animals.bufallos;
            case "CHICKEN" :return animals.chickens;
            case "TURKEY" :return animals.turkeys;
            case "BEAR" :return animals.bears;
            case "LION" :return animals.lions;
            case "TIGER" :return animals.tigers;
            case "CAT" :return animals.cats;
            case "DOG" :return animals.dogs;
        }
        return null;
    }

    public Goods returnGoodInWarehouseByName(String name){
        for (Goods goods1 : gadgets.warehouse.existence) {
            if(goods1.name.equalsIgnoreCase(name)){
                return goods1;
            }
        }
        return null;
    }

    public Goods returnProductByLocation(double x , double y){
        for (Goods productGood : goods.productGoods) {
            if(productGood.x==x&&productGood.y==y){
                return productGood;
            }
        }
        return null;
    }

    public Goods returnInTruckGoodsByName(String name){
        for (Goods truckgood : gadgets.truckgoods) {
            if(truckgood.name.equalsIgnoreCase(name))
                return truckgood;
        }
        return null;
    }

    //check time passing after turn :
    public boolean wellFulling(){
        if(gadgets.well.fulling==null)return false;
        if(gadgets.well.fulling.getDate()+gadgets.well.timePeride<=Timing.getCurrentTime()){
            gadgets.well.capacity=5;
            return true;
        }
            return false;
    }

    public void producing(){
        for (Chicken chicken : animals.chickens) {
            if(chicken.produce!=null){
                if(chicken.produce.getDate()+chicken.produceTime<=Timing.getCurrentTime()){
                    goods.productGoods.add(new Egg(chicken.x, chicken.y));
                    chicken.isFull=false;
                    chicken.produce=null;
                }
            }
        }
        for (Turkey turkey : animals.turkeys) {
            if (turkey.produce!=null) {
                if (turkey.produce.getDate() + turkey.produceTime <= Timing.getCurrentTime()) {
                    goods.productGoods.add(new Feather(turkey.x, turkey.y));
                    turkey.isFull=false;
                    turkey.produce=null;
                }
            }
        }
        for (Bufallo bufallo : animals.bufallos) {
            if (bufallo.produce != null) {
                if (bufallo.produce.getDate() + bufallo.produceTime <= Timing.getCurrentTime()) {
                    goods.productGoods.add(new Milk(bufallo.x, bufallo.y));
                    bufallo.isFull=false;
                    bufallo.produce = null;
                }
            }
        }
    }

        //Moving methods :
    public boolean movingProducerAnimal(producerAnimal animal) {
        double path;
        double xDest = animal.x, yDest = animal.y;
        double minPath = 99999999;
        for (Grass grass : goods.grasses) {
            path = Math.abs(animal.x - grass.x) + Math.abs(animal.y - grass.y);
            if (path < minPath) {
                minPath = path;
                xDest = grass.x;
                yDest = grass.y;
            }
        }
        return goToDest(xDest,yDest,animal);
    }

    public boolean goToDest(double xDest , double yDest , Animal animal){
        if (animal.x == xDest && animal.y == yDest) {
            return false;
        } else if (animal.x == xDest) {
            if (animal.y > yDest) {
                animal.y-=10;
            } else {
                animal.y+=10;
                return true;
            }
        } else if (animal.y == yDest) {
            if (animal.x > xDest) {
                animal.x-=10;
            } else {
                animal.x+=10;
                return true;
            }
        } else {
            if (Math.abs(animal.x - xDest) > Math.abs(animal.y - yDest)) {
                if (animal.x > xDest) {
                    animal.x-=10;
                } else {
                    animal.x+=10;
                    return true;
                }
            } else {
                if (animal.y > yDest) {
                    animal.y-=10;
                    return true;
                } else {
                    animal.y+=10;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean movingCatAnimal(Animal animal){
        double path;
        double xDest = 0, yDest = 0;
        double minPath = 9999999;
        for (Goods productGood : goods.productGoods) {
            path = Math.abs(animal.x - productGood.x) + Math.abs(animal.y - productGood.y);
            if (path < minPath) {
                minPath = path;
                xDest = productGood.x;
                yDest = productGood.y;
            }
        }
        return goToDest(xDest,yDest,animal);
    }

    public void movingDog(Dog dog){
        double path;
        double xDest= dog.x , yDest= dog.y;
        double minPath=9999999;
        for (Lion lion : animals.lions) {
            path=Math.abs(dog.x- lion.x)+Math.abs(dog.y-lion.y);
            if(path<minPath){
                minPath=path;
                xDest= lion.x;
                yDest= lion.y;
            }
        }
        if(animals.lions.size()>0) {
            goToDest(xDest, yDest, dog);
            dogAndWildDie();
        }
        for (Tiger tiger : animals.tigers) {
            path=Math.abs(dog.x- tiger.x)+Math.abs(dog.y-tiger.y);
            if(path<minPath){
                minPath=path;
                xDest= tiger.x;
                yDest= tiger.y;
            }
        }
        if(animals.tigers.size()>0) {
            goToDest(xDest, yDest, dog);
            dogAndWildDie();
        }
        for (Bear bear : animals.bears) {
            path=Math.abs(dog.x- bear.x)+Math.abs(dog.y-bear.y);
            if(path<minPath){
                minPath=path;
                xDest= bear.x;
                yDest= bear.y;
            }
        }
        if(animals.bears.size()>0){
            goToDest(xDest, yDest, dog);
            dogAndWildDie();
        }
        if(animals.lions.size()==0&&animals.tigers.size()==0&&animals.bears.size()==0)
        dog.randomMoving();


    }

    public void movingAllAnimal(){
        for (Chicken chicken : animals.chickens) {
            movingProducerAnimal(chicken);
        }
        for (Turkey turkey : animals.turkeys) {
            movingProducerAnimal(turkey);
        }
        for (Bufallo bufallo : animals.bufallos) {
            movingProducerAnimal(bufallo);
        }
        for (Cat cat : animals.cats) {
            movingCatAnimal(cat);
        }
        for (Dog dog : animals.dogs) {
            movingDog(dog);
        }
        for (Lion lion : animals.lions) {
            lion.randomMoving();
        }
        for (Tiger tiger : animals.tigers) {
            tiger.randomMoving();
        }
        for (Bear bear : animals.bears) {
            bear.randomMoving();
        }
        wildsAttack();
    }

        //Eating methods :
    public boolean eat(producerAnimal animal,Grass grass){
       {
            if(!animal.isFull&&animal.produce==null){
                animal.livies=110;
                animal.isFull=true;
                animal.produce=new Timing();
                goods.grasses.remove(grass);
                return true;
            }
        }
        return false;
    }

    public void eatAllanimal(){
        for (int i = 0; i < this.goods.grasses.size(); i++) {
            producerAnimal producer;
            if(( producer=   this.findMostHungry(this.goods.grasses.get(i)))!=null){
                System.out.println("chicken not null");
                if(eat(producer,this.goods.grasses.get(i))) i--;
                if(producer.isFull){
                    System.out.println("chicken is full");
                    if(producer.produce==null) producer.produce = new Timing();
                }
            }
        }
    }

    public  producerAnimal findMostHungry(Grass grass){
        ArrayList<producerAnimal> hungries = new ArrayList<>();
            for (Chicken chicken : animals.chickens) {
                if((grass.x<= chicken.x+40&&grass.x>= chicken.x-40)&&(grass.y<= chicken.y+40&&grass.y>= chicken.y-40)){
                    hungries.add(chicken);
                    System.out.println("chicken");
                }
            }
            for (Turkey turkey : animals.turkeys) {
                if((grass.x<= turkey.x+40&&grass.x>= turkey.x-40)&&(grass.y<= turkey.y+40&&grass.y>= turkey.y-40)){
                    hungries.add(turkey);
                }
            }
            for (Bufallo bufallo : animals.bufallos) {
                if((grass.x<= bufallo.x+40&&grass.x>= bufallo.x-40)&&(grass.y<= bufallo.y+40&&grass.y>= bufallo.y-40)){
                    hungries.add(bufallo);
                }
            }
        int min=Integer.MAX_VALUE;
        int index=-1;
        for (producerAnimal hungry : hungries) {
            if(hungry.livies<min){
                min= hungry.livies;
                index=hungries.indexOf(hungry);
            }
        }
        if(hungries!=null&& index!=-1)
            return hungries.get(index);
        return null;
    }

    public void decreaseLive(){
        boolean bool;
        ArrayList<Bufallo> index1  = new ArrayList<>();
        for (Bufallo bufallo : animals.bufallos) {
                   bufallo.livies-=10;
                   bufallo.isFull=false;
                   if(bufallo.isDie()) index1.add(bufallo);
        }
        for (Bufallo bufallo : index1) {
            bool=false;
            for (Bufallo bufallo1 : animals.bufallos) {
                if(bufallo.equals(bufallo1)) bool=true;
            }
            if(bool){
                animals.bufallos.remove(bufallo);
            }

        }
        index1.clear();
        ArrayList<Chicken> index  = new ArrayList<>();
        for (Chicken chicken : animals.chickens) {
                    chicken.livies-=10;
                    chicken.isFull=false;
                    if(chicken.isDie())
                        index.add(chicken);
        }
        for (Chicken chicken : index) {
            bool =false;
            for (Chicken chicken1 : animals.chickens) {
                if(chicken.equals(chicken1)) bool = true;
            }
            if(bool){
                animals.chickens.remove(chicken);
            }

        }
        index.clear();
        ArrayList<Turkey> index2 = new ArrayList<>();
        for (Turkey turkey : animals.turkeys) {
                    turkey.livies-=10;
                    turkey.isFull=false;
                    if(turkey.isDie()) index2.add(turkey);
        }
        for (Turkey turkey : index2) {
            bool=false;
            for (Turkey turkey1 : animals.turkeys) {
                if(turkey.equals(turkey1)) bool = true;
            }
            if(bool) {
                animals.turkeys.remove(turkey);
            }
        }
        index2.clear();
    }


    public void dogAndWildDie(){
        ArrayList<Animal> saveKilling = new ArrayList<>();
        boolean bool ;
        for (Lion lion : animals.lions) {
            for (Dog dog : animals.dogs) {
                if((dog.x<= lion.x+40&&dog.x>= lion.x-40)&&(dog.y<= lion.y+40&&dog.y>= lion.y-40)){
                    saveKilling.add(dog);
                    saveKilling.add(lion);
                }
            }
            for (Animal animal : saveKilling) {
                bool=false;
                for (Dog dog : animals.dogs) {
                    if(dog==animal)
                        bool=true;
                }
                if(bool){
                    animals.dogs.remove(animal);
                }

            }
        }
        for (Animal animal : saveKilling) {
            bool=false;
            for (Lion lion : animals.lions) {
                if(lion==animal)
                    bool=true;
            }
            if(bool){
                animals.lions.remove(animal);
            }

        }
        saveKilling.clear();

        for (Tiger tiger : animals.tigers) {
            for (Dog dog : animals.dogs) {
                if((dog.x<= tiger.x+40&&dog.x>= tiger.x-40)&&(dog.y<= tiger.y+40&&dog.y>= tiger.y-40)){
                    saveKilling.add(dog);
                    saveKilling.add(tiger);
                }
            }
            for (Animal animal : saveKilling) {
                bool=false;
                for (Dog dog : animals.dogs) {
                    if(dog==animal)
                        bool=true;
                }
                if(bool){
                    animals.dogs.remove(animal);
                }

            }
        }
        for (Animal animal : saveKilling) {
            bool=false;
            for (Tiger tiger : animals.tigers) {
                if(tiger==animal);
                bool=true;
            }
            if(bool){
                animals.tigers.remove(animal);
            }

        }
        saveKilling.clear();


        for (Bear bear : animals.bears) {
            for (Dog dog : animals.dogs) {
                if((dog.x<= bear.x+40&&dog.x>= bear.x-40)&&(dog.y<= bear.y+40&&dog.y>= bear.y-40)){
                    saveKilling.add(dog);
                    saveKilling.add(bear);
                }
            }
            for (Animal animal : saveKilling) {
                bool=false;
                for (Dog dog : animals.dogs) {
                    if(dog==animal)
                        bool=true;
                }
                if(bool){
                    animals.dogs.remove(animal);
                }
            }
        }
        for (Animal animal : saveKilling) {
            bool=false;
            for (Bear bear : animals.bears) {
                if(animal==bear);
                bool=true;
            }
            if(bool){
                animals.bears.remove(animal);
            }

        }
    }

    public void wildsAttack(){
        //Attack Animals :
        ArrayList<producerAnimal> saveDeath = new ArrayList<>();
        boolean bool ;
        for (Lion lion : animals.lions) {
            for (Chicken chicken : animals.chickens) {
                if((chicken.x<= lion.x+40&&chicken.x>= lion.x-40)&&(chicken.y<= lion.y+40&&chicken.y>= lion.y-40))
                    saveDeath.add(chicken);
            }
            for (Turkey turkey : animals.turkeys) {
                if((turkey.x<= lion.x+40&&turkey.x>= lion.x-40)&&(turkey.y<= lion.y+40&&turkey.y>= lion.y-40))
                    saveDeath.add(turkey);
            }
            for (Bufallo bufallo : animals.bufallos) {
                if((bufallo.x<= lion.x+40&&bufallo.x>= lion.x-40)&&(bufallo.y<= lion.y+40&&bufallo.y>= lion.y-40))
                    saveDeath.add(bufallo);
            }
        }
        for (Tiger tiger : animals.tigers) {
            for (Chicken chicken : animals.chickens) {
                if((chicken.x<= tiger.x+40&&chicken.x>= tiger.x-40)&&(chicken.y<= tiger.y+40&&chicken.y>= tiger.y-40))
                    saveDeath.add(chicken);
            }
            for (Turkey turkey : animals.turkeys) {
                if((turkey.x<= tiger.x+40&&turkey.x>= tiger.x-40)&&(turkey.y<= tiger.y+40&&turkey.y>= tiger.y-40))
                    saveDeath.add(turkey);
            }
            for (Bufallo bufallo : animals.bufallos) {
                if((bufallo.x<= tiger.x+40&&bufallo.x>= tiger.x-40)&&(bufallo.y<= tiger.y+40&&bufallo.y>= tiger.y-40))
                    saveDeath.add(bufallo);
            }
        }
        for (Bear bear : animals.bears) {
            for (Chicken chicken : animals.chickens) {
                if((chicken.x<= bear.x+40&&chicken.x>= bear.x-40)&&(chicken.y<= bear.y+40&&chicken.y>= bear.y-40))
                    saveDeath.add(chicken);
            }
            for (Turkey turkey : animals.turkeys) {
                if((turkey.x<= bear.x+40&&turkey.x>= bear.x-40)&&(turkey.y<= bear.y+40&&turkey.y>= bear.y-40))
                    saveDeath.add(turkey);
            }
            for (Bufallo bufallo : animals.bufallos) {
                if((bufallo.x<= bear.x+40&&bufallo.x>= bear.x-40)&&(bufallo.y<= bear.y+40&&bufallo.y>= bear.y-40))
                    saveDeath.add(bufallo);
            }
        }
        for (producerAnimal producerAnimal : saveDeath) {
            bool=false;
            for (Chicken chicken : animals.chickens) {
                if(chicken==producerAnimal)
                    bool=true;
            }
            if(bool){
                animals.chickens.remove(producerAnimal);

            }

            bool=false;
            for (Turkey turkey : animals.turkeys) {
                if(turkey==producerAnimal)
                    bool=true;
            }
            if(bool){
                animals.turkeys.remove(producerAnimal);
            }

            bool=false;
            for (Bufallo bufallo : animals.bufallos) {
                if(bufallo==producerAnimal)
                    bool=true;
            }
            if(bool){
                animals.bufallos.remove(producerAnimal);
            }

        }
        //Attack Goods :
        for (int i = 0; i < goods.productGoods.size(); i++) {
            for (Lion lion : animals.lions) {
                if(goods.productGoods.get(i).x== lion.x&&goods.productGoods.get(i).y==lion.y){
                    goods.productGoods.remove(i);
                    i--;
                }
            }
        }
        for (int i = 0; i < goods.productGoods.size(); i++) {
            for (Tiger tiger : animals.tigers) {
                if(goods.productGoods.get(i).x== tiger.x&&goods.productGoods.get(i).y==tiger.y){
                    goods.productGoods.remove(i);
                    i--;
                }
            }
        }
        for (int i = 0; i < goods.productGoods.size(); i++) {
            for (Bear bear : animals.bears) {
                if(goods.productGoods.get(i).x== bear.x&&goods.productGoods.get(i).y==bear.y){
                    goods.productGoods.remove(i);
                    i--;
                }
            }
        }
    }

    public void wildComing(){
        if(this.personsController.getCurrentUser().currentLevel.TimeOfBearComes.get(Timing.getCurrentTime())!=null){
            animals.bears.add(new Bear());
        }
        if(this.personsController.getCurrentUser().currentLevel.TimeOfTigerComes.get(Timing.getCurrentTime())!=null){
            animals.tigers.add(new Tiger());
        }
        if(this.personsController.getCurrentUser().currentLevel.TimeOfLionComes.get(Timing.getCurrentTime())!=null){
            animals.lions.add(new Lion());
        }
    }

    public void expire(){
        for (int i = 0; i < goods.productGoods.size(); i++) {
            if(goods.productGoods.get(i).timeOfRemaining+goods.productGoods.get(i).dateOfCreated.getDate()<=Timing.getCurrentTime()){
                goods.productGoods.remove(i);
                i--;
            }
        }
        for (Lion lion : animals.lions) {
            if (cageTimeSet.get(lion)!=null) {
                HashMap<Integer, Integer> cageSet = cageTimeSet.get(lion);
                if(cageSet.get(lion.cage)!=null&&lion.cage>0)
                if(cageSet.get(lion.cage)+2<Timing.getCurrentTime()){
                    cageSet.remove(lion.cage);
                    lion.cage--;
                }
            }
        }
        for (Tiger tiger : animals.tigers) {
            if (cageTimeSet.get(tiger)!=null) {
                HashMap<Integer, Integer> cageSet = cageTimeSet.get(tiger);
                if(cageSet.get(tiger.cage)!=null&&tiger.cage>0)
                if(cageSet.get(tiger.cage)+2<Timing.getCurrentTime()){
                    cageSet.remove(tiger.cage);
                    tiger.cage--;
                }
            }
        }
        for (Bear bear : animals.bears) {
            if (cageTimeSet.get(bear)!=null) {
                HashMap<Integer, Integer> cageSet = cageTimeSet.get(bear);
                if(cageSet.get(bear.cage)!=null&&bear.cage>0)
                if(cageSet.get(bear.cage)+2<Timing.getCurrentTime()){
                    cageSet.remove(bear.cage);
                    bear.cage--;
                }
            }
        }
    }


}
