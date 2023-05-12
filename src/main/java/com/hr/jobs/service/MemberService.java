package com.hr.jobs.service;

import com.hr.jobs.controller.dto.SignInDto;
import com.hr.jobs.controller.dto.SignUpDto;

public interface MemberService {
    SignUpDto.Response create(SignUpDto.Request request);
    SignInDto.Response getByCredentials(SignInDto.Request request);
}
