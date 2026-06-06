package com.example.fincard.domain.quiz.dto.response;

import com.example.fincard.domain.quiz.entity.Quiz;
import lombok.Getter;

@Getter
public class QuizResponse {

    private final Long id;
    private final String category;
    private final String question;
    private final String optionA;
    private final String optionB;
    private final String optionC;
    private final String optionD;
    private final int point;

    public QuizResponse(Quiz quiz) {
        this.id = quiz.getId();
        this.category = quiz.getCategory();
        this.question = quiz.getQuestion();
        this.optionA = quiz.getOptionA();
        this.optionB = quiz.getOptionB();
        this.optionC = quiz.getOptionC();
        this.optionD = quiz.getOptionD();
        this.point = quiz.getPoint();
    }
}