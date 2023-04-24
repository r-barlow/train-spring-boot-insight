package za.co.neslotech.spring.trainspringbootinsight.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
public class JwtAuthenticationService {

    @Value("${jwt.secret}")
    private String secret;

    public <T> T getClaim(final String token, final Function<Claims, T> resolver) {
        return resolver.apply(extractAllClaims(token));
    }

    public Claims extractAllClaims(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(final String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(final String token, final UserDetails userDetails) {
        return userDetails.getUsername()
                .equals(getUsername(token)) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(final String token) {
        return getClaim(token, Claims::getExpiration).before(new Date());
    }

    public String createToken(final UserDetails userDetails) {
        return createToken(new HashMap<>(), userDetails);
    }

    public String createToken(final Map<String, Object> claims, final UserDetails userDetails) {

        final var issued = new Date();
        final var expiration = new Date(issued.getTime() + TimeUnit.DAYS.toMillis(1));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issued)
                .setExpiration(expiration)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}
