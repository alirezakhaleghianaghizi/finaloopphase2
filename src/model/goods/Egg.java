package model.goods;

public class Egg extends Goods{

    public Egg( double x, double y) {
        super(GoodsEnum.EGG.toString(), x, y,GoodsEnum.EGG.cost, GoodsEnum.EGG.capacity,GoodsEnum.EGG.timeOfRemaining);
    }
}
