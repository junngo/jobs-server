package com.hr.jobs.controller.dto;

import com.hr.jobs.domain.job.Job;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class JobListDto {

    private Long id;

    private String companyName;

    private String storedLogoName;

    private List<String> subDescList;

    private String city;

    private LocalDateTime createdAt;

    public static JobListDto from(Job job) {
        return JobListDto.builder()
                .id(job.getId())
                .companyName(job.getCompanyName())
                .storedLogoName(job.getStoredLogoName())
                .subDescList(
                        job.getSubDescList()
                                .stream()
                                .map(subDesc -> subDesc.getDescription()).collect(Collectors.toList())
                )
                .city(job.getCity())
                .createdAt(job.getCreatedAt())
                .build();
    }
}
