package com.aithmetic.gateway.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Configuration
public class GatewayConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayConfig.class);

    @Autowired
    private Environment environment;

    @Bean
    public RouteLocator customRouteLocator( RouteLocatorBuilder routeLocatorBuilder){

        String customerServiceUrl = environment.getProperty("customer.service.url");
        String productServiceUrl = environment.getProperty("product.service.url");
        String orderServiceUrl = environment.getProperty("order.service.url");

        LOGGER.info("Configuring routes for customer-service: {}", customerServiceUrl);
        LOGGER.info("Configuring routes for product-service: {}", productServiceUrl);
        LOGGER.info("Configuring routes for order-service: {}", orderServiceUrl);

        return routeLocatorBuilder.routes()
                .route("customer-service", r->r
                        .path("/customer/**")
                        .uri(customerServiceUrl))
                .route("product-service", r->r
                        .path("/product/**")
                        .uri(productServiceUrl))
                .route("order-service", r->r
                        .path("/order/**")
                        .uri(orderServiceUrl))
                .build();

    }
}
