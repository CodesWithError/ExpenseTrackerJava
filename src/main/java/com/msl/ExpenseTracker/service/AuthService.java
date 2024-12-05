package com.msl.ExpenseTracker.service;

//*****Spring Annotation****
import com.msl.ExpenseTracker.model.EmailVerificationToken;
import com.msl.ExpenseTracker.repo.EmailVerificationRepo;
import com.msl.ExpenseTracker.validation.SignupValidation;
import com.msl.ExpenseTracker.enums.ServiceCode;
import com.msl.ExpenseTracker.dto.Response;
import com.msl.ExpenseTracker.model.Users;
import com.msl.ExpenseTracker.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//****Model/Entity*****

//****Repo/Dao*****

//****Enums*****

//***Validation***
import static com.msl.ExpenseTracker.utility.ConfigReader.getPasswordRegexp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//****Lombok annotation*******
import lombok.extern.slf4j.Slf4j;

//******Exception******
import java.io.IOException;
//***************
import java.time.LocalDateTime;
import java.util.UUID;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;



@Service
@Slf4j
public class AuthService {
    @Autowired
    UserRepo repo;
    @Autowired
    SignupValidation validation;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JWTService jwtService;

    @Autowired
    EmailVerificationRepo eRepo;

    @Autowired
    EmailService emailService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    Response response = new Response();
    public Response register(Users user) throws IOException{
    	ServiceCode status=validation.validateUserDetails(user);
    	try {
            if(ServiceCode.SIGNUP01==status){
                user.setPassword(encoder.encode(user.getPassword()));
                user.setCreatedAt(LocalDateTime.now());
                user.setActive(false);
                repo.save(user);
                log.info("data {}",user);
                user.setCreatedBy(user.getId());
                repo.save(user);
//             *****************Email verification part****************************
                String token = UUID.randomUUID().toString();
                EmailVerificationToken verificationToken = new EmailVerificationToken();
                verificationToken.setToken(token);
                verificationToken.setUser(user);
                verificationToken.setExpiryDate(LocalDateTime.now().plusHours(24)); // token valid for 24 hours
                eRepo.save(verificationToken);
                emailService.sendVerificationEmail(user.getEmail(),token,user.getName());
            }
    	}catch(Exception e) {
    		log.error("Error In Signup",e);
    		e.printStackTrace();
            status=ServiceCode.SIGNUP07;
    	}finally {
            response.setCode(status.getCode());
            response.setMessage(status.getMessage());
            log.info("{}", response);
        }
        return response;
    }

    public Response verify(Users user) {
        ServiceCode status=ServiceCode.LOGIN01;
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            if(authentication.isAuthenticated()){
                log.info("{}",status.getMessage());
                response.setJwtToken(jwtService.generateToken(user.getUsername()));
            }
        }
        catch (BadCredentialsException e){
            status=ServiceCode.LOGIN02;
            log.error("{}",status.getMessage());
            e.printStackTrace();
        }finally {
            response.setCode(status.getCode());
            response.setMessage(status.getMessage());
            log.info("{}",response);
        }
        return response;
    }


    public String generateOtp(Users user){
//        Users user=repo.findByEmail(email);
        String token = UUID.randomUUID().toString();
        EmailVerificationToken verificationToken = new EmailVerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(LocalDateTime.now().plusHours(24)); // token valid for 24 hours
        eRepo.save(verificationToken);
        emailService.sendForgetPasswordEmail(user.getEmail(),token,user.getName());
        return ServiceCode.VER02.getCode()+" "+ServiceCode.VER02.getMessage();
    }


    public String resetPassword(Users user, String newPassword) throws IOException {
        if(newPassword.equals(user.getPassword())){
            return ServiceCode.ETA03.getCode()+" "+ServiceCode.ETA03.getMessage();
        }
        Pattern patternPassword = Pattern.compile(getPasswordRegexp());
        Matcher searchPassword = patternPassword.matcher(newPassword);
        boolean matchPassword = searchPassword.find();
        if(matchPassword){
            user.setPassword(encoder.encode(newPassword));
            repo.save(user);
            return ServiceCode.ETA01.getCode()+" "+ServiceCode.ETA01.getMessage();
        }
        return  ServiceCode.SIGNUP10.getCode()+" "+ServiceCode.SIGNUP10.getMessage();

    }
}
