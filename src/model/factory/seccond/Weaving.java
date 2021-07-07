package model.factory.seccond;

import model.factory.Factory;
import model.factory.FactoryMoney;

public class Weaving extends Factory {
    public Weaving( double x, double y) {
        super(FactoryMoney.WEAVING.getMoney(),FactoryMoney.WEAVING.toString(), x, y, 7);

    }
}
