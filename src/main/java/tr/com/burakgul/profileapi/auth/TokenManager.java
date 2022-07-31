package tr.com.burakgul.profileapi.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Component
public class TokenManager {

    @Value("${jwt.secret.key:verySecretKey}")
    private String key;

    @Value("${jwt.token.expire.minute:5}")
    private Integer expireMinute;

    public String generateToken(String username){
        long timeMillis = System.currentTimeMillis();

        return Jwts.builder()
                .setSubject(username)
                .setIssuer("profile-api")
                .setIssuedAt(new Date(timeMillis))
                .setExpiration(new Date(timeMillis + this.expireMinute*60*1000))
                .signWith(SignatureAlgorithm.HS512, this.key)
                .compact();
    }

    public Boolean hasTokenValid(String token){
        return this.getUserFromToken(token) != null && this.hasTokenNotExpire(token);
    }

    public String getUserFromToken(String token){
        Claims claims = this.parseToken(token);
        return claims.getSubject();
    }

    private Boolean hasTokenNotExpire(String token){
        Claims claims = this.parseToken(token);
        Date now = new Date(System.currentTimeMillis());
        return claims.getExpiration().after(now);
    }

    private Claims parseToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(this.key)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "Gönderilen token bu sunucu tarafından imzalanmış bir token değildir.");
        }
    }
}
