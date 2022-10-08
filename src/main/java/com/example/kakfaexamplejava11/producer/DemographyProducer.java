package com.example.kakfaexamplejava11.producer;

import com.example.kakfaexamplejava11.domain.Province;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class DemographyProducer {

    @Autowired
    KafkaTemplate<Long,String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void send(Province province) throws JsonProcessingException {
        Long key = province.getProvinceId();
        String value = objectMapper.writeValueAsString(province);
        ListenableFuture<SendResult<Long, String>> sendResultListenableFuture = kafkaTemplate.sendDefault(key, value);
        sendResultListenableFuture.addCallback(new ListenableFutureCallback<SendResult<Long, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                handleFailure(key,value,ex);
            }

            @Override
            public void onSuccess(SendResult<Long, String> result) {
                handleSuccess(key,value,result);
            }
        });
    }

    private void handleFailure(Long key, String value, Throwable ex) {
        log.error("error sending to the consumer {}",ex.getMessage());
        try{
            throw ex;
        }catch(Throwable e) {
            log.error("error message {}", e.getMessage());
        }
    }

    private void handleSuccess(Long key, String value, SendResult<Long, String> result) {
        log.info("message sent success --------");
        log.info("message sent with key: {} and value is :{}",key,value);
        log.info("message sent on partition {}",result.getProducerRecord().partition());
    }
}
