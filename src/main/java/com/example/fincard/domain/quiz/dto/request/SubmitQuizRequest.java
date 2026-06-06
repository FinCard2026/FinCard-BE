package com.example.fincard.domain.quiz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubmitQuizRequest {

    @NotNull(message = "퀴즈 ID를 입력해주세요.")
    private Long quizId;

    @NotBlank(message = "답안을 입력해주세요.")
    @Pattern(regexp = "^[ABCD]$", message = "답안은 A, B, C, D 중 하나여야 합니다.")
    private String answer;
}