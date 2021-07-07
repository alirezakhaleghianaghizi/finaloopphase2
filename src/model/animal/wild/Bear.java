package model.animal.wild;

import model.animal.AnimalEnum;

public class Bear extends WildAnimal implements WildAnimals {
     int cautch;
     public int cage;
    public Bear() {
        super(AnimalEnum.BEAR.toString(), 3, 3, AnimalEnum.BEAR.getFootStep(), AnimalEnum.BEAR.getLivies(), AnimalEnum.BEAR.getCost());
        this.cautch=4;
        this.cage=0;
    }

}
