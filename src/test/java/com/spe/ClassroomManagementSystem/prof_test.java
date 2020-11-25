
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
	public class prof_test
	{

    	@Autowired
        private LoginService loginService;
    	
    	@Autowired
        private ProfessorService professorService;
    	

    	

    	
	   
	    
	    @ParameterizedTest
	    @CsvSource({",,asthaandpragati@gmail.com,professor,,Error Saving Professor.." , 
	    		"asthaname,pragatiusername,invalidemail,ta,,Error Saving Professor.." , 
	    		"asthaname,,invalidemail,committee,password,Error Saving Professor.." , 
	    		",pragatiusername,asthaandpragati@gmail.com,sac,password,Error Saving Professor.." , 
	    		",pragatiusername,invalidemail,admin,password,Error Saving Professor.." , 
	    		"asthaname,,asthaandpragati@gmail.com,invaliduser,password,Error Saving Professor.." , 
	    		"asthaname,,asthaandpragati@gmail.com,admin,,Error Saving Professor.." , 
	    		",pragatiusername,invalidemail,invaiduser,,Error Saving Professor.." , 
	    		",pragatiusername,invalidemail,committee,,Error Saving Professor.." , 
	    		"asthaname,,invalidemail,sac,,Error Saving Professor.." , 
	    		"asthaname,,asthaandpragati@gmail.com,ta,password,Error Saving Professor.." , 
	    		"asthaname,pragatiusername,invalidemail,professor,password,Error Saving Professor.." , 
	    		",pragatiusername,asthaandpragati@gmail.com,ta,password,Error Saving Professor.." , 
	    		"asthaname,pragatiusername,asthaandpragati@gmail.com,committee,password,Saved Professor successfully"})
	    public void professorServiceSaveProfessorTest(String name,String username,String email,String userType,String password,String result)
	    		throws FileNotFoundException,IOException
	    {
	    	
	    			 HttpSession session=null;
	    			 Professor professor = new Professor();
	    		     professor.setProfessorName(name);            		//name
	    		     professor.setUserName(username);                  //username
	    		     professor.setProfessorEmail(email);           		//email
	    		     Login login = new Login();
		    		 login.setUserType(userType);                       //usertype
		    		 login.setPassword(password);                       //password
		    		 login.setUserName(username);					 	//username
	    		     professor.setForeignId(login);  
	    			 Assert.assertEquals(result, professorService.saveProfessor(professor));	
	  	
	    }
	    
	    
	}