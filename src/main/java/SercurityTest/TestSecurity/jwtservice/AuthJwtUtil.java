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
  
}
