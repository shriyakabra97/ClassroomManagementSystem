package com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Models.Day;
import com.spe.ClassroomManagementSystem.Service.ClassTimingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.sql.Time;

@RestController
public class TimeTableController {
    private static final Logger logger = LoggerFactory.getLogger(TimeTableController.class);

    @Autowired
    private ClassTimingService classTimingService;


    @RequestMapping("/saveInClassTimings")
    public RedirectView saveInClassTimings(
            @RequestParam("classCode") String classCode,
            @RequestParam("day") String day,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            HttpSession session){
        logger.trace("saveInClassTimings called");
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

        boolean retVal = classTimingService.saveInClassTiming(classCode, startTimeFormat, endTimeFormat, day1, session);
        //System.out.println("retVal = " + retVal);
        //System.out.println(session.getAttribute("save_messsage"));
        RedirectView rv = new RedirectView();
        rv.setUrl("AddTimetable.jsp");
        return rv;

    }
}
