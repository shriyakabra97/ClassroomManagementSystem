package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Committee;
import com.spe.ClassroomManagementSystem.Repository.CommitteeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommitteeServiceImpl implements CommitteeService{

    @Autowired
    private CommitteeRepository committeeRepository;

    @Override
    public Committee saveCommittee(Committee committee){
        return committeeRepository.save(committee);
    }
}
