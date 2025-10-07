package com.example.umc9th.domain.foodcategory.entity;

import com.example.umc9th.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String name;
    //음식 카테고리는 보통 수정/삭제를 잘 하지 않으므로 양방향 매핑 안함
}