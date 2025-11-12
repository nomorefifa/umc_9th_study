package com.example.umc9th.global.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * QueryDSL 설정
 * JPAQueryFactory를 Bean으로 등록하여 영속성 컨텍스트와 연동
 */
@Configuration
@RequiredArgsConstructor
public class QueryDslConfig {

    @PersistenceContext
    private final EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}