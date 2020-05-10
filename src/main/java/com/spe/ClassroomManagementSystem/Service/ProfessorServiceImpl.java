package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Professor;
import com.spe.ClassroomManagementSystem.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public String saveProfessor(Professor professor){
        String msg;
        try {
            professorRepository.save(professor);
            msg = "Saved Professor Successfully";
        }
        catch (Exception e){
            msg = "Error saving Professor..";
        }
        return msg;
    }
}
