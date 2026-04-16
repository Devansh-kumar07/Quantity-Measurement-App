package com.qma.historyservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qma.historyservice.entity.HistoryEntity;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
    List<HistoryEntity> findByUsername(String username);
    List<HistoryEntity> findByUsernameAndOperation(String username, String operation);
    @Transactional 
    void deleteByUsername(String username);
}