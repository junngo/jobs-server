package com.hr.jobs.service;

import com.hr.jobs.controller.dto.JobListDto;

import java.util.List;

public interface JobService {
    List<JobListDto> getJobList();
}
