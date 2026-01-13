package br.com.britto.appmoments.security.service;

import br.com.britto.appmoments.dto.login.LoginResponseDTO;
import br.com.britto.appmoments.exception.TokenException;
import br.com.britto.appmoments.security.authuser.AuthUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class AccessTokenService {

    @Value("${JWT_SECRET:secret-key}")
    private String secret;

    private static final long TOKEN_EXPIRATION = 24 * 60 * 60 * 1000;
    private static final String ISSUER = "BRITTO";

    public String generateLoginToken(AuthUser authUser) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(authUser.getUsername())
                    .withExpiresAt(LocalDateTime.now().plusSeconds(TOKEN_EXPIRATION).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new TokenException("Erro ao gerar Token");
        }
    }

    public String validateLoginToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new TokenException("Erro ao validar Token");
        }
    }

}
