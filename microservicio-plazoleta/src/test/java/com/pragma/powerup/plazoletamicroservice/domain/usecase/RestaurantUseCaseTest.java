package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.client.ITrackingFeignClient;
import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;
import com.pragma.powerup.plazoletamicroservice.domain.model.RestaurantStatus;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.RESTAURANT_STATUS_DELETED_ID;
import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.RESTAURANT_STATUS_DELETED_NAME;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Autowired
    private IOrderPersistencePort orderPersistencePort;

    @Mock
    private ITrackingFeignClient trackingFeignClient;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurant = new Restaurant(1L, "McDonald","1234","TEST DIRECTION","1234566","http://www.google.com",1L, null);
    }

    @Test
    void getAllRestaurants() {
        when(restaurantPersistencePort.getAllRestaurants()).thenReturn(Arrays.asList(restaurant));
        assertNotNull(restaurantUseCase.getAllRestaurants());
    }

    @Test
    void deleteRestaurant() {
    }
}