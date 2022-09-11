package com.fastcampus.spring.dmaker.controller;

import com.fastcampus.spring.dmaker.config.JpaAuditingConfiguration;
import com.fastcampus.spring.dmaker.dto.DeveloperDto;
import com.fastcampus.spring.dmaker.service.DMakerService;
import com.fastcampus.spring.dmaker.type.DeveloperLevel;
import com.fastcampus.spring.dmaker.type.DeveloperSkillType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DMakerController.class)
@MockBean(JpaAuditingConfiguration.class)
class DMakerControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private DMakerService dMakerService;
    protected MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);
    @Test
    public void find() throws Exception{
        //given
        DeveloperDto developerDto1 = DeveloperDto.builder()
                                       .developerLevel(DeveloperLevel.JUNIOR)
                                       .memberId("id1")
                                       .developerSkillType(DeveloperSkillType.BACK_END)
                                       .build();
        DeveloperDto developerDto2 = DeveloperDto.builder()
                                                 .developerLevel(DeveloperLevel.JUNGIOR)
                                                 .memberId("id1")
                                                 .developerSkillType(DeveloperSkillType.FRONT_END)
                                                 .build();
        DeveloperDto developerDto3 = DeveloperDto.builder()
                                                 .developerLevel(DeveloperLevel.SENIOR)
                                                 .memberId("id1")
                                                 .developerSkillType(DeveloperSkillType.BACK_END)
                                                 .build();


        given(dMakerService.getAllDevelopers()).willReturn(Arrays.asList(developerDto3, developerDto2, developerDto1));
        //when
        //then
        mvc.perform(get("/api/developers").contentType(contentType))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.[0].developerSkillType", is(DeveloperSkillType.BACK_END.name())))
           .andExpect(jsonPath("$.[1].developerSkillType", is(DeveloperSkillType.FRONT_END.name())))
           .andExpect(jsonPath("$.[2].developerSkillType", is(DeveloperSkillType.BACK_END.name())));

    }
}