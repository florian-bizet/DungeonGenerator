package model;

public class Floor {
    private int w;
    private int h;

    private Room[] rooms;

    private char[][] generated;

    public Floor(int w, int h, int roomCount) {
        this.w = w;
        this.h = h;
        this.rooms = new Room[roomCount];

        this.generated = new char[h][w];

        for (int iH = 0; iH < h; iH++)
            for (int iW = 0; iW < w; iW++)
                this.generated[iH][iW] = '#';
    }

    public void generate() {
        //Generating Rooms
        for (int i = 0; i < rooms.length; i++) {
            
            int x     = 1 + (int) (Math.random()*this.w);
            int y     = 1 + (int) (Math.random()*this.h);
            int roomW = 5 + (int) (Math.random()*this.w/5);
            int roomH = 5 + (int) (Math.random()*this.h/5);

            if (x < 0 || x >= this.w) {i--; continue;}
            if (y < 0 || y >= this.h) {i--; continue;}
            if (x+roomW >= this.w) {i--; continue;}
            if (y+roomH >= this.h) {i--; continue;}
            
            rooms[i] = new Room(x, y, roomW, roomH);
            
            for (int j = 0; j < i; j++) {
                if (rooms[i].intersect(rooms[j])) {i--; break;}
                if (rooms[j].intersect(rooms[i])) {i--; break;}
            }
        }

        //Saving rooms in generated Array 
        for (int i = 0; i < rooms.length; i++) {
            for (int iH = 0; iH < rooms[i].getH(); iH++)
                for (int iW = 0; iW < rooms[i].getW(); iW++)
                    this.generated[rooms[i].getY() + iH][rooms[i].getX() + iW] = ' ';
        }

        //Generating Roads
        for (int i = 1; i < rooms.length; i++) {
            int startingWay = (int) (Math.random()*2);

            if (startingWay == 1) {
                this.horizontalPath(rooms[i-1].getCenterX(), rooms[i].getCenterX(), rooms[i-1].getCenterY());
                this.verticalPath(rooms[i-1].getCenterY(), rooms[i].getCenterY(), rooms[i].getCenterX());
            } else {
                this.verticalPath(rooms[i-1].getCenterY(), rooms[i].getCenterY(), rooms[i-1].getCenterX());
                this.horizontalPath(rooms[i-1].getCenterX(), rooms[i].getCenterX(), rooms[i].getCenterY());
            }
        }

    }

    public void horizontalPath(int x1, int x2, int y) {
        int maxX = Math.max(x1, x2);
        int minX = Math.min(x1, x2);

        for (int i = minX ; i <= maxX; i++) generated[y][i] = ' ';
    }

    public void verticalPath(int y1, int y2, int x) {
        int maxY = Math.max(y1, y2);
        int minY = Math.min(y1, y2);

        for (int i = minY ; i <= maxY; i++) generated[i][x] = ' ';
    }

    public String toString() {
        String s = "";

        for (int iH = 0; iH < h; iH++) {
            for (int iW = 0; iW < w; iW++) {
                s += this.generated[iH][iW];
            }

            s += "\n";
        }
            

        return s;
    }

    public static void main(String[] args) {
        Floor f = new Floor(80, 80, 8);
        f.generate();
        System.out.println(f);
    }
}
