package com.example.umc9th.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class ReviewRequest {

    public record AddReviewDTO(
            @NotNull(message = "가게 ID는 필수입니다.")
            Long storeId,

            @NotBlank(message = "리뷰 제목은 필수입니다.")
            String title,

            @NotBlank(message = "리뷰 내용은 필수입니다.")
            String body,

            @NotNull(message = "별점은 필수입니다.")
            @Min(value = 1, message = "별점은 최소 1점입니다.")
            @Max(value = 5, message = "별점은 최대 5점입니다.")
            Float score
    ) {}
}