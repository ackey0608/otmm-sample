package com.example.participant1.controller;

import com.oracle.microtx.springboot.lra.annotation.Compensate;
import com.oracle.microtx.springboot.lra.annotation.Complete;
import com.oracle.microtx.springboot.lra.annotation.LRA;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static com.oracle.microtx.springboot.lra.annotation.LRA.LRA_HTTP_CONTEXT_HEADER;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    @PostMapping("/test-api/{pattern}")
    @LRA(value = LRA.Type.MANDATORY, end = false)
    public ResponseEntity<String> create(
            @PathVariable String pattern,
            @RequestHeader(LRA_HTTP_CONTEXT_HEADER) String lraId) {

        log.info("Participant1 start. LRA: {}", lraId);

        if ("error1st".equals(pattern)) {
            log.error("error!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NG");
        }

        log.info("Participant1 end. LRA: {}", lraId);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/complete")
    @Complete
    public ResponseEntity<?> complete(
            @RequestHeader(LRA_HTTP_CONTEXT_HEADER) String lraId) {

        log.info("Participant1 completed. LRA: {}", lraId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/compensate")
    @Compensate
    public ResponseEntity<?> compensate(
            @RequestHeader(LRA_HTTP_CONTEXT_HEADER) String lraId) {

        // Do some compensations.
        log.warn("Participant1 cancelled. LRA: {}", lraId);
        return ResponseEntity.ok().build();
    }

}
