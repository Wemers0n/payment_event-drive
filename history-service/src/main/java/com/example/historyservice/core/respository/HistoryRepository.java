package com.example.historyservice.core.respository;

import com.example.historyservice.core.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
