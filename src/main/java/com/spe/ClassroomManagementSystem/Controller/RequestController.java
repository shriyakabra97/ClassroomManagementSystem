package  com.spe.ClassroomManagementSystem.Controller;

import com.spe.ClassroomManagementSystem.Models.*;
import com.spe.ClassroomManagementSystem.Service.ClassroomService;
import com.spe.ClassroomManagementSystem.Service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.sql.Date;
import java.sql.Time;

@RestController
public class RequestController {

    @Autowired
    private RequestService requestService;
    @Autowired
    private ClassroomService classroomService;

    @RequestMapping("/postRequest/{classCode}")
    public RedirectView classroomRequest(HttpSession session,
                                 @PathVariable String classCode){


       boolean success_requested = requestService.saveRequest(session, classCode);

       RedirectView rv = new RedirectView();
       rv.setUrl("/AvailableClassrooms.jsp");
       return rv;




    }
}