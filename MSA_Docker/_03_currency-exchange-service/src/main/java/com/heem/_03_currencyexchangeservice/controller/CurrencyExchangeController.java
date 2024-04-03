package com.heem._03_currencyexchangeservice.controller;

import com.heem._03_currencyexchangeservice.repo.CurrencyExchangeRepository;
import com.heem._03_currencyexchangeservice.entity.CurrencyExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    private Environment environment;

    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    public CurrencyExchangeController(Environment theEnvironment, CurrencyExchangeRepository theCurrencyExchangeRepository) { // constructor injection
        environment = theEnvironment;
        currencyExchangeRepository = theCurrencyExchangeRepository;
    }



    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to ) {

        //CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
        logger.info("retrieve EchangeValue called with {} to {}", from, to);
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        if(currencyExchange == null) {
            throw new RuntimeException("Unable to FInd data for " + from  + " to " + to);

        }
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;

    }
}
