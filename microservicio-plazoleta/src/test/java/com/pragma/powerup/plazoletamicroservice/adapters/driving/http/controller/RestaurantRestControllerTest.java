package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.controller;

import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

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

    @Test
    void saveRestaurant() throws Exception {
        // Please note that you must be a registered user with owner role to run this test successfully.
        Restaurant restaurant =
                new Restaurant(10L,"TEST NAME","919191","TEST DIRECTION",
                                "123456778", "https://www.testlogo.com/",3L);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");

        HttpEntity<Restaurant> request  = new HttpEntity<>(restaurant, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/restaurant",
                HttpMethod.POST,
                request,
                String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}