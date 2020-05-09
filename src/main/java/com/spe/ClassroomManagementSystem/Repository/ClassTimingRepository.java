package com.spe.ClassroomManagementSystem.Repository;

import com.spe.ClassroomManagementSystem.Models.ClassTiming;
import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Models.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.util.List;

public interface ClassTimingRepository extends JpaRepository<ClassTiming,Long> {

   // List<ClassTiming> getAllByDayOfTheWeekAndStartTimeGreaterThanEqualAndEndTimeLessThanEqual(Day day, Time startTimeFormat, Time endTimeFormat);

    //List<ClassTiming> getAllByClassroomAndEndTimeBeforeOrStartTimeAfter(Classroom classroom, Time startTime, Time endTime );

    List<ClassTiming> getAllByClassroomAndDayOfTheWeek(Classroom classroom, Day day);
}
