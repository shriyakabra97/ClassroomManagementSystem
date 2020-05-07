package com.spe.ClassroomManagementSystem.Repository;

import com.spe.ClassroomManagementSystem.Models.ClassTimings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassTimingRepository extends JpaRepository<ClassTimings,Long> {
}
