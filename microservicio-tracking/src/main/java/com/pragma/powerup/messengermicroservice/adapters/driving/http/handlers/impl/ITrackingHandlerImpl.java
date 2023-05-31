package com.pragma.powerup.messengermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.messengermicroservice.adapters.driving.http.handlers.ITrackingHandler;
import com.pragma.powerup.messengermicroservice.adapters.driving.http.mapper.IMessengerResponseMapper;
import com.pragma.powerup.messengermicroservice.domain.api.ITrackingServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ITrackingHandlerImpl implements ITrackingHandler {

    private final IMessengerResponseMapper messengerResponseMapper;
    private final ITrackingServicePort messengerServicePort;

    @Override
    public void trackingOrder() {
        messengerServicePort.trackingOrder();
    }
}
