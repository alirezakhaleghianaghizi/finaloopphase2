package model.factory.first;

import model.factory.Factory;
import model.factory.FactoryMoney;

public class EggPowder extends Factory {

    public EggPowder(double x,double y) {
        super(FactoryMoney.EGGPOWDER.getMoney(),FactoryMoney.EGGPOWDER.toString(), x, y,4);
    }

}
