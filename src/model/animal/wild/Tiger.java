package model.animal.wild;

import model.animal.AnimalEnum;

public class Tiger extends WildAnimal implements WildAnimals {
    int cautch;
    public int cage;
    public Tiger() {
        super(AnimalEnum.TIGER.toString(), 3, 3, AnimalEnum.TIGER.getFootStep(), AnimalEnum.TIGER.getLivies(), AnimalEnum.TIGER.getCost());
        this.cautch=4;
        this.cage=0;
    }

}
