package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> findMyReviewsWithFilters(Long memberId, Predicate predicate);
}