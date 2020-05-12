package com.spe.ClassroomManagementSystem;

import com.spe.ClassroomManagementSystem.Utils.CheckFulfilledRequestsTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;

@SpringBootApplication
public class ClassroomManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassroomManagementSystemApplication.class, args);
		Timer timer = new Timer();
		CheckFulfilledRequestsTask checkFulfilledRequestsTask = new CheckFulfilledRequestsTask();
		timer.schedule(checkFulfilledRequestsTask, 0, 10000);
	}
}
