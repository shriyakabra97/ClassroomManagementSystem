package  com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Models.*;
import com.spe.ClassroomManagementSystem.Repository.ClassroomRepository;
import com.spe.ClassroomManagementSystem.Service.*;
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
import java.util.Objects;

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
    @RequestMapping("/rejectRequest/{index}/{loginId}/{classroomId}/{requestId}")
    public RedirectView rejectRequest(HttpSession session,
                                     @PathVariable int index,
                                     @PathVariable Long loginId,
                                     @PathVariable Long classroomId,
                                     @PathVariable Long requestId

                                      )
    {
        session.setAttribute("rejectMsg",null);
        boolean success=requestService.saveRejectedRequest(requestId);
        RedirectView rv = new RedirectView();

        if (success) {
            rv = this.classroomRequest(session);
            session.setAttribute("view_req_msg","Request Rejected Successfully");
        }else {
            session.setAttribute("view_req_msg","Error Rejecting Request");
        }

        rv.setUrl("/ViewRequests.jsp");
        return rv;
    }

    //accepting the request
    @RequestMapping("/acceptRequest/{index}/{loginId}/{classroomId}/{requestId}/{date}/{startTime}/{endTime}")
    public RedirectView acceptRequest(HttpSession session,
                                         @PathVariable int index,
                                         @PathVariable Long loginId,
                                         @PathVariable Long classroomId,
                                         @PathVariable Long requestId,
                                        @PathVariable Date date,
                                        @PathVariable Time startTime,
                                        @PathVariable Time endTime)
    {
        session.setAttribute("acceptMsg",null);
        System.out.println("Accept Request------------start------------");
        System.out.println("index = "+index);
        Classroom classroom=classroomRepository.findByClassroomId(classroomId);
        boolean classAvailable=classTimingService.checkAvailableClassroom(classroom,date,startTime,endTime);


        RedirectView rv = new RedirectView();
        //class is available and request can be accepted
        if(classAvailable)
        {
            boolean success=requestService.saveAcceptedRequest(requestId);
            //request successfully accepted
            if(success)
            {
            rv = this.classroomRequest(session);
                session.setAttribute("view_req_msg","Successfully Accepted Request");

            }
            else{
                session.setAttribute("view_req_msg","Error Accepting Request");
            }
        }


        //class not available..rejecting the request
        else
        {
            boolean success=requestService.saveRejectedRequest(requestId);
            //request successfully rejected
            if(success)
            {
               rv= this.classroomRequest(session);
               session.setAttribute("view_req_msg","Classroom Not Available. Rejected Request.");

            }
            else {
                session.setAttribute("view_req_msg","Classroom Not Available. Error Rejecting Request.");
            }
        }
        rv.setUrl("/ViewRequests.jsp");
        return rv;
    }

    //get all requests with requested status---to be displayed on admin's dashboard
    @RequestMapping("/getAllRequests")
    public RedirectView classroomRequest(HttpSession session)
    {
        List<Request> currentRequestsList = requestService.getByRequestStatus(REQUESTED);
        List<ReturnableRequest> returnableRequestList = new ArrayList<>();
        for (int i = 0 ; i < currentRequestsList.size(); i++) {
            Request r = currentRequestsList.get(i);
            String projector;
            String cleaning;
            if (r.isProjector())
                projector = "Yes";
            else projector = "No";

            if (r.isCleaningRequired())
                cleaning = "Yes";
            else cleaning = "No";

            ReturnableRequest returnableRequest = new ReturnableRequest(
                    r.getStartTime(),
                    r.getEndTime(),
                    r.getClassRequestDate(),
                    r.getComment(),
                    r.getPlugs(),
                    projector,
                    cleaning,
                    r.getClassroom().getClassCode(),
                    r.getRequestor().getUserName(),
                    r.getPurpose(),
                    r.getRequestor().getLoginId(),
                    r.getClassroom().getClassroomId(),
                    r.getRequestId(),
                    i
            );
            returnableRequestList.add(returnableRequest);
            System.out.println("request id , i = "+i);
        }
        System.out.println("redirecting to ViewRequests.jsp");
        RedirectView rv=new RedirectView();
        session.setAttribute("returnableRequestList", returnableRequestList);
        System.out.println(returnableRequestList.size());
        session.setAttribute("currentRequests",currentRequestsList);
        rv.setUrl("/ViewRequests.jsp");
        return rv;
    }



}