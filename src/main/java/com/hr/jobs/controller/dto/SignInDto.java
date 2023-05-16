package com.hr.jobs.controller.dto;

import com.hr.jobs.domain.member.Member;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class SignInDto {

    @Builder
    @AllArgsConstructor
    @ToString
    @Getter
    public static class Request {

        @NotNull
        private String email;

        @NotNull
        private String password;
    }

    @Getter
    @Builder
    public static class Response {
        private String token;
        private String username;
        private String email;

        public static SignInDto.Response from(Member member, String token) {
            return Response.builder()
                    .username(member.getUsername())
                    .email(member.getEmail())
                    .token(token)
                    .build();
        }
    }
}
