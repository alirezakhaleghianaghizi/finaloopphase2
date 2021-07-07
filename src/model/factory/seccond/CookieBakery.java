package model.factory.seccond;

import model.factory.Factory;
import model.factory.FactoryMoney;

public class CookieBakery extends Factory {
    public CookieBakery( double x, double y) {
        super(FactoryMoney.COOKIEBAKERY.getMoney(),FactoryMoney.COOKIEBAKERY.toString(), x, y,5);
    }
}
