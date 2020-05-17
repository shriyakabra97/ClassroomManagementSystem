package com.spe.ClassroomManagementSystem.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

;

@Service
public class MailServiceImpl implements MailService{

    private JavaMailSender javaMailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender)
    {
        this.javaMailSender=javaMailSender;
    }

    public void sendNotification(String email,String text)
    {
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("connecttopragati@gmail.com");
        mail.setText(text);
        javaMailSender.send(mail);

    }
}
