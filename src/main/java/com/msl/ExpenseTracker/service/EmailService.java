package com.msl.ExpenseTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    public void sendVerificationEmail(String toEmail, String token,String name) {
        String emailBody = String.format(
                "Hii %s,\n\n" +
                        "Thank you for registering! To complete your signup," +
                        " please verify your email by clicking the link below:\n\n" +
                        "http://localhost:8080/eta/verify?token=" + token +"\n\nThank you,\n" +
                        "Expense Tracker Support",
                name
        );
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Email Verification");
        message.setText(emailBody);
        mailSender.send(message);
    }

    public void sendForgetPasswordEmail(String toEmail, String token,String name) {
        String emailBody = String.format(
                "Hii %s,\n\n" +
                        "A password reset request was sent for your account. " +
                        "If you did not make this request, please ignore this email.\n\n" +
                        " Please click on the below link to forget your password:\n" +
                        "http://localhost:8080/eta/resetPassword?token=" + token  +
                        "\n\nIf you have any questions or need further assistance, feel free to contact our support team.\n\n" +
                        "Thank you,\n" +
                        "Expense Tracker Support",
                name
        );
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Forget Password");
        message.setText(emailBody);
        mailSender.send(message);
    }
}
