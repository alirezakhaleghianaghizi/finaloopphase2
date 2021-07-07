package controller;

import model.factory.Factory;
import model.factory.FactoryMoney;
import model.factory.first.EggPowder;
import model.factory.first.MilkSeprator;
import model.factory.first.Spinnery;
import model.factory.seccond.Chickenerry;
import model.factory.seccond.CookieBakery;
import model.factory.seccond.IceCreamFactory;
import model.factory.seccond.Weaving;
import model.goods.*;

import java.util.ArrayList;

public class ControllerFactory {
    public EggPowder eggPowderFactories;
    public MilkSeprator milkSepratorFactories;
    public Spinnery spinneryFactories;
    public  CookieBakery cookieBakeryFactories;
    public IceCreamFactory iceCreamFactories;
    public  Weaving weavingFactories;
    public Chickenerry chickenerryFactory;

    public ControllerFactory() { }

    //Working fac methods
        // first fac :
    public int workFactory(Factory factory, ControllerGadget gadget){
        if (factory.name.equalsIgnoreCase(FactoryMoney.EGGPOWDER.toString())) return workEggPowder((EggPowder) factory,gadget);
        if (factory.name.equalsIgnoreCase(FactoryMoney.SPINNERY.toString())) return workSpinnery((Spinnery) factory,gadget);
        if (factory.name.equalsIgnoreCase(FactoryMoney.COOKIEBAKERY.toString())) return workCookieBakery((CookieBakery) factory,gadget);
        if (factory.name.equalsIgnoreCase(FactoryMoney.MILKSEPERATOR.toString())) return workMilkSeprator( (MilkSeprator) factory,gadget);
        if (factory.name.equalsIgnoreCase(FactoryMoney.ICECREAMFACTORY.toString())) return workIceCreamFactory((IceCreamFactory) factory,gadget);
        if (factory.name.equalsIgnoreCase(FactoryMoney.WEAVING.toString())) return workWeaving( (Weaving) factory,gadget);
        if (factory.name.equalsIgnoreCase(FactoryMoney.CHICKENERRY.toString())) return workChickennery( (Chickenerry) factory,gadget);
        return 0;
    }
    
    public int  workEggPowder(EggPowder eggPowder, ControllerGadget gadget) {
        if (eggPowder.level == 1) {
            for (Goods goods1 : gadget.warehouse.existence) {
                if (goods1.name.equals(GoodsEnum.EGG.name())) {
                    if(eggPowder.work(1)) {
                        gadget.warehouse.existence.remove(goods1);
                        gadget.warehouse.eggInWareHouse.remove(goods1);
                        return 1;
                    }
                    return -1;
                }
            }
            return 0;
        }else {
            int a = 0;
            ArrayList<Integer> index =new ArrayList<>();
            ArrayList<Integer> indexOfGood =new ArrayList<>();
            while (a <= 2) {
                for (Goods g : gadget.warehouse.existence) {
                    if (g.name.equals(GoodsEnum.EGG.name())) {
                        a++;
                        indexOfGood.add(gadget.warehouse.eggInWareHouse.indexOf(g));
                        index.add(gadget.warehouse.existence.indexOf(g));
                    }
                }
            }
            if(a==0) return 0;
            if(eggPowder.work(a)){
                for (int i = 0; i < index.size(); i++) gadget.warehouse.existence.remove(index.get(i));
                for (int i = 0; i < indexOfGood.size(); i++) gadget.warehouse.eggInWareHouse.remove(indexOfGood.get(i));
                return 1;
            }
            return -1;
        }
    }

