
package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Day;
import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.Professor;
import com.spe.ClassroomManagementSystem.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
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
    	

	    @Test
	    public void loginServiceSaveTest() throws FileNotFoundException,IOException
	    {
	    	try(BufferedReader bf=new BufferedReader(new FileReader("/home/pragati/Desktop/SEM3/Testing/login.txt")))
	    	{
	    		
	    		while(bf.ready())
	    		{
	    			HttpSession session=null;
	    			String[] current=bf.readLine().trim().split(",");
	    			Login login = new Login();
	    			login.setUserType(current[0]);   //username
	    			login.setPassword(current[1]);   //password
	    			login.setUserName(current[2]);   //usertype
	    			Assert.assertEquals(false, loginService.save(login,session));
	        		
	    		}
	    	}
	    
	    }
	    
	    @Test
	    public void loginServiceCheckCredentialsTest()
	    		throws FileNotFoundException,IOException
	    {
	    	try(BufferedReader bf=new BufferedReader(new FileReader("/home/pragati/Desktop/SEM3/Testing/login.txt")))
	    	{
	    		
	    		while(bf.ready())
	    		{
	    			HttpSession session=null;
	    			String[] current=bf.readLine().trim().split(",");
	    			Assert.assertEquals(false, loginService.checkCredentials(current[0],current[1],current[2],session));	
	    		}
	    	}
	    	
	    }
	   
	    
	    @Test
	    public void professorServiceSaveProfessorTest()
	    		throws FileNotFoundException,IOException
	    {
	    	try(BufferedReader bf=new BufferedReader(new FileReader("/home/pragati/Desktop/SEM3/Testing/login.txt")))
	    	{
	    		
	    		while(bf.ready())
	    		{
	    			HttpSession session=null;
	    			String[] current=bf.readLine().trim().split(",");   //5 values
	    			
	    			 Professor professor = new Professor();
	    		     professor.setProfessorName(current[0]);            //name
	    		     professor.setUserName(current[1]);                 //username
	    		     professor.setProfessorEmail(current[2]);           //email
	    		     Login login = new Login();
		    		 login.setUserType(current[3]);                     //usertype
		    		 login.setPassword(current[4]);                     //password
		    		 login.setUserName(current[1]);						//username
	    		     professor.setForeignId(login);  
	    			Assert.assertEquals(false, professorService.saveProfessor(professor));	
	    		}
	    	}
	    	
	    }
	    
	    
	    @Test
	    public void classTimingServiceSaveInClassTiminTgest()
	    		throws FileNotFoundException,IOException
	    {
	    	try(BufferedReader bf=new BufferedReader(new FileReader("/home/pragati/Desktop/SEM3/Testing/login.txt")))
	    	{
	    		
	    		while(bf.ready())
	    		{
	    			HttpSession session=null;
	    			String[] current=bf.readLine().trim().split(",");   //4 values
	    			
	    			 Day day1 = Day.SUNDAY;//initialization
	    		     switch (current[1])
	    		     {
	    		            case "SUNDAY": day1=Day.SUNDAY;break;
	    		            case "MONDAY": day1=Day.MONDAY;break;
	    		            case "TUESDAY": day1=Day.TUESDAY;break;
	    		            case "WEDNESDAY": day1=Day.WEDNESDAY;break;
	    		            case "THURSDAY": day1=Day.THURSDAY;break;
	    		            case "FRIDAY": day1=Day.FRIDAY;break;
	    		            case "SATURDAY": day1=Day.SATURDAY;break;
	    		     }
	    		        Time startTimeFormat = Time.valueOf(current[1] +":00");
	    		        Time endTimeFormat = Time.valueOf(current[2] +":00");
	    			
	     
	    			Assert.assertEquals(false,classTimingService.saveInClassTiming(current[0], startTimeFormat, endTimeFormat, day1, session));	
	    		}
	    	}
	    	
	    }
	    
	}
