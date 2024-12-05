package com.msl.ExpenseTracker.controller;

//*****Spring Annotation******
import com.msl.ExpenseTracker.dto.EmailRequest;
import com.msl.ExpenseTracker.dto.PasswordRequest;
import com.msl.ExpenseTracker.enums.ServiceCode;
import com.msl.ExpenseTracker.model.EmailVerificationToken;
import com.msl.ExpenseTracker.repo.EmailVerificationRepo;
import com.msl.ExpenseTracker.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//****Model/Entity*****
import com.msl.ExpenseTracker.dto.Response;
import com.msl.ExpenseTracker.model.Users;

//*******Service*******
import com.msl.ExpenseTracker.service.AuthService;

//******Exception******
import java.io.IOException;
import java.time.LocalDateTime;


@RestController
@CrossOrigin
@RequestMapping("/eta")
public class AuthController{
    @Autowired
    AuthService service;

    @Autowired
    EmailVerificationRepo eRepo;

    @Autowired
    UserRepo repo;
    
    @PostMapping("/signup")
    public Response register(@RequestBody Users user) throws IOException{
        return  service.register(user);
    }

    @PostMapping("/login")
    public Response login(@RequestBody Users user) {
        return service.verify(user);
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyToken(@RequestParam(required = true) String token) {
        EmailVerificationToken verificationToken = eRepo.findByToken(token);

        if (verificationToken == null || verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Invalid or expired token");
        }

        Users user = verificationToken.getUser();
        user.setActive(true);
        repo.save(user);
        eRepo.delete(verificationToken);
        return ResponseEntity.ok("Email verified successfully");
    }

    @PostMapping("/forgetPassword")
    public String forgetPassword(@RequestBody EmailRequest emailRequest) {
        String email = emailRequest.getEmail();
        System.out.println(email);
        Users user = repo.findByEmail(email);
        System.out.println(user);
        if (user != null) {  // Check if user exists
            return service.generateOtp(user);
        }else{
            return ServiceCode.VER03.getCode() + " " + ServiceCode.VER03.getMessage();
        }
    }
    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam String token,@RequestBody PasswordRequest passwordRequest) throws IOException {
        EmailVerificationToken verificationToken = eRepo.findByToken(token);
        if (verificationToken == null || verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return ServiceCode.ETA02.getCode()+" "+ServiceCode.ETA02.getMessage();
        }
        Users user = verificationToken.getUser();
        eRepo.delete(verificationToken);
        return service.resetPassword(user,passwordRequest.getNewPassword());
    }
}
