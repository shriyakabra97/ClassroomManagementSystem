package com.spe.ClassroomManagementSystem.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

;

@Service
public class MailServiceImpl implements MailService{
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    private JavaMailSender javaMailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender)
    {
        this.javaMailSender=javaMailSender;
    }

    public void sendNotification(String email,String text , String subject)
    {
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("connecttopragati@gmail.com");
        mail.setText(text);
        mail.setSubject(subject);
        javaMailSender.send(mail);
        logger.info("mail sent");
    }
}
