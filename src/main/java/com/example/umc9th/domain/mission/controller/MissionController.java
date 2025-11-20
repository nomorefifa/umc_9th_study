package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.dto.request.MissionRequest;
import com.example.umc9th.domain.mission.dto.response.MissionResponse;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    /**
     * 미션 도전하기 API
     * POST /missions/challenge
     */
    @PostMapping("/challenge")
    public ApiResponse<MissionResponse.ChallengeMissionDTO> challengeMission(
            @Valid @RequestBody MissionRequest.ChallengeMissionDTO dto
    ) {
        // 회원 ID는 1L로 하드코딩
        Long memberId = 1L;

        MissionResponse.ChallengeMissionDTO response =
                missionService.challengeMission(memberId, dto);

        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_CHALLENGE_SUCCESS,
                response
        );
    }
}