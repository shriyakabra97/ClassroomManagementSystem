
package com.spe.ClassroomManagementSystem.Service;

import javax.servlet.http.HttpSession;

public interface RequestService {

    public boolean saveRequest(HttpSession session, String classCode);
}