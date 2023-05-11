package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestaurantRestControllerTest {

    @Autowired
    private RestaurantRestController restaurantRestController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAllRestaurants() throws Exception {
        Assertions.assertThat(restTemplate
                .getForObject(("http://localhost:" + port + "/restaurant"), String.class))
            .contains("urlLogo");
    }
}