package model.gadget;

public enum GadgetEnum {
    WELL(5),
    WAREHOUSE(30),
    TRUCK(15)
    ;

    int capacity;

    GadgetEnum(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
