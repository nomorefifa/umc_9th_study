package com.example.umc9th.domain.review.dto.response;

import com.example.umc9th.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 리뷰 응답 DTO
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    private Long reviewId;
    private String title;
    private String body;
    private Float score;
    private String storeName;
    private String memberName;
    private LocalDateTime createdAt;

    /**
     * Entity -> DTO 변환
     */
    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .title(review.getTitle())
                .body(review.getBody())
                .score(review.getScore())
                .storeName(review.getStore().getName())
                .memberName(review.getMember().getName())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
