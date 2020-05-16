package com.spe.ClassroomManagementSystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RedirectController {
    ModelAndView mv=new ModelAndView();
    //default home page not working
    @RequestMapping("")
    public ModelAndView indexPage(){
        mv.setViewName("index.html");
        return  mv;
    }

    @RequestMapping("/AddClassroom")
    public ModelAndView addClassroom(){
        mv.setViewName("AddClassroom.jsp");
        return  mv;
    }
    @RequestMapping("/AddTimetable")
    public ModelAndView addTimetable(){
        mv.setViewName("AddTimetable.jsp");
        return  mv;
    }
    @RequestMapping("/AdminDashboard")
    public ModelAndView adminDashboard(){
        mv.setViewName("AdminDashboard.jsp");
        return  mv;
    }
    @RequestMapping("/AvailableClassrooms")
    public ModelAndView AvailableClassroms(){
        mv.setViewName("AvailableClassrooms.jsp");
        return  mv;
    }
    @RequestMapping("/CommitteeDashboard")
    public ModelAndView committeeDashboard(){
        mv.setViewName("CommitteeDashboard.jsp");
        return  mv;
    }
    @RequestMapping("/Error")
    public ModelAndView error(){
        mv.setViewName("Error.jsp");
        return  mv;
    }
    @RequestMapping("/InvalidLogin")
    public ModelAndView invalidlogin(){
        mv.setViewName("InvalidLogin.jsp");
        return  mv;
    }
    @RequestMapping("/LoginFirst")
    public ModelAndView loginFirst(){
        mv.setViewName("LoginFirst.jsp");
        return  mv;
    }
    @RequestMapping("/ProfessorDashboard")
    public ModelAndView professorDashboard(){
        mv.setViewName("ProfessorDashboard.jsp");
        return  mv;
    }
    @RequestMapping("/RegisterUser")
    public ModelAndView registerUser(){
        mv.setViewName("RegisterUser.jsp");
        return  mv;
    }
    @RequestMapping("/SACDashboard")
    public ModelAndView sacDashboard(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("SACDashboard.jsp");
        return  mv;
    }
    @RequestMapping("/TADashboard")
    public ModelAndView taDashboard(){
        mv.setViewName("TADashboard.jsp");
        return  mv;
    }

    @RequestMapping("/ViewRequests")
    public ModelAndView vireRequest(){
        mv.setViewName("ViewRequests.jsp");
        return  mv;
    }
}
