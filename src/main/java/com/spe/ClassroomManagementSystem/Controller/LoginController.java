package com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public RedirectView logIntoSystem(@RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                      @RequestParam("usertype") String usertype,
                                      HttpSession session
                                      ) {
        System.out.println("/login called");
        logger.trace("login called");
        boolean loginSuccess = loginService.checkCredentials(username, password, usertype, session);
        logger.info("login successful");
        RedirectView rv = new RedirectView();
        if(loginSuccess == false) {
            logger.info("login unsuccessful");
            rv.setUrl("InvalidLogin.jsp");
        } else {
            switch (usertype) {
                case "admin":
                    logger.info("admin logged in");
                    session.setAttribute("admin_login", "admin_login");
                    rv.setUrl("AdminDashboard.jsp");
                    break;
                case "professor":
                    logger.info("professor logged in");
                    session.setAttribute("professor_login", "professor_login");
                    rv.setUrl("ProfessorDashboard.jsp");
                    break;
                case "ta":
                    logger.info("ta logged in");
                    session.setAttribute("ta_login", "ta_login");
                    rv.setUrl("TADashboard.jsp");
                    break;
                case "committee":
                    logger.info("committee logged in");
                    session.setAttribute("committee_login", "committee_login");
                    rv.setUrl("CommitteeDashboard.jsp");
                    break;
                case "sac":
                    logger.info("sac logged in");
                    session.setAttribute("sac_login", "sac_login");
                    rv.setUrl("SACDashboard.jsp");
                    break;
            }
        }
        return rv;
    }

}
