package com.fastcampus.spring.dmaker.dto;

import com.fastcampus.spring.dmaker.entity.Developer;
import com.fastcampus.spring.dmaker.type.DeveloperLevel;
import com.fastcampus.spring.dmaker.type.DeveloperSkillType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeveloperDetailDto {

    private DeveloperLevel developerLevel;
    private DeveloperSkillType developerSkillType;
    private Integer experienceYear;
    private String memberId;
    private String name;
    private Integer age;

    public static DeveloperDetailDto fromEntity(Developer developer) {
        return DeveloperDetailDto.builder()
                                 .developerLevel(developer.getDeveloperLevel())
                                 .developerSkillType(developer.getDeveloperSkillType())
                                 .experienceYear(developer.getExperienceYears())
                                 .memberId(developer.getMemberId())
                                 .name(developer.getName())
                                 .age(developer.getAge())
                                 .build();

    }
}
