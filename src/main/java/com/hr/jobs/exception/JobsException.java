package com.hr.jobs.exception;

import lombok.Getter;

@Getter
public class JobsException extends RuntimeException {
    private JobsErrorCode jobsErrorCode;
    private String detailMessage;

    public JobsException(JobsErrorCode jobsErrorCode) {
        super(jobsErrorCode.getMessage());
        this.jobsErrorCode = jobsErrorCode;
        this.detailMessage = jobsErrorCode.getMessage();
    }
}
