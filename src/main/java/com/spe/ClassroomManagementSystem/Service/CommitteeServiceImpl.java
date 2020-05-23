package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Committee;
import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.TA;
import com.spe.ClassroomManagementSystem.Repository.CommitteeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommitteeServiceImpl implements CommitteeService{
    private static final Logger logger = LoggerFactory.getLogger(CommitteeServiceImpl.class);

    @Autowired
    private CommitteeRepository committeeRepository;

    @Override
    public String saveCommittee(Committee committee){
        String msg;
        try {
            committeeRepository.save(committee);
            logger.info("Committee saved successfully");
            msg = "Saved Committee Successfully";
        }catch (Exception e){
            logger.error("Failed Saving Committee");
            msg = "Failed Saving Committee";
        }
        return msg;
    }

    @Override
    public Committee findByForeignId(Login login)
    {
        return  committeeRepository.findByForeignId(login);
    }
}
