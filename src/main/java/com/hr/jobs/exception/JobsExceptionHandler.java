package com.hr.jobs.exception;

import com.hr.jobs.controller.dto.JobsErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.hr.jobs.exception.JobsErrorCode.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class JobsExceptionHandler {

    @ResponseStatus(value= HttpStatus.OK)
    @ExceptionHandler(JobsException.class)
    @ResponseBody
    public JobsErrorResponse handleJobsException(
            JobsException e,
            HttpServletRequest request
    ) {
        log.error("errorCode: {}, url: {}", e.getJobsErrorCode(), request.getRequestURI());
        return JobsErrorResponse.builder()
                .errorCode(e.getJobsErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JobsErrorResponse handleException(
            Exception e,
            HttpServletRequest request
    ) {
        log.error("url: {}, message: {}", request.getRequestURI(), e.getMessage(), e);
        return JobsErrorResponse.builder()
                .errorCode(INTERNAL_SERVER_ERROR)
                .errorMessage(INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }
}
