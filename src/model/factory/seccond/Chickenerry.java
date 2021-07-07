package model.factory.seccond;

import model.factory.Factory;
import model.factory.FactoryMoney;

public class Chickenerry extends Factory {
    public Chickenerry( double x, double y) {
        super(FactoryMoney.CHICKENERRY.getMoney(),FactoryMoney.CHICKENERRY.toString(), x, y,5);
    }
}
