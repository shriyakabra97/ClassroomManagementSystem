package com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
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
}