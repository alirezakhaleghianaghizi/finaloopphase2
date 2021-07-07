package model.goods;

public class Flour extends Goods {
    public Flour( double x, double y) {
        super(GoodsEnum.FLOUR.toString(), x, y,GoodsEnum.FLOUR.cost, GoodsEnum.FLOUR.capacity,GoodsEnum.FLOUR.timeOfRemaining);
    }
}
