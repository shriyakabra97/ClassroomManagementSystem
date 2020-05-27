package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.Sac;
import com.spe.ClassroomManagementSystem.Models.TA;
import com.spe.ClassroomManagementSystem.Repository.TaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaServiceImpl implements TaService{
    private static final Logger logger = LoggerFactory.getLogger(TaServiceImpl.class);

    @Autowired
    private TaRepository taRepository;

    @Override
    public String  saveTa(TA ta){
        String msg;
        try {
            taRepository.save(ta);
            logger.info("TA saved successfully");
            msg = "TA Added Successfully";
        }catch (Exception e){
            logger.error("could not save TA");
            msg = "Failed Saving TA";
        }
        return msg;
    }

    @Override
    public TA findByForeignId(Login login)
    {
        return  taRepository.findByForeignId(login);
    }

}
