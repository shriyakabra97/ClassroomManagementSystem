package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;

@SpringBootTest
public class LoginServiceImplTest {
//    @BeforeAll
//    static void addData() {
//        LoginService loginService = new LoginServiceImpl();
//        Login thangaraju = new Login("professor", "thangaraju", "thangaraju");
//        loginService.save(thangaraju);
//        Login login = new Login("committee", "foodcomm", "foodcomm");
//        loginService.save(login);
//        Login admin1 = new Login("admin", "admin1", "admin1");
//        loginService.save(admin1);
//        Login sac1 = new Login("sac", "sac1", "sac1");
//        loginService.save(sac1);
//        Login mt2019 = new Login("ta", "MT2019", "MT2019");
//        loginService.save(mt2019);
//    }

    @Test
    void checkLogin() {
        LoginService loginService = new LoginServiceImpl();

//        assertEquals(true, loginService.checkCredentials("mkd", "mkd", "admin"), "1");
//        assertEquals(true, loginService.checkCredentials("shriya", "shriya", "committee"), "2");
//        assertEquals(false, loginService.checkCredentials("mkd", "des", "admin"), "3");
//        assertEquals(false, loginService.checkCredentials("mkd", "mkd", "sac"), "4");
//        assertEquals(false, loginService.checkCredentials("shriya", "mkd", "ta"), "5");
    }
}
