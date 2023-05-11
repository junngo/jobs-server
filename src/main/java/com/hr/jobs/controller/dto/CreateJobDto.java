package com.hr.jobs.controller.dto;

import com.hr.jobs.domain.job.Job;
import com.hr.jobs.util.UploadFileInfo;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CreateJobDto {

    @Builder
    @AllArgsConstructor
    @ToString
    @Getter
    public static class Request {
        @NotNull
        private String companyName;

        @NotNull
        private String jobTitle;

        private MultipartFile logoImage;

        @NotNull
        private String description;

        private List<String> subDescList;

        @NotNull
        private String city;

        public Job toEntity(UploadFileInfo fileInfo) {
            return Job.builder()
                    .companyName(companyName)
                    .jobTitle(jobTitle)
                    .logoName(fileInfo.getOriginFilename())
                    .storedLogoName(fileInfo.getStoredFilename())
                    .description(description)
                    .city(city)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class Response {
        private Long id;

        public static CreateJobDto.Response from(Job job) {
            return Response.builder()
                    .id(job.getId())
                    .build();
        }
    }
}
