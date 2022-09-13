package com.fastcampus.spring.dmaker.service;

import com.fastcampus.spring.dmaker.dto.CreateDeveloper;
import com.fastcampus.spring.dmaker.dto.DeveloperDetailDto;
import com.fastcampus.spring.dmaker.dto.DeveloperDto;
import com.fastcampus.spring.dmaker.dto.UpdateDeveloper;
import com.fastcampus.spring.dmaker.entity.Developer;
import com.fastcampus.spring.dmaker.entity.RetiredDeveloper;
import com.fastcampus.spring.dmaker.exception.DMakerException;
import com.fastcampus.spring.dmaker.repository.DeveloperRepository;
import com.fastcampus.spring.dmaker.repository.RetiredDeveloperRepository;
import com.fastcampus.spring.dmaker.type.DeveloperLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.fastcampus.spring.dmaker.exception.DMakerErrorCode.*;

@Service
@RequiredArgsConstructor
public class DMakerService {

    private final DeveloperRepository developerRepository;
    private final RetiredDeveloperRepository retiredDeveloperRepository;

    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {
        //business validate
        validateCreateDeveloperRequest(request);
        Developer developer = request.toEntity();
        System.out.println("level: "+developer.getDeveloperLevel());
        Developer dev = developerRepository.save(developer);

        return CreateDeveloper.Response.fromEntity(dev);

    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {
        validateDeveloperLevelAndYears(request.getDeveloperLevel(), request.getExperienceYear());
        developerRepository.findByMemberId(request.getMemberId())
                           .ifPresent(developer -> {
                            throw new DMakerException(DUPLICATED_MEMBER_ID);
                           });
    }
    private static void validateDeveloperLevelAndYears(DeveloperLevel developerLevel, Integer experienceYear) {
        if (experienceYear < 10 && developerLevel == DeveloperLevel.SENIOR) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if (developerLevel == DeveloperLevel.JUNGIOR && (experienceYear < 3 || experienceYear > 10)) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if (developerLevel == DeveloperLevel.JUNIOR && experienceYear > 4) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
    }

    @Transactional(readOnly = true)
    public List<DeveloperDto> getAllDevelopers() {
        return developerRepository.findAll()
                                  .stream()
                                  .map(developer -> DeveloperDto.fromEntity(developer))
                                  .collect(Collectors.toList());


    }

    @Transactional(readOnly = true)
    public DeveloperDetailDto getDeveloperDetail(String memberId) {

        return DeveloperDetailDto.fromEntity(getDeveloperByMemberId(memberId));

    }

    @Transactional
    public DeveloperDetailDto updateDeveloper(UpdateDeveloper.Request request) {
        validateDeveloperLevelAndYears(request.getDeveloperLevel(), request.getExperienceYear());
        Developer developer = getDeveloperByMemberId(request.getMemberId());
        return DeveloperDetailDto.fromEntity(developer.update(request));

    }

    @Transactional
    public void retiredDeveloper(String memberId) {
        Developer developer = getDeveloperByMemberId(memberId);
        retiredDeveloperRepository.save(RetiredDeveloper.create(developer));
        developerRepository.delete(developer);
    }

    private Developer getDeveloperByMemberId(String memberId) {
        Developer developer = developerRepository.findByMemberId(memberId)
                                                 .orElseThrow(() -> new DMakerException(NO_DEVELOPER));
        return developer;
    }
}
