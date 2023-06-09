package com.hr.jobs.service;

import com.hr.jobs.controller.dto.CreateJobDto;
import com.hr.jobs.controller.dto.JobDetailDto;
import com.hr.jobs.controller.dto.JobListDto;
import com.hr.jobs.domain.job.Job;
import com.hr.jobs.domain.job.JobRepository;
import com.hr.jobs.domain.job.SubDesc;
import com.hr.jobs.domain.job.SubDescRepository;
import com.hr.jobs.util.FileStore;
import com.hr.jobs.util.UploadFileInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final SubDescRepository subDescRepository;
    private final FileStore fileStore;

    @Value("${file.download.url}")
    private String fileUrl;

    @Override
    public List<JobListDto> getJobList() {
        return jobRepository.findByAllJobWithFetchJoin()
                .stream().map(job -> JobListDto.from(job, fileUrl))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CreateJobDto.Response createJob(CreateJobDto.Request request) {
        UploadFileInfo fileInfo = fileStore.storeFile(request.getLogoImage());
        Job job = jobRepository.save(request.toEntity(fileInfo));
        if (request.getSubDescList() != null) {
            request.getSubDescList().forEach(
                    s -> subDescRepository.save(
                            SubDesc.builder()
                                    .job(job)
                                    .description(s)
                                    .build()
                    )
            );
        }
        return CreateJobDto.Response.from(job);
    }

    @Override
    public JobDetailDto getJobDetail(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Not Found Data"));
        return JobDetailDto.from(job);
    }
}
