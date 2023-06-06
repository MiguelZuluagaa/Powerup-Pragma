package com.pragma.powerup.messengermicroservice.adapters.driven.mongo.adapter;

import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.mappers.ITrackingOrderEntityMapper;
import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.repositories.ITrackingOrderRepository;
import com.pragma.powerup.messengermicroservice.domain.model.TrackingOrder;
import com.pragma.powerup.messengermicroservice.domain.spi.ITrackingOrderPersistencePort;
import lombok.AllArgsConstructor;

import java.util.Date;


@AllArgsConstructor
public class TrackingOrderMongoAdapterOrder implements ITrackingOrderPersistencePort {

    private final ITrackingOrderRepository trackingRepository;
    private final ITrackingOrderEntityMapper trackingEntityMapper;

    @Override
    public void trackingOrder(TrackingOrder trackingOrder) {
        trackingOrder.setDate(new Date());
        trackingRepository.save(trackingEntityMapper.toCollection(trackingOrder));
    }
}
