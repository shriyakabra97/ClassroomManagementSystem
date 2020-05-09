package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.ClassTiming;
import com.spe.ClassroomManagementSystem.Models.Day;
import com.spe.ClassroomManagementSystem.Repository.ClassTimingRepository;
import jdk.internal.net.http.common.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
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
    public boolean checkOverlappingInterval(Long id, Day day){
        List<ClassTiming> classTimingsList=classTimingRepository.findByClassroomClassroomIdAndDayOfTheWeek(id,day);
        List<Pair<Time, Character>> times=new ArrayList<Pair<Time, Character>>();
        for(ClassTiming classTiming:classTimingsList){
                times.add(new Pair<Time, Character>(classTiming.getStartTime(),'s'));
                times.add(new Pair<Time, Character>(classTiming.getEndTime(),'d'));
        }
        Collections.sort(times);
        int max=600;
        List<Integer> ans=new ArrayList<>();
        for ()
        return true;
    }
}
