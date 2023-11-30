package com.aithmetic.gateway.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class GatewayConfig {

    @Autowired
    private Environment environment;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){

        String productServiceUrl = environment.getProperty("product.service.url");

        return routeLocatorBuilder.routes()
                .route("product-service", r->r
                        .path("/product/**")
                        .uri(productServiceUrl))
                .build();
    }
}
