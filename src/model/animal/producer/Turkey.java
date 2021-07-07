package model.animal.producer;

import model.animal.AnimalEnum;

public class Turkey extends producerAnimal implements producerAnimals  {

    public Turkey() {
        super(AnimalEnum.TURKEY.toString(), 3, 3, AnimalEnum.TURKEY.getFootStep(), AnimalEnum.TURKEY.getLivies(), AnimalEnum.TURKEY.getCost());
        this.produceTime = 3;
        this.isFull=true;
    }
}
