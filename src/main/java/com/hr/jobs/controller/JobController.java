package com.hr.jobs.controller;

import com.hr.jobs.controller.dto.ApplyDto;
import com.hr.jobs.controller.dto.CreateJobDto;
import com.hr.jobs.controller.dto.JobDetailDto;
import com.hr.jobs.controller.dto.JobListDto;
import com.hr.jobs.service.JobService;
import com.hr.jobs.util.FileStore;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class JobController {

    private final JobService jobService;
    private final FileStore fileStore;

    @GetMapping("/jobs")
    public ResponseEntity<List<JobListDto>> getJobs() {
        return ResponseEntity.ok(jobService.getJobList());
    }

    @PostMapping("/job")
    public ResponseEntity<CreateJobDto.Response> createJob (
            @Valid @ModelAttribute CreateJobDto.Request request
    ) {
        return ResponseEntity.ok(
                jobService.createJob(request)
        );
    }

    /**
     * Image download on the html of web browser. Not directly download in person without the html.
     *
     * @param filename
     * @return
     * @throws IOException
     */
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadImage (
            @PathVariable final String filename
    ) throws IOException {
        Resource resource = new UrlResource("file:" + fileStore.getFileFullPath(filename));
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<JobDetailDto> getJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(
                jobService.getJobDetail(jobId)
        );
    }

    @PostMapping("/apply")
    public ResponseEntity<ApplyDto.Response> applyJob (
            @Valid @ModelAttribute ApplyDto.Request request,
            @AuthenticationPrincipal String memberId
    ) {
        return ResponseEntity.ok(
                jobService.applyJob(request, memberId)
        );
    }
}
