package com.hr.jobs.service;

import com.hr.jobs.controller.dto.SignInDto;
import com.hr.jobs.controller.dto.SignUpDto;
import com.hr.jobs.domain.member.Member;
import com.hr.jobs.domain.member.MemberRepository;
import com.hr.jobs.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    @Override
    public SignUpDto.Response create(SignUpDto.Request request) {
        memberRepository.findByEmail(request.getEmail())
                .ifPresent(member -> {throw new IllegalStateException("There exist Email already");});
        Member member = memberRepository.save(request.toEntity());
        return SignUpDto.Response.from(member);
    }

    @Override
    public SignInDto.Response getByCredentials(SignInDto.Request request) {
        Member member = memberRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(() -> new RuntimeException("Login failed."));
        return SignInDto.Response.from(member, tokenProvider.create(member));
    }
}
