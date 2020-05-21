package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.Sac;
import com.spe.ClassroomManagementSystem.Models.TA;

public interface SacService {

    String saveSac(Sac sac);
    Sac findByForeignId(Login login);
}
