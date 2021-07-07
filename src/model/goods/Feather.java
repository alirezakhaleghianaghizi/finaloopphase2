package model.goods;

public class Feather extends Goods{
    public Feather( double x, double y) {
        super(GoodsEnum.FEATHER.toString(), x, y,GoodsEnum.FEATHER.cost, GoodsEnum.FEATHER.capacity,GoodsEnum.FEATHER.timeOfRemaining);
    }
}
