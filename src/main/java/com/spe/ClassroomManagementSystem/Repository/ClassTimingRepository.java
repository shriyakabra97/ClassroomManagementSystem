package com.spe.ClassroomManagementSystem.Repository;

import com.spe.ClassroomManagementSystem.Models.ClassTiming;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassTimingRepository extends JpaRepository<ClassTiming,Long> {
}
