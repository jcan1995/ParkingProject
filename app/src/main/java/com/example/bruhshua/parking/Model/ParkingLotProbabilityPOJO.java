package com.example.bruhshua.parking.Model;

/**
 * Created by bruhshua on 5/16/17.
 */

public class ParkingLotProbabilityPOJO {

    private double probability;
    private String parkingLot;


    public ParkingLotProbabilityPOJO(double probability, String parkingLot) {
        this.probability = probability;
        this.parkingLot = parkingLot;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public String getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
    }


}
