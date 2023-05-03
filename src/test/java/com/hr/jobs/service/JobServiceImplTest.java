package com.hr.jobs.service;

import com.hr.jobs.controller.dto.JobListDto;
import com.hr.jobs.domain.job.Job;
import com.hr.jobs.domain.job.JobRepository;
import com.hr.jobs.domain.job.SubDesc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobServiceImpl jobService;

    @Test
    public void get_job_list_success() {
        // given
        List<SubDesc> subDescList = Arrays.asList(
                SubDesc.builder().desc("Java").build(),
                SubDesc.builder().desc("Spring Boot").build()
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
        given(jobRepository.findAll())
            .willReturn(Arrays.asList(job1, job2));

        // when
        List<JobListDto> response = jobService.getJobList();

        // then
        assertEquals(2, response.size());
        assertEquals(job1.getId(), response.get(0).getId());
        assertEquals(job1.getCompanyName(), response.get(0).getCompanyName());
        assertEquals(job1.getStoredLogoName(), response.get(0).getStoredLogoName());
        assertEquals(job1.getSubDescList(), response.get(0).getSubDescList());
        assertEquals(job1.getCity(), response.get(0).getCity());
        assertEquals(job1.getCreatedAt(), response.get(0).getCreatedAt());
    }
}