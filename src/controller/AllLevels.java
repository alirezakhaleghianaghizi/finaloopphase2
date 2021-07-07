package controller;

import fileOperator.LevelFileOperating;
import model.level.Level;

import java.util.ArrayList;

public class AllLevels {
    public int numberOfLevels;
    public ArrayList<Level> levels;
    public LevelFileOperating FILE=new LevelFileOperating();
    public AllLevels(AllLevels levels) {
        if(this==null){
            this.levels = levels.levels;
             numberOfLevels=levels.numberOfLevels;
        }
    }
    public AllLevels(){
        if(this==null){
            this.numberOfLevels=0;
            this.levels=new ArrayList<>();
        }
    }


    public static void main(String[] args) {


        AllLevels allLevels=new AllLevels();
       System.out.println("ok" +
                "");
/*
            Level[] levels=new Level[10];
             HashMap<Integer, Tiger> TimeOfTigerComes=new HashMap<>();
             HashMap<Integer, Lion> TimeOfLionComes=new HashMap<>();
             HashMap<Integer, Bear> TimeOfBearComes=new HashMap<>();
             TimeOfBearComes.put(60,new Bear());
            TimeOfTigerComes.put(180,new Tiger());
            TimeOfLionComes.put(120,new Lion());
            levels[0]=new Level(200,"CHICKEN",4,"EGG",10,"COIN",500,TimeOfTigerComes,TimeOfLionComes,TimeOfBearComes,360,100);
            TimeOfBearComes.remove(60,new Bear());
            TimeOfTigerComes.remove(180,new Tiger());
            TimeOfLionComes.remove(120,new Lion());
            TimeOfBearComes.put(60,new Bear());
            TimeOfBearComes.put(40,new Bear());
            TimeOfBearComes.put(100,new Bear());
            TimeOfBearComes.put(180,new Bear());
            levels[1]=new Level(400,"CHICKEN",10,"COOKIE",5,"FLOUR",5,TimeOfTigerComes,TimeOfLionComes,TimeOfBearComes,500,300);
            TimeOfBearComes.put(60,new Bear());
            TimeOfBearComes.put(61,new Bear());
            TimeOfBearComes.remove(40,new Bear());
            TimeOfBearComes.put(100,new Bear());
            TimeOfBearComes.put(101,new Bear());
            TimeOfBearComes.remove(180,new Bear());
            levels[2]=new Level(300,"FEATHER",4,"TURKEY",5,"COIN",500,TimeOfTigerComes,TimeOfLionComes,TimeOfBearComes,400,600);
            TimeOfBearComes.put(60,new Bear());
            TimeOfBearComes.put(61,new Bear());
            TimeOfBearComes.put(62,new Bear());
            TimeOfBearComes.put(100,new Bear());
            TimeOfBearComes.put(101,new Bear());
            TimeOfBearComes.put(102,new Bear());
            levels[3]=new Level(500,"FEATHER",10,"TURKEY",10,"SILK",5,TimeOfTigerComes,TimeOfLionComes,TimeOfBearComes,600,500);
            TimeOfBearComes.remove(60,new Bear());
            TimeOfBearComes.put(61,new Bear());
            TimeOfBearComes.put(62,new Bear());
            TimeOfBearComes.put(100,new Bear());
            TimeOfBearComes.put(101,new Bear());
            TimeOfBearComes.remove(102,new Bear());
            levels[4]=new Level(200,"BUFALLO",2,"MILK",10,"COIN",700,TimeOfTigerComes,TimeOfLionComes,TimeOfBearComes,460,500);
            TimeOfBearComes.remove(61,new Bear());
            TimeOfBearComes.remove(62,new Bear());
            TimeOfBearComes.remove(100,new Bear());
            TimeOfBearComes.remove(101,new Bear());
            TimeOfLionComes.put(61,new Lion());
            TimeOfLionComes.put(62,new Lion());
            TimeOfLionComes.put(100,new Lion());
            TimeOfLionComes.put(101,new Lion());
            levels[5]=new Level(700,"BUFALLO",5,"MILK",20,"SEPARATEDMILK",10,TimeOfTigerComes,TimeOfLionComes,TimeOfBearComes,900,600);
            TimeOfLionComes.put(61,new Lion());
            TimeOfLionComes.remove(62,new Lion());
            TimeOfLionComes.put(100,new Lion());
            TimeOfLionComes.remove(101,new Lion());
            levels[6]=new Level(800,"MILK",20,"SEPARATEDMILK",10,"ICECREAM",15,TimeOfTigerComes,TimeOfLionComes,TimeOfBearComes,660,600);
            TimeOfLionComes.remove(100,new Lion());
            TimeOfLionComes.remove(60,new Lion());
           TimeOfTigerComes.put(50,new Tiger());
            TimeOfTigerComes.put(70,new Tiger());
            TimeOfTigerComes.put(120,new Tiger());
            levels[7]=new Level(200,"TIGERDOLL",2,"COIN",450,"BUFALLO",4,TimeOfTigerComes,TimeOfLionComes,TimeOfBearComes,200,300);
            levels[8]=new Level(200,"FEATHER",20,"TURKEY",5,"SILK",5,TimeOfTigerComes,TimeOfLionComes,TimeOfBearComes,150,400);
            levels[9]=new Level(400,"TURKEY",5,"SILK",10,"CLOTH",10,TimeOfTigerComes,TimeOfLionComes,TimeOfBearComes,260,500);
            LevelFileOperating LEVELOPERATOR=new LevelFileOperating();
            for (int j = 0; j < 10; j++) {
                allLevels.levels.add(levels[j]);
                allLevels.numberOfLevels++;
            }
        System.out.println("ok");try{  LEVELOPERATOR.jasonWriter(allLevels);}
        catch (Exception e){
            System.out.println("ok");
        }*/
        allLevels=allLevels.FILE.reloadLevels(allLevels);
        System.out.println(allLevels.numberOfLevels);
        int l=1;
        for (Level level : allLevels.levels) {
            System.out.println("LEVEL "+l+" :");
            l++;
            System.out.println("startCoins "+level.startCoins);  System.out.println("");
            System.out.print("task1 "+level.task1);System.out.println("\ttask1Number "+level.task1Number);  System.out.println("");
            System.out.print("TASK2 "+level.task2);System.out.println("\ttask2Number "+level.task2Number);  System.out.println("");
            System.out.print("task3 "+level.task3);System.out.println("\ttask3Number "+level.task3Number);  System.out.println("");
           int j=0;
            for (int i = 0; i < 200; i++) {
                if(level.TimeOfBearComes.containsKey(i)){
                    j++;  System.out.println("");
                    System.out.println(j+"bear comes at second "+i);
                }
            }
             j=0;
            for (int i = 0; i < 200; i++) {
                if(level.TimeOfLionComes.containsKey(i)){
                    j++;  System.out.println("");
                    System.out.println(j+"Lion comes at second "+i);
                }
            }
             j=0;
            for (int i = 0; i < 200; i++) {
                if(level.TimeOfTigerComes.containsKey(i)){
                    j++;  System.out.println("");
                    System.out.println(j+"Tiger comes at second "+i);
                }
            }

            System.out.println("");
            System.out.println("MAX TIME "+level.maxTime);  System.out.println("");
            System.out.println("presentCoin "+level.presentCoin);
        }
        }

}

