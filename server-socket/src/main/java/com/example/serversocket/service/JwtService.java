package com.example.serversocket.service;

import com.example.serversocket.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("${SECRET_KEY}")
    private String SECRET_KEY;
    final String TOKEN = "Token: ";

    public String generateToken(User user) {
        String jwt = Jwts
                .builder()
                .setSubject(user.getId()+" "+user.getFullName())
                .setIssuedAt(new Date())
                .setExpiration( new Date(new Date().getTime() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
        return jwt;
    }
    public boolean validateJwtToken(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
            Date endDate = claims.getExpiration();
            if(endDate==null) return false;
            return endDate.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }
}
