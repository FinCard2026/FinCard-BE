package com.example.fincard.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OnboardingRequest {

    @NotBlank(message = "직업을 입력해주세요.")
    private String job;

    @NotBlank(message = "나이대를 입력해주세요.")
    private String ageRange;

    @NotBlank(message = "소득 구간을 입력해주세요.")
    private String incomeRange;

    @NotBlank(message = "지역을 입력해주세요.")
    private String region;

    @NotEmpty(message = "관심 분야를 1개 이상 선택해주세요.")
    private List<String> interests;
}
