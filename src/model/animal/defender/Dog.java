package model.animal.defender;

import model.animal.Animal;
import model.animal.AnimalEnum;

public class Dog extends Animal {

    public Dog() {
        super(AnimalEnum.DOG.toString(), 3, 3, AnimalEnum.DOG.getFootStep(), AnimalEnum.DOG.getLivies(), AnimalEnum.DOG.getCost());
    }
}
