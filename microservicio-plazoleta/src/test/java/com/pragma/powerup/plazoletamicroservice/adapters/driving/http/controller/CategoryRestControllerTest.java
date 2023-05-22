package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.controller;

import com.pragma.powerup.plazoletamicroservice.domain.model.Category;
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
class CategoryRestControllerTest {

    @Autowired
    private CategoryRestController categoryRestController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String token = null;

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
    }

    @Test
    void getAllCategories() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        headers.set("Authorization","Bearer "+ token);// You need put a token here.

        HttpEntity<Restaurant> request  = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/category/getAllCategories/",
                HttpMethod.GET,
                request,
                String.class);

        Assertions.assertThat(response.getBody()).contains("name");
    }

    @Test
    void saveCategory() {
        Category category =
                new Category(null,"TEST","TEST");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        headers.set("Authorization","Bearer "+ token);// You need put a token here.

        HttpEntity<Category> request  = new HttpEntity<>(category, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/category/saveCategory/",
                HttpMethod.POST,
                request,
                String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}