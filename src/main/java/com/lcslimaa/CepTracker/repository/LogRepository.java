package com.lcslimaa.CepTracker.repository;

import com.lcslimaa.CepTracker.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<LogEntity, Long> {

    List<LogEntity> findByCep(String cep);
}
