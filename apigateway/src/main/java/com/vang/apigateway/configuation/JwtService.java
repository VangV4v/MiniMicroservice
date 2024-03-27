package com.vang.apigateway.configuation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.vang.minimicroservice.common.AuthKey;
import org.vang.minimicroservice.service.FieldNameCommon;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    public boolean isValidateToken(String token) {

        return extractClaim(token, Claims::getExpiration).after(new Date());
    }

    public String extractRole(String token) {

        Claims claims = getClaim(token);
        return claims.get(FieldNameCommon.ROLE).toString();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <R> R extractClaim(String token, Function<Claims, R> claimsReturn) {

        Claims claims = getClaim(token);
        return claimsReturn.apply(claims);
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