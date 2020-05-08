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

@RestController
public class TimeTableController {
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private ClassTimingService classTimingService;

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
}
