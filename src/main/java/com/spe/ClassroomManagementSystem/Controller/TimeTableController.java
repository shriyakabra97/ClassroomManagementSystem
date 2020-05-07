package com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Models.ClassTimings;
import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Models.Day;
import com.spe.ClassroomManagementSystem.Service.ClassroomService;
import com.spe.ClassroomManagementSystem.Service.ClassTimingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;

@RestController
public class TimeTableController {
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private ClassTimingService classTimingService;

    @RequestMapping("/timetable")
    public ResponseEntity<ClassTimings> saveTimetable(@RequestParam("classCode") String classCode, @RequestParam("day") Day day, @RequestParam("startTime")Time startTime, @RequestParam("endTime") Time endTime){
        Classroom classroom=classroomService.findByClassCode(classCode);
        ClassTimings classTimings=new ClassTimings();
        classTimings.setClassroom(classroom);
        classTimings.setStartTime(startTime);
        classTimings.setEndTime(endTime);
        classTimings.setDayOfTheWeek(day);
        return ResponseEntity.ok(classTimingService.saveTimetable(classTimings));
    }
}
