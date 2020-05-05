package com.spe.ClassroomManagementSystem.Repository;

import com.spe.ClassroomManagementSystem.Model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login,Long> {
}
