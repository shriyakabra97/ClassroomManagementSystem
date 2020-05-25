package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.Sac;
import com.spe.ClassroomManagementSystem.Repository.SacRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SacServiceImplTest {
    @Autowired
    private SacService sacService ;
    @MockBean
    private SacRepository sacRepository;
    @Test
    void saveSac() {
        Login login = new Login("sac", "sac", "password");
        Sac sac = new Sac("sac", "SAC", "sac@gmail.com", login);
        when(sacRepository.save(sac)).thenReturn(
                sac
        );
        assertEquals("SAC Saved Successfully", sacService.saveSac(sac));
    }

    @Test
    void findByForeignId() {
        Login login = new Login("sac", "sac", "password");
        when(sacRepository.findByForeignId(login)).thenReturn(
                new Sac("sac", "SAC", "sac@gmail.com", login)
        );
        assertEquals("SAC", sacService.findByForeignId(login).getSacName());
    }
}