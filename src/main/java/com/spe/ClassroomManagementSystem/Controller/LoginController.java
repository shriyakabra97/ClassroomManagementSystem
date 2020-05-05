package com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Service.LoginService;
import com.spe.ClassroomManagementSystem.Service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public RedirectView logIntoSystem(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("usertype") String usertype) {
        boolean loginSuccess = loginService.checkCredentials(username, password, usertype);
//        ModelAndView mv = new ModelAndView();
//        if(loginSuccess == false) {
//            mv.setViewName("index.html");
//        } else {
//            switch (usertype) {
//                case "admin": mv.setViewName("AdminDashboard.jsp");
//                    break;
//                case "professor": mv.setViewName("ProfessorDashboard.jsp");
//                    break;
//                case "ta": mv.setViewName("TADashboard.jsp");
//                    break;
//                case "committee": mv.setViewName("CommitteeDashboard.jsp");
//                    break;
//                case "sac": mv.setViewName("SACDashboard.jsp");
//                    break;
//            }
//        }
//        return mv;
        RedirectView rv = new RedirectView();
        if (loginSuccess) {
            rv.setUrl("/AdminDashboard.jsp");
        } else {
            rv.setUrl("/index.html");
        }

        return rv;
    }
}
