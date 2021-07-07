package controller;

import model.gadget.Warehouse;
import model.gadget.Well;
import model.gadget.vehicle.Truck;
import model.goods.Goods;
import view.Timing;

import java.util.ArrayList;

public class ControllerGadget {

    public Truck truck;
    public Well well;
    public Warehouse warehouse;
    ArrayList<Goods> truckgoods;

    public ControllerGadget() {
        this.truck = new Truck(7,7 );
        this.well = new Well(0,3);
        this.warehouse = new Warehouse(7,3 );
        this.truckgoods = new ArrayList<>();
    }


    public int  truckLoad(Goods good,MainController mainController) {
        if (this.truck.isFull(good)){
            return -1;
        }
        else  if(this.truck.go) {
            return 0;
        } else {
            this.truckgoods.add(good);
            this.truck.avaiableCap -= good.capacity;
            warehouse.existence.remove(good);
            mainController.returnArrByGoodName(good.name).remove(good);
            return 1;
        }
    }

    public int truckUnload(Goods good) {
        if (this.truck.go) return -1;
        else if(!this.truckgoods.contains(good))return 0;
        else{
            this.truckgoods.remove(good);
            warehouse.existence.add(good);
            warehouse.seprateGoods(good);
            this.truck.avaiableCap += good.capacity;
            return 1;
        }
    }

    public boolean truckGo() {
        if (this.truck.go) return false;
        this.truck.goTime = new Timing();
        this.truck.go = true;
        return true;
    }

    public boolean truckCome() {
        if (this.truck.goTime.getDate() + 10 <= Timing.getCurrentTime()) {
            truck.go = false;
            this.truck.goTime = null;
            return true;
        }
        return false;
    }

    public boolean well() {
        if (!this.well.isfull) {
            this.well.fulling = new Timing();
            return true;
        }
        return false;
    }

    //search methods :

}


