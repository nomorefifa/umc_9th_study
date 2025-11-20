package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberErrorCode;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.request.ReviewRequest;
import com.example.umc9th.domain.review.dto.response.ReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreErrorCode;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.umc9th.domain.review.entity.QReview.review;
import static com.example.umc9th.domain.store.entity.QStore.store;

/**
 * Review Service
 * BooleanBuilder를 사용하여 동적 WHERE 조건 생성
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    /**
     * 내가 작성한 리뷰 조회 (동적 필터링)
     *
     * @param memberId 회원 ID
     * @param storeName 가게 이름 (선택)
     * @param scoreRange 별점 범위 (선택: "5", "4", "3", "2", "1")
     * @return 필터링된 리뷰 목록
     */
    public List<ReviewResponse.MyReviewList> getMyReviews(Long memberId, String storeName, String scoreRange) {
        // 에러 검증 추가
        if (memberId == null || memberId <= 0) {
            throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
        }

        // BooleanBuilder로 동적 조건 생성
        BooleanBuilder builder = new BooleanBuilder();

        // 1. 가게명 필터 (포함 검색)
        if (storeName != null && !storeName.isBlank()) {
            builder.and(review.store.name.containsIgnoreCase(storeName));
        }

        // 2. 별점대 필터
        if (scoreRange != null && !scoreRange.isBlank()) {
            addScoreRangeCondition(builder, scoreRange);
        }

        // Repository에서 조회
        List<Review> reviews = reviewRepository.findMyReviewsWithFilters(memberId, builder);

        // 리뷰가 없는 경우 처리
        if (reviews.isEmpty()) {
            throw new GeneralException(GeneralErrorCode.REVIEW_NOT_FOUND);
        }

        // Entity -> DTO 변환
        return reviews.stream()
                .map(ReviewResponse.MyReviewList::from)
                .collect(Collectors.toList());
    }

    /**
     * 별점 범위 조건 추가
     *
     * @param builder BooleanBuilder
     * @param scoreRange 별점 범위 ("5", "4", "3", "2", "1")
     */
    private void addScoreRangeCondition(BooleanBuilder builder, String scoreRange) {
        switch (scoreRange) {
            case "5":
                // 5점: 5.0 이상
                builder.and(review.score.goe(5.0f));
                break;
            case "4":
                // 4점대: 4.0 이상 5.0 미만
                builder.and(review.score.goe(4.0f).and(review.score.lt(5.0f)));
                break;
            case "3":
                // 3점대: 3.0 이상 4.0 미만
                builder.and(review.score.goe(3.0f).and(review.score.lt(4.0f)));
                break;
            case "2":
                // 2점대: 2.0 이상 3.0 미만
                builder.and(review.score.goe(2.0f).and(review.score.lt(3.0f)));
                break;
            case "1":
                // 1점대: 1.0 이상 2.0 미만
                builder.and(review.score.goe(1.0f).and(review.score.lt(2.0f)));
                break;
            default:
                // 잘못된 범위는 무시
                break;
        }
    }

    /**
     * 가게에 리뷰 추가
     *
     * @param memberId 회원 ID (하드코딩: 1L)
     * @param dto 리뷰 추가 요청 DTO
     * @return 생성된 리뷰 정보
     */
    @Transactional
    public ReviewResponse.AddReviewDTO addReview(Long memberId, ReviewRequest.AddReviewDTO dto) {
        // 1. 회원 존재 여부 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 2. 가게 존재 여부 확인
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 3. Review 엔티티 생성
        Review review = ReviewConverter.toReview(dto, member, store);

        // 4. DB 저장
        Review savedReview = reviewRepository.save(review);

        // 5. 응답 DTO 변환 및 반환
        return ReviewConverter.toAddReviewDTO(savedReview);
    }
}
