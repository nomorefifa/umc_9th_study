package com.example.umc9th.domain.mission.dto.request;

import jakarta.validation.constraints.NotNull;

public class MissionRequest {

    public record ChallengeMissionDTO(
            @NotNull(message = "미션 ID는 필수입니다.")
            Long missionId
    ) {}
}