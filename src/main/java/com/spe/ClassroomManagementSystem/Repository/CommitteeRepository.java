package com.spe.ClassroomManagementSystem.Repository;

import com.spe.ClassroomManagementSystem.Models.Committee;
import com.spe.ClassroomManagementSystem.Models.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitteeRepository extends JpaRepository<Committee,Long> {

    Committee findByForeignId(Login login);
}
