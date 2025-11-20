package com.example.umc9th.domain.review.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(
            HttpStatus.CREATED,
            "REVIEW201_1",
            "리뷰가 성공적으로 등록되었습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}