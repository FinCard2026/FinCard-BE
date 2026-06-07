package com.example.fincard.domain.auth.service;

import com.example.fincard.domain.auth.dto.request.LoginRequest;
import com.example.fincard.domain.auth.dto.request.SignupRequest;
import com.example.fincard.domain.auth.dto.response.TokenResponse;
import com.example.fincard.domain.user.entity.User;
import com.example.fincard.domain.user.repository.UserRepository;
import com.example.fincard.global.exception.CustomException;
import com.example.fincard.global.exception.ErrorCode;
import com.example.fincard.global.jwt.JwtProvider;
import com.example.fincard.global.redis.RedisTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RedisTokenService redisTokenService;

    @Transactional
    public void signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new CustomException(ErrorCode.DUPLICATE_PHONE);
        }

        userRepository.save(User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .build());
    }

    @Transactional
    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        return issueTokens(user.getId());
    }

    public void logout(Long userId) {
        redisTokenService.deleteRefreshToken(userId);
    }

    @Transactional
    public TokenResponse refresh(String refreshToken) {
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        Long userId = jwtProvider.getUserId(refreshToken);
        String stored = redisTokenService.getRefreshToken(userId);

        if (stored == null || !stored.equals(refreshToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        return issueTokens(userId);
    }

    @Transactional
    public void withdraw(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        redisTokenService.deleteRefreshToken(userId);
        userRepository.hardDeleteById(userId);
    }

    private TokenResponse issueTokens(Long userId) {
        String accessToken = jwtProvider.createAccessToken(userId);
        String refreshToken = jwtProvider.createRefreshToken(userId);
        redisTokenService.saveRefreshToken(userId, refreshToken, jwtProvider.getRefreshTokenExpiry());
        return new TokenResponse(accessToken, refreshToken);
    }
}