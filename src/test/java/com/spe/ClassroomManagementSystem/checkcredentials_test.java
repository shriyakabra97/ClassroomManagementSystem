package com.spe.ClassroomManagementSystem;

import com.spe.ClassroomManagementSystem.Models.Day;
import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.Professor;
import com.spe.ClassroomManagementSystem.Service.LoginService;
import com.spe.ClassroomManagementSystem.Service.ProfessorService;
import com.spe.ClassroomManagementSystem.Service.ClassTimingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.Assert;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;


	@SpringBootTest
	public class checkcredentials_test
	{

    	@Autowired
        private LoginService loginService;
    	
  
	    @ParameterizedTest
	    @CsvSource({",,professor,false",
	    	"asthauser,pragatipassword,professor,true",
	    	"asthauser,,ta,false",
	    	",pragatipassword,ta,false",
	    	",pragatipassword,committee,false",
	    	"asthauser,,committee,false",
	    	"asthauser,,sac,false",
	    	",pragatipassword,sac,false",
	    	",pragatipassword,admin,false",
	    	",asthauser,admin,false",
	    	"asthauser,,invaliduser,false",
	    	",pragatipassword,invaliduser,false"})
	    public void loginServiceCheckCredentialsTest(String username, String password, String userType, boolean result)
	    		throws FileNotFoundException,IOException
	    {
	    	
	    			HttpSession session=null;
	 	    		Assert.assertEquals(result, loginService.checkCredentials(username ,password,userType,session));	
	  	
	    }
}
	    
