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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.vang.minimicroservice.common.SecurityCommon;
import reactor.core.publisher.Mono;

import java.util.Arrays;

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

        String urlPermitAll [] = {"/api/v1/auth/customer/","/api/v1/auth/admin/", "/api/v1/auth/seller/"};
        String urlRoleCustomer [] = {"/api/v1/carts/**", "/api/v1/addresses/**", "/api/v1/orders/**"};
        String urlRoleAdmin [] = {"/api/v1/customers/**", "/api/v1/admins/**", "/api/v1/sellers/**"};
        String urlRoleSeller [] = {"/api/v1/brands/**", "/api/v1/categories/**", "/api/v1/products/**", "/api/v1/confirm-orders/**"};
        http.csrf(crsf -> crsf.disable());
        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("*"));
            configuration.setAllowedMethods(Arrays.asList("*"));
            configuration.setAllowedHeaders(Arrays.asList("*"));
            return configuration;
        }));
        http.httpBasic(httpBasicSpec -> httpBasicSpec.disable());
        http.authorizeExchange(auth ->
                auth.pathMatchers(urlPermitAll).permitAll()
                        .pathMatchers(urlRoleCustomer).hasAuthority(SecurityCommon.ROLE_CUSTOMER)
                        .pathMatchers(urlRoleAdmin).hasAuthority(SecurityCommon.ROLE_ADMIN)
                        .pathMatchers(urlRoleSeller).hasAuthority(SecurityCommon.ROLE_SELLER)
                        .anyExchange().authenticated()
                );
        http.addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }
}