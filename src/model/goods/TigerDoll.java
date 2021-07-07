package model.goods;

public class TigerDoll extends Goods {
    public TigerDoll( double x, double y) {
        super(GoodsEnum.TIGERDOLL.toString(), x, y,GoodsEnum.TIGERDOLL.cost, GoodsEnum.TIGERDOLL.capacity,GoodsEnum.TIGERDOLL.timeOfRemaining);
    }
}
