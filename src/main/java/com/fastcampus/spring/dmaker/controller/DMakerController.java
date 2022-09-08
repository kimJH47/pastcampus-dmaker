package com.fastcampus.spring.dmaker.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


/**
 * @author kim
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class DMakerController {


    @GetMapping("/api/developers")
    public List<String> getAllDevelopers() {
        log.info("GET /developers HTTP/1.1");
        return Arrays.asList("test");
    }

}
