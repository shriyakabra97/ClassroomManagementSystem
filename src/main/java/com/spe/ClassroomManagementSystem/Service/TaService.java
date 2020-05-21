package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.Sac;
import com.spe.ClassroomManagementSystem.Models.TA;

public interface TaService {
    String saveTa(TA ta);
    TA findByForeignId(Login login);
}
