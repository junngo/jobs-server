package com.hr.jobs.service;

import com.hr.jobs.controller.dto.JobListDto;
import com.hr.jobs.domain.job.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public List<JobListDto> getJobList() {
        return jobRepository.findAll()
                .stream().map(JobListDto::from)
                .collect(Collectors.toList());
    }
}
