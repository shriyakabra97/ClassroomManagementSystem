package com.spe.ClassroomManagementSystem.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class SpringSessionController {
    @RequestMapping(value="/destroy" )
    public RedirectView destroyingSession(HttpSession session)
    {
        System.out.println("inside session controller...");
        session.invalidate();

        return new RedirectView("/");
    }
}





