package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Classroom;

public interface ClassroomService {
    Classroom findByClassCode(String classCode);

    Classroom saveClassroom(Classroom cr);
}
