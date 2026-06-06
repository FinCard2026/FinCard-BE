package com.example.fincard.domain.quiz.controller;

import com.example.fincard.domain.quiz.dto.request.CreateQuizRequest;
import com.example.fincard.domain.quiz.dto.response.QuizListResponse;
import com.example.fincard.domain.quiz.service.QuizService;
import com.example.fincard.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 관라자용
@Tag(name = "Admin - Quiz", description = "관리자 퀴즈 API")
@RestController
@RequestMapping("/api/admin/quiz")
@RequiredArgsConstructor
public class QuizAdminController {

    private final QuizService quizService;

    @Operation(summary = "퀴즈 등록 (관리자)")
    @PostMapping
    public ResponseEntity<BaseResponse<Void>> createQuiz(
            @Valid @RequestBody CreateQuizRequest request) {
        quizService.createQuiz(request);
        return ResponseEntity.ok(BaseResponse.success("퀴즈가 등록되었습니다.", null));
    }

    @Operation(summary = "퀴즈 목록 조회 (관리자)")
    @GetMapping
    public ResponseEntity<BaseResponse<QuizListResponse>> getQuizList() {
        return ResponseEntity.ok(BaseResponse.success("퀴즈 목록 조회에 성공했습니다.", quizService.getQuizList()));
    }
}