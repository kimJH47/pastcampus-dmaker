package com.fastcampus.spring.dmaker.service;

import com.fastcampus.spring.dmaker.dto.DeveloperDetailDto;
import com.fastcampus.spring.dmaker.entity.Developer;
import com.fastcampus.spring.dmaker.repository.DeveloperRepository;
import com.fastcampus.spring.dmaker.repository.RetiredDeveloperRepository;
import com.fastcampus.spring.dmaker.type.DeveloperLevel;
import com.fastcampus.spring.dmaker.type.DeveloperSkillType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class DMakerServiceTest {

    @Mock
    private DeveloperRepository developerRepository;
    @Mock
    private RetiredDeveloperRepository retiredDeveloperRepository;
    @InjectMocks
    private DMakerService dMakerService;

    @Test
    public void findByMemberId() throws Exception{

        //given
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(Developer.builder()
                                                 .developerLevel(DeveloperLevel.JUNIOR)
                                                 .age(30)
                                                 .developerSkillType(DeveloperSkillType.BACK_END)
                                                 .name("tray")
                                                 .memberId("TRAY")
                                                 .experienceYears(3)
                                                 .build()));

        //when
        DeveloperDetailDto developerDetail = dMakerService.getDeveloperDetail("anyId");
        //then
        then(developerRepository).should(times(1))
                                 .findByMemberId(anyString());
    }

}