package model.goods;

public class SepratedMilk extends Goods{
    public SepratedMilk( double x, double y) {
        super(GoodsEnum.SEPARATEDMILK.toString(), x, y,GoodsEnum.SEPARATEDMILK.cost, GoodsEnum.SEPARATEDMILK.capacity,GoodsEnum.SEPARATEDMILK.timeOfRemaining);
    }
}
