package com.spe.ClassroomManagementSystem.Repository;

import com.spe.ClassroomManagementSystem.Models.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login,Long> {

    Login findByUserNameAndPassword(String username,String password);

    Login findByUserNameAndUserType(String username, String userType);

    Login findByLoginId(Long loginId);
}
