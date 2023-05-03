package com.hr.jobs.controller.dto;

import com.hr.jobs.domain.job.Job;
import com.hr.jobs.domain.job.SubDesc;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class JobListDto {

    private Long id;

    private String companyName;

    private String storedLogoName;

    private List<SubDesc> subDescList;

    private String city;

    private LocalDateTime createdAt;

    public static JobListDto from(Job job) {
        return JobListDto.builder()
                .id(job.getId())
                .companyName(job.getCompanyName())
                .storedLogoName(job.getStoredLogoName())
                .subDescList(job.getSubDescList())
                .city(job.getCity())
                .createdAt(job.getCreatedAt())
                .build();
    }
}
