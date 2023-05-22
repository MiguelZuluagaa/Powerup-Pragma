package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.controller;

import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestaurantRestControllerTest {

    @Autowired
    private RestaurantRestController restaurantRestController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String token = null;
    private String tokenCustomer = null;

    @BeforeEach
    void setUp() {
        String email = "admin@gmail.com";
        String password = "999";

        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");

        HttpEntity<Map<String, Object>> request  = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8090/auth/login",
                HttpMethod.POST,
                request,
                String.class);

        token = response.getBody().replace("{\"token\":\"","").replace("\"}","");

        email = "customer@gmail.com";
        password = "444";

        user = new HashMap<>();
        user.put("email", email);
        user.put("password", password);

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");

        request  = new HttpEntity<>(user, headers);
        response = restTemplate.exchange(
                "http://localhost:8090/auth/login",
                HttpMethod.POST,
                request,
                String.class);

        tokenCustomer = response.getBody().replace("{\"token\":\"","").replace("\"}","");
    }

    @Test
    void getAllRestaurants() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        headers.set("Authorization","Bearer "+ token);// You need put a token here.

        HttpEntity<Restaurant> request  = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/restaurant",
                HttpMethod.GET,
                request,
                String.class);

        Assertions.assertThat(response.getBody()).contains("urlLogo");
    }

    @Test
    void getRestaurantsWithPagination() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        headers.set("Authorization","Bearer "+ tokenCustomer);// You need put a token here.

        HttpEntity<Restaurant> request  = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/restaurant/getRestaurantsWithPagination/2/0",
                HttpMethod.GET,
                request,
                String.class);

        Assertions.assertThat(response.getBody()).contains("name");
    }

    @Test
    void saveRestaurant() throws Exception {
        // Please note that you must be a registered user with owner role to run this test successfully.
        // The microservice User must be running.
        Restaurant restaurant =
                new Restaurant(10L,"TEST NAME","123123123123","TEST DIRECTION",
                                "123456778", "https://www.testlogo.com/",8L);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        headers.set("Authorization","Bearer "+ token);// You need put a token here.

        HttpEntity<Restaurant> request  = new HttpEntity<>(restaurant, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/restaurant",
                HttpMethod.POST,
                request,
                String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}