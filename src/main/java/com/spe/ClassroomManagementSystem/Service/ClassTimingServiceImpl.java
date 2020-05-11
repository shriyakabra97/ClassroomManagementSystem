package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.ClassTiming;
import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Models.Day;
import com.spe.ClassroomManagementSystem.Repository.ClassTimingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ClassTimingServiceImpl implements ClassTimingService {
    @Autowired
    private ClassTimingRepository classTimingRepository;

    @Autowired
    private ClassTimingService classTimingService;

    @Autowired
    private ClassroomService classroomService;

    private boolean checkOverlapping(List<Interval> intervalList){
        for (int i = 1; i < intervalList.size() ; i++) {
            if( intervalList.get(i-1).endTime.compareTo(intervalList.get(i).startTime ) > 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public ClassTiming saveTimetable(ClassTiming classTimings){
        return classTimingRepository.save(classTimings);
    }

    @Override
    public List<ClassTiming> getByClassroomAndDay(Classroom classroom, Day day){
        return classTimingRepository.getAllByClassroomAndDayOfTheWeek(classroom, day);
    }
    @Override
    public boolean saveInClassTiming(String classCode, Time startTimeFormat, Time endTimeFormat, Day day1, HttpSession session){
        Classroom classroom = classroomService.getClassroomByClassCode(classCode);
        List<ClassTiming> classTimingList = classTimingService.getByClassroomAndDay(classroom, day1);
        boolean returnVal = true;
        boolean isOverlapping;
        //create an interval list
        List<Interval> intervalList = new ArrayList<>();
        //this interval is requested
        Interval requestInterval = new Interval(startTimeFormat, endTimeFormat);
        //add requested interval to interval list
        intervalList.add(requestInterval);
        //add all intervals from classTimingList to intervalList
        for (ClassTiming classtiming: classTimingList) {
            System.out.println("hello");
            Interval interval = new Interval(classtiming.getStartTime(), classtiming.getEndTime());
            intervalList.add(interval);
        }
        //sort by start time
        Collections.sort(intervalList, new Comparator<Interval>() {
            @Override
            public int compare(Interval interval, Interval t1) {
                return interval.startTime.compareTo(t1.startTime);
            }
        });
        //sorting has happened on basis of start time.
        //check if they overlap
        isOverlapping = checkOverlapping(intervalList);
        if (!isOverlapping){
            //save in db if no overlapping
            //create classTimingObject
            ClassTiming classTiming = new ClassTiming(day1, startTimeFormat, endTimeFormat, classroom);
            //save in db
            classTimingService.saveTimetable(classTiming);
            session.setAttribute("save_message", "Saved successfully..");
            returnVal = true;
        }else {
            session.setAttribute("save_message", "Error entering in DB..overlapping time");
            returnVal= false;
        }

        return returnVal;

    }

}
