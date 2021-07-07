package model.goods;

public class Silk extends Goods{
    public Silk( double x, double y) {
        super(GoodsEnum.SILK.toString(), x, y,GoodsEnum.SILK.cost, GoodsEnum.SILK.capacity,GoodsEnum.SILK.timeOfRemaining);
    }
}
