package com.vang.authsellerservice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.vang.minimicroservice.common.AuthKey;
import org.vang.minimicroservice.common.SecurityCommon;
import org.vang.minimicroservice.service.FieldNameCommon;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    public String generateToken(String username) {

        Map<String, String> claims = new HashMap<String, String>();
        claims.put(FieldNameCommon.ROLE, SecurityCommon.ROLE_SELLER);
        return createToken(username, claims);
    }

    private String createToken(String username, Map<String, String> claims) {
        return
                Jwts
                        .builder()
                        .subject(username)
                        .claims(claims)
                        .expiration(new Date(System.currentTimeMillis() + (60000 * 20)))
                        .signWith(getKey())
                        .compact();
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(AuthKey.SECRET));
    }
}