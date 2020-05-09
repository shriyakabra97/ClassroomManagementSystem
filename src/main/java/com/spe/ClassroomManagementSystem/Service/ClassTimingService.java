package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.ClassTiming;
import com.spe.ClassroomManagementSystem.Models.Day;

public interface ClassTimingService {
    ClassTiming saveTimetable(ClassTiming classTimings);

    boolean checkOverlappingInterval(Long id, Day day);
}
