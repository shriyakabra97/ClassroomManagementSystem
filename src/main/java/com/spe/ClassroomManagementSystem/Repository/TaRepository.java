package com.spe.ClassroomManagementSystem.Repository;

import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.Sac;
import com.spe.ClassroomManagementSystem.Models.TA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaRepository extends JpaRepository<TA,Long> {

    TA  findByForeignId(Login login);
}
