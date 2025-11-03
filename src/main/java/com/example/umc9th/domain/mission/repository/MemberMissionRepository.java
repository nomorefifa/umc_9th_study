package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 내가 진행중, 진행 완료한 미션 목록
    @Query("select mm from MemberMission mm " +
            "join fetch mm.mission m " +
            "where mm.member.id = :memberId " +
            "and mm.id < :cursor " +
            "order by case when mm.status = 'CHALLENGING' then 1 else 2 end, mm.id desc")
    List<MemberMission> findMemberMissions(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor
    );
}
