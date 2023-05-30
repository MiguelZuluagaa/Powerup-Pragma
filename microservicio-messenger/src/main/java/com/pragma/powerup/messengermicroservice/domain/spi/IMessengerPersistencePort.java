package com.pragma.powerup.messengermicroservice.domain.spi;

public interface IMessengerPersistencePort {
    void sendMessage(Long idOrder);
}
