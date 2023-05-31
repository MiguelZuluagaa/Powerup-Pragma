package com.pragma.powerup.messengermicroservice.domain.api;

public interface IMessengerServicePort {
    void sendMessage(String statusOrder, String phoneNumber);
}
