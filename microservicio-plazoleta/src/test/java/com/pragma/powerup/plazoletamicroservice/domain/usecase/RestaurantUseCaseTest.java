package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RestaurantUseCaseTest {

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurant = new Restaurant(1L, "McDona","1234","TEST DIRECTION","1234566","http://www.google.com",1L);
    }

    @Test
    void getAllRestaurants() {
        when(restaurantPersistencePort.getAllRestaurants()).thenReturn(Arrays.asList(restaurant));
        assertNotNull(restaurantUseCase.getAllRestaurants());
    }

    @Test
    void saveRestaurant() {
    }
}