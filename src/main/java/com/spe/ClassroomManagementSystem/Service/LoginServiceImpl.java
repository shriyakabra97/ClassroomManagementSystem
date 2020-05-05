package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Login save(Login login){
        return loginRepository.save(login);
    }

    @Override
    public  Login findByUsernameAndPassword(String username,String password){
        return loginRepository.findByUserNameAndPassword(username,password);
    }

    @Override
    public boolean checkCredentials(String username, String password, String userType) {
        Login user = loginRepository.findByUserNameAndUserType(username, userType);
        if (user == null) {
            return false;
        } else {
            if (user.getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
