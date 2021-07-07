package model.animal.producer;

import model.animal.AnimalEnum;

public class Chicken extends producerAnimal implements producerAnimals  {
    public Chicken() {
        super(AnimalEnum.CHICKEN.toString(), 3, 3, AnimalEnum.CHICKEN.getFootStep(), AnimalEnum.CHICKEN.getLivies(), AnimalEnum.CHICKEN.getCost());
        this.produceTime = 2;
        this.isFull=true;
    }
}
