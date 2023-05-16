package com.hr.jobs.controller.dto;

import com.hr.jobs.exception.JobsErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobsErrorResponse {
    private JobsErrorCode errorCode;
    private String errorMessage;
}
