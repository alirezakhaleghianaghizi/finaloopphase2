package model.goods;

public class Water extends Goods{
    public Water( double x, double y) {

        super(GoodsEnum.WATER.toString(), x, y,GoodsEnum.WATER.cost, GoodsEnum.WATER.capacity,GoodsEnum.WATER.timeOfRemaining);
    }
}
