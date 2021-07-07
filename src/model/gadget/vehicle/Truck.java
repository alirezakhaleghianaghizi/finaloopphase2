package model.gadget.vehicle;

import model.gadget.Gadget;
import model.gadget.GadgetEnum;
import model.goods.Goods;
import view.Timing;

public class Truck extends Gadget {
    int truckSpeed;
    public int avaiableCap;
    public boolean go;
    public Timing goTime ;
    public Truck(double x , double y) {

        super(GadgetEnum.TRUCK.toString(), x, y, GadgetEnum.TRUCK.getCapacity());
        this.truckSpeed = 5;
        this.avaiableCap= GadgetEnum.TRUCK.getCapacity();
        this.go=false;
    }

    public boolean isFull(Goods goods){
        if(avaiableCap<goods.capacity)
        return true;
        return false;

    }



}
