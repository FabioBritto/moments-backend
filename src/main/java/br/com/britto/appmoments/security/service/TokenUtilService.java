package br.com.britto.appmoments.security.service;

import br.com.britto.appmoments.dto.login.LoginResponseDTO;
import br.com.britto.appmoments.model.Cliente;
import br.com.britto.appmoments.security.authuser.AuthUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class TokenUtilService {

    @Value("${JWT_SECRET:secret-key}")
    private String secret;

    private static final long TOKEN_EXPIRATION = 24 * 60 * 60 * 1000;

    private static final String ISSUER = "Britto";


    public LoginResponseDTO generateLoginToken(AuthUser authUser) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(authUser.getUsername())
                    .withExpiresAt(LocalDateTime.now().plusDays(TOKEN_EXPIRATION).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);
            return new LoginResponseDTO(token);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar Token: " + e.getMessage());
        }
    }

    public String validateLoginToken(String token) {
        System.out.println("TOKEN:" + token);
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Erro ao validar Token: " + e.getMessage());
        }
    }

}
