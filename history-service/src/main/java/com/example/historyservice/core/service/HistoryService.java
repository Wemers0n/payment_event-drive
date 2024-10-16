package com.example.historyservice.core.service;

import com.example.historyservice.core.dtos.event.Event;
import com.example.historyservice.core.model.History;
import com.example.historyservice.core.respository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoryService {

    private static final String TRANSACTION_ID_PATTERN = "%s_%s";

    private final HistoryRepository historyRepository;

    public void saveHistory(Event event){
        createHistory(event);
    }

    private void createHistory(Event event){
//        String id = String.format(TRANSACTION_ID_PATTERN, Instant.now().toEpochMilli(), UUID.randomUUID());
        var history = History.builder()
                .transactionId(event.getTransactionId().getId())
                .status(event.getStatus())
                .amount(event.getAmount())
                .build();
        save(history);
    }

    private void save(History history){
        this.historyRepository.save(history);
    }
}
