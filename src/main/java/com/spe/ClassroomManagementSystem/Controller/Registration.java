package com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Model.*;
import com.spe.ClassroomManagementSystem.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Registration {

    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private CommitteeRepository committeeRepository;
    @Autowired
    private SacRepository sacRepository;
    @Autowired
    private TeachingAssistantRepository teachingAssistantRepository;
    @Autowired
    private LoginRepository loginRepository;

    @PostMapping("/register/professor")
    public ModelAndView registerProfessor(@RequestBody Professor professor){
        professorRepository.save(professor);
        Login login=new Login();
        login.setUserType("professor");
        login.setUsername(professor.getUsername());
        login.setPassword(professor.getPassword());
        loginRepository.save(login);
        ModelAndView mv=new ModelAndView();
        return mv;
    }
    @PostMapping("/register/commitee")
    public ModelAndView registerCommittee(@RequestBody Committee committee){
        committeeRepository.save(committee);
        Login login=new Login();
        login.setUserType("committee");
        login.setUsername(committee.getUsername());
        login.setPassword(committee.getPassword());
        loginRepository.save(login);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("admin.jsp");
        return mv;
    }
    @PostMapping("/register/sac")
    public ModelAndView registerSac(@RequestBody Sac sac){
        sacRepository.save(sac);
        Login login=new Login();
        login.setUserType("sac");
        login.setUsername(sac.getUsername());
        login.setPassword(sac.getPassword());
        loginRepository.save(login);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("admin.jsp");
        return mv;
    }
    @PostMapping("/register/ta")
    public ModelAndView registerSac(@RequestBody TeachingAssistant teachingAssistant){
        teachingAssistantRepository.save(teachingAssistant);
        Login login=new Login();
        login.setUserType("ta");
        login.setUsername(teachingAssistant.getUsername());
        login.setPassword(teachingAssistant.getPassword());
        loginRepository.save(login);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("admin.jsp");
        return mv;
    }
}
