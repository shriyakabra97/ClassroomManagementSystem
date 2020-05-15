package com.spe.ClassroomManagementSystem.Utils;

import com.spe.ClassroomManagementSystem.Models.Request;
import com.spe.ClassroomManagementSystem.Models.RequestStatus;
import com.spe.ClassroomManagementSystem.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckFulfilledRequestsImpl implements CheckFulfilledRequests {
    @Autowired
    private RequestRepository requestRepository;

//    @Override
//    public List<Request> findByRequestStatus(RequestStatus requestStatus) {
//        return requestRepository.findAllByRequestStatus(requestStatus);
//    }

    @Override
    @Scheduled(initialDelay = 60000, fixedRate = 60000)
    public void markFulfilled() {
        long currentMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentMillis);
        Time currentTime = new Time(currentMillis);
        requestRepository.updateRequestStatus(currentDate, currentTime);
    }
}
