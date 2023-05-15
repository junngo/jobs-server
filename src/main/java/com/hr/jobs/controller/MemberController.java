package com.hr.jobs.controller;

import com.hr.jobs.controller.dto.SignInDto;
import com.hr.jobs.controller.dto.SignUpDto;
import com.hr.jobs.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerMember(@Valid @RequestBody SignUpDto.Request request) {
        return ResponseEntity.ok(memberService.create(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signInMember(@Valid @RequestBody SignInDto.Request request) {
        return ResponseEntity.ok(memberService.getByCredentials(request));
    }

    @GetMapping("/auth/check")
    public ResponseEntity checkAuthUser(@AuthenticationPrincipal String request) {
        return ResponseEntity.ok(memberService.getMemberInfo(request));
    }
}
