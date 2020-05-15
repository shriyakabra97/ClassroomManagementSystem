
package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Classroom;
import com.spe.ClassroomManagementSystem.Models.Request;
import com.spe.ClassroomManagementSystem.Models.RequestStatus;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

public interface RequestService {

    public boolean saveRequest(HttpSession session, String classCode);
    public List<Request> getByRequestStatus(RequestStatus requestStatus);
    public List<Request> getByClassroomAndDateAndRequestStatus(Classroom classroom, Date date , RequestStatus requestStatus);
}