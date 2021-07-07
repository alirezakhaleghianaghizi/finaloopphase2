package model.factory;

public enum FactoryMoney {
    EGGPOWDER(150),
    MILKSEPERATOR(250),
    SPINNERY(400),
    COOKIEBAKERY(250),
    ICECREAMFACTORY(400),
    CHICKENERRY(400),
    WEAVING(550);
    int money;

    FactoryMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
