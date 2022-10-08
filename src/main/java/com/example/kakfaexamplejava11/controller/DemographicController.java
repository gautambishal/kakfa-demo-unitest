package com.example.kakfaexamplejava11.controller;

import com.example.kakfaexamplejava11.domain.DemographyEvent;
import com.example.kakfaexamplejava11.domain.EventType;
import com.example.kakfaexamplejava11.domain.Province;
import com.example.kakfaexamplejava11.producer.DemographyProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@RestController
@RequestMapping("/api/v1")
public class DemographicController {
    @Autowired
    DemographyProducer demographyProducer;

    @PostMapping("/province")
    public ResponseEntity<Province> postProvince(@RequestBody Province province) throws JsonProcessingException, NoSuchAlgorithmException {
//        demographyProducer.send(province);
        DemographyEvent demographyEvent = new DemographyEvent();
        demographyEvent.setEventType(EventType.NEW);
        demographyEvent.setId(SecureRandom.getInstanceStrong().nextLong());
        demographyEvent.setObject(province);
        demographyProducer.sendP(demographyEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(province);
    }
}
