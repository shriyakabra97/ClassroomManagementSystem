package com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Models.*;
import com.spe.ClassroomManagementSystem.Repository.ClassroomRepository;
import com.spe.ClassroomManagementSystem.Service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static com.spe.ClassroomManagementSystem.Models.RequestStatus.REQUESTED;

@RestController
public class RequestController {
    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    private RequestService requestService;

    @Autowired
    private ClassTimingService classTimingService;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private SacService sacService;

    @Autowired
    private TaService taService;

    @Autowired
    private CommitteeService committeeService;


    @RequestMapping("/postRequest/{classCode}")
    public RedirectView classroomRequest(HttpSession session,
                                         @PathVariable String classCode) {
        logger.trace("classroomRequest called");
        boolean success_requested = requestService.saveRequest(session, classCode);
        if (success_requested) {
            logger.info("classroom request placed successfully");
            session.setAttribute("req_save_msg", "Request Sent successfully.");
        } else {
            logger.info("classroom request placement unsuccessful");
            session.setAttribute("req_save_msg", "Failed to Send Request. Please Try Again.");
        }

        //redirect acc to user type
        RedirectView rv = new RedirectView();
        switch ((String) session.getAttribute("userType")) {
            case "professor":
                rv.setUrl("/ProfessorDashboard.jsp");
                break;
            case "ta":
                rv.setUrl("/TADashboard.jsp");
                break;
            case "committee":
                rv.setUrl("/CommitteeDashboard.jsp");
                break;
            case "sac":
                rv.setUrl("/SACDashboard.jsp");
                break;
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
    ) {
        logger.trace("rejectRequest called");
        session.setAttribute("view_req_msg", null);
        boolean success = requestService.saveRejectedRequest(requestId);

        RedirectView rv = new RedirectView();

        String email = "";
        String name = "";
        String classCode = "";
        Date date;
        Time startTime;
        Time endTime;
        if (success) {

            //get login object from loginId
            Login login = loginService.findByLoginId(loginId);
            String usertype = login.getUserType();

            //get the mail from the table according to usertype
            switch (usertype) {
                case "sac":
                    Sac sac = sacService.findByForeignId(login);
                    email = sac.getSacEmail();
                    name = sac.getSacName();

                    break;
                case "ta":
                    TA ta = taService.findByForeignId(login);
                    email = ta.getTaEmail();
                    name = ta.getTaName();

                    break;
                case "committee":
                    Committee committee = committeeService.findByForeignId(login);
                    email = committee.getCommitteeEmail();
                    name = committee.getCommitteeName();

                    break;
                case "professor":
                    Professor professor = professorService.findByForeignId(login);
                    email = professor.getProfessorEmail();
                    name = professor.getProfessorName();

                    break;
            }

            classCode = classroomRepository.findByClassroomId(classroomId).getClassCode();
            date = requestService.getByRequestId(requestId).getClassRequestDate();
            startTime = requestService.getByRequestId(requestId).getStartTime();
            endTime = requestService.getByRequestId(requestId).getEndTime();

            rv = this.classroomRequest(session);
            session.setAttribute("view_req_msg", "Request Successfully REJECTED!");
            mailService.sendNotification(email,
                    "Hello " + name + ",\n" + "Your request for Classroom " + classCode + " for Date " + date + " from " + startTime + " to " + endTime
                            + " has been REJECTED." + "\n" + " Sorry for the inconvenience" + "\n" + "Classroom Management Team",
                    "Classroom request REJECTED"

            );
            //System.out.println("Mail of rejection sent");
        } else {
            session.setAttribute("view_req_msg", "Error Rejecting Request");
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
                                      @PathVariable Time endTime) {
        logger.trace("acceptRequest called");
        session.setAttribute("view_req_msg", null);
        Classroom classroom = classroomRepository.findByClassroomId(classroomId);
        boolean classAvailable = classTimingService.checkAvailableClassroom(classroom, date, startTime, endTime);
        RedirectView rv = new RedirectView();
        //class is available and request can be accepted
        if (classAvailable) {
            boolean success = requestService.saveAcceptedRequest(requestId);
            //request successfully accepted
            if (success) {
                String email = "";
                String name = "";
                String classCode = "";

                //get login object from loginId
                Login login = loginService.findByLoginId(loginId);
                String usertype = login.getUserType();

                //get the mail from the table according to usertype
                switch (usertype) {
                    case "sac":
                        Sac sac = sacService.findByForeignId(login);
                        email = sac.getSacEmail();
                        name = sac.getSacName();
                        break;
                    case "ta":
                        TA ta = taService.findByForeignId(login);
                        email = ta.getTaEmail();
                        name = ta.getTaName();
                        break;
                    case "committee":
                        Committee committee = committeeService.findByForeignId(login);
                        email = committee.getCommitteeEmail();
                        name = committee.getCommitteeName();
                        break;
                    case "professor":
                        Professor professor = professorService.findByForeignId(login);
                        email = professor.getProfessorEmail();
                        name = professor.getProfessorName();
                        break;
                }

                rv = this.classroomRequest(session);
                session.setAttribute("view_req_msg", "Request Successfully ACCEPTED");
                classCode = classroomRepository.findByClassroomId(classroomId).getClassCode();

                mailService.sendNotification(email,
                        "Hello " + name + ",\n" +
                                "Your request for Classroom" + classCode + " for Date " + date + " from " + startTime + " to " + endTime
                                + " has been ACCEPTED." + "\n" + "Thank You for using our Application." + "\n" + "Classroom Management Team"
                        , "Classroom request ACCEPTED"
                );
                if (requestService.getByRequestId(requestId).isCleaningRequired()) {
                    mailService.sendNotification("cleaningstaffiiitb@gmail.com",
                            //don't change this email id
                            //cleaning staff email id: cleaningstaffiiitb@gmail.com
                            //password for this email id: cleaningstaff123#
                            //you can login to see the cleaning requests
                            "Dear Cleaning Staff, \n" +
                                    "Please have a look at the details of room to be cleaned \n" +
                                    "Classroom : " + classCode + "\n" +
                                    "Date : " + date + "\n" +
                                    "Before Time : " + startTime + "\n" +
                                    "Thank you for the support. \n" +
                                    "Classroom Management Team",
                            "Cleaning Request for Classroom"

                    );
                }
                System.out.println("Mail of acceptance sent");
                //send mail to security
                mailService.sendNotification("gatestaffiiitb@gmail.com",
                        //don't change this email id
                        //cleaning staff email id: gatestaffiiitb@gmail.com
                        //password for this email id: securitystaff123#
                        //you can login to see the gate requests
                        "Dear Security Staff, \n" +
                                "Please have a look at the details of room to be opened \n" +
                                "Classroom : " + classCode + "\n" +
                                "Date : " + date + "\n" +
                                "Time : " + startTime + " to " + endTime + "\n" +
                                "Thank you for the support. \n" +
                                "Classroom Management Team",
                        "Request to Open Classroom"

                );


            } else {
                session.setAttribute("view_req_msg", "Error Accepting Request");
            }
        }

        //class not available..rejecting the request
        else {
            boolean success = requestService.saveRejectedRequest(requestId);
            //request successfully rejected
            if (success) {
                String email = "";
                String name = "";
                String classCode = "";
                //get login object from loginId
                Login login = loginService.findByLoginId(loginId);
                String usertype = login.getUserType();

                //get the mail from the table according to usertype
                switch (usertype) {
                    case "sac":
                        Sac sac = sacService.findByForeignId(login);
                        email = sac.getSacEmail();
                        name = sac.getSacName();
                        break;
                    case "ta":
                        TA ta = taService.findByForeignId(login);
                        email = ta.getTaEmail();
                        name = ta.getTaName();
                        break;
                    case "committee":
                        Committee committee = committeeService.findByForeignId(login);
                        email = committee.getCommitteeEmail();
                        name = committee.getCommitteeName();
                        break;
                    case "professor":
                        Professor professor = professorService.findByForeignId(login);
                        email = professor.getProfessorEmail();
                        name = professor.getProfessorName();
                        break;
                }
                classCode = classroomRepository.findByClassroomId(classroomId).getClassCode();

                rv = this.classroomRequest(session);
                session.setAttribute("view_req_msg", "Classroom Not Available.Request successfully REJECTED.");
                mailService.sendNotification(email,
                        "Hello " + name + ",\n" +
                                "Your request for Classroom" + classCode + " for Date " + date + " from " + startTime + " to " + endTime
                                + " has been REJECTED because the classroom was not available at that moment." + "\n" +
                                "Sorry for the inconvenience" + "\n" +
                                "Classroom Management Team",
                        "Classroom Request REJECTED");
                System.out.println("Mail of rejection(when admin tried to accept) sent");

            } else {
                session.setAttribute("view_req_msg", "Classroom Not Available. Error Rejecting Request.");

            }
        }
        rv.setUrl("/ViewRequests.jsp");
        return rv;
    }


    //get all requests with requested status---to be displayed on admin's dashboard
    @RequestMapping("/getAllRequests")
    public RedirectView classroomRequest(HttpSession session) {
        logger.trace("classroomRequest called");
        List<Request> currentRequestsList = requestService.getByRequestStatus(REQUESTED);
        List<ReturnableRequest> returnableRequestList = new ArrayList<>();
        for (int i = 0; i < currentRequestsList.size(); i++) {
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
            logger.info("request id , i = " + i);
        }
        //System.out.println("redirecting to ViewRequests.jsp");
        RedirectView rv = new RedirectView();
        session.setAttribute("returnableRequestList", returnableRequestList);
        //System.out.println(returnableRequestList.size());
        session.setAttribute("currentRequests", currentRequestsList);
        rv.setUrl("/ViewRequests.jsp");
        return rv;
    }


}