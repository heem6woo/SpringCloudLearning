package com.heem.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

// To zipkin trace the restemplate
@Configuration(proxyBeanMethods = false)
class RestTemplateConfiguration {

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}

@RestController
public class CurrencyConversionController {

    // Using proxy via feign
    CurrencyExchangeProxy currencyExchangeProxy;

    @Autowired
    private RestTemplate restTemplate;

    public CurrencyConversionController (CurrencyExchangeProxy theCurrencyExchangeProxy) {
        currencyExchangeProxy = theCurrencyExchangeProxy;
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable Integer quantity
    ) {
        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);
        Float totalCalculatedAmount = (float) (quantity * currencyConversion.getConversionMultiple());


        return new CurrencyConversion(currencyConversion.getId(), from, to, currencyConversion.getConversionMultiple(),quantity,
                quantity * currencyConversion.getConversionMultiple(),
                currencyConversion.getEnvironment() + " feign");
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculate(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable Integer quantity
    ) {
        //CurrencyConversion theCurrencyConversion = new CurrencyConversion(10001L, from, to, quantity, BigDecimal.ONE);


        // Get responseEnity by requesting to CurrencyConversion
        // passing the pathVariable through HashMap (name ,value)
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        /*
        ResponseEntity<CurrencyConversion> responseEntity =  new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,
                uriVariables
        );

         */

        ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,
                uriVariables
        );

        // JPA binding the json result to CurrencyConversion object??

        CurrencyConversion currencyConversion = responseEntity.getBody();

        Float totalCalculatedAmount = (float) (quantity * currencyConversion.getConversionMultiple());


        return new CurrencyConversion(currencyConversion.getId(), from, to, currencyConversion.getConversionMultiple(),quantity,
                quantity * currencyConversion.getConversionMultiple(),
                currencyConversion.getEnvironment() + " rest template");


    }
}
