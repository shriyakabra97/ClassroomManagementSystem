package com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Models.ClassTiming;
import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Models.Day;
import com.spe.ClassroomManagementSystem.Service.ClassroomService;
import com.spe.ClassroomManagementSystem.Service.ClassTimingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class TimeTableController {
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private ClassTimingService classTimingService;

    private boolean checkOverlapping(List<Interval> intervalList){
        for (int i = 1; i < intervalList.size() ; i++) {
            if( intervalList.get(i-1).endTime.compareTo(intervalList.get(i).startTime ) > 0){
                return true;
            }
        }
        return false;
    }


    @RequestMapping("/timetable")
    public RedirectView saveTimetable(@RequestParam("classCode") String classCode,
                                      @RequestParam("day") String day,
                                      @RequestParam("startTime") String startTime,
                                      @RequestParam("endTime") String endTime,
                                      HttpSession session) {
        Classroom classroom=classroomService.findByClassCode(classCode);
        ClassTiming classTiming = new ClassTiming();
        classTiming.setClassroom(classroom);
        classTiming.setStartTime(Time.valueOf(startTime + ":00"));
        classTiming.setEndTime(Time.valueOf(endTime + ":00"));
        classTiming.setDayOfTheWeek(Day.valueOf(day));
        classTimingService.saveTimetable(classTiming);
        RedirectView rv = new RedirectView();
        rv.setUrl("/AddTimetable.jsp");
        return rv;
    }

    @RequestMapping("/saveInClassTimings")
    public RedirectView saveInClassTimings(
            @RequestParam("classCode") String classCode,
            @RequestParam("day") String day,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            HttpSession session){
        Day day1 = Day.SUNDAY;//initialization
        switch (day){
            case "SUNDAY": day1=Day.SUNDAY;break;
            case "MONDAY": day1=Day.MONDAY;break;
            case "TUESDAY": day1=Day.TUESDAY;break;
            case "WEDNESDAY": day1=Day.WEDNESDAY;break;
            case "THURSDAY": day1=Day.THURSDAY;break;
            case "FRIDAY": day1=Day.FRIDAY;break;
            case "SATURDAY": day1=Day.SATURDAY;break;
        }
        Time startTimeFormat = Time.valueOf(startTime +":00");
        Time endTimeFormat = Time.valueOf(endTime +":00");
        //get classroom
        Classroom classroom = classroomService.getClassroomByClassCode(classCode);
        //get classtimings
        List<ClassTiming> classTimingList = classTimingService.getByClassroomAndDay(classroom, day1);
        boolean isOverlapping = true ;
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
        }else {
            session.setAttribute("save_message", "Error entering in DB..overlapping time");
        }

        //redirect
        System.out.println(session.getAttribute("save_messsage"));
        RedirectView rv = new RedirectView();
        rv.setUrl("AddTimetable.jsp");
        return rv;

    }


}
