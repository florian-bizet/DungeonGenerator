package model;

public class Dungeon {
    private final int MAX_ROOMS;
    private final int FLOOR_COUNT;

    private Floor currentFloor;
    private int currentFloorID;

    public Dungeon(int floorCount, int maxRooms) {
        this.FLOOR_COUNT = floorCount;
        this.MAX_ROOMS   = maxRooms;

        this.currentFloorID = 0;
    }

}
