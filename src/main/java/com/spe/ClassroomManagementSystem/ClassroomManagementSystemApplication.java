package com.spe.ClassroomManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClassroomManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassroomManagementSystemApplication.class, args);
	}
}
