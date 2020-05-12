package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;
}
