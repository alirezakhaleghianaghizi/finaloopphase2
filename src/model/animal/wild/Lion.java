package model.animal.wild;

import model.animal.AnimalEnum;

public class Lion extends WildAnimal implements WildAnimals  {
    int cautch;
    public int cage;
    public Lion() {
        super(AnimalEnum.LION.toString(), 3, 3, AnimalEnum.LION.getFootStep(), AnimalEnum.LION.getLivies(), AnimalEnum.LION.getCost());
        this.cautch=3;
        this.cage=0;
    }

}
