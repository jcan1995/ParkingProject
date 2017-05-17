package com.example.bruhshua.parking.Model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by bruhshua on 5/16/17.
 */

public class ParkingLotProbabilityPOJO {

    private int probability;
    private String parkingLot;
    private LatLng location;

    public ParkingLotProbabilityPOJO(){}

    public ParkingLotProbabilityPOJO(int probability, String parkingLot) {
        this.probability = probability;
        this.parkingLot = parkingLot;
        switch (parkingLot){
            case "X":
                this.location = new LatLng(33.127918,-117.163773);
                break;
            case "Y":
                this.location = new LatLng(33.128298,-117.164449);
                break;
            case "Z":
                this.location = new LatLng(33.129010,-117.164360);
                break;
            case "B":
                this.location = new LatLng(33.127136,-117.163151);
                break;
            case "C":
                this.location = new LatLng(33.126857,-117.161198);
                break;
            case "F":
                this.location = new LatLng(33.126336,-117.156971);
                break;
            case "O":
                this.location = new LatLng(33.133018,-117.158149);
                break;
            case "K":
                this.location = new LatLng(33.134060,-117.155135);
                break;
            case "L":
                this.location = new LatLng(33.132283,-117.159480);
                break;
            case "G":
                this.location = new LatLng(33.132023,-117.157516);
                break;
            default:
                this.location = new LatLng(33.129213,-117.159743);
                break;
        }


    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    public String getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
    }


}
