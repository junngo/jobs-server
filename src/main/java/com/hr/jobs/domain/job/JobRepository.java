package com.hr.jobs.domain.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("select j from Job j join fetch j.subDescList")
    List<Job> findByAllJobWithFetchJoin();
}
