package com.pragma.powerup.messengermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.messengermicroservice.adapters.driving.http.handlers.IMessengerHandler;
import com.pragma.powerup.messengermicroservice.adapters.driving.http.mapper.IMessengerResponseMapper;
import com.pragma.powerup.messengermicroservice.domain.api.IMessengerServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IMessengerHandlerImpl implements IMessengerHandler {

    private final IMessengerResponseMapper messengerResponseMapper;
    private final IMessengerServicePort messengerServicePort;

    @Override
    public void sendMessage(Long idOrder) {
        messengerServicePort.sendMessage(idOrder);
    }
}
