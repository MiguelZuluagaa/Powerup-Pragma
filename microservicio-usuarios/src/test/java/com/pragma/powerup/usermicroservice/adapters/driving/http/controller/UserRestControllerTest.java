package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

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
                .getForObject(("http://localhost:" + port + "/user/id/4"), String.class))
                .contains("idRole");
    }

    @Test
    void getUserByDni() throws Exception {
        Assertions.assertThat(restTemplate
                        .getForObject(("http://localhost:" + port + "/user/dni/999"), String.class))
                .contains("idRole");
    }
}