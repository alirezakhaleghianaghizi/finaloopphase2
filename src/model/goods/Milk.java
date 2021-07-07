package model.goods;

public class Milk extends Goods{
    public Milk( double x, double y) {
        super(GoodsEnum.MILK.toString(), x, y,GoodsEnum.MILK.cost, GoodsEnum.MILK.capacity,GoodsEnum.MILK.timeOfRemaining);
    }
}
