package model;

import java.util.Arrays;

public class Floor {
    private int w;
    private int h;

    private Room[] rooms;

    private int[][] generated;

    public Floor(int w, int h, int roomCount) {
        this.w = w;
        this.h = h;
        this.rooms = new Room[roomCount];

        this.generated = new int[h][w];

        for (int iH = 0; iH < h; iH++)
            for (int iW = 0; iW < w; iW++)
                this.generated[iH][iW] = 4;
    }

    public int[][] generate() {
        //Generating Rooms
        for (int i = 0; i < rooms.length; i++) {
            
            int x     = 2 + (int) (Math.random()*(this.w-2));
            int y     = 2 + (int) (Math.random()*(this.h-2));
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
            for (int cptY = 0; cptY < rooms[i].getH(); cptY++) {
                for (int cptX = 0; cptX < rooms[i].getW(); cptX++) {
                    this.generated[rooms[i].getY() + cptY][rooms[i].getX() +cptX] = -1;
                }
            }
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

        //Wall Post-processing
        for (int i = 1; i < generated.length-1; i++) {
            for (int j = 1; j < generated[i].length-1; j++) {
                generated[i][j] = getTile(i, j);
                generated[i][j] = randomize(i,j);
            }
        }

        return this.generated;

    }

    public void horizontalPath(int x1, int x2, int y) {
        int maxX = Math.max(x1, x2);
        int minX = Math.min(x1, x2);

        for (int i = minX ; i <= maxX; i++) {
            generated[y][i] = -1;
        }
    }

    public void verticalPath(int y1, int y2, int x) {
        int maxY = Math.max(y1, y2);
        int minY = Math.min(y1, y2);

        for (int i = minY ; i <= maxY; i++) {
            generated[i][x] = -1;
        }
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

    public int getTile(int i, int j) {
        boolean[] tiles = new boolean[9];

        for (int cpt = 0; cpt < tiles.length; cpt++) {
            tiles[cpt] = (generated[i-1+cpt/3][j-1+cpt%3] != -1);
        }

        if (!tiles[4]) return generated[i][j];

        
        //One Way
        if (!tiles[1] && !tiles[7] && !tiles[5]) return 23;
        if (!tiles[1] && !tiles[7] && !tiles[3]) return 21;

        if (!tiles[3] && !tiles[5] && !tiles[1]) return 19;
        if (!tiles[3] && !tiles[5] && !tiles[7]) return 25;
        
        if (!tiles[1] && !tiles[7]) return 10;
        if (!tiles[3] && !tiles[5]) return 12;

        //Angles
        if (!tiles[0] && !tiles[1] && !tiles[3]) return 0;
        if (!tiles[1] && !tiles[2] && !tiles[5]) return 2;
        if (!tiles[3] && !tiles[6] && !tiles[7]) return 6;
        if (!tiles[5] && !tiles[7] && !tiles[8]) return 8;
        
        //Side Walls
        for (int cpt = 1; cpt < tiles.length; cpt += 2) {
            if (!tiles[cpt]) return cpt;
        }

        //Corner Pieces
        if (!tiles[0]) return 49;
        if (!tiles[2]) return 48;
        if (!tiles[6]) return 46;
        if (!tiles[8]) return 45;

        return generated[i][j];
    }

    public int randomize(int i, int j) {
        int rand = (int)(Math.random()*3);

        if (rand == 0 && generated[i][j] >= 0) {
            generated[i][j] += 72;
        }

        return generated[i][j];
    }

    public static void main(String[] args) {
        Floor f = new Floor(80, 80, 8);
        f.generate();
        System.out.println(f);
    }
}
