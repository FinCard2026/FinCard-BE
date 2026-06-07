package com.example.fincard.domain.quiz.controller;

import com.example.fincard.domain.quiz.dto.request.SubmitQuizRequest;
import com.example.fincard.domain.quiz.dto.response.QuizResponse;
import com.example.fincard.domain.quiz.dto.response.SubmitQuizResponse;
import com.example.fincard.domain.quiz.service.QuizService;
import com.example.fincard.global.response.BaseResponse;
import com.example.fincard.global.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


// 사용자용
@Tag(name = "Quiz", description = "퀴즈 API")
@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @Operation(summary = "오늘의 퀴즈 조회", description = "랜덤으로 퀴즈 1개를 조회합니다.")
    @GetMapping("/today")
    public ResponseEntity<BaseResponse<QuizResponse>> getTodayQuiz() {
        return ResponseEntity.ok(BaseResponse.success("퀴즈 조회에 성공했습니다.", quizService.getTodayQuiz()));
    }

    @Operation(summary = "퀴즈 답안 제출", description = "퀴즈 답안을 제출하고 정답 여부 및 해설을 반환합니다. 정답 시 코인이 지급됩니다.")
    @PostMapping("/submit")
    public ResponseEntity<BaseResponse<SubmitQuizResponse>> submitQuiz(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody SubmitQuizRequest request) {
        return ResponseEntity.ok(BaseResponse.success("답안이 제출되었습니다.", quizService.submitQuiz(userDetails.getUserId(), request)));
    }
}