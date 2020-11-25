
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
	public class Test1
	{

    	@Autowired
        private LoginService loginService;
  
    
    	@ParameterizedTest
 	    @CsvSource({"professor,,,false",
 	    		"ta,abcd,,false",
 	    		"committee,abcd,user,true",
 	    		"sac,,user,false",
 	    		"admin,,user,false",
 	    		"invaliduser,,user,false",
 	    		"invaliduser,abcd,,false", 
 	    		"admin,abcd,,false",
 	    		"sac,abcd,,false", 
 	    		"committee,,,false",
 	    		"ta,,user,false",
 	    		"professor,abcd,user1,true"})
	    public void loginServiceSaveTest(String userType,String password,String username,boolean result) 
	    		throws FileNotFoundException,IOException
	    {
	    	
	    			HttpSession session=null;
	    			
	    			Login login = new Login();
	    			login.setUserType(userType);   //username
	    			login.setPassword(password);   //password
	    			login.setUserName(username);   //usertype
	    			Assert.assertEquals(result, loginService.save(login,session));
	        		
	    }
	    	
	  
	}
