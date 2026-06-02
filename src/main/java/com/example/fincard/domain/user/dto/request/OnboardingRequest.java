package com.example.fincard.domain.user.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OnboardingRequest {

    @NotBlank(message = "직업을 입력해주세요.")
    private String job;

    @NotNull(message = "나이를 입력해주세요.")
    @Min(value = 1, message = "나이는 1 이상이어야 합니다.")
    private Integer age;

    @NotNull(message = "소득을 입력해주세요.")
    @Min(value = 0, message = "소득은 0 이상이어야 합니다.")
    private Long income;

    @NotBlank(message = "지역을 입력해주세요.")
    private String region;
}
