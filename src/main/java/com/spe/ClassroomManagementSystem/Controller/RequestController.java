package com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.sql.Date;

@RestController
public class RequestController {

    @Autowired
    private RequestService requestService;

    @RequestMapping("/request")
    public void classroomRequest(@PathParam("classCode") String classCode,
                                 @PathParam("loginId") Long requestorId,
                                 @PathParam("userName") String requestorName,
                                 @RequestParam("purpose") String purpose,
                                 @RequestParam("startTime")String startTime,
                                 @RequestParam("endTime") String endTime,
                                 @RequestParam("datepicker") Date date,
                                 @RequestParam("plugs") long plugs,
                                 @RequestParam(value = "projectorCheck", defaultValue = "false") boolean projectorNeeded,
                                 @RequestParam(value = "cleanCheck", defaultValue = "false") boolean cleaningNeeded,
                                 HttpSession session){


    }
}
