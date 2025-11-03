package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    /**
     * 커서 기반 미션 조회
     * @param memberId 회원 ID
     * @param cursor 커서 값 (null이면 첫 페이지)
     */
    public List<MemberMission> getMemberMissions(Long memberId, Long cursor) {
        // cursor가 null이면 Long.MAX_VALUE 사용 (첫 페이지를 의미)
        Long cursorValue = (cursor != null) ? cursor : Long.MAX_VALUE;

        return memberMissionRepository
                .findMemberMissions(memberId, cursorValue)
                .stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    /**
     * 도전 가능한 미션 목록 조회 (커서 기반 페이징)
     *
     * @param regionId 지역 ID
     * @param memberId 회원 ID (이미 도전중인 미션 제외용)
     * @param cursor 커서 값 (null이면 첫 페이지)
     * @return 미션 목록 (최대 10개)
     */
    public List<Mission> getAvailableMissions(Long regionId, Long memberId, Long cursor) {
        // cursor가 null이면 Long.MAX_VALUE 사용 (첫 페이지)
        Long cursorValue = (cursor != null) ? cursor : Long.MAX_VALUE;

        return missionRepository
                .findAvailableMissions(regionId, memberId, cursorValue)
                .stream()
                .limit(10)  // 최대 10개
                .collect(Collectors.toList());
    }
}