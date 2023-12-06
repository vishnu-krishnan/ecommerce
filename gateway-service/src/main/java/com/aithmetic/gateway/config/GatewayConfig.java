package com.aithmetic.gateway.config;


import org.jetbrains.annotations.NotNull;
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
    public RouteLocator customRouteLocator(@NotNull RouteLocatorBuilder routeLocatorBuilder){

        String customerServiceUrl = environment.getProperty("customer.service.url");
        String productServiceUrl = environment.getProperty("product.service.url");
        String orderServiceUrl = environment.getProperty("order.service.url");

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
