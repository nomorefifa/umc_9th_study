package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.response.ReviewResponse;
import com.example.umc9th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/my")
    public List<ReviewResponse> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String scoreRange
    ) {
        // 여기에 브레이크포인트! ⬅️
        List<ReviewResponse> result = reviewService.getMyReviews(memberId, storeName, scoreRange);
        return result;  // 여기에도 브레이크포인트! ⬅️
    }
}