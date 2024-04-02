package com.vang.authcustomerservice.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.vang.minimicroservice.common.AuthKey;
import org.vang.minimicroservice.service.FieldNameCommon;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    private final MyUserdetailService userdetailService;

    @Autowired
    public JwtService(MyUserdetailService userdetailService) {
        this.userdetailService = userdetailService;
    }

    public String generateToken(String username) {

        UserDetails userDetails = userdetailService.loadUserByUsername(username);
        String role = userDetails.getAuthorities().toString();
        Map<String, String> claims = new HashMap<>();
        claims.put(FieldNameCommon.ROLE, role);
        return createToken(username, claims);
    }

    public Claims getClaims(String token) {

        return Jwts
                .parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {

        return extractClaims(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {

        return extractUsername(token).equals(userDetails.getUsername()) && extractClaims(token, Claims::getExpiration).after(new Date());
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimReturn) {

        Claims claims = getClaims(token);
        return claimReturn.apply(claims);
    }

    private String createToken(String username, Map<String, String> claims) {

        return Jwts
                .builder()
                .claims(claims)
                .signWith(getKey())
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() * 200000))
                .compact();
    }

    private Key getKey() {

        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(AuthKey.SECRET));
    }

}