package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 이미 도전 중인 미션인지 확인
    @Query("SELECT mm FROM MemberMission mm " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.mission.id = :missionId")
    Optional<MemberMission> findByMemberIdAndMissionId(
            @Param("memberId") Long memberId,
            @Param("missionId") Long missionId
    );
}