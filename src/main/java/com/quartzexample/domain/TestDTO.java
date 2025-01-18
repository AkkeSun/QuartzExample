package com.quartzexample.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TestDTO {

    private String name;

    @Builder
    public TestDTO(String name) {
        this.name = name;
    }
}
