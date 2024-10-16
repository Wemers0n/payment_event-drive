package com.example.notificationservice.core.consumer;

import com.example.notificationservice.config.utils.JsonUtil;
import com.example.notificationservice.core.services.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationConsumer {

    private final NotificationService notificationService;
    private final JsonUtil jsonUtil;

    public NotificationConsumer(NotificationService notificationService, JsonUtil jsonUtil) {
        this.notificationService = notificationService;
        this.jsonUtil = jsonUtil;
    }

    @KafkaListener(topics = "${spring.kafka.topic.payment-notification}",groupId = "${spring.kafka.consumer.group-id}")
    public void consumeNotification(String message){
        log.info("Receiving message: {}", message);

        var event = this.jsonUtil.toEvent(message);
        this.notificationService.send(event);
//        String messageId = notificationService.sendNotification(message, "notification for kafka");
    }
}
