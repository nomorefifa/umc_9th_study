package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.request.ReviewRequest;
import com.example.umc9th.domain.review.dto.response.ReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;

public class ReviewConverter {

    // DTO -> Entity
    public static Review toReview(ReviewRequest.AddReviewDTO dto, Member member, Store store) {
        return Review.builder()
                .title(dto.title())
                .body(dto.body())
                .score(dto.score())
                .member(member)
                .store(store)
                .build();
    }

    // Entity -> DTO
    public static ReviewResponse.AddReviewDTO toAddReviewDTO(Review review) {
        return ReviewResponse.AddReviewDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .createdAt(review.getCreatedAt())
                .build();
    }
}