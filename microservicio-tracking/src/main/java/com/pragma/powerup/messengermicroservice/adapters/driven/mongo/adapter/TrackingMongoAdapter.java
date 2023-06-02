package com.pragma.powerup.messengermicroservice.adapters.driven.mongo.adapter;

import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.mappers.ITrackingEntityMapper;
import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.repositories.ITrackingRepository;
import com.pragma.powerup.messengermicroservice.domain.model.Tracking;
import com.pragma.powerup.messengermicroservice.domain.spi.ITrackingPersistencePort;
import lombok.AllArgsConstructor;

import java.util.Date;


@AllArgsConstructor
public class TrackingMongoAdapter implements ITrackingPersistencePort {

    private final ITrackingRepository trackingRepository;
    private final ITrackingEntityMapper trackingEntityMapper;

    @Override
    public void trackingOrder(Tracking tracking) {
        tracking.setDate(new Date());
        trackingRepository.save(trackingEntityMapper.toCollection(tracking));
    }
}
