package com.fastcampus.spring.dmaker.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.fastcampus.spring.dmaker.constant.DMakerConstant.MAX_JUNIOR_EXPERIENCE_YEARS;
import static com.fastcampus.spring.dmaker.constant.DMakerConstant.MIN_SENIOR_EXPERIENCE_YEARS;

@RequiredArgsConstructor
@Getter
public enum DeveloperLevel {
    NEW("신입 개발자", 0,0),
    JUNIOR("주니어 개발자",1, MAX_JUNIOR_EXPERIENCE_YEARS),
    JUNGIOR("중니어 개발자",MAX_JUNIOR_EXPERIENCE_YEARS+1,MIN_SENIOR_EXPERIENCE_YEARS-1),
    SENIOR("시니어 개발자",MIN_SENIOR_EXPERIENCE_YEARS,70);
    private final String DESCRIPTION;
    private final Integer maxExperienceYears;
    private final Integer minExperienceYears;
}
