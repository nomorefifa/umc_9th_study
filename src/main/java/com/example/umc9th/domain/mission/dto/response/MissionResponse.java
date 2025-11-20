package com.example.umc9th.domain.mission.dto.response;

import com.example.umc9th.global.common.enums.MissionStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionResponse {

    @Builder
    public record ChallengeMissionDTO(
            Long memberMissionId,
            Long missionId,
            String storeName,
            String missionSpec,
            Integer reward,
            LocalDate deadline,
            MissionStatus status,
            LocalDateTime createdAt
    ) {}
}