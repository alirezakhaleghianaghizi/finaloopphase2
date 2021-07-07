package model.animal.wild;

import model.animal.Animal;

public  abstract class WildAnimal extends Animal {
    public WildAnimal(String name, double x, double y, double footStep, int livies, int cost) {
        super(name, x, y, footStep, livies, cost);
    }
}
