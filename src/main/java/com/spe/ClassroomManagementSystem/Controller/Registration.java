package com.spe.ClassroomManagementSystem.Controller;


import com.spe.ClassroomManagementSystem.Models.*;
import com.spe.ClassroomManagementSystem.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.Table;

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


    @PostMapping("/register")
    public RedirectView registerUser(    @RequestParam("usertype") String usertype,
                                         @RequestParam("name") String name,
                                          @RequestParam("username") String username,
                                          @RequestParam("email") String email,
                                          @RequestParam("password") String password) {

        Login login = new Login();
        login.setUserType(usertype);
        login.setPassword(password);
        login.setUserName(username);
        loginService.save(login);
        System.out.println("Added in login table..");
        switch (usertype){
            case "professor":
                Professor professor = new Professor();
                professor.setProfessorName(name);
                professor.setUserName(username);
                professor.setProfessorEmail(email);
                professor.setForeignId(login);
                professorService.saveProfessor(professor);
                System.out.println("Added in professor table");
                break;
            case "ta":
                TA ta = new TA();
                ta.setTaName(name);
                ta.setTaEmail(email);
                ta.setUserName(username);
                ta.setForeignId(login);
                taService.saveTa(ta);
                System.out.println("Added in TA table..");
                break;
            case "committee":
                Committee committee = new Committee();
                committee.setUserName(username);
                committee.setCommitteeName(name);
                committee.setCommitteeEmail(email);
                committee.setForeignId(login);
                committeeService.saveCommittee(committee);
                System.out.println("Added in Committee table..");
                break;
            case "sac":
                Sac sac = new Sac();
                sac.setSacName(name);
                sac.setUserName(username);
                sac.setSacEmail(email);
                sac.setForeignId(login);
                sacService.saveSac(sac);
                System.out.println("Added in SAC table");
                break;

        }
        RedirectView rv = new RedirectView();
        String  rurl = "/RegisterUser.jsp";
        rv.setUrl(rurl);
        return rv;
    }


}