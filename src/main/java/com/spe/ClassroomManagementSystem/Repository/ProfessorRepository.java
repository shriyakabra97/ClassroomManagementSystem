package com.spe.ClassroomManagementSystem.Repository;

import com.spe.ClassroomManagementSystem.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}
