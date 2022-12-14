package com.fastcampus.spring.dmaker.entity;

import com.fastcampus.spring.dmaker.type.DeveloperLevel;
import com.fastcampus.spring.dmaker.type.DeveloperSkillType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
public class RetiredDeveloper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DeveloperLevel developerLevel;
    @Enumerated(EnumType.STRING)
    private DeveloperSkillType developerSkillType;

    private Integer experienceYears;
    private String name;
    private Integer age;

    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime updateAt;

    public static RetiredDeveloper create(Developer developer) {
        return RetiredDeveloper.builder()
                               .developerLevel(developer.getDeveloperLevel())
                               .developerSkillType(developer.getDeveloperSkillType())
                               .experienceYears(developer.getExperienceYears())
                               .name(developer.getName())
                               .age(developer.getAge())
                               .build();
    }
}
