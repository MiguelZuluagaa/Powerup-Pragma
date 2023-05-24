package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.controller;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderDishEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.domain.model.Dish;
import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String tokenCustomer = null;
    private String tokenEmployee = null;

    @BeforeEach
    void setUp() {
        String email = "customer@gmail.com";
        String password = "444";

        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        headers.set("Authorization","Bearer "+ tokenCustomer);

        HttpEntity<Map<String, Object>> request  = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8090/auth/login",
                HttpMethod.POST,
                request,
                String.class);

        tokenCustomer = response.getBody().replace("{\"token\":\"","").replace("\"}","");


        email = "employee@gmail.com";
        password = "333";

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

        tokenEmployee = response.getBody().replace("{\"token\":\"","").replace("\"}","");
    }

    @Test
    void createOrder() {
        ArrayList<OrderDishEntity> dishes = new ArrayList<>();
        dishes.add(new OrderDishEntity(
                    null,
                    null,
                    new DishEntity(1L,null,null,null,null,null,null,null),
                    2L));

        dishes.add(new OrderDishEntity(
                null,
                null,
                new DishEntity(2L,null,null,null,null,null,null,null),
                2L));

        RestaurantEntity restaurant = new RestaurantEntity(16L,null,null,null,null,null,null);

        CreateOrderRequestDto order = new CreateOrderRequestDto(
                1L,
                restaurant,
                dishes
        );


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        headers.set("Authorization","Bearer "+ tokenCustomer);// You need put a token here.

        HttpEntity<CreateOrderRequestDto> request  = new HttpEntity<>(order, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/order/createOrder/",
                HttpMethod.POST,
                request,
                String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getOrdersByStatus() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        headers.set("Authorization","Bearer "+ tokenEmployee);// You need put a token here.

        HttpEntity<Restaurant> request  = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/order/getOrdersByStatus/16/2/2/1",
                HttpMethod.GET,
                request,
                String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}