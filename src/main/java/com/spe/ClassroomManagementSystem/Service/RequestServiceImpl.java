package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.*;
import com.spe.ClassroomManagementSystem.Repository.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static com.spe.ClassroomManagementSystem.Models.RequestStatus.GRANTED;
import static com.spe.ClassroomManagementSystem.Models.RequestStatus.REJECTED;

@Service
public class RequestServiceImpl implements RequestService {
    private static final Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ClassroomService classroomService;

    @Override
    public List<Request> getByClassroomAndDateAndRequestStatus(Classroom classroom, Date date,RequestStatus requestStatus){
        return requestRepository.getAllByClassroomAndClassRequestDateAndRequestStatus(classroom,date,requestStatus);
    }

    @Override
    public List<Request> getByRequestStatus(RequestStatus requestStatus)
    {
              logger.trace("getByRequestStatus called");
              return requestRepository.getAllByRequestStatus(requestStatus);
    }

    @Override
    public boolean saveRejectedRequest(Long requestId)
    {
        try {
            Request r = requestRepository.getAllByRequestId(requestId);
            r.setRequestStatus(REJECTED);
            requestRepository.save(r);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean saveAcceptedRequest(Long requestId) {

        try {
            Request r = requestRepository.getAllByRequestId(requestId);
            r.setRequestStatus(GRANTED);
            requestRepository.save(r);
            return true;
        }catch (Exception e){
        return false;
        }
    }

    @Override
    public boolean saveRequest(HttpSession session, String classCode){
        Request request = new Request();
        request.setClassroom(classroomService.getClassroomByClassCode(classCode));
        request.setProjector((boolean)session.getAttribute("projectorNeeded"));
        request.setCleaningRequired((boolean)session.getAttribute("cleaningNeeded"));
        request.setPurpose((String) session.getAttribute("purpose"));
        request.setPlugs((Long) session.getAttribute("reqPlugs"));

        Professor professor = (Professor) session.getAttribute("professor");
        if (professor != null) {
            Login login = professor.getForeignId();
            request.setRequestor(login); }

        Sac sac = (Sac) session.getAttribute("sac");
        if (sac!=null){
            Login login1 = sac.getForeignId();
            request.setRequestor(login1);
        }

        Committee committee = (Committee) session.getAttribute("committee");
        if (committee!=null){
            Login login2 = committee.getForeignId();
            request.setRequestor(login2);
        }

        TA ta = (TA) session.getAttribute("ta");
        if (ta!=null){
            Login login3 = ta.getForeignId();
            request.setRequestor(login3);

        }

        request.setClassRequestDate((Date) session.getAttribute("reqDate"));
        request.setStartTime((Time) session.getAttribute("reqStartTime"));
        request.setEndTime((Time) session.getAttribute("reqEndTime"));
        request.setRequestStatus(RequestStatus.REQUESTED);
        request.setComment("");

        try {
            requestRepository.save(request);
            session.removeAttribute("purpose");
            session.removeAttribute("reqStartTime");
            session.removeAttribute("reqEndTime");
            session.removeAttribute("cleaningNeeded");
            session.removeAttribute("projectorNeeded");
            session.removeAttribute("reqPlugs");
            session.removeAttribute("reqDate");
            session.removeAttribute("reqCapacity");


            return true;
        }catch (Exception e){
            return false;
        }

    }
    @Override
    public Request getByRequestId(long requestId){
        return  requestRepository.getAllByRequestId(requestId);
    }
}