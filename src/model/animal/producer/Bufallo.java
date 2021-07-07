package model.animal.producer;

import model.animal.AnimalEnum;

public class Bufallo extends producerAnimal implements producerAnimals {
    public Bufallo() {
        super(AnimalEnum.BUFALLO.toString(), 3, 3, AnimalEnum.BUFALLO.getFootStep(), AnimalEnum.BUFALLO.getLivies(), AnimalEnum.BUFALLO.getCost());
        this.produceTime = 5;
        this.isFull=true;
    }
}
