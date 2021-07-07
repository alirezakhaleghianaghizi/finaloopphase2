package model.animal;

public enum AnimalEnum {
    BUFALLO(1,100,400),
    CHICKEN(1,100,100),
    TURKEY(1,100,200),
    BEAR(1,100,400),
    LION(1,100,300),
    TIGER(2,100,500),
    CAT(1,100,150),
    DOG(1,100,100)

    ;


    int footStep;
    int livies ;
    int cost ;


    public int getFootStep() {
        return footStep;
    }

    public int getLivies() {
        return livies;
    }

    public int getCost() {
        return cost;
    }

    AnimalEnum(int footStep, int livies, int cost) {
        this.footStep = footStep;
        this.livies = livies;
        this.cost = cost;
    }



}
