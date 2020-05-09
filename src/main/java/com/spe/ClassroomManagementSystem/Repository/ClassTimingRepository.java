package com.spe.ClassroomManagementSystem.Repository;

import com.spe.ClassroomManagementSystem.Models.ClassTiming;
import com.spe.ClassroomManagementSystem.Models.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.time.OffsetDateTime;
import java.util.List;

public interface ClassTimingRepository extends JpaRepository<ClassTiming,Long> {

    boolean existsByClassroomClassroomIdAndDayOfTheWeekAndStartTimeGreaterThanAndEndTimeLessThan(
            Long id,
            Day day,
            Time startTime,
            Time endTime
    );
    List<ClassTiming> findByClassroomClassroomIdAndDayOfTheWeek(Long id,Day day);
}
