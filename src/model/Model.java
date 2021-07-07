package model;

public abstract class Model {
    public String name ;
    public double x=0;
    public double y=0;

    public Model(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
