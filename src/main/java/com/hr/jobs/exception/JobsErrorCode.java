package com.hr.jobs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JobsErrorCode {
    MEMBER_NOT_EXISTS("MEMBER INFO DOES NOT EXIST"),
    JOB_NOT_EXISTS("JOB DOES NOT EXIST"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR");

    private final String message;
}
