package com.heem._01_limit_services.controller;

import com.heem._01_limit_services.bean.Limits;
import com.heem._01_limit_services.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {


    private Configuration configuration;

    @Autowired
    public LimitsController(Configuration theConfiguration) {
        configuration = theConfiguration;
    }

    @GetMapping("/limits")
    public Limits retrieveLimits() {
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
