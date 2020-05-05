package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginServiceImplTests {
    @BeforeAll
    void addData() {
        LoginService loginService = new LoginServiceImpl();
        Login thangaraju = new Login("professor", "thangaraju", "thangaraju");
        loginService.save(thangaraju);
        Login login = new Login("committee", "foodcomm", "foodcomm");
        loginService.save(login);
        Login admin1 = new Login("admin", "admin1", "admin1");
        loginService.save(admin1);
        Login sac1 = new Login("sac", "sac1", "sac1");
        loginService.save(sac1);
        Login mt2019 = new Login("ta", "MT2019", "MT2019");
        loginService.save(mt2019);
    }

    @Test
    void checkLogin() {
        LoginService loginService = new LoginServiceImpl();
        assertEquals(loginService.checkCredentials("thangaraju", "thangaraju", "professor"), true);
        assertEquals(loginService.checkCredentials("MT2019", "MT2019", "ta"), true);
        assertEquals(loginService.checkCredentials("MT2019", "MT2019", "professor"), false);
        assertEquals(loginService.checkCredentials("MT2019", "MT2020", "ta"), false);
        assertEquals(loginService.checkCredentials("mkd", "MT2019", "ta"), false);
    }
}
