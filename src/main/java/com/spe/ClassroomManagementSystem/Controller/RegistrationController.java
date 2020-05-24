package com.spe.ClassroomManagementSystem.Controller;


import com.spe.ClassroomManagementSystem.Models.*;
import com.spe.ClassroomManagementSystem.Service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.Table;
import javax.servlet.http.HttpSession;

@RestController
public class RegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

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
    public RedirectView registerUser(@RequestParam("usertype") String usertype,
                                        @RequestParam("name") String name,
                                          @RequestParam("username") String username,
                                          @RequestParam("email") String email,
                                          @RequestParam("password") String password,
                                     HttpSession session
                                     ) {
        logger.trace("registerUser called");
        Login login = new Login();
        login.setUserType(usertype);
        login.setPassword(password);
        login.setUserName(username);
        boolean saved = loginService.save(login, session);
        boolean reg;
        if (saved) {
            logger.info("user added");
            String msg ="";
            switch (usertype) {
                case "professor":
                    Professor professor = new Professor();
                    professor.setProfessorName(name);
                    professor.setUserName(username);
                    professor.setProfessorEmail(email);
                    professor.setForeignId(login);
                    msg = professorService.saveProfessor(professor);
                    session.setAttribute("msg", msg);
                    break;
                case "ta":
                    TA ta = new TA();
                    ta.setTaName(name);
                    ta.setTaEmail(email);
                    ta.setUserName(username);
                    ta.setForeignId(login);
                    msg = taService.saveTa(ta);
                    session.setAttribute("msg", msg);
                    break;
                case "committee":
                    Committee committee = new Committee();
                    committee.setUserName(username);
                    committee.setCommitteeName(name);
                    committee.setCommitteeEmail(email);
                    committee.setForeignId(login);
                    msg = committeeService.saveCommittee(committee);
                    session.setAttribute("msg", msg);
                    break;
                case "sac":
                    Sac sac = new Sac();
                    sac.setSacName(name);
                    sac.setUserName(username);
                    sac.setSacEmail(email);
                    sac.setForeignId(login);
                    msg = sacService.saveSac(sac);
                    session.setAttribute("msg", msg);
                    break;

            }

        }

        RedirectView rv = new RedirectView();
        String rurl = "/RegisterUser.jsp";
        rv.setUrl(rurl);
        return rv;
    }


}