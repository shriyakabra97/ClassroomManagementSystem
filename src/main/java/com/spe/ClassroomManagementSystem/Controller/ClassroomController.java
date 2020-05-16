package com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Models.Day;
import com.spe.ClassroomManagementSystem.Models.Professor;
import com.spe.ClassroomManagementSystem.Service.ClassroomService;
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
    @Autowired
    private ClassroomService classroomService;

    @RequestMapping("/classroom")
    public RedirectView addClassroom(@RequestParam("classCode") String classCode,
                                      @RequestParam("capacity") int capacity,
                                      @RequestParam("plugs") int plugs,
                                      @RequestParam(value = "projector", required = false) String projector,
                                      HttpSession session) {
        boolean projectorAvailable = (projector == null) ? false : true;
        Classroom cr = new Classroom(classCode, capacity, projectorAvailable, plugs);

        String class_save_msg = classroomService.saveClassroom(cr);
        session.setAttribute("class_save_msg", class_save_msg);
        RedirectView rv = new RedirectView();
        rv.setUrl("/AddClassroom");
        return rv;
    }

    @RequestMapping("/getAllClassrooms")
    public RedirectView getAllClassrooms(HttpSession session){
        List<Classroom> classroomList= classroomService.findAllClassrooms();
        session.setAttribute("classroomList", classroomList);
        System.out.println(classroomList);
        RedirectView rv = new RedirectView();
        rv.setUrl("/AddTimetable");
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

        System.out.println("proj needed = "+projectorNeeded);
        System.out.println("cleaning needed ="+cleaningNeeded);
        Time startTimeFormat = Time.valueOf(startTime +":00");
        Time endTimeFormat = Time.valueOf(endTime +":00");
        Day day = Day.SUNDAY; //random initialization
        switch (date.getDay()){
            case 0:  day = Day.SUNDAY; break;
            case 1:  day = Day.MONDAY; break;
            case 2:  day = Day.TUESDAY; break;
            case 3:  day = Day.WEDNESDAY; break;
            case 4:  day = Day.THURSDAY; break;
            case 5:  day = Day.FRIDAY; break;
            case 6:  day = Day.SATURDAY; break;
        }
        List<Classroom> finalClassroomList;
        if (purpose.equals("exams")) {
            capacity = capacity * 2;
        }
        finalClassroomList = classroomService.getAvailableClassrooms(capacity, plugs, projectorNeeded, startTimeFormat, endTimeFormat, day,date);

        session.removeAttribute("availableClassrooms");
        session.setAttribute("availableClassrooms", finalClassroomList);
        session.setAttribute("purpose",purpose);
        session.setAttribute("startTime",startTimeFormat);
        session.setAttribute("endTime",endTimeFormat);
        session.setAttribute("datepicker",date);
        session.setAttribute("plugs",plugs);
        session.setAttribute("projectorCheck",projectorNeeded);
        session.setAttribute("cleanCheck",cleaningNeeded);

        //System.out.println(finalClassroomList.get(0).getClassCode());

        session.setAttribute("purpose", purpose);
        session.setAttribute("reqStartTime", startTimeFormat);
        session.setAttribute("reqEndTime", endTimeFormat);
        session.setAttribute("cleaningNeeded", cleaningNeeded);
        session.setAttribute("projectorNeeded", projectorNeeded);
        session.setAttribute("reqPlugs", plugs);
        session.setAttribute("reqDate", date);
        session.setAttribute("reqCapacity", capacity);

        RedirectView rv = new RedirectView();
        rv.setUrl("/AvailableClassrooms");
        return rv;
    }
}