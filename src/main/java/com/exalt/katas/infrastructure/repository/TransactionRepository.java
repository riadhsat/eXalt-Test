package com.exalt.katas.infrastructure.repository;

import com.exalt.katas.infrastructure.model.TransactionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDto, Integer> {

  Page<TransactionDto> findAll(Pageable pageable);
}
