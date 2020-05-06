package com.spe.ClassroomManagementSystem.Controller;


import com.spe.ClassroomManagementSystem.Models.*;
import com.spe.ClassroomManagementSystem.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Registration {

    @Autowired
    private CommitteeService committeeService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private SacService sacService;
    @Autowired
    private TaService taService;
    @Autowired
    private LoginService loginService;
    Login login = new Login();


    @PostMapping("/register/committee")
    public ModelAndView registerCommittee(@RequestParam String name, @RequestParam String username, @RequestParam String email, @RequestParam String password) {
        login.setUserName(username);
        login.setPassword(password);
        login.setUserType("committee");
        loginService.save(login);
        login = loginService.findByUsernameAndPassword(username, password);
        Committee committee = new Committee();
        committee.setCommitteeName(name);
        committee.setUserName(username);
        committee.setCommitteeEmail(email);
        committee.setForeignId(login);
        committeeService.saveCommittee(committee);


        ModelAndView mv = new ModelAndView();
        mv.setViewName("AdminDashboard.jsp");
        return mv;
    }

    @PostMapping("/register/professor")
    public ModelAndView registerProfessor(@RequestParam String name, @RequestParam String username, @RequestParam String email, @RequestParam String password) {
        login.setUserName(username);
        login.setPassword(password);
        login.setUserType("professor");
        loginService.save(login);
        login = loginService.findByUsernameAndPassword(username, password);
        Professor professor = new Professor();
        professor.setProfessorName(name);
        professor.setUserName(username);
        professor.setProfessorEmail(email);
        professor.setForeignId(login);
        professorService.saveProfessor(professor);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("AdminDashboard.jsp");
        return mv;
    }

    @PostMapping("/register/sac")
    public ModelAndView registerSac(@RequestParam String name, @RequestParam String username, @RequestParam String email, @RequestParam String password) {
        login.setUserName(username);
        login.setPassword(password);
        login.setUserType("sac");
        login = loginService.findByUsernameAndPassword(username, password);
        Sac sac = new Sac();
        sac.setSacName(name);
        sac.setUserName(username);
        sac.setSacEmail(email);
        sac.setForeignId(login);
        sacService.saveSac(sac);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("AdminDashboard.jsp");
        return mv;
    }

    @PostMapping("/register/ta")
    public ModelAndView registerTa(@RequestParam String name, @RequestParam String username, @RequestParam String email, @RequestParam String password) {
        login.setUserName(username);
        login.setPassword(password);
        login.setUserType("ta");
        login = loginService.findByUsernameAndPassword(username, password);
        TA ta = new TA();
        ta.setTaName(name);
        ta.setUserName(username);
        ta.setTaEmail(email);
        ta.setForeignId(login);
        taService.saveTa(ta);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("AdminDashboard.jsp");
        return mv;
    }

}