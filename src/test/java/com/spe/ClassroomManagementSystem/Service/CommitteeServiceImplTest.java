package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Committee;
import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Repository.CommitteeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class CommitteeServiceImplTest {

    @Autowired
    private CommitteeService committeeService;

    @MockBean
    private CommitteeRepository committeeRepository;
    @Test
    void saveCommittee() {
        Login login = new Login("committee", "foodcomm", "password");
        Committee committee = new Committee("foodcomm", "Food Committee", "manavdesai1997@gmail.com", login);
        when(committeeRepository.save(committee)).thenReturn(
                committee
        );
        assertEquals("Saved Committee Successfully", committeeService.saveCommittee(committee));
    }

    @Test
    void findByForeignId() {
        Login login = new Login("committee", "foodcomm", "password");
        when(committeeRepository.findByForeignId(login)).thenReturn(
                new Committee("foodcomm", "Food Committee", "manavdesai1997@gmail.com", login)
        );
        assertEquals("Food Committee", committeeService.findByForeignId(login).getCommitteeName());
    }
}