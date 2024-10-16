package com.example.historyservice.core.consumer;

import com.example.historyservice.config.utils.JsonUtil;
import com.example.historyservice.core.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HistoryConsumer {

    private final HistoryService historyService;
    private final JsonUtil jsonUtil;

    @KafkaListener(topics = "${spring.kafka.topic.payment-notification}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeHistory(String message){
        log.info("Receiving message: {}", message);
        var event = jsonUtil.toEvent(message);

        this.historyService.saveHistory(event);
    }
}
