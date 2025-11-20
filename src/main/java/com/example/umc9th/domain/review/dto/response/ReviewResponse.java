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
public class ReviewResponse {

    /**
     * 내 리뷰 목록 조회 응답
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewList {

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
        public static MyReviewList from(Review review) {
            return MyReviewList.builder()
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

    // 기존 ReviewResponse 클래스에 아래 레코드 추가
    @Builder
    public record AddReviewDTO(
            Long reviewId,
            Long storeId,
            String storeName,
            LocalDateTime createdAt
    ) {}
}
