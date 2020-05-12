package com.spe.ClassroomManagementSystem.Repository;

import com.spe.ClassroomManagementSystem.Models.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
