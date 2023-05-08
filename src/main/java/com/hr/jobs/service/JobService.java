package com.hr.jobs.service;

import com.hr.jobs.controller.dto.CreateJobDto;
import com.hr.jobs.controller.dto.JobDetailDto;
import com.hr.jobs.controller.dto.JobListDto;

import java.util.List;

public interface JobService {
    List<JobListDto> getJobList();
    CreateJobDto.Response createJob(CreateJobDto.Request request);
    JobDetailDto getJobDetail(Long jobId);
}
