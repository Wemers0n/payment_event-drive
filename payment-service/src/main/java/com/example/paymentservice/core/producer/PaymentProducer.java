package com.example.paymentservice.core.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.payment-notification}")
    private String paymentNotificationTopic;

    public void sendEvent(String payload){
        try{
            log.info("Sending event to topic {} with data {}", paymentNotificationTopic, payload);
            this.send(paymentNotificationTopic, payload);
        } catch (Exception e){
            log.error("Error trying to send data topic {} with data {}", paymentNotificationTopic, payload, e);
        }
    }

    private void send(String topic, String payload){
        kafkaTemplate.send(topic, payload);
    }
}
