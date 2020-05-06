package com.spe.ClassroomManagementSystem.Controller;


import com.spe.ClassroomManagementSystem.Models.*;
import com.spe.ClassroomManagementSystem.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RegistrationController {

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
    public RedirectView registerCommittee(@RequestParam String name, @RequestParam String username, @RequestParam String email, @RequestParam String password) {
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


        RedirectView rv = new RedirectView();
        rv.setUrl("AdminDashboard.jsp");
        return rv;
    }

    @PostMapping("/register/professor")
    public RedirectView registerProfessor(@RequestParam String name, @RequestParam String username, @RequestParam String email, @RequestParam String password) {
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

        RedirectView rv = new RedirectView();
        rv.setUrl("AdminDashboard.jsp");
        return rv;
    }

    @PostMapping("/register/sac")
    public RedirectView registerSac(@RequestParam String name, @RequestParam String username, @RequestParam String email, @RequestParam String password) {
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

        RedirectView rv = new RedirectView();
        rv.setUrl("AdminDashboard.jsp");
        return rv;
    }

    @PostMapping("/register/ta")
    public RedirectView registerTa(@RequestParam String name, @RequestParam String username, @RequestParam String email, @RequestParam String password) {
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

        RedirectView rv = new RedirectView();
        rv.setUrl("AdminDashboard.jsp");
        return rv;
    }

}