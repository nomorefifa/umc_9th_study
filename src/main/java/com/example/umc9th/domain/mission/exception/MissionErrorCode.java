package com.example.umc9th.domain.mission.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "해당 미션을 찾을 수 없습니다."
    ),

    ALREADY_CHALLENGING(
            HttpStatus.CONFLICT,
            "MISSION409_1",
            "이미 도전 중인 미션입니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}