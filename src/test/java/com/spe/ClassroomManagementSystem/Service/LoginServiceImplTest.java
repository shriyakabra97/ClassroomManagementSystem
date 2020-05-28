package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Repository.LoginRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class LoginServiceImplTest {
    @Autowired
    private LoginService loginService;
    @MockBean
    private LoginRepository loginRepository;

    @Test
    void findByUsernameAndPassword() {
        String username = "shriyakabra97";
        String password = "password";
       when(loginRepository.findByUserNameAndPassword(username, password)).thenReturn(
               new Login("professor", "shriyakabra97", "password")
       );
       assertEquals("professor", loginService.findByUsernameAndPassword(username, password).getUserType());
    }


    @Test
    void findByLoginId() {
        long loginId = 12;
        when(loginService.findByLoginId(loginId)).thenReturn(
                new Login("professor", "shriyakabra97", "password")
        );
        assertEquals("shriyakabra97", loginService.findByLoginId(loginId).getUserName());
    }
}