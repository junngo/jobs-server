package com.hr.jobs.service;

import com.hr.jobs.controller.dto.CreateJobDto;
import com.hr.jobs.controller.dto.JobListDto;
import com.hr.jobs.domain.job.Job;
import com.hr.jobs.domain.job.JobRepository;
import com.hr.jobs.domain.job.SubDesc;
import com.hr.jobs.util.FileStore;
import com.hr.jobs.util.UploadFileInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private FileStore fileStore;

    @InjectMocks
    private JobServiceImpl jobService;

    @Test
    public void get_job_list_success() {
        // given
        List<SubDesc> subDescList = Arrays.asList(
                SubDesc.builder().description("Java").build(),
                SubDesc.builder().description("Spring Boot").build()
        );
        Job job1 = Job.builder()
                .id(1L)
                .companyName("Job1 Company")
                .storedLogoName("logo1 file")
                .description("job1 description")
                .subDescList(subDescList)
                .city("LA")
                .createdAt(LocalDateTime.now())
                .build();
        Job job2 = Job.builder()
                .id(2L)
                .companyName("Job2 Company")
                .storedLogoName("logo2 file")
                .description("job2 description")
                .subDescList(subDescList)
                .city("CA")
                .createdAt(LocalDateTime.now())
                .build();
        given(jobRepository.findByAllJobWithFetchJoin())
            .willReturn(Arrays.asList(job1, job2));

        // when
        List<JobListDto> response = jobService.getJobList();

        // then
        assertEquals(2, response.size());
        assertEquals(job1.getId(), response.get(0).getId());
        assertEquals(job1.getCompanyName(), response.get(0).getCompanyName());
        assertNotNull(response.get(0).getStoredLogoName());
        assertEquals(
                job1.getSubDescList().stream().map(
                        subDesc -> subDesc.getDescription()).collect(Collectors.toList()
                ),
                response.get(0).getSubDescList()
        );
        assertEquals(job1.getCity(), response.get(0).getCity());
        assertEquals("0 minutes ago", response.get(0).getCreatedAt());
    }

    @Test
    public void create_job_success() {
        // given
        CreateJobDto.Request request = CreateJobDto.Request.builder()
                .companyName("Test Company1")
                .description("Test description")
                .city("Seoul")
                .build();
        UploadFileInfo fileInfo = new UploadFileInfo();
        given(fileStore.storeFile(any()))
                .willReturn(fileInfo);
        Job job = request.toEntity(fileInfo);
        given(jobRepository.save(any()))
                .willReturn(job);

        // when
        CreateJobDto.Response response = jobService.createJob(request);

        // then
        assertNotNull(response);
        assertEquals(job.getId(), response.getId());
    }
}