    public int workSpinnery(Spinnery spinnery, ControllerGadget gadget) {
        if (spinnery.level == 1) {
            for (Goods goods1 : gadget.warehouse.existence) {
                if (goods1.name.equals(GoodsEnum.FEATHER.name())) {
                    if(spinnery.work(1)){
                        gadget.warehouse.existence.remove(goods1);
                        gadget.warehouse.featherInWareHouse.remove(goods1);
                        return 1;
                    }
                    return -1;
                }
            }
            return 0;
        }else {
            int a = 0;
            ArrayList<Integer>index=new ArrayList<>();
            ArrayList<Integer> indexOfGood =new ArrayList<>();
            while (a <= 2) {
                for (Goods g : gadget.warehouse.existence) {
                    if (g.name.equals(GoodsEnum.FEATHER.name())) {
                        a++;
                        index.add(gadget.warehouse.existence.indexOf(g));
                        indexOfGood.add(gadget.warehouse.featherInWareHouse.indexOf(g));
                    }
                }
            }
          if(a==0) return 0;
            if(spinnery.work(a)){
                for (int i = 0; i < index.size(); i++) gadget.warehouse.existence.remove(index.get(i));
                for (int i = 0; i < indexOfGood.size(); i++) gadget.warehouse.existence.remove(indexOfGood.get(i));
                return 1;
            }
            return -1;
        }
    }

    public int workMilkSeprator(MilkSeprator milkSeprator,ControllerGadget gadget) {
        if (milkSeprator.level == 1) {
            for (Goods goods1 : gadget.warehouse.existence) {
                if (goods1.name.equals(GoodsEnum.MILK.name())) {
                    if(milkSeprator.work(1)){
                        gadget.warehouse.existence.remove(goods1);
                        gadget.warehouse.milkInWareHouse.remove(goods1);
                        return 1;
                    }
                    return -1;
                }
            }
            return 0;
        }else {
            int a = 0;
            ArrayList<Integer>index=new ArrayList<>();
            ArrayList<Integer>indexOfGood=new ArrayList<>();
            while (a <= 2) {
                for (Goods g : gadget.warehouse.existence) {
                    if (g.name.equals(GoodsEnum.MILK.name())) {
                        a++;
                        index.add(gadget.warehouse.existence.indexOf(g));
                        indexOfGood.add(gadget.warehouse.milkInWareHouse.indexOf(g));
                    }
                }
            }
            if(a==0) return 0;
            if(milkSeprator.work(a)){
                for (int i = 0; i < index.size(); i++) gadget.warehouse.existence.remove(index.get(i));
                for (int i1 = 0; i1 < indexOfGood.size(); i1++) gadget.warehouse.milkInWareHouse.remove(indexOfGood.get(i1));
                return 1;
            }
            return -1;
        }
    }

    public int workCookieBakery(CookieBakery cookieBakery, ControllerGadget gadget) {
        if (cookieBakery.level == 1) {
            for (Goods goods1 : gadget.warehouse.existence) {
                if (goods1.name.equals(GoodsEnum.FLOUR.name())) {
                    if(cookieBakery.work(1)) {
                        gadget.warehouse.existence.remove(goods1);
                        gadget.warehouse.flourInWareHouse.remove(goods1);
                        return 1;
                    }
                    return -1;
                }
            }
            return 0;
        }else {
            int a = 0;
            ArrayList<Integer> index=new ArrayList<>();
            ArrayList<Integer> indexOfGood=new ArrayList<>();
            while (a <= 2) {
                for (Goods g : gadget.warehouse.existence) {
                    if (g.name.equals(GoodsEnum.FLOUR.name())) {
                        a++;
                        index.add(gadget.warehouse.existence.indexOf(g));
                        indexOfGood.add(gadget.warehouse.flourInWareHouse.indexOf(g));
                    }
                }
            }
         if(a==0) return 0;
            if(cookieBakery.work(a)){
                for (int i = 0; i < index.size(); i++) gadget.warehouse.existence.remove(index.get(i));
                for (int i1 = 0; i1 < indexOfGood.size(); i1++) gadget.warehouse.flourInWareHouse.remove(indexOfGood.get(i1));
                return 1;
            }
            return -1;
        }
    }

