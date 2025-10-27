package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // 리뷰 작성 쿼리
    @Transactional
    public Review createReview(Member member, Store store, String title, String content, Float rate) {
        Review review = Review.builder()
                .member(member)
                .store(store)
                .title(title)      // 제목 추가 (엔티티에 있음)
                .body(content)     // SQL의 content -> body
                .score(rate)       // SQL의 rate -> score
                .build();

        // JPA의 save() 메서드가 자동으로 INSERT 쿼리 생성
        // created_at, updated_at은 자동으로 설정됨
        return reviewRepository.save(review);
    }
}