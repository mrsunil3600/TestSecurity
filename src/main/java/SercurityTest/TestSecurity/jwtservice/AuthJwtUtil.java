package SercurityTest.TestSecurity.jwtservice;

import SercurityTest.TestSecurity.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class AuthJwtUtil {
    @Value("${jwt.secret.key}")
    private  String secretkey;
    private SecretKey secretKey(){
        return Keys.hmacShaKeyFor(secretkey.getBytes(StandardCharsets.UTF_8));
    }
    public  String createToken(UserEntity user){
    String token=Jwts.builder()
            .setSubject(user.getUsername())
            .claim("Id",user.getId())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis()+(1000*60*60)))
            .signWith(secretKey())
            .compact();
    return token;
    }

    public Claims extractToken(String token){
        return  Jwts.parserBuilder()
                .setSigningKey(secretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token){
        return  extractToken(token).getSubject();
    }
}
