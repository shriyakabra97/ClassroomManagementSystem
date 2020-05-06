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
    public TA saveTa(TA ta){
        return taRepository.save(ta);
    }
}
