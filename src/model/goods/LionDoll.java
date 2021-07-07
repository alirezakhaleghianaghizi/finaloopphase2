package model.goods;

public class LionDoll extends Goods {

         public LionDoll( double x, double y) {
             super(GoodsEnum.LIONDOLL.toString(), x, y,GoodsEnum.LIONDOLL.cost, GoodsEnum.LIONDOLL.capacity,GoodsEnum.LIONDOLL.timeOfRemaining);
    }
}
