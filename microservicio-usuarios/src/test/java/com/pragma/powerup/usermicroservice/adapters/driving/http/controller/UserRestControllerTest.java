package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.LoginRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRestControllerTest {

    @Autowired
    private UserRestController userRestController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String token = null;

    @BeforeEach
    void setUp() {
        LoginRequestDto user = new LoginRequestDto(
                "admin@gmail.com",
                "999");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");

        HttpEntity<LoginRequestDto> request  = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/auth/login",
                HttpMethod.POST,
                request,
                String.class);

        token = response.getBody().replace("{\"token\":\"","").replace("\"}","");
    }

    @Test
    void getUserById() throws Exception {
        HttpHeaders headersRole = new HttpHeaders();
        headersRole.setContentType(MediaType.APPLICATION_JSON);
        headersRole.set("charset", "utf-8");
        headersRole.set("Authorization", "Bearer " + token);

        HttpEntity<String> requestEntity = new HttpEntity<>(headersRole);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/user/id/1",
                HttpMethod.GET,
                requestEntity,
                String.class);

        Assertions.assertThat(response.getBody()).contains("idRole");
    }

    @Test
    void getUserByDni() throws Exception {
        HttpHeaders headersRole = new HttpHeaders();
        headersRole.setContentType(MediaType.APPLICATION_JSON);
        headersRole.set("charset", "utf-8");
        headersRole.set("Authorization", "Bearer " + token);

        HttpEntity<String> requestEntity = new HttpEntity<>(headersRole);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/user/dni/999",
                HttpMethod.GET,
                requestEntity,
                String.class);

        Assertions.assertThat(response.getBody()).contains("idRole");
    }

    @Test
    void getUserByEmail() throws Exception {
        HttpHeaders headersRole = new HttpHeaders();
        headersRole.setContentType(MediaType.APPLICATION_JSON);
        headersRole.set("charset", "utf-8");
        headersRole.set("Authorization", "Bearer " + token);

        HttpEntity<String> requestEntity = new HttpEntity<>(headersRole);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/user/findByEmail/TESTTESTTES@gmail.com",
                HttpMethod.GET,
                requestEntity,
                String.class);

        Assertions.assertThat(response.getBody()).contains("name");
    }

    @Test
    void saveUser() throws Exception {
        UserRequestDto user = new UserRequestDto(
                "TEST NAME",
                "TEST LASTNAME",
                "TESTTESTTES@gmail.com",
                "1234567",
                "78623",
                "12-12-2001",
                "1256");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<UserRequestDto> request  = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/user/saveUser/",
                HttpMethod.POST,
                request,
                String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}