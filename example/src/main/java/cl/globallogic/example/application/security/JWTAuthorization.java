package cl.globallogic.example.application.security;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cl.globallogic.example.data.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTAuthorization  {

	public static final int JWT_EXPIRATION = 12 * 60 * 60 * 1000; //12 horas
	
	@Value("${jwt.security_secret}")
    private  String secretSecurity;
    
	
	public String generateToken(UserEntity user) {
		
		Date expiration = new Date(System.currentTimeMillis() + JWT_EXPIRATION );
        return Jwts.builder()
        		.setId(UUID.randomUUID().toString())
        		.setSubject(user.getEmail())
        		.setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secretSecurity).compact();
    }

}
