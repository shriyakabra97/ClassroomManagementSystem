package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.Professor;
import com.spe.ClassroomManagementSystem.Models.TA;
import com.spe.ClassroomManagementSystem.Repository.ProfessorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    private static final Logger logger = LoggerFactory.getLogger(ProfessorServiceImpl.class);

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public String saveProfessor(Professor professor){
        String msg;
        try {
            professorRepository.save(professor);
            logger.info("added a new professor");
            msg = "Saved Professor Successfully";
        }
        catch (Exception e){
            logger.error("could not save professor");
            msg = "Error saving Professor..";
        }
        return msg;
    }

    @Override
    public Professor findByForeignId(Login login)
    {
        return  professorRepository.findByForeignId(login);
    }
}
