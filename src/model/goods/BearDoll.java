package model.goods;

public class BearDoll extends Goods {
    public BearDoll( double x, double y) {
        super(GoodsEnum.BEARDOLL.toString(), x, y,GoodsEnum.BEARDOLL.cost, GoodsEnum.BEARDOLL.capacity,GoodsEnum.BEARDOLL.timeOfRemaining);
    }
}
