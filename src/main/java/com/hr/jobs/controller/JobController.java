package com.hr.jobs.controller;

import com.hr.jobs.controller.dto.JobListDto;
import com.hr.jobs.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class JobController {

    private final JobService jobService;

    @GetMapping("/jobs")
    public ResponseEntity<List<JobListDto>> getJobs() {
        return ResponseEntity.ok(jobService.getJobList());
    }
}
