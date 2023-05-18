package com.hr.jobs.service;

import com.hr.jobs.controller.dto.ApplyDto;
import com.hr.jobs.controller.dto.CreateJobDto;
import com.hr.jobs.controller.dto.JobDetailDto;
import com.hr.jobs.controller.dto.JobListDto;
import com.hr.jobs.domain.member.Member;

import java.util.List;

public interface JobService {
    List<JobListDto> getJobList();
    CreateJobDto.Response createJob(CreateJobDto.Request request);
    JobDetailDto getJobDetail(Long jobId);
    ApplyDto.Response applyJob(ApplyDto.Request request, String memberId);
}
