package com.example.fincard.domain.quiz.service;

import com.example.fincard.domain.quiz.dto.request.CreateQuizRequest;
import com.example.fincard.domain.quiz.dto.request.SubmitQuizRequest;
import com.example.fincard.domain.quiz.dto.response.QuizListResponse;
import com.example.fincard.domain.quiz.dto.response.QuizResponse;
import com.example.fincard.domain.quiz.dto.response.SubmitQuizResponse;
import com.example.fincard.domain.quiz.entity.Quiz;
import com.example.fincard.domain.quiz.repository.QuizRepository;
import com.example.fincard.domain.user.entity.User;
import com.example.fincard.domain.user.repository.UserRepository;
import com.example.fincard.global.exception.CustomException;
import com.example.fincard.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createQuiz(CreateQuizRequest request) {
        Quiz quiz = Quiz.builder()
                .category(request.getCategory())
                .question(request.getQuestion())
                .optionA(request.getOptionA())
                .optionB(request.getOptionB())
                .optionC(request.getOptionC())
                .optionD(request.getOptionD())
                .answer(request.getAnswer())
                .explanation(request.getExplanation())
                .point(request.getPoint())
                .build();

        quizRepository.save(quiz);
    }

    @Transactional(readOnly = true)
    public QuizListResponse getQuizList() {
        List<QuizResponse> quizzes = quizRepository.findAll()
                .stream()
                .map(QuizResponse::new)
                .toList();

        return new QuizListResponse(quizzes.size(), quizzes);
    }

    @Transactional(readOnly = true)
    public QuizResponse getTodayQuiz() {
        Quiz quiz = quizRepository.findRandomQuiz()
                .orElseThrow(() -> new CustomException(ErrorCode.QUIZ_NOT_FOUND));
        return new QuizResponse(quiz);
    }

    @Transactional
    public SubmitQuizResponse submitQuiz(Long userId, SubmitQuizRequest request) {
        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new CustomException(ErrorCode.QUIZ_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        boolean correct = quiz.getAnswer().equals(request.getAnswer());
        int earnedPoint = correct ? quiz.getPoint() : 0;

        if (correct) {
            user.addCoin((long) earnedPoint);
        }

        return new SubmitQuizResponse(correct, quiz.getAnswer(), quiz.getExplanation(), earnedPoint);
    }
}