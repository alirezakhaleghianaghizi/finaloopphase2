package model.goods;

public class Cloth extends Goods {
    public Cloth( double x, double y) {
        super(GoodsEnum.CLOTH.toString(), x, y,GoodsEnum.CLOTH.cost, GoodsEnum.CLOTH.capacity,GoodsEnum.CLOTH.timeOfRemaining);
    }
}