    public int workWeaving(Weaving weaving, ControllerGadget gadget) {
        if (weaving.level == 1) {
            for (Goods goods1 : gadget.warehouse.existence) {
                if (goods1.name.equals(GoodsEnum.SILK.name())) {
                    if(weaving.work(1)){
                        gadget.warehouse.existence.remove(goods1);
                        gadget.warehouse.silkInWareHouse.remove(goods1);
                        return 1;
                    }
                    return -1;
                }
            }
            return 0;
        }else {
            int a = 0;
            ArrayList<Integer>index=new ArrayList<>();
            ArrayList<Integer>indexOfGood=new ArrayList<>();
            while (a <= 2) {
                for (Goods g : gadget.warehouse.existence) {
                    if (g.name.equals(GoodsEnum.SILK.name())) {
                        a++;
                        index.add(gadget.warehouse.existence.indexOf(g));
                        indexOfGood.add(gadget.warehouse.silkInWareHouse.indexOf(g));
                    }
                }
            }
          if(a==0) return 0;
            if(weaving.work(a)) {
                for (int i = 0; i < index.size(); i++) gadget.warehouse.existence.remove(index.get(i));
                for (int i = 0; i < indexOfGood.size(); i++) gadget.warehouse.silkInWareHouse.remove(indexOfGood.get(i));
                return 1;
            }
            return -1;
        }
    }
    public int workChickennery(Chickenerry chickenerry, ControllerGadget gadget) {
        if (chickenerry.level == 1) {
            for (Goods goods1 : gadget.warehouse.existence) {
                if (goods1.name.equals(GoodsEnum.EGG.name())) {
                    if(chickenerry.work(1)){
                        gadget.warehouse.existence.remove(goods1);
                        gadget.warehouse.eggInWareHouse.remove(goods1);
                        return 1;
                    }
                    return -1;
                }
            }
            return 0;
        }else {
            int a = 0;
            ArrayList<Integer>index=new ArrayList<>();
            ArrayList<Integer>indexOfGood=new ArrayList<>();
            while (a <= 2) {
                for (Goods g : gadget.warehouse.existence) {
                    if (g.name.equals(GoodsEnum.EGG.name())) {
                        a++;
                        index.add(gadget.warehouse.existence.indexOf(g));
                        indexOfGood.add(gadget.warehouse.eggInWareHouse.indexOf(g));
                    }
                }
            }
          if(a==0) return 0;
            if(chickenerry.work(a)) {
                for (int i = 0; i < index.size(); i++) gadget.warehouse.existence.remove(index.get(i));
                for (int i = 0; i < indexOfGood.size(); i++) gadget.warehouse.eggInWareHouse.remove(indexOfGood.get(i));
                return 1;
            }
            return -1;
        }
    }

    public int  workIceCreamFactory(IceCreamFactory iceCreamFactory, ControllerGadget gadget) {
        if (iceCreamFactory.level == 1) {
            for (Goods goods1 : gadget.warehouse.existence) {
                if (goods1.name.equals(GoodsEnum.SEPARATEDMILK.name())) {
                    if(iceCreamFactory.work(1)){
                        gadget.warehouse.existence.remove(goods1);
                        gadget.warehouse.sepratedMilkInWareHouse.remove(goods1);
                        return 1;
                    }
                    return -1;
                }
            }
            return 0;
        }else {
            int a = 0;
            ArrayList<Integer> index =new ArrayList<>();
            ArrayList<Integer> indexOfGood =new ArrayList<>();
            while (a <= 2) {
                for (Goods g : gadget.warehouse.existence) {
                    if (g.name.equals(GoodsEnum.SEPARATEDMILK.name())) {
                        a++;
                        index.add(gadget.warehouse.existence.indexOf(g));
                        indexOfGood.add(gadget.warehouse.sepratedMilkInWareHouse.indexOf(g));
                    }
                }
            }
           if(a==0) return 0;
            if(iceCreamFactory.work(a)){
                for (int i = 0; i < index.size(); i++)gadget.warehouse.existence.remove(index.get(i));
                for (int i = 0; i < indexOfGood.size(); i++)gadget.warehouse.sepratedMilkInWareHouse.remove(indexOfGood.get(i));
                return 1;
            }
            return -1;
        }
    }

}
