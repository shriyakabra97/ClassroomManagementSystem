
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
    	
    	@Autowired
        private ProfessorService professorService;
    	
    	@Autowired
        private ClassTimingService classTimingService;
    	

    	@ParameterizedTest
 	    @CsvSource({})
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
	    	
	    
	    
	    @ParameterizedTest
	    @CsvSource({})
	    public void loginServiceCheckCredentialsTest(String username, String password, String userType, boolean result)
	    		throws FileNotFoundException,IOException
	    {
	    	
	    			HttpSession session=null;
	 	    		Assert.assertEquals(result, loginService.checkCredentials(username ,password,userType,session));	
	  	
	    }
	   
	    
	    @ParameterizedTest
	    @CsvSource({})
	    public void professorServiceSaveProfessorTest(String name,String username,String email,String userType,String password,boolean result)
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
	    
	    
	    
	    @ParameterizedTest
	    @CsvSource({})
	    public void classTimingServiceSaveInClassTiminTgest(String classCode, String startTime,String endTime,String day,boolean result)
	    		throws FileNotFoundException,IOException
	    {
	    	
	    			HttpSession session=null;
	    		    Day day1 = Day.SUNDAY;//initialization
	    		     switch (day)
	    		     {
	    		            case "SUNDAY": day1=Day.SUNDAY;break;
	    		            case "MONDAY": day1=Day.MONDAY;break;
	    		            case "TUESDAY": day1=Day.TUESDAY;break;
	    		            case "WEDNESDAY": day1=Day.WEDNESDAY;break;
	    		            case "THURSDAY": day1=Day.THURSDAY;break;
	    		            case "FRIDAY": day1=Day.FRIDAY;break;
	    		            case "SATURDAY": day1=Day.SATURDAY;break;
	    		     }
	    		        Time startTimeFormat = Time.valueOf(startTime +":00");
	    		        Time endTimeFormat = Time.valueOf(endTime +":00");
	    			
	     
	    			Assert.assertEquals(result,classTimingService.saveInClassTiming(classCode, startTimeFormat, endTimeFormat, day1, session));	
	    		
	    }
	    
	}
