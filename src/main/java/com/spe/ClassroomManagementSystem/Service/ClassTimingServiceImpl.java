package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.ClassTiming;
import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Models.Day;
import com.spe.ClassroomManagementSystem.Repository.ClassTimingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class ClassTimingServiceImpl implements ClassTimingService {
    @Autowired
    private ClassTimingRepository classTimingRepository;

    @Override
    public ClassTiming saveTimetable(ClassTiming classTimings){
        return classTimingRepository.save(classTimings);
    }

    @Override
    public List<ClassTiming> getByClassroomAndDay(Classroom classroom, Day day){
        return classTimingRepository.getAllByClassroomAndDayOfTheWeek(classroom, day);
    }

}
