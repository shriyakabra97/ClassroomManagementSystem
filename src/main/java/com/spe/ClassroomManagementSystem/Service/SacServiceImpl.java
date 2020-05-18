package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.Sac;
import com.spe.ClassroomManagementSystem.Models.TA;
import com.spe.ClassroomManagementSystem.Repository.SacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SacServiceImpl implements SacService{

    @Autowired
    private SacRepository sacRepository;

    @Override
    public String  saveSac(Sac sac){
        String  msg ;
        try {
            sacRepository.save(sac);
            msg ="SAC Saved Successfully";
        }catch (Exception e){
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
