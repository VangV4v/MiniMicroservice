package com.vang.apigateway.auth;


import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.vang.minimicroservice.common.AuthKey;
import org.vang.minimicroservice.common.NumberUtils;
import org.vang.minimicroservice.common.SecurityCommon;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtFilter implements WebFilter {

    private final JwtService jwtService;

    @Autowired
    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String token = getToken(exchange);
        if(!StringUtils.isBlank(token)) {

            if(jwtService.validateToken(token)) {

                String role = jwtService.extractRole(token);
                String username = jwtService.extractUsername(token);
                List<GrantedAuthority> listGrant = List.of(new SimpleGrantedAuthority(role));
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, listGrant);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authenticationToken));
            }
        }
        return chain.filter(exchange);
    }

    private String getToken(ServerWebExchange exchange) {

        String token = null;
        String authHeader = exchange.getRequest().getHeaders().getFirst(AuthKey.AUTHORIZATION);
        if(!StringUtils.isBlank(authHeader) && authHeader.startsWith(AuthKey.BEARER)) {

            token = authHeader.substring(NumberUtils.SEVEN);
        }
        return token;
    }
}