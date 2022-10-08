package com.example.kakfaexamplejava11.ig.com.example.kafkaexamplejava11.controller;

import com.example.kakfaexamplejava11.controller.DemographicController;
import com.example.kakfaexamplejava11.domain.Province;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemographyControllerTests {

    @Autowired
    TestRestTemplate testRestTemplate;


    @Test
    void givenProvince_whenPostRequest_thenReturnCreatedStatus() throws Exception {
        //given
        Province province = new Province(44L,"province2","","");
        ObjectMapper objectMapper = new ObjectMapper();
        HttpEntity<Province> provinceHttpEntity = new HttpEntity<>(province);
        //when
        ResponseEntity<Province> output = testRestTemplate.exchange("/api/v1/province", HttpMethod.POST, provinceHttpEntity, Province.class);

        //then
        assertThat(output).isNotNull();
        assertThat(output.getStatusCode()).isEqualTo(HttpStatus.CREATED);


    }
}
