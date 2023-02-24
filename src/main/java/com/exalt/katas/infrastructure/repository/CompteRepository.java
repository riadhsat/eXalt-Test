package com.exalt.katas.infrastructure.repository;

import com.exalt.katas.infrastructure.model.CompteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<CompteDto,Integer> {

}
