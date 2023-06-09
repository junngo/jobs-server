package com.hr.jobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JobsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobsServerApplication.class, args);
	}

}
