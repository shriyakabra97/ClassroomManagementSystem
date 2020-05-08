package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.ClassTiming;
import com.spe.ClassroomManagementSystem.Repository.ClassTimingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassTimingServiceImpl implements ClassTimingService {
    @Autowired
    private ClassTimingRepository classTimingRepository;

    @Override
    public ClassTiming saveTimetable(ClassTiming classTimings){
        return classTimingRepository.save(classTimings);
    }

}
