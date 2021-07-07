package model.goods;

public class IceCream extends Goods{
    public IceCream( double x, double y) { super(GoodsEnum.ICECREAM.toString(), x, y,GoodsEnum.ICECREAM.cost, GoodsEnum.ICECREAM.capacity,GoodsEnum.ICECREAM.timeOfRemaining);
    }
}
