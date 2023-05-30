package com.pragma.powerup.messengermicroservice.adapters.driving.http.handlers;

public interface IMessengerHandler {
    void sendMessage(String statusOrder, String phoneNumber);
}
