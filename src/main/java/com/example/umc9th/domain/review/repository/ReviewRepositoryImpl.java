package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.umc9th.domain.member.entity.QMember.member;
import static com.example.umc9th.domain.review.entity.QReview.review;
import static com.example.umc9th.domain.store.entity.QStore.store;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> findMyReviewsWithFilters(Long memberId, Predicate predicate) {
        return queryFactory
                .selectFrom(review)                          // SELECT * FROM review
                .leftJoin(review.member, member).fetchJoin() // LEFT JOIN member (N+1 방지)
                .leftJoin(review.store, store).fetchJoin()   // LEFT JOIN store (N+1 방지)
                .where(
                        review.member.id.eq(memberId),           // WHERE member_id = ?
                        predicate                                 // AND 동적 조건
                )
                .orderBy(review.createdAt.desc())            // ORDER BY created_at DESC
                .fetch();
    }
}