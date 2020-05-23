package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.*;
import com.spe.ClassroomManagementSystem.Repository.LoginRepository;
import com.spe.ClassroomManagementSystem.Repository.ProfessorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public boolean save(Login login, HttpSession session){
        List<Login> loginList = loginRepository.findAll();
        for (Login l:loginList) {
            if (login.getUserName().equals(l.getUserName()) && login.getUserType().equals(l.getUserType())){
                logger.error("User Already Exists");
                session.setAttribute("msg", "User Already Exists");
                return false;
            }
        }

        loginRepository.save(login);
        logger.info("User saved successfully");
        return true;

    }

    @Override
    public  Login findByUsernameAndPassword(String username,String password){
        return loginRepository.findByUserNameAndPassword(username,password);
    }

    @Override
    public boolean checkCredentials(String username, String password, String userType, HttpSession session) {
        session.setAttribute("userType", userType);
        if (userType.equals("admin")){
            if (username.equals("admin") && password.equals("admin")){
                return true;
            }
        }
        Login user = loginRepository.findByUserNameAndUserType(username, userType);
        System.out.println(user);

        if (user == null) {
            return false;
        } else {
            if (user.getPassword().equals(password)) {
                switch (userType) {
                    case "professor":
                        session.setAttribute("login", true);
                        Professor professor = user.getProfessor();
                        session.setAttribute("professor", professor);
                        break;
                    case "ta":
                        session.setAttribute("login", true);
                        TA ta = user.getTa();
                        session.setAttribute("ta", ta);
                        break;
                    case "committee":
                        session.setAttribute("login", true);
                        Committee committee = user.getCommittee();
                        session.setAttribute("committee", committee);
                        break;
                    case "sac":
                        session.setAttribute("login", true);
                        Sac sac = user.getSac();
                        session.setAttribute("sac", sac);
                        break;

                }
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public Login findByLoginId(Long loginId)
    {
        return loginRepository.findByLoginId(loginId);
    }
}
