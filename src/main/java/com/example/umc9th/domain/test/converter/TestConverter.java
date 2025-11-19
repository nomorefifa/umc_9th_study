package com.example.umc9th.domain.test.converter;

import com.example.umc9th.domain.test.dto.res.TestResDto;

public class TestConverter {
    public static TestResDto.TestDto toTestingDTO(String testing) {
        return TestResDto.TestDto.builder()
                .testing(testing)
                .build();
    }
}
