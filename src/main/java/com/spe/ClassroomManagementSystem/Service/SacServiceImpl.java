package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Sac;
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
            msg ="SAC Saaved Successfully";
        }catch (Exception e){
            msg = "Failed Saving SAC";
        }
        return msg;
    }
}
