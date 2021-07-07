package model.level;

import model.animal.wild.Bear;
import model.animal.wild.Lion;
import model.animal.wild.Tiger;

import java.util.HashMap;

public class Level {
    public int startCoins;
    public String task1;
    public int task1Number;
    public String task2;
    public int task2Number;
    public String task3;
    public int task3Number;
    public HashMap<Integer, Tiger> TimeOfTigerComes;
    public HashMap<Integer, Lion> TimeOfLionComes;
    public HashMap<Integer, Bear> TimeOfBearComes;
    public int maxTime;
    public int presentCoin;

    public Level(int startCoins, String task1, int task1Number, String task2, int task2Number, String task3, int task3Number, HashMap<Integer, Tiger> timeOfTigerComes, HashMap<Integer, Lion> timeOfLionComes, HashMap<Integer, Bear> timeOfBearComes, int maxTime, int presentCoin) {
        this.startCoins = startCoins;
        this.task1 = task1;
        this.task1Number = task1Number;
        this.task2 = task2;
        this.task2Number = task2Number;
        this.task3 = task3;
        this.task3Number = task3Number;
        TimeOfTigerComes = timeOfTigerComes;
        TimeOfLionComes = timeOfLionComes;
        TimeOfBearComes = timeOfBearComes;
        this.maxTime = maxTime;
        this.presentCoin = presentCoin;
    }
}
