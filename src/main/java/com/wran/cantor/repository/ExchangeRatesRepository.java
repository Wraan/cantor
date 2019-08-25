package com.wran.cantor.repository;

import com.wran.cantor.model.ExchangeRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRatesRepository extends JpaRepository<ExchangeRates, Long> {
    ExchangeRates findTopByOrderByIdDesc();
    List<ExchangeRates> findTop20ByOrderByIdDesc();
}
