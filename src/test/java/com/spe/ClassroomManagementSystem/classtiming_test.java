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
	public class classtiming_test
	{
    	@Autowired
        private ClassTimingService classTimingService;
    	
        @ParameterizedTest
	    @CsvSource({"A204,MONDAY,-01:00,-02:00,false" , 
	"@44,FUNDAY,00:00,-02:00,false" , 
	"@44,MONDAY,16:00,00:00,false" , 
	"A204,FUNDAY,24:00,00:00,false" , 
	"A204,FUNDAY,45:00,15:00,false" , 
	"@44,MONDAY,45:00,24:00,false" , 
	"@44,MONDAY,24:00,15:00,false" , 
	"A204,FUNDAY,16:00,24:00,false" , 
	"A204,FUNDAY,00:00,37:00,false" , 
	"@44,MONDAY,-01:00,37:00,false" , 
	"@44,MONDAY,00:00,24:00,false" , 
	"@44,FUNDAY,-01:00,24:00,false" , 
	"@44,FUNDAY,16:00,15:00,false" , 
	"@44,FUNDAY,24:00,24:00,false" , 
	"@44,FUNDAY,45:00,37:00,false" , 
	"@44,FUNDAY,45:00,-02:00,false" , 
	"@44,FUNDAY,24:00,-02:00,false" , 
	"@44,FUNDAY,16:00,-02:00,false" , 
	"@44,FUNDAY,-01:00,15:00,false" , 
	"@44,FUNDAY,00:00,15:00,false" , 
	"@44,FUNDAY,00:00,00:00,false" , 
	"@44,FUNDAY,-01:00,00:00,false" , 
	"@44,FUNDAY,16:00,37:00,false" , 
	"@44,FUNDAY,24:00,37:00,false" , 
	"@44,FUNDAY,45:00,00:00,false"})
	    public void classTimingServiceSaveInClassTimingTest(String classCode, String day, String startTime, String endTime, boolean result) 
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
