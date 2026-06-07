package com.example.fincard.global.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisTokenService {

    private static final String REFRESH_TOKEN_PREFIX = "RT:";

    private final StringRedisTemplate redisTemplate;

    public void saveRefreshToken(Long userId, String refreshToken, long expiryMs) {
        redisTemplate.opsForValue()
                .set(REFRESH_TOKEN_PREFIX + userId, refreshToken, expiryMs, TimeUnit.MILLISECONDS);
    }

    public String getRefreshToken(Long userId) {
        return redisTemplate.opsForValue().get(REFRESH_TOKEN_PREFIX + userId);
    }

    public void deleteRefreshToken(Long userId) {
        redisTemplate.delete(REFRESH_TOKEN_PREFIX + userId);
    }
}