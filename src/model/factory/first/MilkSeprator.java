package model.factory.first;

import model.factory.Factory;
import model.factory.FactoryMoney;

public class MilkSeprator extends Factory {

    public MilkSeprator( double x, double y) {
        super(FactoryMoney.MILKSEPERATOR.getMoney(),FactoryMoney.MILKSEPERATOR.toString(), x, y, 5);
    }

}
