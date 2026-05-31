package com.example.demo.dto;

import com.example.demo.Model.Parking;

public class SearchResponseDTO {

    private Parking parking;

    private Double distance;

    private boolean slotAvailable;

    // GETTERS & SETTERS

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public boolean isSlotAvailable() {
        return slotAvailable;
    }

    public void setSlotAvailable(boolean slotAvailable) {
        this.slotAvailable = slotAvailable;
    }
}