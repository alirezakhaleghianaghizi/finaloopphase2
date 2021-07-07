package model.gadget;

import model.goods.Goods;
import model.goods.GoodsEnum;

import java.util.ArrayList;

public class Warehouse extends Gadget {
    public ArrayList<Goods> existence;
    public int currentCapacity;
    public ArrayList <Goods> eggInWareHouse ;
    public ArrayList <Goods> cookieInWareHouse ;
    public ArrayList <Goods> clothInWareHouse ;
    public ArrayList <Goods> featherInWareHouse ;
    public ArrayList <Goods> flourInWareHouse ;
    public ArrayList <Goods> milkInWareHouse ;
    public ArrayList <Goods> sepratedMilkInWareHouse ;
    public ArrayList <Goods> iceCreamInWareHouse ;
    public ArrayList <Goods> silkInWareHouse ;
    public ArrayList <Goods> bearDollInWareHouse ;
    public ArrayList <Goods> lionDollInWareHouse ;
    public ArrayList <Goods> tigerDollInWareHouse ;

    public Warehouse(double x, double y) {
        super(GadgetEnum.WAREHOUSE.toString(), x, y, GadgetEnum.WAREHOUSE.capacity);
        this.existence = new ArrayList<>();
        this.eggInWareHouse= new ArrayList<>();
        this.cookieInWareHouse = new ArrayList<>();
        this.clothInWareHouse= new ArrayList<>();
        this.featherInWareHouse = new ArrayList<>();
        this.flourInWareHouse = new ArrayList<>();
        this.milkInWareHouse = new ArrayList<>();
        this.silkInWareHouse = new ArrayList<>();
        this.bearDollInWareHouse = new ArrayList<>();
        this.lionDollInWareHouse = new ArrayList<>();
        this.tigerDollInWareHouse = new ArrayList<>();
        this.sepratedMilkInWareHouse = new ArrayList<>();
        this.iceCreamInWareHouse = new ArrayList<>();
    }

    public boolean haveSpace(Goods goods) {
        this.currentCapacity=GadgetEnum.WAREHOUSE.capacity;
        for (Goods goods1 : existence) {
            this.currentCapacity-= goods.capacity;
        }
        if (currentCapacity > goods.capacity)
            return true;
            return false;
    }

    public void seprateGoods(Goods goods){
            if(goods.name.equals(GoodsEnum.MILK.name())){
                milkInWareHouse.add(goods);
            }else if(goods.name.equals(GoodsEnum.SEPARATEDMILK.name())){
                sepratedMilkInWareHouse.add(goods);
            }else if(goods.name.equals(GoodsEnum.FEATHER.name())){
                featherInWareHouse.add(goods);
            }else if(goods.name.equals(GoodsEnum.FLOUR.name())){
                flourInWareHouse.add(goods);
            }else if(goods.name.equals(GoodsEnum.ICECREAM.name())){
                iceCreamInWareHouse.add(goods);
            }else if(goods.name.equals(GoodsEnum.SILK.name())){
                silkInWareHouse.add(goods);
            }else if(goods.name.equals(GoodsEnum.COOKIE.name())){
                cookieInWareHouse.add(goods);
            }else if(goods.name.equals(GoodsEnum.BEARDOLL.name())){
                bearDollInWareHouse.add(goods);
            }else if(goods.name.equals(GoodsEnum.LIONDOLL.name())){
                lionDollInWareHouse.add(goods);
            }else if(goods.name.equals(GoodsEnum.TIGERDOLL.name())){
                tigerDollInWareHouse.add(goods);
            }else if(goods.name.equals(GoodsEnum.CLOTH.name())){
                clothInWareHouse.add(goods);
            }else if(goods.name.equals(GoodsEnum.EGG.name())){
                eggInWareHouse.add(goods);
            }
        }
}
