package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.Sac;
import com.spe.ClassroomManagementSystem.Models.TA;
import com.spe.ClassroomManagementSystem.Repository.SacRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SacServiceImpl implements SacService{
    private static final Logger logger = LoggerFactory.getLogger(SacServiceImpl.class);

    @Autowired
    private SacRepository sacRepository;

    @Override
    public String  saveSac(Sac sac){
        String  msg ;
        try {
            sacRepository.save(sac);
            logger.info("SAC saved successfully");
            msg ="SAC Saved Successfully";
        }catch (Exception e){
            logger.error("could not save SAC");
            msg = "Failed Saving SAC";
        }
        return msg;
    }

    @Override
    public Sac findByForeignId(Login login)
    {
        return  sacRepository.findByForeignId(login);
    }
}
