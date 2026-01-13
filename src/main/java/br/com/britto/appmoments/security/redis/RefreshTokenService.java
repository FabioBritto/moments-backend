package br.com.britto.appmoments.security.redis;

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

    public Optional<Integer> validate(String token) {
        String value = redisTemplate.opsForValue().get(PREFIX + token);

        if(value == null) return Optional.empty();

        return Optional.of(Integer.parseInt(value));
    }

    public void revoke(String token) {
        redisTemplate.delete(PREFIX + token);
    }
}
