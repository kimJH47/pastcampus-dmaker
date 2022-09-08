package com.fastcampus.spring.dmaker.type;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DeveloperSkillType {
    BACK_END("백앤드 개발자"),
    FRONT_END("프론드앤드 개발자"),
    FULL_STACK("풀스택 개발자");

    private final String DESCRIPTION;

}
