package com.vang.apigateway.configuation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguation {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {

        return authentication -> Mono.empty();
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {

        http.csrf(csrf -> csrf.disable());
        http.authorizeExchange(auth -> {
            auth.pathMatchers("/api/v1/auth-customer/**").permitAll()
                    .pathMatchers("/api/v1/brands/").hasRole("CUSTOMER")
                    .pathMatchers("/api/v1/customers/").permitAll()
                    .anyExchange().denyAll();
        });
        http.addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }
}