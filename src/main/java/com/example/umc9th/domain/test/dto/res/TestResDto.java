package com.example.umc9th.domain.test.dto.res;

import lombok.Builder;
import lombok.Getter;

public class TestResDto {

    @Builder
    @Getter
    public static class TestDto {
        private String testing;
    }
}
