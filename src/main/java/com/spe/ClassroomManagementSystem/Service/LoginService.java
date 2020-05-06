package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Login;

import javax.servlet.http.HttpSession;

public interface LoginService {
    Login save(Login login);

    Login findByUsernameAndPassword(String username, String password);

    boolean checkCredentials(String username, String password, String userType , HttpSession session);
}
