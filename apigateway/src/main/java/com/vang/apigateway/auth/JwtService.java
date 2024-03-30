package com.vang.apigateway.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.vang.minimicroservice.common.AuthKey;
import org.vang.minimicroservice.service.FieldNameCommon;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    public boolean validateToken(String token) {

        return extractClaim(token, Claims::getExpiration).after(new Date());
    }

    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {

        Claims claims = getClaim(token);
        return claims.get(FieldNameCommon.ROLE).toString();
    }

    private <R> R extractClaim(String token, Function<Claims, R> claimReturn) {

        Claims claims = getClaim(token);
        return claimReturn.apply(claims);
    }

    private Claims getClaim(String token) {

        return Jwts
                .parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getKey() {

        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(AuthKey.SECRET));
    }
}