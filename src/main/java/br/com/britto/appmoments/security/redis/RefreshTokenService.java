package br.com.britto.appmoments.security.redis;

import br.com.britto.appmoments.dto.login.RefreshTokenDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Component
public class RefreshTokenService {

    private static final String PREFIX = "REFRESH_TOKEN:";

    private final RedisTemplate<String, String> redisTemplate;

    public RefreshTokenService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String create(Integer clienteId) {
        String token = UUID.randomUUID().toString();

        redisTemplate.opsForValue().set(PREFIX + token, clienteId.toString(), Duration.ofDays(3));
        return token;
    }

    public Optional<Integer> validate(RefreshTokenDTO token) {
        System.out.println("TOKEN QUE CHEGOU: " + token);
        System.out.println("TOKEN COMPLETO COM PREFIXO: " + PREFIX + token.refreshToken());
        String value = redisTemplate.opsForValue().get(PREFIX + token.refreshToken());

        System.out.println("VALUE :" + value);
        if(value == null) return Optional.empty();

        return Optional.of(Integer.parseInt(value));
    }

    public void revoke(RefreshTokenDTO token) {
        System.out.println("TOKEN QUE CHEGOU: " + token.refreshToken());
        System.out.println("TOKEN COMPLETO COM PREFIXO: " + PREFIX + token.refreshToken());
        redisTemplate.delete(PREFIX + token.refreshToken());
    }
}
