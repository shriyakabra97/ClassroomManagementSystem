package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Classroom;

import java.util.List;

public interface ClassroomService {
    Classroom findByClassCode(String classCode);

    Classroom saveClassroom(Classroom cr);

    List<Classroom> findAllClassrooms();
}
