package com.example.fincard.domain.quiz.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QuizListResponse {

    private final int total;
    private final List<QuizResponse> quizzes;
}