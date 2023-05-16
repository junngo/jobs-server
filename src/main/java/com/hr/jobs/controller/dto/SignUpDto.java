package com.hr.jobs.controller.dto;

import com.hr.jobs.domain.member.Member;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class SignUpDto {
    @Builder
    @AllArgsConstructor
    @ToString
    @Getter
    public static class Request {
        @NotNull
        private String email;

        @NotNull
        private String username;

        @NotNull
        private String password;

        public Member toEntity() {
            return Member.builder()
                    .email(email)
                    .username(username)
                    .password(password)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class Response {
        private Long id;

        public static SignUpDto.Response from(Member member) {
            return Response.builder()
                    .id(member.getId())
                    .build();
        }
    }
}
