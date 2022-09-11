package com.fastcampus.spring.dmaker.dto;


import com.fastcampus.spring.dmaker.entity.Developer;
import com.fastcampus.spring.dmaker.type.DeveloperLevel;
import com.fastcampus.spring.dmaker.type.DeveloperSkillType;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateDeveloper {


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {

        @NotNull
        private DeveloperLevel developerLevel;
        @NotNull
        private DeveloperSkillType developerSkillType;
        @NotNull
        @Min(0)
        @Max(20)
        private Integer experienceYear;
        @NotNull
        @Size(min = 3, max = 50, message = "memberId size must 3~ 50")
        private String memberId;
        @NotNull
        @Size(min = 3, max = 20, message = "memberId size must 3~ 20")
        private String name;
        @Min(18)
        private Integer age;

        public Developer toEntity() {
            return Developer.builder()
                            .experienceYears(this.experienceYear)
                            .developerLevel(this.developerLevel)
                            .developerSkillType(this.developerSkillType)
                            .memberId(this.memberId)
                            .name(this.name)
                            .age(this.age)
                            .build();

        }


    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {

        private DeveloperLevel developerLevel;
        private DeveloperSkillType developerSkillType;
        private Integer experienceYear;
        private String memberId;

        public static Response fromEntity(Developer developer) {
            return Response.builder()
                           .developerSkillType(developer.getDeveloperSkillType())
                           .developerLevel(developer.getDeveloperLevel())
                           .experienceYear(developer.getExperienceYears())
                           .memberId(developer.getMemberId())
                           .build();

        }
    }

}
