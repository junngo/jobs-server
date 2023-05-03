package com.hr.jobs.domain.job;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table
@Entity
public class Job {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String companyName;

    private String logoName;

    private String storedLogoName;

    @Column(nullable = false)
    private String description;

    @OneToMany
    private List<SubDesc> subDescList;

    @Column(nullable = false)
    private String city;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
