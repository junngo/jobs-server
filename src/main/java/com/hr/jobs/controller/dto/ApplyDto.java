package com.hr.jobs.controller.dto;

import com.hr.jobs.domain.apply.Apply;
import com.hr.jobs.domain.job.Job;
import com.hr.jobs.domain.member.Member;
import com.hr.jobs.util.UploadFileInfo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

public class ApplyDto {

    @Builder
    @AllArgsConstructor
    @ToString
    @Getter
    public static class Request {
        @NotNull
        private Long jobId;
        @NotNull
        private MultipartFile resume;

        public Apply toEntity(Job job, Member member, UploadFileInfo fileInfo) {
            return Apply.builder()
                    .job(job)
                    .member(member)
                    .resumeName(fileInfo.getOriginFilename())
                    .resumeStoredName(fileInfo.getStoredFilename())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class Response {
        private Long id;

        public static ApplyDto.Response from(Apply apply) {
            return Response.builder()
                    .id(apply.getId())
                    .build();
        }
    }
}
