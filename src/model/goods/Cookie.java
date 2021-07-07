package model.goods;

public class Cookie extends Goods {
    public Cookie( double x, double y) {
        super(GoodsEnum.COOKIE.toString(), x, y,GoodsEnum.COOKIE.cost, GoodsEnum.COOKIE.capacity,GoodsEnum.COOKIE.timeOfRemaining);
    }
}
