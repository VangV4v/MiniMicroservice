package com.vang.authadminservice.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vang.minimicroservice.common.AuthKey;
import org.vang.minimicroservice.service.FieldNameCommon;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    private final AdminClientGrpc adminClientGrpc;

    @Autowired
    public JwtService(AdminClientGrpc adminClientGrpc) {
        this.adminClientGrpc = adminClientGrpc;
    }

    public String generateToken(String username) {

        Map<String, String> claims = new HashMap<>();
        Map<String, Object> response = adminClientGrpc.responseLoginAdmin(username);
        claims.put(FieldNameCommon.ROLE, response.get(FieldNameCommon.ROLE).toString());
        return createToken(username, claims);
    }

    private String createToken(String username, Map<String, String> claims) {

        return Jwts
                .builder()
                .signWith(getKey())
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + (60000 * 20)))
                .claims(claims)
                .compact();
}

    private Key getKey() {

        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(AuthKey.SECRET));
    }
}