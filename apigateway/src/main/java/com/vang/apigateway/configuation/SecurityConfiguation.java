package com.vang.apigateway.configuation;

import com.vang.apigateway.auth.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.vang.minimicroservice.common.SecurityCommon;
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

        String urlPermitAll [] = {"/api/v1/auth-customer/**"};
        String urlRoleCustomer [] = {"/api/v1/customers/**","/api/v1/brands/**"};
        http.csrf(crsf -> crsf.disable());
        http.httpBasic(httpBasicSpec -> httpBasicSpec.disable());
        http.authorizeExchange(auth ->
                auth.pathMatchers(urlPermitAll).permitAll()
                        .pathMatchers(urlRoleCustomer).hasAuthority(SecurityCommon.ROLE_CUSTOMER)
                        .anyExchange().authenticated()
                );
        http.addFilterBefore(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }
}