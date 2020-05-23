package com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Models.Day;
import com.spe.ClassroomManagementSystem.Service.ClassroomService;
import com.spe.ClassroomManagementSystem.Utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@RestController
public class ClassroomController {
    private static final Logger logger = LoggerFactory.getLogger(ClassroomController.class);

    @Autowired
    private ClassroomService classroomService;

    @RequestMapping("/classroom")
    public RedirectView addClassroom(@RequestParam("classCode") String classCode,
                                      @RequestParam("capacity") int capacity,
                                      @RequestParam("plugs") int plugs,
                                      @RequestParam(value = "projector", required = false) String projector,
                                      HttpSession session) {
        logger.trace("addClassroom called");
        boolean projectorAvailable = (projector == null) ? false : true;
        Classroom cr = new Classroom(classCode, capacity, projectorAvailable, plugs);
        String class_save_msg = classroomService.saveClassroom(cr);
        session.setAttribute("class_save_msg", class_save_msg);
        RedirectView rv = new RedirectView();
        rv.setUrl("/AddClassroom.jsp");
        return rv;
    }

    @RequestMapping("/getAllClassrooms")
    public RedirectView getAllClassrooms(HttpSession session){
        logger.trace("getAllClassroom called");
        List<Classroom> classroomList= classroomService.findAllClassrooms();
        session.setAttribute("classroomList", classroomList);
        RedirectView rv = new RedirectView();
        rv.setUrl("/AddTimetable.jsp");
        return rv;
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
        logger.trace("getAvailableClasses called");
        Time startTimeFormat = Time.valueOf(startTime +":00");
        Time endTimeFormat = Time.valueOf(endTime +":00");
        Day day = Day.SUNDAY; //random initialization
        switch (DateUtils.getDayOfTheWeekFromDate(date)) {
            case 1:  day = Day.SUNDAY; break;
            case 2:  day = Day.MONDAY; break;
            case 3:  day = Day.TUESDAY; break;
            case 4:  day = Day.WEDNESDAY; break;
            case 5:  day = Day.THURSDAY; break;
            case 6:  day = Day.FRIDAY; break;
            case 7:  day = Day.SATURDAY; break;
        }
        List<Classroom> finalClassroomList;
        if (purpose.equals("exams")) {
            capacity = capacity * 2;
        }
        finalClassroomList = classroomService.getAvailableClassrooms(capacity, plugs, projectorNeeded, startTimeFormat, endTimeFormat, day,date);

        session.removeAttribute("availableClassrooms");
        session.setAttribute("availableClassrooms", finalClassroomList);

        session.setAttribute("purpose", purpose);
        session.setAttribute("reqStartTime", startTimeFormat);
        session.setAttribute("reqEndTime", endTimeFormat);
        session.setAttribute("cleaningNeeded", cleaningNeeded);
        session.setAttribute("projectorNeeded", projectorNeeded);
        session.setAttribute("reqPlugs", plugs);
        session.setAttribute("reqDate", date);
        session.setAttribute("reqCapacity", capacity);

        RedirectView rv = new RedirectView();
        rv.setUrl("/AvailableClassrooms.jsp");
        return rv;
    }
}