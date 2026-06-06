package com.example.fincard.domain.quiz.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubmitQuizResponse {

    private final boolean correct;      // 정답 여부
    private final String answer;        // 정답
    private final String explanation;   // 해설
    private final int earnedPoint;      // 획득 포인트 (오답 시 0)
}