package com.example.fincard.domain.user.controller;

import com.example.fincard.domain.user.dto.request.OnboardingRequest;
import com.example.fincard.domain.user.dto.request.UpdateUserRequest;
import com.example.fincard.domain.user.dto.response.CoinResponse;
import com.example.fincard.domain.user.dto.response.UserMeResponse;
import com.example.fincard.domain.user.service.UserService;
import com.example.fincard.global.response.BaseResponse;
import com.example.fincard.global.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "사용자 API")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "내 정보 입력 (온보딩)", description = "현재 직업, 나이, 소득, 지역을 입력합니다.")
    @PostMapping("/me")
    public ResponseEntity<BaseResponse<Void>> onboarding(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody OnboardingRequest request) {
        userService.onboarding(userDetails.getUserId(), request);
        return ResponseEntity.ok(BaseResponse.success("온보딩 정보가 저장되었습니다.", null));
    }

    @Operation(summary = "내 정보 조회")
    @GetMapping("/me")
    public ResponseEntity<BaseResponse<UserMeResponse>> getMe(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(BaseResponse.success("내 정보 조회에 성공했습니다.", userService.getMe(userDetails.getUserId())));
    }

    @Operation(summary = "내 정보 수정", description = "직업, 나이, 소득, 지역을 수정합니다.")
    @PutMapping("/me")
    public ResponseEntity<BaseResponse<Void>> updateMe(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody UpdateUserRequest request) {
        userService.updateMe(userDetails.getUserId(), request);
        return ResponseEntity.ok(BaseResponse.success("내 정보가 수정되었습니다.", null));
    }

    @Operation(summary = "내 코인 조회")
    @GetMapping("/coin")
    public ResponseEntity<BaseResponse<CoinResponse>> getCoin(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(BaseResponse.success("코인 조회에 성공했습니다.", userService.getCoin(userDetails.getUserId())));
    }
}