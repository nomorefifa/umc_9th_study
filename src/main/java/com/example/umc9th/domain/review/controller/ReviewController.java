package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.response.ReviewResponse;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/my")
    public ApiResponse<List<ReviewResponse.MyReviewList>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String scoreRange
    ) {
        List<ReviewResponse.MyReviewList> result = reviewService.getMyReviews(memberId, storeName, scoreRange);

        // 성공 응답 통일
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}