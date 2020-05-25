package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.TA;
import com.spe.ClassroomManagementSystem.Repository.TaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class TaServiceImplTest {

    @Autowired
    private TaService taService;
    @MockBean
    private TaRepository taRepository;
    @Test
    void saveTa() {
        Login login = new Login("ta", "ta", "password");
        TA ta = new TA("ta", "ta", "ta@gmail.com", login);
        when(taRepository.save(ta)).thenReturn(
                ta
        );
        assertEquals("TA Added Successfully", taService.saveTa(ta));
    }

    @Test
    void findByForeignId() {
        Login login = new Login("ta", "ta", "password");
        when(taRepository.findByForeignId(login)).thenReturn(
                new TA("ta", "ta", "ta@gmail.com", login)
        );
        assertEquals("ta", taService.findByForeignId(login).getTaName());
    }
}