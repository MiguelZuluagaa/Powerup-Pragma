package com.pragma.powerup.messengermicroservice.domain.usecase;

import com.pragma.powerup.messengermicroservice.domain.api.IMessengerServicePort;
import com.pragma.powerup.messengermicroservice.domain.spi.IMessengerPersistencePort;

public class MessengerUseCase implements IMessengerServicePort {
    private final IMessengerPersistencePort messengerPersistencePort;

    public MessengerUseCase(IMessengerPersistencePort messengerPersistencePort) {
        this.messengerPersistencePort = messengerPersistencePort;
    }

    @Override
    public void sendMessage(Long idOrder) {
        messengerPersistencePort.sendMessage(idOrder);
    }
}
