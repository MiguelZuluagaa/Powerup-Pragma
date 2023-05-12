package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.assertj.core.api.Assertions;
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

    @Test
    void getUserById() throws Exception {
        Assertions.assertThat(restTemplate
                .getForObject(("http://localhost:" + port + "/user/id/1"), String.class))
                .contains("idRole");
    }

    @Test
    void getUserByDni() throws Exception {
        Assertions.assertThat(restTemplate
                        .getForObject(("http://localhost:" + port + "/user/dni/999"), String.class))
                .contains("idRole");
    }

    @Test
    void saveUser() throws Exception {
        // Please note that you must be a registered user with owner role to run this test successfully.
        RoleEntity role= new RoleEntity(1L, "TEST ROLE","TEST ROLE");

        UserRequestDto user = new UserRequestDto(
                "TEST NAME",
                "TEST LASTNAME",
                "email@gmail.com",
                "1234567",
                "1256",
                "12-12-2001",
                "1256",
                role);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");

        HttpEntity<UserRequestDto> request  = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/user/",
                HttpMethod.POST,
                request,
                String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }
}