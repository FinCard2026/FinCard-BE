package com.example.fincard.domain.user.service;

import com.example.fincard.domain.user.dto.request.OnboardingRequest;
import com.example.fincard.domain.user.dto.request.UpdateUserRequest;
import com.example.fincard.domain.user.dto.response.CoinResponse;
import com.example.fincard.domain.user.dto.response.UserMeResponse;
import com.example.fincard.domain.user.entity.User;
import com.example.fincard.domain.user.repository.UserRepository;
import com.example.fincard.global.exception.CustomException;
import com.example.fincard.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void onboarding(Long userId, OnboardingRequest request) {
        User user = findUser(userId);
        user.onboarding(request.getJob(), request.getAge(), request.getIncome(), request.getRegion());
    }

    @Transactional(readOnly = true)
    public UserMeResponse getMe(Long userId) {
        return new UserMeResponse(findUser(userId));
    }

    @Transactional
    public void updateMe(Long userId, UpdateUserRequest request) {
        User user = findUser(userId);
        user.update(request.getJob(), request.getAge(), request.getIncome(), request.getRegion());
    }

    @Transactional(readOnly = true)
    public CoinResponse getCoin(Long userId) {
        return new CoinResponse(findUser(userId).getCoin());
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}