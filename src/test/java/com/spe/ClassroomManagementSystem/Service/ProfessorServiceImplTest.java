package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.Professor;
import com.spe.ClassroomManagementSystem.Repository.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class ProfessorServiceImplTest {
    @Autowired
    private  ProfessorService professorService;
    @MockBean
    private ProfessorRepository professorRepository;

    @Test
    void saveProfessor() {
        Login login = new Login("professor", "shriyakabra97", "password");
        String userName = "shriyakabra97";
        String professorName = "Shriya Kabra";
        String professorEmail = "shriyakabra97@gmail.com";
        Professor professor = new Professor(userName, professorName, professorEmail , login);
        when(professorRepository.save(professor)).thenReturn(
                new Professor(userName, professorName, professorEmail , new Login("professor", "shriyakabra97", "password"))
        );
        assertEquals("Saved Professor Successfully", professorService.saveProfessor(professor));

    }

    @Test
    void findByForeignId() {
        Login login = new Login("professor", "shriyakabra97", "password");
        when(professorRepository.findByForeignId(login)).thenReturn(
                new Professor("shriyakabra97", "Shriya Kabra", "shriyakabra97@gmail.com" , new Login("professor", "shriyakabra97", "password"))
        );
        assertEquals("Shriya Kabra",professorService.findByForeignId(login).getProfessorName());
    }
}