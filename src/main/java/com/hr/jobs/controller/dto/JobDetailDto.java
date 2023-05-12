package com.hr.jobs.controller.dto;

import com.hr.jobs.domain.job.Job;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class JobDetailDto {

    private String companyName;

    private String jobTitle;

    private String description;

    private String city;

    private List<String> subDescList;

    private String createdAt;

    public static JobDetailDto from(Job job) {
        // Time
        LocalDateTime createdAt = job.getCreatedAt();
        LocalDateTime t2 = LocalDateTime.now();
        Period period = Period.between(createdAt.toLocalDate(), t2.toLocalDate());
        Duration duration = Duration.between(createdAt, t2);

        String printTime = null;
        if (duration.toMinutes() <= 60) {
            printTime = duration.toMinutes() + " minutes ago";
        } else if (duration.toHours() <= 24) {
            printTime = duration.toHours() + " hours ago";
        } else if (period.getDays() <= 31) {
            printTime = period.getDays() + " days ago";
        } else if (period.getMonths() <= 12) {
            printTime = period.getMonths() + " months ago";
        } else {
            printTime = period.getYears() + " years ago";
        }

        return JobDetailDto.builder()
                .companyName(job.getCompanyName())
                .jobTitle(job.getJobTitle())
                .description(job.getDescription())
                .subDescList(
                        job.getSubDescList()
                                .stream()
                                .map(subDesc -> subDesc.getDescription()).collect(Collectors.toList())
                )
                .city(job.getCity())
                .createdAt(printTime)
                .build();
    }
}
