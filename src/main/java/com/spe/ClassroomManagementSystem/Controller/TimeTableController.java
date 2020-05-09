package com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Models.ClassTiming;
import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Models.Day;
import com.spe.ClassroomManagementSystem.Repository.ClassTimingRepository;
import com.spe.ClassroomManagementSystem.Service.ClassroomService;
import com.spe.ClassroomManagementSystem.Service.ClassTimingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.util.List;

@RestController
public class TimeTableController {
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private ClassTimingService classTimingService;
    @Autowired
    private ClassTimingRepository classTimingRepository;
    @RequestMapping("/timetable")
    public RedirectView saveTimetable(@RequestParam("classCode") String classCode,
                                      @RequestParam("day") String day,
                                      @RequestParam("startTime") String startTime,
                                      @RequestParam("endTime") String endTime,
                                      HttpSession session) {
        Classroom classroom=classroomService.findByClassCode(classCode);
        System.out.println("classroom id:"+classroom.getClassroomId());
        System.out.println("day:"+Day.valueOf(day));
        System.out.println("startTime:"+Time.valueOf(startTime + ":00"));
        System.out.println("endTime:"+Time.valueOf(endTime + ":00"));
        Long id=classroom.getClassroomId();
        boolean ans=classTimingRepository.existsByClassroomClassroomIdAndDayOfTheWeekAndStartTimeGreaterThanAndEndTimeLessThan(
             id,Day.valueOf(day), (Time.valueOf(endTime + ":00")),Time.valueOf(endTime + ":00")
        );
        RedirectView rv = new RedirectView();
        System.out.println("ans: "+ans);
        if(ans==false) {

            ClassTiming classTiming = new ClassTiming();
            classTiming.setClassroom(classroom);
            classTiming.setStartTime(Time.valueOf(startTime + ":00"));
            classTiming.setEndTime(Time.valueOf(endTime + ":00"));
            classTiming.setDayOfTheWeek(Day.valueOf(day));
            classTimingService.saveTimetable(classTiming);

            rv.setUrl("/AddTimetable.jsp");
        }else{
            classTimingService.checkOverlappingInterval(id,Day.valueOf(day));
            System.out.println("overlapping interval");
            rv.setUrl("/Error.jsp");
        }
        return rv;
    }
}
