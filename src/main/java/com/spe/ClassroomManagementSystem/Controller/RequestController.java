package  com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Models.*;
import com.spe.ClassroomManagementSystem.Repository.ClassroomRepository;
import com.spe.ClassroomManagementSystem.Service.ClassTimingService;
import com.spe.ClassroomManagementSystem.Service.ClassroomService;
import com.spe.ClassroomManagementSystem.Service.Interval;
import com.spe.ClassroomManagementSystem.Service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static com.spe.ClassroomManagementSystem.Models.RequestStatus.REQUESTED;

@RestController
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private ClassTimingService classTimingService;

    @Autowired
    private ClassroomRepository classroomRepository;


    @RequestMapping("/postRequest/{classCode}")
    public RedirectView classroomRequest(HttpSession session,
                                 @PathVariable String classCode)
    {

       boolean success_requested = requestService.saveRequest(session, classCode);

       //redirect acc to user type
        RedirectView rv = new RedirectView();
        switch ((String) session.getAttribute("userType"))
        {
            case "professor": rv.setUrl("/ProfessorDashboard.jsp");break;
            case "ta":rv.setUrl("/TADashboard.jsp");break;
            case "committee":rv.setUrl("/CommitteeDashboard.jsp");break;
            case "sac":rv.setUrl("/SACDashboard.jsp");break;

        }
       return rv;

    }


    //rejecting the request
    @RequestMapping("/rejectRequest/{loginId}/{classroomId}/{requestId}")
    public RedirectView rejectRequest(HttpSession session,
                                         @PathVariable Long loginId,
                                         @PathVariable Long classroomId,
                                         @PathVariable Long requestId)
    {
        session.setAttribute("rejectMsg",null);
        boolean success=requestService.saveRejectedRequest(requestId);

        //request successfully rejected
        RedirectView rv = new RedirectView();
        if(success==true)
        { session.setAttribute("rejectMsg", "Request successfully rejected");}
        else
        { session.setAttribute("rejectMsg", "Request cann't be processed.Please try again");}

        rv.setUrl("/acceptReject.jsp");
        return rv;
    }

    //accepting the request
    @RequestMapping("/acceptRequest/{loginId}/{classroomId}/{requestId}/{date}/{startTime}/{endTime}")
    public RedirectView acceptRequest(HttpSession session,
                                         @PathVariable Long loginId,
                                         @PathVariable Long classroomId,
                                         @PathVariable Long requestId,
                                        @PathVariable Date date,
                                        @PathVariable Time startTime,
                                        @PathVariable Time endTime)
    {
        session.setAttribute("acceptMsg",null);

        Classroom classroom=classroomRepository.findByClassroomId(classroomId);
        boolean classAvailable=classTimingService.checkAvailableClassroom(classroom,date,startTime,endTime);


        RedirectView rv = new RedirectView();
        //class is available and request can be accepted
        if(classAvailable==true)
        {
            boolean success=requestService.saveAcceptedRequest(requestId);
            //request successfully rejected
            if(success==true)
            { session.setAttribute("acceptMsg", "Classroom available.Request successfully accepted.");}
            else
            { session.setAttribute("acceptMsg", "Request cann't be processed.Please try again!");}
        }


        //class not available..rejecting the request
        else
        {
            boolean success=requestService.saveRejectedRequest(requestId);
            //request successfully rejected
            if(success==true)
            { session.setAttribute("acceptMsg", "Classroom not available.Request successfully rejected.");}
            else
            { session.setAttribute("acceptMsg", "Request cann't be processed.Please try again!");}
        }
        rv.setUrl("/acceptReject.jsp");
        return rv;
    }

    //get all requests with requested status---to be displayed on admin's dashboard
    @RequestMapping("/getAllRequests")
    public RedirectView classroomRequest(HttpSession session)
    {
        List<Request> currentRequestsList = requestService.getByRequestStatus(REQUESTED);

        RedirectView rv=new RedirectView();
        session.setAttribute("currentRequests",currentRequestsList);
        rv.setUrl("/ViewRequests.jsp");
        return rv;
    }



}