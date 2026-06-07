package com.example.fincard.global.jwt;

import com.example.fincard.global.exception.CustomException;
import com.example.fincard.global.exception.ErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    private final SecretKey secretKey;
    private final long accessTokenExpiry;
    private final long refreshTokenExpiry;

    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-expiration}") long accessTokenExpiry,
            @Value("${jwt.refresh-token-expiration}") long refreshTokenExpiry) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.accessTokenExpiry = accessTokenExpiry;
        this.refreshTokenExpiry = refreshTokenExpiry;
    }

    public String createAccessToken(Long userId) {
        return buildToken(userId, accessTokenExpiry);
    }

    public String createRefreshToken(Long userId) {
        return buildToken(userId, refreshTokenExpiry);
    }

    public Long getUserId(String token) {
        return Long.parseLong(getClaims(token).getSubject());
    }

    public long getRefreshTokenExpiry() {
        return refreshTokenExpiry;
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (CustomException e) {
            return false;
        }
    }

    private String buildToken(Long userId, long expiry) {
        return Jwts.builder()
                .subject(String.valueOf(userId))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(secretKey)
                .compact();
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().verifyWith(secretKey).build()
                    .parseSignedClaims(token).getPayload();
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.EXPIRED_TOKEN);
        } catch (JwtException e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }
}