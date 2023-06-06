package com.pragma.powerup.messengermicroservice.configuration;

import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.adapter.TrackingOrderMongoAdapterOrder;
import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.adapter.TrackingRestaurantMongoAdapter;
import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.mappers.ITrackingOrderEntityMapper;
import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.mappers.ITrackingRestaurantEntityMapper;
import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.repositories.ITrackingOrderRepository;
import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.repositories.ITrackingRestaurantRepository;
import com.pragma.powerup.messengermicroservice.domain.api.ITrackingRestaurantServicePort;
import com.pragma.powerup.messengermicroservice.domain.api.ITrackingOrderServicePort;
import com.pragma.powerup.messengermicroservice.domain.spi.ITrackingOrderPersistencePort;
import com.pragma.powerup.messengermicroservice.domain.spi.ITrackingRestaurantPersistencePort;
import com.pragma.powerup.messengermicroservice.domain.usecase.TrackingRestaurantUseCase;
import com.pragma.powerup.messengermicroservice.domain.usecase.TrackingOrderUseCaseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableFeignClients
public class BeanConfiguration {

    private final ITrackingOrderRepository trackingRepository;
    private final ITrackingOrderEntityMapper trackingEntityMapper;

    private final ITrackingRestaurantRepository trackingRestaurantRepository;
    private final ITrackingRestaurantEntityMapper trackingRestaurantEntityMapper;

    @Bean
    public ITrackingOrderServicePort messengerServicePort() {
        return new TrackingOrderUseCaseOrder(messengerPersistencePort());
    }

    @Bean
    public ITrackingOrderPersistencePort messengerPersistencePort() {
        return new TrackingOrderMongoAdapterOrder(trackingRepository,trackingEntityMapper);
    }

    @Bean
    public ITrackingRestaurantServicePort trackingRestaurantServicePort() {
        return new TrackingRestaurantUseCase(trackingRestaurantPersistencePort());
    }

    @Bean
    public ITrackingRestaurantPersistencePort trackingRestaurantPersistencePort() {
        return new TrackingRestaurantMongoAdapter(trackingRestaurantRepository,trackingRestaurantEntityMapper);
    }

}
