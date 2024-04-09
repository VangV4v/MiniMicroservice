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

//    @Bean
//    public CorsConfigurationSource configurationSource() {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.addAllowedOrigin("*");
//        corsConfig.addAllowedMethod("*");
//        corsConfig.addAllowedHeader("*");
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig);
//        return source;
//    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {

        String urlPermitAll [] = {"/api/v1/auth/customer","/api/v1/auth/admin"};
        String urlRoleCustomer [] = {"/api/v1/brands/**"};
        String urlRoleAdmin [] = {"/api/v1/customers/**"};
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
                        .anyExchange().authenticated()
                );
        http.addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }
}