package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.Professor;


public interface ProfessorService {
    String  saveProfessor(Professor professor);
    Professor findByForeignId(Login login);
}
