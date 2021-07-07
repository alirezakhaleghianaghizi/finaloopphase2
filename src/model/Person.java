package model;

import model.level.Level;

import java.util.HashMap;

public class Person {
    public String userName;
    public String password;
    public int level;
    public Level currentLevel;
    public int coins ;
    public int totalCoins ;

    public Person(String userName, String password, int coins) {
        this.userName = userName;
        this.password = password;
        this.coins = coins;
        this.level=0;
        this.totalCoins=0;
    }
}
