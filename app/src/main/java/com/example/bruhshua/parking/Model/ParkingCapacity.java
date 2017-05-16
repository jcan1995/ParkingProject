package com.example.bruhshua.parking.Model;

/**
 * Created by evancausey on 5/15/17.
 */

public class ParkingCapacity {
    private int time;
    private int findSpotInF;
    private int findSpotInC;
    private int findSpotInB;
    private int findSpotInX;
    private int findSpotInY;
    private int findSpotInZ;
    private int findSpotInG;
    private int findSpotInO;
    private int findSpotInK;

    public ParkingCapacity(int time, int findSpotInF, int findSpotInC, int findSpotInB, int findSpotInX, int findSpotInY, int findSpotInZ, int findSpotInG, int findSpotInO, int findSpotInK) {
        this.time = time;
        this.findSpotInF = findSpotInF;
        this.findSpotInC = findSpotInC;
        this.findSpotInB = findSpotInB;
        this.findSpotInX = findSpotInX;
        this.findSpotInY = findSpotInY;
        this.findSpotInZ = findSpotInZ;
        this.findSpotInG = findSpotInG;
        this.findSpotInO = findSpotInO;
        this.findSpotInK = findSpotInK;
    }

    public int getFindSpotInF() {
        return findSpotInF;
    }
    public int getFindSpotInC() {
        return findSpotInC;
    }
    public int getFindSpotInB() {
        return findSpotInB;
    }

    public int getFindSpotInX() {
        return findSpotInX;
    }

    public int getFindSpotInY() {
        return findSpotInY;
    }

    public int getFindSpotInZ() {
        return findSpotInZ;
    }

    public int getFindSpotInG() {
        return findSpotInG;
    }

    public int getFindSpotInO() {
        return findSpotInO;
    }

    public int getFindSpotInK() {
        return findSpotInK;
    }

    public int getTime() {
        return time;
    }
}
