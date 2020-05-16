package  com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Models.*;
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


    @RequestMapping("/postRequest/{classCode}")
    public RedirectView classroomRequest(HttpSession session,
                                 @PathVariable String classCode){

//       session.setAttribute();
       boolean success_requested = requestService.saveRequest(session, classCode);

       //redirect acc to user type
        RedirectView rv = new RedirectView();

        switch ((String) session.getAttribute("userType")){
            case "professor": rv.setUrl("/ProfessorDashboard.jsp");break;
            case "ta":rv.setUrl("/TADashboard.jsp");break;
            case "committee":rv.setUrl("/CommitteeDashboard.jsp");break;
            case "sac":rv.setUrl("/SACDashboard.jsp");break;

        }
       return rv;

    }


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