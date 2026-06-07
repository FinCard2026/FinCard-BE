package com.example.fincard.domain.auth.controller;

import com.example.fincard.domain.auth.dto.request.LoginRequest;
import com.example.fincard.domain.auth.dto.request.RefreshRequest;
import com.example.fincard.domain.auth.dto.request.SignupRequest;
import com.example.fincard.domain.auth.dto.response.TokenResponse;
import com.example.fincard.domain.auth.service.AuthService;
import com.example.fincard.global.response.BaseResponse;
import com.example.fincard.global.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "회원 계정 관련 API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<Void> signup(@Valid @RequestBody SignupRequest request) {
        authService.signup(request);
        return BaseResponse.success("회원가입이 완료되었습니다.", null);
    }

    @PostMapping("/login")
    public BaseResponse<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        return BaseResponse.success("로그인이 완료되었습니다.", authService.login(request));
    }

    @PostMapping("/logout")
    public BaseResponse<Void> logout(@AuthenticationPrincipal CustomUserDetails userDetails) {
        authService.logout(userDetails.getUserId());
        return BaseResponse.success("로그아웃이 완료되었습니다.", null);
    }

    @PostMapping("/refresh")
    public BaseResponse<TokenResponse> refresh(@Valid @RequestBody RefreshRequest request) {
        return BaseResponse.success("토큰이 갱신되었습니다.", authService.refresh(request.getRefreshToken()));
    }

    @DeleteMapping("/withdraw")
    public BaseResponse<Void> withdraw(@AuthenticationPrincipal CustomUserDetails userDetails) {
        authService.withdraw(userDetails.getUserId());
        return BaseResponse.success("회원탈퇴가 완료되었습니다.", null);
    }
}