package com.heem._03_currencyexchangeservice.repo;

import com.heem._03_currencyexchangeservice.entity.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    CurrencyExchange findByFromAndTo(String from, String to);

}
