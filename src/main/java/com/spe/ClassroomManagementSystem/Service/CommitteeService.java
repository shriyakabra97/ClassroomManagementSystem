package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Committee;
import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.TA;

public interface CommitteeService {

    String saveCommittee(Committee committee);
    Committee findByForeignId(Login login);
}
