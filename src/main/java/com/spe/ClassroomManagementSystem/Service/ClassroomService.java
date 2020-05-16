package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Models.Day;

import java.sql.Time;
import java.util.List;
import java.sql.Date;
public interface ClassroomService {
    Classroom findByClassCode(String classCode);

    String saveClassroom(Classroom cr);

    List<Classroom> findAllClassrooms();

    List<Classroom> getClassroomByFormFilter(long capacity, long plugs, boolean projectorAvailable);

    List<Classroom> getClassroomByFormFilterWithoutProjectorConstraint(long capacity, long plugs);

    Classroom getClassroomByClassCode(String classCode);

    List<Classroom> getAvailableClassrooms(long capacity, long plugs, boolean projectorNeeded, Time startTimeFormat, java.sql.Time endTimeFormat, Day day,Date date);


}
