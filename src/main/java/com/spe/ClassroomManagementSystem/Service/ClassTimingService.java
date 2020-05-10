package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.ClassTiming;
import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Models.Day;

import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.util.List;

public interface ClassTimingService {
    ClassTiming saveTimetable(ClassTiming classTimings);

    List<ClassTiming> getByClassroomAndDay(Classroom classroom, Day day);

    boolean saveInClassTiming(String classCode, Time startTimeFormat, Time endTimeFormat, Day day1, HttpSession session);
}
