package model;

import model.level.Level;

import java.util.HashMap;

public class Person {
    public String userName;
    public String password;
    public int level ;
    public HashMap<Integer, Level> completedLevel;
    public HashMap<Integer,Integer> personCoinEachLevel;
    public Level currentLevel;
    public int coins ;
    public int totalCoins ;

    public Person(String userName, String password, HashMap<Integer, Level> completedLevel, HashMap<Integer, Integer> personCoinEachLevel, int coins) {
        this.userName = userName;
        this.password = password;
        this.completedLevel = completedLevel;
        this.personCoinEachLevel = personCoinEachLevel;
        this.coins = coins;
        this.level=0;
        this.totalCoins=0;
    }
}
