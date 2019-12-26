package com.spm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.spm.**"})
@EntityScan({"com.spm.**"})
@ComponentScan({"com.spm.**"})
@EnableAsync
public class SpmApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpmApiApplication.class, args);
	}

}
