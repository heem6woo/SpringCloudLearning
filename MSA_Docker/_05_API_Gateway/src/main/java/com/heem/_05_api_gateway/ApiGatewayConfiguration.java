package com.heem._05_api_gateway;


import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    // Customize route
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

        /*
        Function<PredicateSpec, Buildable<Route>> routeFunction
                = p -> p.path("/get")
                        .filters(f ->
                                f.addRequestHeader("MyHeader", "MyUri")
                                 .addRequestParameter("Param", "MyValue")
                        )

                        .uri("http://httpbin.org:80");

         */
        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f ->
                                f.addRequestHeader("MyHeader", "MyUri")
                                        .addRequestParameter("Param", "MyValue")
                        )

                        .uri("http://httpbin.org:80")) //routeFunction
                // simply the path: CURRENCY-EXCHANGE/currency-exchange/from/{from}/to/{to}
                //                  currency-exchange/from/{from}/to/{to}
                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange")) // currency-exchange or CURRENCY-EXCHANGE
                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion")) // currency-conversion or CURRENCY-CONVERSION
                .route(p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion")) // currency-conversion or CURRENCY-CONVERSION
                .route(p -> p.path("/currency-conversion-new/**")
                        .filters(f -> f.rewritePath(
                                "/currency-conversion-new/(?<segment>.*)",
                                "/currency-conversion-feign/${segment}"
                        ))
                        .uri("lb://currency-conversion")) // currency-exchange or CURRENCY-EXCHANGE
                .build();

    }
}
