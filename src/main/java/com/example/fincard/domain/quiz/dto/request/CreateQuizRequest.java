package com.example.fincard.domain.quiz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateQuizRequest {

    @NotBlank(message = "카테고리를 입력해주세요.")
    private String category;

    @NotBlank(message = "문제를 입력해주세요.")
    private String question;

    @NotBlank(message = "보기 A를 입력해주세요.")
    private String optionA;

    @NotBlank(message = "보기 B를 입력해주세요.")
    private String optionB;

    @NotBlank(message = "보기 C를 입력해주세요.")
    private String optionC;

    @NotBlank(message = "보기 D를 입력해주세요.")
    private String optionD;

    @NotBlank(message = "정답을 입력해주세요.")
    @Pattern(regexp = "^[ABCD]$", message = "정답은 A, B, C, D 중 하나여야 합니다.")
    private String answer;

    @NotBlank(message = "해설을 입력해주세요.")
    private String explanation;

    @NotNull(message = "포인트를 입력해주세요.")
    @Positive(message = "포인트는 0보다 커야 합니다.")
    private Integer point;
}