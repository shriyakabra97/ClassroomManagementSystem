package com.spe.ClassroomManagementSystem.Repository;

import com.spe.ClassroomManagementSystem.Model.Committee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitteeRepository extends JpaRepository<Committee,Long> {
}
