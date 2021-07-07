package controller;

import menus.Logger;
import model.Person;
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
import model.goods.BearDoll;
import model.goods.LionDoll;
import model.goods.TigerDoll;
import view.Timing;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ControllerAnimal {

    ArrayList<Tiger> tigers = new ArrayList<>();
    ArrayList<Bear> bears = new ArrayList<>();
    ArrayList<Lion> lions = new ArrayList<>();
    ArrayList<Dog> dogs = new ArrayList<>();
    ArrayList<Cat> cats = new ArrayList<>();
    ArrayList<Chicken> chickens = new ArrayList<>();
    ArrayList<Bufallo> bufallos= new ArrayList<>();
    ArrayList<Turkey> turkeys  = new ArrayList<>();

    public int buyAnimal(String name, Person person){
        switch (name){
            case "chicken" :
                if(person.totalCoins>= AnimalEnum.CHICKEN.getCost()){
                    person.totalCoins-=AnimalEnum.CHICKEN.getCost();
                    chickens.add(new Chicken());
                    return 1;
                }
                return 0;
            case "bufallo":
                if(person.totalCoins>= AnimalEnum.BUFALLO.getCost()){
                    person.totalCoins-=AnimalEnum.BUFALLO.getCost();
                    bufallos.add(new Bufallo());
                    return 1;
                }
                return 0;
            case "turkey":
                if(person.totalCoins>= AnimalEnum.TURKEY.getCost()){
                    person.totalCoins-=AnimalEnum.TURKEY.getCost();
                    turkeys.add(new Turkey());
                    return 1;
                }
                return 0;
            case "cat":
                if(person.totalCoins>= AnimalEnum.CAT.getCost()){
                    person.totalCoins-=AnimalEnum.CAT.getCost();
                    cats.add(new Cat());
                    return 1;
                }
                return 0;
            case "dog":
                if(person.totalCoins>= AnimalEnum.DOG.getCost()){
                    person.totalCoins-=AnimalEnum.DOG.getCost();
                   dogs.add(new Dog());
                    return 1;
                }
                return 0;
        }
        return -1;
    }

    public int  cage(double x, double y, ControllerGoods goods, Logger logger){

        for (Tiger tiger : tigers) {
            if(tiger.x==x&&tiger.y==y){
             tiger.cage+=1;
                HashMap<Integer ,Integer> cageSet = new HashMap<>();
                cageSet.put(1,Timing.getCurrentTime());
             if(tiger.cage!=1) {
                 cageSet= MainController.cageTimeSet.get(tiger);
                 cageSet.put(tiger.cage, Timing.getCurrentTime());
             }
                MainController.cageTimeSet.put(tiger,cageSet);
             logger.commands.add("INFO ,"+logger.lastChange.toString()+", caged the tiger");
             if(tiger.cage==4){
                 goods.productGoods.add(new TigerDoll(x,y));
                 tigers.remove(tiger);
                 logger.lastChange=new Date();
                 logger.commands.add("INFO ,"+logger.lastChange.toString()+",tiger caged and send to the warehouse");
                 return 1;
             }
                return 0;
            }
        }
        for (Lion lion : lions) {
            if(lion.x==x&&lion.y==y){
                lion.cage+=1;
                HashMap<Integer ,Integer> cageSet = new HashMap<>();
                cageSet.put(1,Timing.getCurrentTime());
                if(lion.cage!=1) {
                    cageSet= MainController.cageTimeSet.get(lion);
                    cageSet.put(lion.cage, Timing.getCurrentTime());
                }
                MainController.cageTimeSet.put(lion,cageSet);
                logger.commands.add("INFO ,"+logger.lastChange.toString()+", caged the lion");
                if(lion.cage==3){
                    goods.productGoods.add(new LionDoll(x,y));
                    lions.remove(lion);
                    logger.lastChange=new Date();
                    logger.commands.add("INFO ,"+logger.lastChange.toString()+",lion caged and send to the warehouse");
                    return 1;
                }
                return 0;
            }
        }
        for (Bear bear : bears) {
            if(bear.x==x&&bear.y==y){
                bear.cage+=1;
                HashMap<Integer ,Integer> cageSet = new HashMap<>();
                cageSet.put(1,Timing.getCurrentTime());
                if(bear.cage!=1) {
                    cageSet= MainController.cageTimeSet.get(bear);
                    cageSet.put(bear.cage, Timing.getCurrentTime());
                }
                MainController.cageTimeSet.put(bear,cageSet);
                logger.commands.add("INFO ,"+logger.lastChange.toString()+", caged the bear");
                if(bear.cage==3){
                    goods.productGoods.add(new BearDoll(x,y));
                    bears.remove(bear);
                    logger.lastChange=new Date();
                    logger.commands.add("INFO ,"+logger.lastChange.toString()+",bear caged and send to the warehouse");
                    return 1;
                }
                return 0;
            }
        }
        return -1;
    }

    public void showAnimal() {
        // name life cage loc
        if (turkeys.size() > 0) {
            for (Turkey turkey : turkeys) {
                System.out.println(turkey.name + " " + turkey.livies + " [" + turkey.x + "," + turkey.y + "]");
            }
        }
        if (chickens.size() > 0) {
            for (Chicken chicken : chickens) {
                System.out.println(chicken.name + " " + chicken.livies + " [" + chicken.x + "," + chicken.y + "]");
            }
        }
        if (bufallos.size() > 0) {
            for (Bufallo bufallo : bufallos) {
                System.out.println(bufallo.name + " " + bufallo.livies + " [" + bufallo.x + "," + bufallo.y + "]");
            }
        }
        if(tigers.size()>0){
            for (Tiger tiger : tigers) {
                System.out.println(tiger.name + " "+"cage needed :"+tiger.cage + " [" + tiger.x + "," + tiger.y + "]");
            }
        }
        if(lions.size()>0){
            for (Lion lion : lions) {
                System.out.println(lion.name + " "+"cage needed :"+lion.cage + " [" + lion.x + "," + lion.y + "]");
            }
        }
        if(bears.size()>0){
            for (Bear bear : bears) {
                System.out.println(bear.name + " "+"cage needed :"+bear.cage + " [" + bear.x + "," + bear.y + "]");
            }
        }
        if(dogs.size()>0){
            for (Dog dog : dogs) {
                System.out.println(dog.name + " [" + dog.x + "," + dog.y + "]");
            }
        }
        if(cats.size()>0){
            for (Cat cat : cats) {
                System.out.println(cat.name + " [" + cat.x + "," + cat.y + "]");
            }
        }
    }

    public Animal findMostHungry(){
        //TODO
        Animal animal ;
        if(chickens.size()>0) {
             animal = chickens.get(0);
        }else if(turkeys.size()>0){
            animal = turkeys.get(0);
        }else {
            animal = bufallos.get(0);
        }

        for (Chicken chicken : chickens) {
            if(chicken.livies<animal.livies)
                animal=chicken;
        }
        for (Turkey turkey : turkeys) {
            if(turkey.livies<animal.livies)
                animal=turkey;
        }
        for (Bufallo bufallo : bufallos) {
            if(bufallo.livies<animal.livies)
                animal=bufallo;
        }
        return animal;
    }
}

