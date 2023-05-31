package com.pragma.powerup.messengermicroservice.domain.model;

import org.bson.types.ObjectId;

import java.util.Date;

public class Tracking {

    private ObjectId id;
    private Long idOrder;
    private Long idCustomer;
    private Long idEmployee;
    private String emailCustomer;
    private String emailEmployee;
    private Date date;
    private String previousStatus;
    private String currentStatus;


    public Tracking(ObjectId id, Long idOrder, Long idCustomer, Long idEmployee, String emailCustomer, String emailEmployee, Date date, String previousStatus, String currentStatus) {
        this.id = id;
        this.idOrder = idOrder;
        this.idCustomer = idCustomer;
        this.idEmployee = idEmployee;
        this.emailCustomer = emailCustomer;
        this.emailEmployee = emailEmployee;
        this.date = date;
        this.previousStatus = previousStatus;
        this.currentStatus = currentStatus;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    public String getEmailEmployee() {
        return emailEmployee;
    }

    public void setEmailEmployee(String emailEmployee) {
        this.emailEmployee = emailEmployee;
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
