package com.pragma.powerup.plazoletamicroservice.domain.model;

import java.util.Date;

public class TrackingRestaurant {

    private Long idRestaurant;
    private Date date;
    private String previousStatus;
    private String currentStatus;

    public TrackingRestaurant(Long idRestaurant, Date date, String previousStatus, String currentStatus) {
        this.idRestaurant = idRestaurant;
        this.date = date;
        this.previousStatus = previousStatus;
        this.currentStatus = currentStatus;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(String previousStatus) {
        this.previousStatus = previousStatus;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}
