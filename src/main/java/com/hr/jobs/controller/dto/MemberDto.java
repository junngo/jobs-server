package com.hr.jobs.controller.dto;

import com.hr.jobs.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDto {
    private String username;

    private String email;

    public static MemberDto from(Member member) {
        return MemberDto.builder()
                .username(member.getUsername())
                .email(member.getEmail())
                .build();
    }
}
