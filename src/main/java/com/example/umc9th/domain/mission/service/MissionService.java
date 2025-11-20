package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberErrorCode;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.request.MissionRequest;
import com.example.umc9th.domain.mission.dto.response.MissionResponse;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.exception.MissionErrorCode;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    /**
     * 미션 도전하기
     *
     * @param memberId 회원 ID (하드코딩: 1L)
     * @param dto 미션 도전 요청 DTO
     * @return 생성된 도전 미션 정보
     */
    @Transactional
    public MissionResponse.ChallengeMissionDTO challengeMission(
            Long memberId,
            MissionRequest.ChallengeMissionDTO dto
    ) {
        // 1. 회원 존재 여부 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 2. 미션 존재 여부 확인
        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        // 3. 이미 도전 중인 미션인지 확인
        memberMissionRepository.findByMemberIdAndMissionId(memberId, dto.missionId())
                .ifPresent(mm -> {
                    throw new MissionException(MissionErrorCode.ALREADY_CHALLENGING);
                });

        // 4. MemberMission 엔티티 생성
        MemberMission memberMission = MissionConverter.toMemberMission(member, mission);

        // 5. DB 저장
        MemberMission savedMemberMission = memberMissionRepository.save(memberMission);

        // 6. 응답 DTO 변환 및 반환
        return MissionConverter.toChallengeMissionDTO(savedMemberMission);
    }
}