package com.pragma.powerup.messengermicroservice.domain.spi;

public interface IMessengerPersistencePort {
    void sendMessage(String statusOrder, String phoneNumber);
}
