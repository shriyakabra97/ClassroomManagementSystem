package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.ClassTiming;
import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Models.Day;
import com.spe.ClassroomManagementSystem.Repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



@Service
public class ClassroomServiceImpl implements ClassroomService{
    @Autowired
    private ClassroomRepository classroomRepository;

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
    public Classroom findByClassCode(String classCode){
        return classroomRepository.findByClassCode(classCode);
    }

    @Override
    public Classroom saveClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }


    @Override
    public List<Classroom> findAllClassrooms(){
        return classroomRepository.findAll();
    }

    @Override
    public List<Classroom> getClassroomByFormFilter(long capacity, long plugs, boolean projectorAvailable){
        return classroomRepository.findAllByCapacityGreaterThanEqualAndPlugsGreaterThanEqualAndProjector(capacity, plugs, projectorAvailable);
    }

    @Override
    public  List<Classroom> getClassroomByFormFilterWithoutProjectorConstraint(long capacity, long plugs){
        return classroomRepository.findAllByCapacityGreaterThanEqualAndPlugsGreaterThanEqual(capacity, plugs);
    }

    @Override
    public  Classroom getClassroomByClassCode(String classCode){
        return classroomRepository.findByClassCode(classCode);
    }

    @Override
    public List<Classroom> getAvailableClassrooms(long capacity, long plugs, boolean projectorNeeded, Time startTimeFormat, Time endTimeFormat, Day day){

        List<Classroom> classroomList;
        if(projectorNeeded == true) {
            //if projector is needed, get classes with projector
            classroomList = classroomService.getClassroomByFormFilter(capacity, plugs, projectorNeeded);
        }else {
            //if projector is not checked, get classes without considering projector
            classroomList = classroomService.getClassroomByFormFilterWithoutProjectorConstraint(capacity, plugs);
        }
        List<Classroom> finalClassroomList = new ArrayList<>();


        for (Classroom classroom:classroomList) {
            boolean isOverlapping = true ;
            List<Interval> intervalList = new ArrayList<>();
            Interval requestInterval = new Interval(startTimeFormat, endTimeFormat);
            intervalList.add(requestInterval);
            List<ClassTiming> classTimingList = classTimingService.getByClassroomAndDay(classroom, day);
            //check intervals overlap or not
            //add all intervals in interval list
            for (ClassTiming classtiming: classTimingList ) {
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
            isOverlapping = checkOverlapping(intervalList);

            System.out.println(classroom.getClassCode());
            System.out.println(isOverlapping);
            if (!isOverlapping){
                finalClassroomList.add(classroom);
            }
        }
        return finalClassroomList;

    }

}
