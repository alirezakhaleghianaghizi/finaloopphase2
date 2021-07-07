package model.animal.asisstant;

import model.animal.Animal;
import model.animal.AnimalEnum;

public class Cat extends Animal {
    public Cat() {
        super(AnimalEnum.CAT.toString(), 3, 3, AnimalEnum.CAT.getFootStep(), AnimalEnum.CAT.getLivies(), AnimalEnum.CAT.getCost());
    }
    public void catMove(){

    }
}
