package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Committee;
import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Repository.CommitteeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CommitteeServiceImplTest {

    @Autowired
    private CommitteeService committeeService;
    @MockBean
    private CommitteeRepository committeeRepository;
    @Test
    void saveCommittee() {

    }

    @Test
    void findByForeignId() {

    }
}