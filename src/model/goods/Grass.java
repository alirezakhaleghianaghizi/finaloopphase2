package model.goods;

public class Grass extends Goods{
    public Grass( double x, double y) {
        super(GoodsEnum.GRASS.toString(), x, y,GoodsEnum.GRASS.cost, GoodsEnum.GRASS.capacity,GoodsEnum.GRASS.timeOfRemaining);
    }
}
