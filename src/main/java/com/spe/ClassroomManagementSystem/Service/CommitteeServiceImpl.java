package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.ClassTiming;
import com.spe.ClassroomManagementSystem.Models.Committee;
import com.spe.ClassroomManagementSystem.Models.Day;
import com.spe.ClassroomManagementSystem.Repository.ClassTimingRepository;
import com.spe.ClassroomManagementSystem.Repository.CommitteeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommitteeServiceImpl implements CommitteeService{

    @Autowired
    private CommitteeRepository committeeRepository;

    @Autowired
    private ClassTimingRepository classTimingRepository;
    @Override
    public String saveCommittee(Committee committee){
        String msg;
        try {
            committeeRepository.save(committee);
            msg = "Saved Committee Successfully";
        }catch (Exception e){
            msg = "Failed Saving Committee";
        }
        return msg;
    }

}
