package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /*
     * 멤버 id = 1 인 멤버 정보 조회
     * 실행되는 JPQL: SELECT m FROM Member m WHERE m.id = ?1
     * Optional<Member> findById(Long id);  // JpaRepository가 기본 제공
     */

    // 쿼리 어노테이션 사용
    @Query("select m from Member m where m.id = :memberId")
    Optional<Member> findMemberById(@Param("memberId") Long memberId);
}