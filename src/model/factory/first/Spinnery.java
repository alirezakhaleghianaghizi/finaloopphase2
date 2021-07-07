package model.factory.first;

import model.factory.Factory;
import model.factory.FactoryMoney;

public class Spinnery extends Factory {
    public Spinnery( double x, double y) {
        super(FactoryMoney.SPINNERY.getMoney(),FactoryMoney.SPINNERY.toString(), x, y, 6);
    }
}
