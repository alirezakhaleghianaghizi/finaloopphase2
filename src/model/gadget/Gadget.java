package model.gadget;
import model.Model;

public  class Gadget extends Model {
     public int capacity ;

    public Gadget(String name, double x, double y, int capacity) {
        super(name, x, y);
        this.capacity=capacity;
    }

}
