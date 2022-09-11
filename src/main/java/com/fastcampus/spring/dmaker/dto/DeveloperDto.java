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
public class DeveloperDto {

    private DeveloperLevel developerLevel;
    private DeveloperSkillType developerSkillType;
    private String memberId;

    public static DeveloperDto fromEntity(Developer developer) {
        return DeveloperDto.builder()
                           .developerLevel(developer.getDeveloperLevel())
                           .developerSkillType(developer.getDeveloperSkillType())
                           .memberId(developer.getMemberId())
                           .build();

    }
}
