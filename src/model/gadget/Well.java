package model.gadget;

import view.Timing;

public class Well extends Gadget{
     public int timePeride;
    public int availableCapacity;
    public Timing fulling;
    public boolean isfull ;

    public Well(double x , double y) {
        super(GadgetEnum.WELL.toString(), x, y, GadgetEnum.WELL.capacity);
        this.capacity=GadgetEnum.WELL.capacity;
        this.timePeride=3;
        this.isfull=true;
        availableCapacity=0;
    }
}
