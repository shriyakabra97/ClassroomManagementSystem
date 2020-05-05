package com.spe.ClassroomManagementSystem.Controller;


import com.spe.ClassroomManagementSystem.Models.*;
import com.spe.ClassroomManagementSystem.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    Login login=new Login();

    @PostMapping("/register/committee")
    public ModelAndView registerCommittee(@RequestParam("name") String name, @RequestParam("username") String username,@RequestParam("email") String email, @RequestParam("password") String password){
        Committee committee=new Committee();
        committee.setCommitteeName(name);
        committee.setUserName(username);
        committee.setCommitteeEmail(email);
        committeeService.saveCommittee(committee);

        login.setUserName(username);
        login.setPassword(password);
        login.setUserType("committee");
        loginService.save(login);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("admin.jsp");
        return  mv;
    }
    @PostMapping("/register/professor")
    public ModelAndView registerProfessor(@RequestParam("name") String name,@RequestParam("username")String username,@RequestParam("email") String email,@RequestParam("password") String password){
        Professor professor=new Professor();
        professor.setProfessorName(name);
        professor.setUserName(username);
        professor.setProfessorEmail(email);
        professorService.saveProfessor(professor);

        login.setUserName(username);
        login.setPassword(password);
        login.setUserType("professor");
        loginService.save(login);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("AdminDashboard.jsp");
        return  mv;
    }
    @PostMapping("/register/sac")
    public ModelAndView registerSac(@RequestParam("name")  String name,@RequestParam("username") String username,@RequestParam("email") String email,@RequestParam("password") String password){
        Sac sac=new Sac();
        sac.setSacName(name);
        sac.setUserName(username);
        sac.setSacEmail(email);
        sacService.saveSac(sac);

        login.setUserName(username);
        login.setPassword(password);
        login.setUserType("sac");
        ModelAndView mv=new ModelAndView();
        mv.setViewName("admin.jsp");
        return  mv;
    }
    @PostMapping("/register/ta")
    public ModelAndView registerTa(@RequestParam("name")  String name,@RequestParam("username")String username,@RequestParam("email") String email,@RequestParam("password") String password){
        TA ta=new TA();
        ta.setTaName(name);
        ta.setUserName(username);
        ta.setTaEmail(email);
        taService.saveTa(ta);

        login.setUserName(username);
        login.setPassword(password);
        login.setUserType("ta");
        ModelAndView mv=new ModelAndView();
        mv.setViewName("admin.jsp");
        return  mv;
    }
}
