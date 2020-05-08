package com.spe.ClassroomManagementSystem.Repository;

import com.spe.ClassroomManagementSystem.Models.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom,Long> {

    Classroom findByClassCode(String classCode);


//    @Query("select u from Classroom")
//    List<Classroom> findAllClassrooms();
}
