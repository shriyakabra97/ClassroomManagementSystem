package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.ClassTiming;
import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Models.Day;
import com.spe.ClassroomManagementSystem.Models.Request;
import com.spe.ClassroomManagementSystem.Repository.ClassTimingRepository;
import com.spe.ClassroomManagementSystem.Utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.spe.ClassroomManagementSystem.Models.RequestStatus.GRANTED;

@Service
public class ClassTimingServiceImpl implements ClassTimingService {
    private static final Logger logger = LoggerFactory.getLogger(ClassTimingServiceImpl.class);

    @Autowired
    private ClassTimingRepository classTimingRepository;

    @Autowired
    private ClassTimingService classTimingService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private RequestService requestService;

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
    public boolean checkAvailableClassroom(Classroom classroom,Date date,Time startTime, Time endTime)
    {
        Day day = Day.SUNDAY; //random initialization
        switch (DateUtils.getDayOfTheWeekFromDate(date)){
            case 1:  day = Day.SUNDAY; break;
            case 2:  day = Day.MONDAY; break;
            case 3:  day = Day.TUESDAY; break;
            case 4:  day = Day.WEDNESDAY; break;
            case 5:  day = Day.THURSDAY; break;
            case 6:  day = Day.FRIDAY; break;
            case 7:  day = Day.SATURDAY; break;
        }
        boolean isOverlapping = true ;
        boolean isOverlapping1=true;
        List<Interval> intervalList = new ArrayList<>();
        List<Interval> intervalList1= new ArrayList<>();

        Interval requestInterval = new Interval(startTime, endTime);
        intervalList.add(requestInterval);
        intervalList1.add(requestInterval);

        //get the rows on the basis of day from class_timing table
        List<ClassTiming> classTimingList = classTimingService.getByClassroomAndDay(classroom, day);
        //get the rows on the basis of date from request table
        List<Request> requestList = requestService.getByClassroomAndDateAndRequestStatus(classroom, date,GRANTED);

        //add all intervals in interval list
        for (ClassTiming classtiming: classTimingList ) {
            Interval interval = new Interval(classtiming.getStartTime(), classtiming.getEndTime());
            intervalList.add(interval);
        }
        for(Request request: requestList) {
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
        isOverlapping1=checkOverlapping(intervalList1);

        System.out.println(isOverlapping+" "+isOverlapping1);
        if (!isOverlapping && !isOverlapping1) {
            return true;
        }
        else
            return false;
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
            logger.info("Timetable saved successfully");
            //session.setAttribute("save_message", "Saved successfully..");
            returnVal = true;
        }else {
            logger.error("Error entering in DB..overlapping time");
            //session.setAttribute("save_message", "Error entering in DB..overlapping time");
            returnVal= false;
        }

        return returnVal;

    }

}
