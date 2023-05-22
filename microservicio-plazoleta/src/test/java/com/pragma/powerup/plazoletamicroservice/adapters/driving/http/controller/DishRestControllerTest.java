package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.controller;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.plazoletamicroservice.domain.model.Category;
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

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DishRestControllerTest {

    @Autowired
    private DishRestController dishRestController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String token = null;

    private CategoryEntity category = null;
    private RestaurantEntity restaurant = null;
    private Dish dish = null;

    @BeforeEach
    void setUp() {

        category = new CategoryEntity(1L,null,null);
        restaurant = new RestaurantEntity(11L,null,null,null,null,null,null);

        dish = new Dish(null,"TEST DISH","TEST DISH",200.0,
                        "http://www.test.com.co/",null,category,restaurant);

        String email = "owner@gmail.com";
        String password = "123";

        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        headers.set("Authorization","Bearer "+ token);

        HttpEntity<Map<String, Object>> request  = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8090/auth/login",
                HttpMethod.POST,
                request,
                String.class);

        token = response.getBody().replace("{\"token\":\"","").replace("\"}","");
    }

    @Test
    void getAllDishes() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        headers.set("Authorization","Bearer "+ token);// You need put a token here.

        HttpEntity<Restaurant> request  = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/dish/getAllDishes/",
                HttpMethod.GET,
                request,
                String.class);

        Assertions.assertThat(response.getBody()).contains("name");
    }

    @Test
    void saveDish() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        headers.set("Authorization","Bearer "+ token);// You need put a token here.

        HttpEntity<Dish> request  = new HttpEntity<>(dish, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/dish/saveDish/",
                HttpMethod.POST,
                request,
                String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void updateDish() {

        dish.setId(20L);
        dish.setDescription("CHANGE DESCRIPTION");
        dish.setPrice(500.0);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        headers.set("Authorization","Bearer "+ token);// You need put a token here.

        HttpEntity<Dish> request  = new HttpEntity<>(dish, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/dish/updateDish/",
                HttpMethod.PUT,
                request,
                String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void activeDish(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        headers.set("Authorization","Bearer "+ token);// You need put a token here.

        HttpEntity<Restaurant> request  = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/dish/activeDish/1",
                HttpMethod.PUT,
                request,
                String.class);

        Assertions.assertThat(response.getBody()).contains("Dish updated successfully");
    }

    @Test
    void disableDish(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        headers.set("Authorization","Bearer "+ token);// You need put a token here.

        HttpEntity<Restaurant> request  = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/dish/disableDish/1",
                HttpMethod.PUT,
                request,
                String.class);

        Assertions.assertThat(response.getBody()).contains("Dish updated successfully");
    }
}