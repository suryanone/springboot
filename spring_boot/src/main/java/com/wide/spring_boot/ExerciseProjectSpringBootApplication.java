package com.wide.spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ExerciseProjectSpringBootApplication extends SpringBootServletInitializer{
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ExerciseProjectSpringBootApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(ExerciseProjectSpringBootApplication.class, args);
	}

}
