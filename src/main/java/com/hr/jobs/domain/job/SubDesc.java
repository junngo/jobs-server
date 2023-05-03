package com.hr.jobs.domain.job;

import jakarta.persistence.*;
import lombok.Builder;

@Builder
@Table
@Entity
public class SubDesc {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String desc;
}
