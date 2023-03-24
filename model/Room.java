package model;

public class Room {
    private int x;
    private int y;
    private int w;
    private int h;

    public Room(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean intersect(Room r) {
        return (this.x < r.x + r.w+2 && this.x + this.w+2 > r.x) && (this.y < r.y + r.h+2 && this.y + this.h+2 > r.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getCenterX() {return x+(w/2);}
    public int getCenterY() {return y+(h/2);}
}
