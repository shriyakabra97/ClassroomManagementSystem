package com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Models.ClassTiming;
import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Models.Day;
import com.spe.ClassroomManagementSystem.Service.ClassTimingService;
import com.spe.ClassroomManagementSystem.Service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private ClassTimingService classTimingService;

    @RequestMapping("/classroom")
    public RedirectView addClassroom(@RequestParam("classCode") String classCode,
                                      @RequestParam("capacity") int capacity,
                                      @RequestParam("plugs") int plugs,
                                      @RequestParam(value = "projector", required = false) String projector,
                                      HttpSession session) {
        boolean projectorAvailable = (projector == null) ? false : true;
        Classroom cr = new Classroom(classCode, capacity, projectorAvailable, plugs);
        classroomService.saveClassroom(cr);
        RedirectView rv = new RedirectView();
        rv.setUrl("/AddClassroom.jsp");
        return rv;
    }

    @RequestMapping("/getAllClassrooms")
    public RedirectView getAllClassrooms(HttpSession session){
        List<Classroom> classroomList= classroomService.findAllClassrooms();
        session.setAttribute("classroomList", classroomList);
        System.out.println(classroomList);
        RedirectView rv = new RedirectView();
        rv.setUrl("/AddTimetable.jsp");
        return rv;
    }

    private boolean checkOverlapping(List<Interval> intervalList){
        for (int i = 1; i < intervalList.size() ; i++) {
            if( intervalList.get(i-1).endTime.compareTo(intervalList.get(i).startTime ) > 0){
                return true;
            }
        }
        return false;
    }


    @RequestMapping("/getAvailableClasses")
    public RedirectView getAvailableClasses(
            @RequestParam("purpose") String purpose,
            @RequestParam("capacity") long capacity,
            @RequestParam("startTime")String startTime,
            @RequestParam("endTime") String endTime,
            @RequestParam("datepicker") Date date,
            @RequestParam("plugs") long plugs,
            @RequestParam(value = "projectorCheck", defaultValue = "false") boolean projectorNeeded,
            @RequestParam(value = "cleanCheck", defaultValue = "false") boolean cleaningNeeded,
            HttpSession session
    ){
        System.out.println("proj needed = "+projectorNeeded);
        System.out.println("cleaning needed ="+cleaningNeeded);
        Time startTimeFormat = Time.valueOf(startTime +":00");
        Time endTimeFormat = Time.valueOf(endTime +":00");
        Day day = Day.SUNDAY; //initialization
        switch (date.getDay()){
            case 0:  day = Day.SUNDAY; break;
            case 1:  day = Day.MONDAY; break;
            case 2:  day = Day.TUESDAY; break;
            case 3:  day = Day.WEDNESDAY; break;
            case 4:  day = Day.THURSDAY; break;
            case 5:  day = Day.FRIDAY; break;
            case 6:  day = Day.SATURDAY; break;
        }
        List<Classroom> classroomList;
        if(projectorNeeded == true) {

            classroomList = classroomService.getClassroomByFormFilter(capacity, plugs, projectorNeeded);
        }else {
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
        session.removeAttribute("availableClassrooms");
        session.setAttribute("availableClassrooms", finalClassroomList);
        //System.out.println(finalClassroomList.get(0).getClassCode());

        RedirectView rv = new RedirectView();
        rv.setUrl("/AvailableClassrooms.jsp");
        return rv;
    }
}