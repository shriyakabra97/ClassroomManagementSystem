package com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public ModelAndView logIntoSystem(@RequestParam String username, @RequestParam String password, @RequestParam String usertype) {
        boolean loginSuccess = loginService.checkCredentials(username, password, usertype);
        ModelAndView mv = new ModelAndView();
        if(loginSuccess == false) {
            mv.setViewName("index.html");
        } else {
            switch (usertype) {
                case "admin": mv.setViewName("AdminDashboard.jsp");
                    break;
                case "professor": mv.setViewName("ProfessorDashboard.jsp");
                    break;
                case "ta": mv.setViewName("TADashboard.jsp");
                    break;
                case "committee": mv.setViewName("CommitteeDashboard.jsp");
                    break;
                case "sac": mv.setViewName("SACDashboard.jsp");
                    break;
            }
        }
        return mv;
    }
}
