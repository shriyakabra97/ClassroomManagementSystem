package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.ClassTiming;
import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Models.Day;
import com.spe.ClassroomManagementSystem.Models.Request;
import com.spe.ClassroomManagementSystem.Repository.ClassroomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.sql.Date;

import static com.spe.ClassroomManagementSystem.Models.RequestStatus.GRANTED;


@Service
public class ClassroomServiceImpl implements ClassroomService {
    private static final Logger logger = LoggerFactory.getLogger(ClassroomServiceImpl.class);

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private ClassTimingService classTimingService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private RequestService requestService;


    private boolean checkOverlapping(List<Interval> intervalList) {
        for (int i = 1; i < intervalList.size(); i++) {
            if (intervalList.get(i - 1).endTime.compareTo(intervalList.get(i).startTime) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Classroom findByClassCode(String classCode) {
        return classroomRepository.findByClassCode(classCode);
    }

    @Override
    public String saveClassroom(Classroom classroom) {
        String class_save_msg;
        try {
            classroomRepository.save(classroom);
            class_save_msg = "Saved Classroom Successfully";
            logger.info("Saved Classroom Successfully");
        } catch (Exception e) {
            class_save_msg = "Failed Saving, Classroom already exists";
            logger.error("Failed Saving, Classroom already exists");
        }
        return class_save_msg;
    }


    @Override
    public List<Classroom> findAllClassrooms() {
        return classroomRepository.findAll();
    }

    @Override
    public List<Classroom> getClassroomByFormFilter(long capacity, long plugs, boolean projectorAvailable) {
        return classroomRepository.findAllByCapacityGreaterThanEqualAndPlugsGreaterThanEqualAndProjector(capacity, plugs, projectorAvailable);
    }

    @Override
    public List<Classroom> getClassroomByFormFilterWithoutProjectorConstraint(long capacity, long plugs) {
        return classroomRepository.findAllByCapacityGreaterThanEqualAndPlugsGreaterThanEqual(capacity, plugs);
    }

    @Override
    public Classroom getClassroomByClassCode(String classCode) {
        return classroomRepository.findByClassCode(classCode);
    }


    @Override
    public List<Classroom> getAvailableClassrooms(long capacity, long plugs, boolean projectorNeeded, Time startTimeFormat, Time endTimeFormat, Day day, Date date) {

        List<Classroom> classroomList;
        if (projectorNeeded == true) {
            //if projector is needed, get classes with projector
            classroomList = classroomService.getClassroomByFormFilter(capacity, plugs, projectorNeeded);
        } else {
            //if projector is not checked, get classes without considering projector
            classroomList = classroomService.getClassroomByFormFilterWithoutProjectorConstraint(capacity, plugs);
        }

        //final list to be returned
        List<Classroom> finalClassroomList = new ArrayList<>();

        //for every classroom
        for (Classroom classroom : classroomList) {
            //if both will be true at the end then only classroom is available
            boolean isOverlapping = true;
            boolean isOverlapping1 = true;

            //these lists contain intervals for 1 classroom(one from classTiming,one from request table)
            List<Interval> intervalList = new ArrayList<>();
            List<Interval> intervalList1 = new ArrayList<>();

            Interval requestInterval = new Interval(startTimeFormat, endTimeFormat);
            intervalList.add(requestInterval);
            intervalList1.add(requestInterval);

            //get the rows on the basis of day from class_timing table
            List<ClassTiming> classTimingList = classTimingService.getByClassroomAndDay(classroom, day);
            //get the rows on the basis of date from request table
            List<Request> requestList = requestService.getByClassroomAndDateAndRequestStatus(classroom, date, GRANTED);

            //add all intervals in interval list
            for (ClassTiming classtiming : classTimingList) {
                Interval interval = new Interval(classtiming.getStartTime(), classtiming.getEndTime());
                intervalList.add(interval);
            }
            for (Request request : requestList) {
                Interval interval = new Interval(request.getStartTime(), request.getEndTime());
                intervalList1.add(interval);
            }
            //sort by start time
            Collections.sort(intervalList, new Comparator<Interval>() {
                //check intervals overlap or not
                @Override
                public int compare(Interval interval, Interval t1) {
                    return interval.startTime.compareTo(t1.startTime);
                }
            });
            Collections.sort(intervalList1, new Comparator<Interval>() {
                //check intervals overlap or not
                @Override
                public int compare(Interval interval, Interval t1) {
                    return interval.startTime.compareTo(t1.startTime);
                }
            });

            //sorting has happened on basis of start time.
            isOverlapping = checkOverlapping(intervalList);
            isOverlapping1 = checkOverlapping(intervalList1);

            System.out.println(classroom.getClassCode());
            System.out.println(isOverlapping);
            if (!isOverlapping && !isOverlapping1) {
                finalClassroomList.add(classroom);
            }
        }
        return finalClassroomList;

    }

}
