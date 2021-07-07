package model.factory.seccond;

import model.factory.Factory;
import model.factory.FactoryMoney;

public class IceCreamFactory extends Factory {
    public IceCreamFactory(double x, double y) {
        super(FactoryMoney.ICECREAMFACTORY.getMoney(),FactoryMoney.ICECREAMFACTORY.toString(), x, y,6);
    }


    public void Producing() {

    }
}
