package com.example.fincard.domain.user.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUserRequest {

    private String job;

    @Min(value = 1, message = "나이는 1 이상이어야 합니다.")
    private Integer age;

    @Min(value = 0, message = "소득은 0 이상이어야 합니다.")
    private Long income;

    private String region;
}
