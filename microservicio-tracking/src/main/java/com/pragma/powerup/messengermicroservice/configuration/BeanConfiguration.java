package com.pragma.powerup.messengermicroservice.configuration;

import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.adapter.TrackingMongoAdapter;
import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.mappers.ITrackingEntityMapper;
import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.repositories.ITrackingRepository;
import com.pragma.powerup.messengermicroservice.domain.api.ITrackingServicePort;
import com.pragma.powerup.messengermicroservice.domain.spi.ITrackingPersistencePort;
import com.pragma.powerup.messengermicroservice.domain.usecase.TrackingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableFeignClients
public class BeanConfiguration {

    private final ITrackingRepository trackingRepository;
    private final ITrackingEntityMapper trackingEntityMapper;

    @Bean
    public ITrackingServicePort messengerServicePort() {
        return new TrackingUseCase(messengerPersistencePort());
    }

    @Bean
    public ITrackingPersistencePort messengerPersistencePort() {
        return new TrackingMongoAdapter(trackingRepository,trackingEntityMapper);
    }

}
