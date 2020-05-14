
package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Request;
import sun.security.jgss.HttpCaller;

import javax.servlet.http.HttpSession;

public interface RequestService {

    public boolean saveRequest(HttpSession session, String classCode);
}