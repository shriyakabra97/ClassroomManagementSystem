package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.TA;
import com.spe.ClassroomManagementSystem.Repository.TaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaServiceImpl implements TaService{

    @Autowired
    private TaRepository taRepository;

    @Override
    public String  saveTa(TA ta){
        String msg;
        try {
            taRepository.save(ta);
            msg = "TA Added Successfully";
        }catch (Exception e){
            msg = "Failed Saving TA";
        }
        return msg;
    }
}
