package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 현재 선택된 지역에서 도전 가능한 미션 목록
    @Query("select m from Mission m " +
            "join fetch m.store s " +
            "where s.region.id = :regionId " +
            "and m.id < :cursor " +
            "and m.id not in (" +
            "    select mm.mission.id " +
            "    from MemberMission mm " +
            "    where mm.member.id = :memberId" +
            ") " +
            "order by m.id desc")
    List<Mission> findAvailableMissions(
            @Param("regionId") Long regionId,
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor
    );
}
