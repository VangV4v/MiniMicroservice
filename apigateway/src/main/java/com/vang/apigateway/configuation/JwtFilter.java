package com.vang.apigateway.configuation;

import org.apache.commons.lang.StringUtils;
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
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtFilter implements WebFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String token = getToken(exchange);
        if(!StringUtils.isBlank(token)) {

            if(jwtService.isValidateToken(token)) {

                String username = jwtService.extractUsername(token);
                List<GrantedAuthority> listGrant = List.of(new SimpleGrantedAuthority(jwtService.extractRole(token)));
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, listGrant);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authenticationToken));
            }
        }
        return chain.filter(exchange);
    }

    private String getToken(ServerWebExchange exchange) {

        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if(!StringUtils.isBlank(authHeader) && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            return token;
        }
        return null;
    }
}