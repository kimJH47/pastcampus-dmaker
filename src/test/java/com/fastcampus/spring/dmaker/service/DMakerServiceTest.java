package com.fastcampus.spring.dmaker.service;

import com.fastcampus.spring.dmaker.dto.CreateDeveloper;
import com.fastcampus.spring.dmaker.dto.DeveloperDetailDto;
import com.fastcampus.spring.dmaker.entity.Developer;
import com.fastcampus.spring.dmaker.exception.DMakerErrorCode;
import com.fastcampus.spring.dmaker.exception.DMakerException;
import com.fastcampus.spring.dmaker.repository.DeveloperRepository;
import com.fastcampus.spring.dmaker.type.DeveloperLevel;
import com.fastcampus.spring.dmaker.type.DeveloperSkillType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DMakerServiceTest {

    @Mock
    private DeveloperRepository developerRepository;
    @InjectMocks
    private DMakerService dMakerService;

    @Test
    public void findByMemberId() throws Exception {

        //given
        Developer developer = getDeveloper();
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(developer));

        //when
        DeveloperDetailDto developerDetail = dMakerService.getDeveloperDetail("anyId");
        //then
        then(developerRepository).should(times(1))
                                 .findByMemberId(anyString());
        assertEquals(developerDetail.getMemberId(), "TRAY");
        assertEquals(developerDetail.getDeveloperSkillType(), DeveloperSkillType.BACK_END);
        assertEquals(developerDetail.getDeveloperLevel(), DeveloperLevel.JUNIOR);
    }

    private static Developer getDeveloper() {
        return Developer.builder()
                        .developerLevel(DeveloperLevel.JUNIOR)
                        .age(30)
                        .developerSkillType(DeveloperSkillType.BACK_END)
                        .name("tray")
                        .memberId("TRAY")
                        .experienceYears(3)
                        .build();
    }

    @Test
    public void createDeveloper_success() throws Exception {
        //given
        CreateDeveloper.Request request = CreateDeveloper.Request.builder()
                                                                 .developerLevel(DeveloperLevel.JUNIOR)
                                                                 .developerSkillType(DeveloperSkillType.BACK_END)
                                                                 .memberId("TRAY")
                                                                 .name("tray")
                                                                 .experienceYear(3)
                                                                 .age(30)
                                                                 .build();

        given(developerRepository.findByMemberId(anyString())).willReturn(Optional.empty());
        given(developerRepository.save(any(Developer.class))).willReturn(request.toEntity());

        ArgumentCaptor<Developer> captor = ArgumentCaptor.forClass(Developer.class);
        //when
        CreateDeveloper.Response developer = dMakerService.createDeveloper(request);
        //then
        verify(developerRepository, times(1))
                .save(captor.capture());
        Developer savedDeveloper = captor.getValue();
        assertEquals(savedDeveloper.getDeveloperLevel(), DeveloperLevel.JUNIOR);
        assertEquals(DeveloperSkillType.BACK_END, savedDeveloper.getDeveloperSkillType());
        assertEquals(savedDeveloper.getMemberId(), "TRAY");

    }

    @Test
    public void createDeveloper_failed_with_duplicated() throws Exception {
        //given
        CreateDeveloper.Request request = CreateDeveloper.Request.builder()
                                                                 .developerLevel(DeveloperLevel.JUNIOR)
                                                                 .developerSkillType(DeveloperSkillType.BACK_END)
                                                                 .memberId("TRAY")
                                                                 .name("tray")
                                                                 .experienceYear(3)
                                                                 .age(30)
                                                                 .build();

        //memberId 중복예외 발생
        given(developerRepository.findByMemberId(anyString())).willReturn(Optional.of(getDeveloper()));
        //when
        //then
        verify(developerRepository, times(0)).save(any(Developer.class));
        DMakerException dMakerException = assertThrows(DMakerException.class, () -> {
            dMakerService.createDeveloper(request);
        });
        assertEquals(dMakerException.getDMakerErrorCode(), DMakerErrorCode.DUPLICATED_MEMBER_ID);


    }


}