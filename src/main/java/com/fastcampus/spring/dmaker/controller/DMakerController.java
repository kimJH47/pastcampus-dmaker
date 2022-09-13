package com.fastcampus.spring.dmaker.controller;


import com.fastcampus.spring.dmaker.dto.CreateDeveloper;
import com.fastcampus.spring.dmaker.dto.DeveloperDetailDto;
import com.fastcampus.spring.dmaker.dto.DeveloperDto;
import com.fastcampus.spring.dmaker.dto.UpdateDeveloper;
import com.fastcampus.spring.dmaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @author kim
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class DMakerController {


    private final DMakerService dMakerService;

    @GetMapping("/api/developers")
    public List<DeveloperDto> getAllDevelopers() {
        log.info("GET /api/developers HTTP/1.1");
        return dMakerService.getAllDevelopers();

    }

    @GetMapping("/api/developers/{memberId}")
    public DeveloperDetailDto getOneDeveloper(@PathVariable final String memberId) {
        log.info("GET /api/developer/{} HTTP/1.1", memberId);
        return dMakerService.getDeveloperDetail(memberId);

    }

    @PostMapping("/api/developers")
    public ResponseEntity<?> createDeveloper(@RequestBody @Valid final CreateDeveloper.Request request) {

        log.info("request : {}", request);
        CreateDeveloper.Response developer = dMakerService.createDeveloper(request);
        return ResponseEntity.ok(developer);

    }

    @PutMapping("/api/developers")
    public DeveloperDetailDto updateDeveloper(@RequestBody @Valid final UpdateDeveloper.Request request) {
        log.info("PUT request : {}", request);
        return dMakerService.updateDeveloper(request);

    }

    @DeleteMapping("/api/developers/{memberId}")
    public ResponseEntity<?> retiredDeveloper(@PathVariable final String memberId) {
        log.info("DELETE request : {}", memberId);
        dMakerService.retiredDeveloper(memberId);
        return ResponseEntity.ok()
                             .build();
    }

}
