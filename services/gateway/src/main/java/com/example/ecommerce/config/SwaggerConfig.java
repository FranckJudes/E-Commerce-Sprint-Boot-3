package com.example.ecommerce.config;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public List<RouteDefinition> routeDefinitions(RouteDefinitionLocator locator) {
        List<RouteDefinition> definitions = new ArrayList<>();
        locator.getRouteDefinitions().subscribe(definitions::add);
        return definitions;
    }
}