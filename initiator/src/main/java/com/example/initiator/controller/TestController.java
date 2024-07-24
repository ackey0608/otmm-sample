package com.example.initiator.controller;

import com.example.initiator.AppProperties;
import com.oracle.microtx.springboot.lra.annotation.AfterLRA;
import com.oracle.microtx.springboot.lra.annotation.LRA;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static com.oracle.microtx.springboot.lra.annotation.LRA.LRA_HTTP_CONTEXT_HEADER;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final AppProperties appProperties;

    @PostMapping("/start-lra/{pattern}")
    @LRA(value = LRA.Type.REQUIRES_NEW)
    public ResponseEntity<String> create(
            @PathVariable String pattern,
            @RequestHeader(LRA_HTTP_CONTEXT_HEADER) String lraId) {

        log.info("start new LRA: {}", lraId);

        callParticipant1(pattern);
        callParticipant2(pattern);

        log.info("end LRA: {}", lraId);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/after-lra")
    @AfterLRA
    public ResponseEntity<?> afterLra(
            @RequestHeader(LRA_HTTP_CONTEXT_HEADER) String lraId,
            @RequestBody String status) {

        log.info("After LRA Called: {}", lraId);
        log.info("Final LRA Status: {}", status);
        return ResponseEntity.ok().build();
    }

    private void callParticipant1(String pattern) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(
                appProperties.getParticipant1Url() + "/" + pattern, null, String.class);
        log.info("Participant1 called: {}", response);
    }

    private void callParticipant2(String pattern) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(
                appProperties.getParticipant2Url() + "/" + pattern, null, String.class);
        log.info("Participant2 called: {}", response);
    }
}
