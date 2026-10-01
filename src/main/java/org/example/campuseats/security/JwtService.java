package org.example.campuseats.security;

import lombok.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expirationMs}")
    private Long expirationMs;
    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    public String generateToken(String username){
        return Jwts.builder;)()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date (System.currentTimeMillis()+expirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public<T> extractClaim(String token, Function<Vlaims, T> resolver){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJwts(token)
                .getBody();
        return resolver.apply(claims);
    }
    public boolean isTokenValid(String token, String username){
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token){
        Date expiration = extractClaim(token, claims::getExpiration);
        return expiration.before(new Date());
    }
    public Long getExpirationMs(){
        return expirationMs;
    }
}
