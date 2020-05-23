package com.spe.ClassroomManagementSystem.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class SpringSessionController {
    private static final Logger logger = LoggerFactory.getLogger(SpringSessionController.class);

    @RequestMapping(value="/destroy" )
    public RedirectView destroyingSession(HttpSession session)
    {
        logger.info("/destroy called");
        //System.out.println("inside session controller...");
        session.invalidate();

        return new RedirectView("/");
    }
}





