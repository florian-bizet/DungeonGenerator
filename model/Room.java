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
        return (this.x >= r.x && this.x <= r.x+r.w+1) && (this.y >= r.y && this.y <= r.y+r.h+1) || (this.x+this.w >= r.x && this.x+this.w <= r.x+r.w+1) && (this.y+this.h >= r.y && this.y+this.h <= r.y+r.h+1);
    }

    

    public static void main(String[] args) {
        Room r1 = new Room(0, 0, 25, 25);
        Room r2 = new Room(5,5,5,5);

        System.out.println("Test 1 : Room 2 inside Room 1");
        System.out.println("Expected output : true");
        System.out.println("Output 1        : " + r1.intersect(r2));
        System.out.println("Output 2        : " + r2.intersect(r1));

        Room r3 = new Room(25, 25, 1, 1);

        System.out.println("Test 2 : Room 3 1 tile next to Room 1");
        System.out.println("Expected output : true");
        System.out.println("Output 1        : " + r1.intersect(r3));
        System.out.println("Output 2        : " + r3.intersect(r1));

        Room r4 = new Room(30, 30, 25, 25);

        System.out.println("Test 3 : Room 4 separated from Room 1");
        System.out.println("Expected output : false");
        System.out.println("Output 1        : " + r1.intersect(r4));
        System.out.println("Output 2        : " + r4.intersect(r1));
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
