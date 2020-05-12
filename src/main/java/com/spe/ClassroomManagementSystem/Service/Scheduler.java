package com.spe.ClassroomManagementSystem.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Scheduler {

    @Scheduled(cron = "0/20 * * * * ?")
    public void create() {
        final LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Current Time: "+currentTime);
    }
}
